/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.service;

import com.quangphuong.crawler.dto.Calendar;
import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.EventDetail;
import java.util.List;

/**
 *
 * @author quangphuong
 */
public interface EventService {
    List<Event> getEvents();
    EventDetail getEventDetail(String eventLink, boolean isLive);
    String getVideoStream(String videoLink);
    List<Calendar.Round> getCalendar(String id);
}
