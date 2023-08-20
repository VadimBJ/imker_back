package de.imker.services.impl;

import de.imker.dto.FileUploadDto;
import de.imker.dto.FilesListDto;
import de.imker.exeptions.NotFoundException;
import de.imker.models.FileUpload;
import de.imker.repositories.FilesRepository;
import de.imker.services.FilesService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilesServiceImpl implements FilesService {

  private final FilesRepository filesRepository;

  @Value("${files.upload.path}")
  private String uploadPath;

  @Override
  public File convertToFile(MultipartFile multipartFile) throws IOException {
    File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    try {
      Path filePath = file.toPath();
      Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new IOException("Failed to convert MultipartFile to File", e);
    }
    return file;
  }

  @Override
  public File resizeImage(File sourceImage, int width, int height, String targetDirectory) {
    File targetImage = new File(targetDirectory, "resized_" + sourceImage.getName());
    try {
      Thumbnails.of(sourceImage)
          .size(width, height)
          .toFile(targetImage);
    } catch (IOException e) {
      return sourceImage;
    }
    return targetImage;
  }

  @Override
  public FileUploadDto uploadFile(MultipartFile file, Integer width, Integer height) throws IOException {
    String originalName = file.getOriginalFilename();
    String storedName = UUID.randomUUID() + originalName;

    File sourceFile = convertToFile(file);
    File resizedFile = resizeImage(sourceFile, width, height, uploadPath);

    byte[] resizedFileBytes = Files.readAllBytes(resizedFile.toPath());

    Files.write(Paths.get(uploadPath + storedName), resizedFileBytes);

    FileUpload fileUpload = FileUpload.builder()
        .originalName(originalName)
        .storedName(storedName)
        .fileType(file.getContentType())
        .size((long) resizedFileBytes.length)
        .build();

    filesRepository.save(fileUpload);

    // Ignore the results of delete since it's a temporary file
    sourceFile.delete();
    resizedFile.delete();

    return FileUploadDto.from(fileUpload);
  }

  @Override
  public FileUploadDto getFileInfoById(Long fileId) {
    FileUpload fileUpload = filesRepository.getFileById(fileId).orElseThrow(
        () -> new NotFoundException("File with id <" + fileId + "> not found"));

    return FileUploadDto.from(fileUpload);
  }

  @Override
  public Resource getFileResource(Long fileId) {
    FileUploadDto fileUploadDto = getFileInfoById(fileId);
    File file = new File(uploadPath, fileUploadDto.getStoredName());

    try {
      byte[] fileContent = Files.readAllBytes(file.toPath());
      return new ByteArrayResource(fileContent);
    } catch (IOException e) {
      throw new NotFoundException("File with id <" + fileId + "> not found");
    }
  }

  @Override
  public FilesListDto getAllFiles() {
    List<FileUpload> files = filesRepository.findAll();

    return new FilesListDto(FileUploadDto.from(files), files.size());
  }

  @Override
  public FileUploadDto deleteFileById(Long fileId) {
    FileUpload fileUpload = filesRepository.getFileById(fileId).orElseThrow(
        () -> new NotFoundException("File with id <" + fileId + "> not found"));

    Path filePath = Paths.get(uploadPath, fileUpload.getStoredName()); // Путь к файлу

    try {
      Files.delete(filePath);
      filesRepository.delete(fileUpload);
    } catch (IOException e) {
      throw new NotFoundException("File not found");
    }
    return FileUploadDto.from(fileUpload);
  }
}