/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author quangphuong
 */
@Component
public class DBHandler {
    public DBHandler() {
    }
    
    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DBConfig dBConfig = new DBConfig(DBConstant.driver, DBConstant.host, 
                    DBConstant.port, DBConstant.schema, DBConstant.user, 
                    DBConstant.password);
            Connection connection = DriverManager.getConnection(dBConfig.toString(),
                    dBConfig.getUser(),dBConfig.getPassword());
            
            return connection;
        } catch (Exception ex) {
            Logger.getLogger(DBConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
