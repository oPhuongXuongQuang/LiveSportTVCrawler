/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.service;

import com.quangphuong.crawler.dto.Highlight;
import java.util.List;

/**
 *
 * @author quangphuong
 */
public interface HighlightService {
    List<Highlight> getHighlights(String date);
    List<Highlight> getSearchResult(String value);
    List<Highlight> getSearchResult(String value, String range);
}
