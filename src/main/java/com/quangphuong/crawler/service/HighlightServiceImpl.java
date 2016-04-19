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

    @Override
    public List<Highlight> getSearchResult(String value) {
        return highlightDAO.getSearchResult(value);
    }

    @Override
    public List<Highlight> getSearchResult(String value, String range) {
        String[] arrs = range.split("-");
        int down = Integer.parseInt(arrs[0]);
        int up = Integer.parseInt(arrs[1]);
        return highlightDAO.getSearchResult(value, down, up);
    }

    @Override
    public List<Highlight> getSuggest(String value) {
        return highlightDAO.getSuggest(value);
    }

    @Override
    public void updateSeen(Highlight id) {
        highlightDAO.updateSeen(id);
    }
    
}
