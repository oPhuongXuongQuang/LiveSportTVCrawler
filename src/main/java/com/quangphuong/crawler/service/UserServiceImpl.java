/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.service;

import com.quangphuong.crawler.dao.UserDAO;
import com.quangphuong.crawler.dto.User;
import com.quangphuong.crawler.dto.Users;
import com.quangphuong.crawler.util.XMLUtil;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quangphuong
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDAO userDAO;
        
    @Override
    public boolean registerUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public User getUserInfo(User user) {
        User userInfo = userDAO.getUser(user);
        return userInfo;
    }

    @Override
    public String getUserList() {
        List<User> list = userDAO.getAllUser();
        System.out.println("========" + list.size());
        Users users = new Users(list);
        return XMLUtil.marshallWithoutFile(users);
    }
    
}
