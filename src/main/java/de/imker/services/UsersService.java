package de.imker.services;

import de.imker.dto.UpdateUserDto;
import de.imker.dto.UserDto;
import de.imker.dto.UserEmailDto;
import de.imker.dto.UserSecretQuestionsDto;
import de.imker.dto.UsersDto;

public interface UsersService {

//  UserDto addUser(NewUserDto newUser);

  UsersDto getAllUsers();

  UserDto deleteUser(Long userId);

  UserDto updateUser(Long userId, UpdateUserDto updateUser);

  UserDto getUser(Long userId);



  UserSecretQuestionsDto getSecretQuestions(UserEmailDto userEmail);

//  UserDto setNewPassword(UserRestorePwdDto restorePwd);
}
