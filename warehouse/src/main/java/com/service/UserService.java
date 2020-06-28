package com.service;

import com.domain.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> selectUser();
    User selectByNikeName(String nikename);
    User selectByName(String name);
    void updateByName(User user);
    User selectByNikeNameAndPwd(String nikename,String pwd);

    void deleteByName(User user);
}
