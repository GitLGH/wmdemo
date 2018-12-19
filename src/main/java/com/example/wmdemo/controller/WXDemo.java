package com.example.wmdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wmdemo.util.OauthWeixin;
import com.example.wmdemo.util.ReqWeiXin;
import com.example.wmdemo.util.Result;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class WXDemo {
    @RequestMapping(value = "/login",method = {RequestMethod.GET, RequestMethod.POST})
    public String login(){
       System.out.println("登录页面");
       return "login";
    }
    @RequestMapping(value = "/getUrl",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result getUrl(){
        System.out.println("拼接授权路径");
        Result result= new Result();
        //得到一个随机数----保证安全
        String state= RandomStringUtils.random(24,"abcdefghijklmnopqrstuvwxyz0123456789");
        try {
            String url= OauthWeixin.me().getAuthorizeUrl(state);
            result.setErrorCode("1");
            result.setErrorMsg("success");
            result.setData(url);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        result.setErrorCode("-1");
        result.setErrorMsg("error");
        result.setData("");
        return result;
    }

    @RequestMapping(value = "/getAccessToken",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result getAccessToken(@RequestParam("code") String code,@RequestParam("state") String state){
        //String openid = request.getParameter("openid");
        // 取消了授权
        Result result=new Result();
        if (StringUtils.isBlank(state)||StringUtils.isBlank(code)){
            result.setErrorCode("500");
            result.setErrorMsg("取消了授权");
            return result;
        }
        //清除state以防下次登录授权失败
        //session.removeAttribute(SESSION_STATE);
        //获取用户信息
        try{
            String accessToken = OauthWeixin.me().getTokenByCode(code);//根据Code获取AccessToken
            System.out.println("controller:accessToken"+accessToken);
            JSONObject jsStr = JSONObject.parseObject(accessToken);
            ReqWeiXin wx = (ReqWeiXin) JSONObject.toJavaObject(jsStr,ReqWeiXin.class);//将json转成对象
            String userInfo = OauthWeixin.me().getUserInfo(wx.getAccess_token(),wx.getOpenid());//根据access_token和openid查询基本信息
            System.out.println("controller:userInfo"+userInfo);
            JSONObject userStr = JSONObject.parseObject(userInfo);
            ReqWeiXin user = (ReqWeiXin) JSONObject.toJavaObject(userStr,ReqWeiXin.class);
            result.setErrorCode("1");
            result.setErrorMsg("success");
            result.setData(user);
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        result.setErrorCode("500");
        result.setErrorMsg("取消了授权");
        return result;

    }
}
