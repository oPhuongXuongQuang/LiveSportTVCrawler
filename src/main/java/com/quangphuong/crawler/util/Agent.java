/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.wicket.util.io.ByteCountingOutputStream;
import org.springframework.stereotype.Component;

/**
 *
 * @author quangphuong
 */
@Component
public class Agent {

    public static long sizeOf(Serializable object) {
        if (object == null) {
            return 0;
        }

        try {
            final ByteCountingOutputStream out = new ByteCountingOutputStream();
            new ObjectOutputStream(out).writeObject(object);
            out.close();
            return out.size();
        } catch (IOException e) {
            return -1;
        }
    }
}
