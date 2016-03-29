/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.service;

import com.quangphuong.crawler.dto.Event;
import java.util.List;

/**
 *
 * @author quangphuong
 */
public interface EventService {
    List<Event> getEvents();
}
