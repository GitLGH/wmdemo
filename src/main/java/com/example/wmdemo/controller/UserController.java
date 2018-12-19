package com.example.wmdemo.controller;

import com.example.wmdemo.mapper.UserMapper;
import com.example.wmdemo.util.MD5Utils;
import com.example.wmdemo.util.Result;
import com.example.wmdemo.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/getUserById",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result getUserById(@RequestParam("id") String  id){
        Result result=new Result();
        System.out.println("连接数据库");
        User user=userMapper.findUserById(Integer.parseInt(id));
        user.setPassWord(MD5Utils.getMD5Token32(user.getPassWord()));
        result.setData(user);
        return result;
    }
}
