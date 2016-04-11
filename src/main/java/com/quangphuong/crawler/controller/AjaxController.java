/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.controller;

import com.google.gson.Gson;
import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.EventDetail;
import com.quangphuong.crawler.dto.Link;
import com.quangphuong.crawler.dto.User;
import com.quangphuong.crawler.service.EventService;
import com.quangphuong.crawler.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Quang
 */
@EnableWebMvc
@Controller
public class AjaxController {
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/comingup.htm", method = RequestMethod.POST)
    @ResponseBody
    public List<Event> comingup(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Event> events = eventService.getEvents();
        System.out.println("");
        return events;
    }
    
    @RequestMapping(value = "/register.htm", method = RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public boolean register(
            HttpServletRequest request,
            HttpServletResponse response,@RequestBody User user) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        boolean result = false;
        if (user != null) {
            result = userService.registerUser(user);
        }
        
        return result;
    }
    
    @RequestMapping(value = "/getEventDetail.htm", method = RequestMethod.POST)
    @ResponseBody
    public EventDetail getEventDetail(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody Link link){
        response.setHeader("Access-Control-Allow-Origin", "*");
//        request.s
//        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Origin");
        return eventService.getEventDetail(link.getValue(), link.isLive());
    }
    
    @RequestMapping(value = "/getVideo.htm", method = RequestMethod.POST)
    @ResponseBody
    public String getVideo(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String link){
        response.setHeader("Access-Control-Allow-Origin", "*");
//        request.s
//        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Origin");
        String videoStream = eventService.getVideoStream(link);
        
        Gson gson = new Gson();
        String json = gson.toJson(videoStream);
        System.out.println("videoStream: " + json);
        return json;
    }
}
