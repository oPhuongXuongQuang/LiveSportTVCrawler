/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.service;

import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.EventDetail;
import com.quangphuong.crawler.util.Crawler;
import com.quangphuong.crawler.util.Getter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quangphuong
 */
@Service
public class EventServiceImpl implements EventService{

    
    @Override
    public List<Event> getEvents() {
        return Getter.getEvents();
    }

    @Override
    public EventDetail getEventDetail(String eventLink, boolean isLive) {
        return Getter.getEventDetail(eventLink, isLive);
    }

    @Override
    public String getVideoStream(String videoLink) {
        return Getter.getVideoStream(videoLink);
    }
    
}
