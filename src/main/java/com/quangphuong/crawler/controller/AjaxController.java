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
import com.quangphuong.crawler.dto.Video;
import com.quangphuong.crawler.service.EventService;
import com.quangphuong.crawler.service.UserService;
import com.quangphuong.crawler.service.HighlightService;
import com.quangphuong.crawler.util.Demo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Response comingup(
            HttpServletRequest request,
            HttpServletResponse response) {
        List<Event> events = null;
        try {
//            Demo.demo();
            events = eventService.getEvents();
            return new Response(200, "",events);
        } catch (Exception ex) {
            return new Response(400, ex.getMessage(),events);
        }
        
    }
    
    @RequestMapping(value = "/register.htm", method = RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public boolean register(
            HttpServletRequest request,
            HttpServletResponse response,@RequestBody User user) throws Exception {
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
        return eventService.getEventDetail(link.getValue(), link.isLive());
    }
    
    @RequestMapping(value = "/getVideo.htm", method = RequestMethod.POST)
    @ResponseBody
    public Response getVideo(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody Video video){
        try {
            String videoStream = eventService.getVideoStream(video);
        
//            Gson gson = new Gson();
//            String json = gson.toJson(videoStream);
//            System.out.println("videoStream: " + json);
            return new Response(200, "", videoStream);
        } catch (Exception e) {
            return new Response(400, e.getMessage(), "");
        }
    }
    
    @RequestMapping(value = "/getHighlights.htm", method = RequestMethod.POST)
    @ResponseBody
    public List<Highlight> getHighlights(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String date) {
        return highlightService.getHighlights(date);
    }
    
    @RequestMapping(value = "/globalSearch.htm", method = RequestMethod.POST)
    @ResponseBody
    public List<Highlight> globalSearch(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String value) {
        return highlightService.getSearchResult(value);
    }
    
    @RequestMapping(value = "/advanceSearch.htm", method = RequestMethod.POST)
    @ResponseBody
    public List<Highlight> advanceSearch(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String value) {
        return highlightService.getSearchResult(value,"5-10");
    }
    
    @RequestMapping(value = "/loadCalendar.htm", method = RequestMethod.POST)
    @ResponseBody
    public Response loadCalendar(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String value) {
        try {
            List<Calendar.Round> rounds = eventService.getCalendar(value);
            return new Response(200, "", rounds);
        } catch (Exception ex) {
            return new Response(400, ex.getMessage(), null);
        }
    }
    
    @RequestMapping(value = "/printCalendar/{id}", method = RequestMethod.GET)
    public void printCalendar(HttpServletRequest request, 
            HttpServletResponse response, @PathVariable String id) {
        try {
            ByteArrayOutputStream out = eventService.printCalendar(id);
            byte[] bytes = out.toByteArray();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Length", String.valueOf(bytes.length));
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
            
        } catch (Exception ex) {
            Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/getSuggestVideos.htm", method = RequestMethod.POST)
    @ResponseBody
    public List<Highlight> getSuggestVideos(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody String value) {
        return highlightService.getSuggest(value);
    }
    
    @RequestMapping(value = "/updateSeen.htm", method = RequestMethod.POST)
    @ResponseBody
    public void updateSeen(HttpServletRequest request, 
            HttpServletResponse response, @RequestBody Highlight value) {
        highlightService.updateSeen(value);
    }
}
