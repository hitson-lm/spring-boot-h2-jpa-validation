package com.example.h2.services;

import com.example.h2.entity.UserInfo;
import com.example.h2.entity.UserInfoRequest;
import com.example.h2.exceptions.custom.UserIdValidationException;
import com.example.h2.exceptions.custom.UserNotFoundException;
import com.example.h2.repository.RepositoryUser;
import com.example.h2.utils.constants.PatternConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.example.h2.utils.constants.PatternConstants.REG_EXP_VALID_ID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl {

  private final RepositoryUser repositoryUser;


  public List<UserInfo> getAllUsers() {

    return repositoryUser.findAll();
  }

  public UserInfo getByIdUser(Long id) {

    log.info("INRESA_METODO");
    if(!(String.valueOf(id)).matches(REG_EXP_VALID_ID)) {
      log.info("EXCEPTION_PATTERN_REG_EXP");
      throw new UserIdValidationException("No cumple con el pattern '^(100|[1-9][0-9]?)$'");
    }
    log.info("CONSULTA_BD");
    return repositoryUser.findById(id)
        .orElseThrow(
            () -> new UserNotFoundException(String.format("User with id %s not found.", id))
        );
  }

  public UserInfo saveUser(UserInfoRequest request) {

    //validateUserRequest(request);

    UserInfo user = new UserInfo();

    user.setName(request.getName());
    user.setPhone(request.getPhone());

    return repositoryUser.save(user);
  }

  public void deleteUser(Long id) {
    repositoryUser.deleteById(id);
  }

  public void updateUser(Long id, UserInfoRequest request) {

    Optional<UserInfo> opt = repositoryUser.findById(id);

    if (opt.isPresent()) {

      UserInfo user = opt.get();

      user.setName(request.getName());
      user.setPhone(request.getPhone());
      repositoryUser.save(user);
    } else {
      throw new RuntimeException(String.format("No existe un user con el id=%s en la BD", id));
    }


  }

  public void validateUserRequest(UserInfoRequest userInfoRequest) {

    List<String> messageError = new ArrayList<>();

    if(userInfoRequest.getName() == null
        || userInfoRequest.getName().isEmpty()) {

      messageError.add("El nombre no puede ser nulo o vacío.");

    } else if (userInfoRequest.getName().length() > 25) {
      messageError.add("El nombre no puede exceder los 25 caracteres.");

    }

    if(userInfoRequest.getPhone() == null || userInfoRequest.getPhone().isEmpty()) {
      messageError.add("El numero telefonico no puede ser nulo o vacío.");

    } else if (!userInfoRequest.getPhone().matches("^\\d{9}$")) {
      messageError.add("El formato de telefeno es invalido");
    }

    if(!messageError.isEmpty()) {
      throw new IllegalArgumentException(String.join(" || ", messageError));

    }

  }

}
