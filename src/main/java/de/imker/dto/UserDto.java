package de.imker.dto;

import de.imker.models.User;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Information about user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

  @Schema(description = "User's ID", example = "1")
  private Long id;

  @Schema(description = "User's e-main", example = "aaa@bbb.ccc")
  private String email;

  @Schema(description = "User's role: ADMIN, USER", example = "USER")
  private String role;

  @Schema(description = "User's state: CONFIRMED, NOT_CONFIRMED, BANNED, DELETED", example = "CONFIRMED")
  private String state;

  @Schema(description = "User's name", example = "Alex Krause")
  private String name;

  @Schema(description = "User's PLZ", example = "01234")
  private String plz;

  @Schema(description = "User's phone", example = "0123456789")
  private String phone;

  @Schema(description = "User's image", example = "???") //TODO
  private String image;

  //  private String secretQuestion;

  @Schema(description = "User's is signed in", example = "TRUE")
  private Boolean isLogin;


  public static UserDto from(User user) {
    return UserDto.builder()
        .id(user.getId())
        .email(user.getEmail())
        .state(user.getState().name())
        .role(user.getRole().name())
        .plz(user.getName())
        .name(user.getName())
        .phone(user.getPhone())
        .image(user.getImage())
//        .secretQuestion(user.getSecretQuestion())
        .isLogin(user.getIsLogin())
        .build();
  }

  public static List<UserDto> from(List<User> users) {
    return users.stream()
        .map(UserDto::from)
        .collect(Collectors.toList());
  }
}
