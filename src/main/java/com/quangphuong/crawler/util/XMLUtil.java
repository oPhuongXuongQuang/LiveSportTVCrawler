/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import com.quangphuong.crawler.dto.Events;
import com.sun.javadoc.ParameterizedType;
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.springframework.core.GenericTypeResolver;

/**
 *
 * @author Quang
 */
public class XMLUtil {

    public static <T> T unmarshallUtil(String xmlPath, Class<T> entityClass) {
        try {
            JAXBContext cxt = JAXBContext.newInstance(entityClass);
            Unmarshaller unmarshaller = cxt.createUnmarshaller();
            
            File file = new File(xmlPath);
            T result = (T) unmarshaller.unmarshal(file);
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static <T> void marshallUtil(String xmlPath, T entityClass) {
        try {
            JAXBContext cxt = JAXBContext.newInstance(entityClass.getClass());
            Marshaller marshaller = cxt.createMarshaller();
            marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File(xmlPath);
            marshaller.marshal(entityClass,file);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
