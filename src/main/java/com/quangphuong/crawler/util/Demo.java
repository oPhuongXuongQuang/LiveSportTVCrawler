/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import com.quangphuong.crawler.dao.UserDAO;
import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.Events;
import com.quangphuong.crawler.dto.User;
import static com.quangphuong.crawler.util.Crawler.comingupCrawler;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author quangphuong
 */
public class Demo {
    public static void demo() throws IOException {
        List<Event> events = comingupCrawler();
        Events events1 = new Events(events);
        XMLUtil.marshallUtil(AppConstant.comingUpData, events1);
    }
}
