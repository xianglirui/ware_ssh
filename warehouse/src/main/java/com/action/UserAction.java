package com.action;


import com.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {
    //注入bean
    @Resource
    private UserService userService;

    Map<String,Object> map = new HashMap<>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }



    //用于封装模型
    private User user = new User();

    //获取名字的方法
    public String saveUser(){
        System.out.println("收到注册请求");
        String nikename = user.getNikename();
        map.clear();
        if(userService.selectByNikeName(nikename)==null){
            userService.addUser(user);
            map.put("success","注册成功");
        }else {
            map.put("error","该用户名已存在");
        }
        return "register";

    }

    public String login(){
        System.out.println("收到登录请求");
        map.clear();
        User user1 = userService.selectByNikeNameAndPwd(user.getNikename(),user.getPassword());
        if(user1!=null){
            map.put("success","success");
            map.put("role",user1.getRole());
            map.put("name",user1.getNikename());
        }else {
            map.put("err","用户名或密码错误");
        }
        return "login";
    }

    public String userList(){
        System.out.println("收到查询用户列表请求");
        map.clear();
        List<User> list = userService.selectUser();
        map.put("list",list);
        System.out.println("list\n"+list);
        return "list";
    }

    public String userInfo(){
        System.out.println("收到查询用户信息请求");
        map.clear();
        User user1 = userService.selectByNikeName(user.getNikename());
        map.put("user",user1);
        return "userInfo";
    }

    public String editUser(){
        System.out.println("收到修改用户信息请求");
        System.out.println(user.getNikename());
        User user1 = userService.selectByNikeName(user.getNikename());
        user.setId(user1.getId());
        map.clear();
        userService.updateByName(user);
        map.put("success","success");
        return "updateUser";
    }

    public String deleteUser(){
        System.out.println("收到删除用户信息请求");
        System.out.println(user.getNikename());
        User user1 = userService.selectByNikeName(user.getNikename());
        user.setId(user1.getId());
        map.clear();
        userService.deleteByName(user);
        map.put("success","success");
        return "deleteUser";
    }

    
    @Override
    public User getModel() {
        return user;
    }
}
