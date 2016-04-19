/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.controller;

import com.google.gson.Gson;
import com.quangphuong.crawler.dto.Calendar;
import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.EventDetail;
import com.quangphuong.crawler.dto.Highlight;
import com.quangphuong.crawler.dto.Link;
import com.quangphuong.crawler.dto.User;
import com.quangphuong.crawler.service.EventService;
import com.quangphuong.crawler.service.UserService;
import com.quangphuong.crawler.service.HighlightService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
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
    @Autowired
    HighlightService highlightService;
    
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
    
    @RequestMapping(value = "/getHighlights.htm", method = RequestMethod.POST)
    @ResponseBody
    public List<Highlight> getHighlights(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String date) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("------date: " + date);
        return highlightService.getHighlights(date);
    }
    
    @RequestMapping(value = "/globalSearch.htm", method = RequestMethod.POST)
    @ResponseBody
    public List<Highlight> globalSearch(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String value) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return highlightService.getSearchResult(value);
    }
    
    @RequestMapping(value = "/advanceSearch.htm", method = RequestMethod.POST)
    @ResponseBody
    public List<Highlight> advanceSearch(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String value) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return highlightService.getSearchResult(value,"5-10");
    }
    
    @RequestMapping(value = "/loadCalendar.htm", method = RequestMethod.POST)
    @ResponseBody
    public List<Calendar.Round> loadCalendar(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String value) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return eventService.getCalendar(value);
    }
    
    @RequestMapping(value = "/printCalendar.htm", method = RequestMethod.POST)
    @ResponseBody
    public String printCalendar(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String value) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ByteArrayOutputStream out = eventService.printCalendar(value);
        
        String base64str = StringUtils.newStringUtf8(Base64.encodeBase64(out.toByteArray(), false));
        System.out.println("------" + base64str);
        return "data:application/pdf;base64," + base64str;
    }
}
