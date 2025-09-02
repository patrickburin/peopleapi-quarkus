package dev.java10x.service;

import dev.java10x.entity.Users;
import dev.java10x.exceptions.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

  public Users createUser(Users users) {
    Users.persist(users);
    return users;
  }

  public List<Users> findAllUsers(Integer page, Integer pageSize) {
    return Users.findAll().page(page, pageSize).list();
  }

  public Users findUsersById(UUID userId) {
    return (Users) Users.findByIdOptional(userId)
        .orElseThrow(UserNotFoundException::new);
  }

  public Users updateUser(UUID userId, Users users) {
    var user = findUsersById(userId);
    user.userName = users.userName;
    user.email = users.email;
    Users.persist(user);

    return users;
  }

  public void deleteUser(UUID userId) {
    var user = findUsersById(userId);
    Users.deleteById(user.id);
  }
}
