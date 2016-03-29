/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.controller;

import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.service.EventService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author quangphuong
 */
@Controller
public class HomeController {
    @Autowired
    EventService eventService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected String home(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        
        return "index";
    }
    
    @RequestMapping(value = "/comingup.htm", method = RequestMethod.POST, headers="Accept=application/json")
    public @ResponseBody List<Event> comingup(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        List<Event> events = eventService.getEvents();
        System.out.println("");
        return events;
    }
}
