package com.service;

import com.dao.UserDao;
import com.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("usersService")
public class UserServiceImpl implements UserService{
    //注入dao
    @Resource
    private UserDao userDao;

    //注入事务管理
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> selectUser() {
        return userDao.selectUser();
    }

    @Override
    public User selectByNikeName(String nikename) {
        return userDao.selectByNikeName(nikename);
    }

    @Override
    public User selectByName(String name) {
        return userDao.selectByName(name);
    }

    @Override
    public void updateByName(User user) {
        userDao.updateByName(user);
    }

    @Override
    public User selectByNikeNameAndPwd(String nikename, String pwd) {
        return userDao.selectByNikeNameAndPwd(nikename,pwd);
    }

    @Override
    public void deleteByName(User user) {
        userDao.deleteByName(user);
    }

}
