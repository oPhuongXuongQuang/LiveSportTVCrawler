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
            boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("jdwp") >= 0;
            DBConfig dBConfig;
            if (isDebug) {
                dBConfig = new DBConfig(DBConstant.driver, DBConstant.host,
                        DBConstant.port, DBConstant.schema, DBConstant.user,
                        DBConstant.password);
            } else {
                dBConfig = new DBConfig(DBConstant.driver, DBConstant.OPENSHIFT_HOST,
                        DBConstant.OPENSHIFT_PORT, DBConstant.schema, DBConstant.OPENSHIFT_USER,
                        DBConstant.OPENSHIFT_PASSWORD);
            }
            Connection connection = DriverManager.getConnection(dBConfig.toString(),
                    dBConfig.getUser(), dBConfig.getPassword());

            return connection;
        } catch (Exception ex) {
            Logger.getLogger(DBConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
