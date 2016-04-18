/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.dbutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author quangphuong
 */
public class ObjectIO {

    public static void write(String path, Object object) {
        ObjectOutputStream s = null;
        try {
            FileOutputStream f = new FileOutputStream(new File(path), false);
            s = new ObjectOutputStream(f);
            s.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                s.flush();
                s.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static Object read(String path) {
        Object object = null;
        ObjectInputStream s = null;
        try {
            FileInputStream f = new FileInputStream(new File(path));
            s = new ObjectInputStream(f);
            object = s.readObject();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return object;
    }
}
