/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.controller;

import com.quangphuong.crawler.dto.User;
import com.quangphuong.crawler.service.EventService;
import com.quangphuong.crawler.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author quangphuong
 */

@Controller
public class HomeController {
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected String home(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        return "index";
    }
    
    
    
    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    @ResponseBody
    public boolean login(
            HttpServletRequest request,
            HttpServletResponse response,@RequestBody User user, HttpSession session) throws Exception {
        System.out.println(user.toString());
        boolean result = false;
        if (user != null) {
            User userInfo = userService.getUserInfo(user);
            if (userInfo != null) {
                userInfo.setPassword("");
                result = true;
                session.setAttribute("USER", userInfo);
            }
        }
        
        return result;
    }
    
    @RequestMapping(value = "/dashboard.htm", method = RequestMethod.GET)
    public String dashboard(
            HttpServletRequest request,
            HttpServletResponse response, Model model, HttpSession session) throws Exception {
        User userInfo = (User) session.getAttribute("USER");
        if (userInfo != null) {
            model.addAttribute("xmlStr", userService.getUserList());
        }
        
//        userService.getUserList();
        return "dashboard";
    }
    
    
}
