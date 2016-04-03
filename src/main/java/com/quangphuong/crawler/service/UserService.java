/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.service;

import com.quangphuong.crawler.dto.User;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 *
 * @author quangphuong
 */
public interface UserService {
    boolean registerUser(User user);
    User getUserInfo(User user);
    String getUserList();
}
