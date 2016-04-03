/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import com.quangphuong.crawler.dao.UserDAO;
import com.quangphuong.crawler.dto.User;
import java.util.List;

/**
 *
 * @author quangphuong
 */
public class Demo {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        List<User> list = userDAO.getAllUser();
        for (User user : list) {
            System.out.println(user);
        }
//        System.out.println(user.toString());
        
//          User newUser = new User(2, "quangphuong", "hahaha", "hahha", "321532453245");
//        userDAO.addUser(newUser);
          
//          userDAO.updateUser(newUser);
    }
}
