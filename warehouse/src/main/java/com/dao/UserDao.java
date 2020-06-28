package com.dao;

import com.domain.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> selectUser();
    User selectByNikeName(String nikename);
    User selectByNikeNameAndPwd(String nikename,String pwd);
    User selectByName(String name);

    void updateByName(User user);

    void deleteByName(User user);
}
