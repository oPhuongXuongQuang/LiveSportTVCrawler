/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.dao;

import com.quangphuong.crawler.dbutil.DBWrapper;
import com.quangphuong.crawler.dto.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author quangphuong
 */
@Repository
public class UserDAO {
    @Autowired
    DBWrapper dBWrapper;
    
    public List<User> getAllUser() {
//        dBWrapper = new DBWrapper();
        return (List<User>)(Object) dBWrapper.getEntities(User.class);
    }
    
    public User getUser(int id) {
//        dBWrapper = new DBWrapper();
        return (User) dBWrapper.getEntity(User.class, id);
    }
    
    public boolean addUser(User user) {
//        dBWrapper = new DBWrapper();
        return dBWrapper.insertEntity(user);
    }
    
    public void updateUser(User user) {
//        dBWrapper = new DBWrapper();
        dBWrapper.updateEntity(user);
    }
    
    public User getUser(User user) {
        return (User) dBWrapper.getEntityByCondition(user);
    }
}
