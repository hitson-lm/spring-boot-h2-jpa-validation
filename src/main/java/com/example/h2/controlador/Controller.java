package com.example.h2.controlador;

import com.example.h2.entity.UserInfo;
import com.example.h2.entity.UserInfoRequest;
import com.example.h2.services.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class Controller {

  private final UserServiceImpl useServiceImpl;

  @GetMapping
  public List<UserInfo> getAllUser() {
    return useServiceImpl.getAllUsers();
  }

  @GetMapping("/{id}")
  public UserInfo getByUserId(@PathVariable Long id) {
    return useServiceImpl.getByIdUser(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(@Valid @RequestBody UserInfoRequest userInfo) {
    useServiceImpl.saveUser(userInfo);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable Long id) {
    useServiceImpl.deleteUser(id);
  }

  @PutMapping("/{id}")
  public void updateUser(@PathVariable Long id, @RequestBody UserInfoRequest userInfo) {
     useServiceImpl.updateUser(id, userInfo);
  }


}
