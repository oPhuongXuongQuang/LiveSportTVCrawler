/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.service;

import com.quangphuong.crawler.dbutil.DBWrapper;
import com.quangphuong.crawler.dto.Highlight;
import com.quangphuong.crawler.dao.HighlightDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quangphuong
 */
@Service
public class HighlightServiceImpl implements HighlightService{
    @Autowired
    HighlightDAO highlightDAO;
    
    @Override
    public List<Highlight> getHighlights(String date) {
        return highlightDAO.getHighlights(date);
    }
    
}
