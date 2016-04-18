/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.dao;

import com.quangphuong.crawler.dbutil.DBWrapper;
import com.quangphuong.crawler.dto.Highlight;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author quangphuong
 */
@Repository
public class HighlightDAO {
    @Autowired
    DBWrapper dBWrapper;
    
    public List<Highlight> getHighlights(String date) {
        return (List<Highlight>) (Object)dBWrapper.getEntitiesByCondition(new Highlight(date),true,"matches");
    }
    
    public List<Highlight> getSearchResult(String value) {
        return (List<Highlight>) (Object)dBWrapper.searchFullText(new Highlight(), value);
    }
    
    public List<Highlight> getSearchResult(String value, int down, int up) {
        return (List<Highlight>) (Object)dBWrapper.searchFullTextRange(new Highlight(), value, down, up);
    }
}
