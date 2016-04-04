/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

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
            marshaller.marshal(entityClass, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> String marshallWithoutFile(T entityClass) {
        try {
            JAXBContext cxt = JAXBContext.newInstance(entityClass.getClass());
            
            Marshaller marshaller = cxt.createMarshaller();
            marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            marshaller.marshal(entityClass, sw);
            System.out.println(sw.toString());
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
