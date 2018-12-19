package com.example.wmdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wmdemo.util.ImageUtil;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@Controller
public class YZMDemo {
    @RequestMapping(value = "/code/get", method = RequestMethod.GET)
    @ResponseBody
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("验证码");
        Object[] objs = ImageUtil.createImage();
        request.getSession().setAttribute("LOGIN_CHECKCODE_", objs[0]);// 设置登陆验证码
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

}
