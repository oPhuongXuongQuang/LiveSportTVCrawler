/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;

/**
 *
 * @author Quang
 */
public class XMLUtil {
    

    public static <T> T unmarshallUtil(String xmlPath, Class<T> entityClass) throws Exception {
//        try {
            JAXBContext cxt = JAXBContext.newInstance(entityClass);
            Unmarshaller unmarshaller = cxt.createUnmarshaller();

            File file = new File(xmlPath);
            T result = (T) unmarshaller.unmarshal(file);

            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
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
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ByteArrayOutputStream convertToPDF(String xmlPath, String xsltInputPath, String pdfOutputPath) throws IOException{
        File xsltFile = new File(xsltInputPath);
        StreamSource xmlSource = new StreamSource(new File(xmlPath));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return out;
    }
    
    public static void convertToFO(String xmlPath, String xsltInputPath, String foOutputPath)  throws IOException, FOPException, TransformerException {
        File xsltFile = new File(xsltInputPath);
        StreamSource xmlSource = new StreamSource(new File(xmlPath));
        OutputStream out;
        out = new java.io.FileOutputStream(foOutputPath);
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            Result res = new StreamResult(out);
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }

}
