package com.example.h2.services;

import com.example.h2.entity.UserInfo;
import com.example.h2.entity.UserInfoRequest;
import com.example.h2.exceptions.ExceptionNotFoundByIdUser;
import com.example.h2.repository.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

  private final RepositoryUser repositoryUser;


  public List<UserInfo> getAllUsers() {

    // Este bloque aplica para CrudRepository, ya q retorna un Iterable
    /*List<UserInfo> list = new ArrayList<>();

    for (UserInfo user : repositoryUser.findAll()) {
      list.add(user);
    }*/
    return repositoryUser.findAll();
  }

  public UserInfo getByIdUser(Long id) {

    Optional<UserInfo> optionalUser = repositoryUser.findById(id);

    if (optionalUser.isPresent()) {

      return optionalUser.get();

    }
    throw new ExceptionNotFoundByIdUser(String.format("No existe un user con el id=%s en la BD", id));
  }

  public UserInfo createUser(UserInfo request) {

    //validateUserRequest(request);

    /*UserInfo user = new UserInfo();

    user.setName(request.getName());
    user.setPhone(request.getPhone());*/

    return repositoryUser.save(request);
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
