/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
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
import org.springframework.stereotype.Component;

/**
 *
 * @author Quang
 */
@Component
public class XMLUtil {
    private static String rootPath;
    
    public XMLUtil() throws URISyntaxException {
        URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
        File f = new File(u.toURI().getPath());
        rootPath = f.getParent();
        f.delete();
    }

    public <T> T unmarshallUtil(String xmlPath, Class<T> entityClass) throws Exception {
//        try {
            JAXBContext cxt = JAXBContext.newInstance(entityClass);
            Unmarshaller unmarshaller = cxt.createUnmarshaller();
            File file = new File(rootPath, xmlPath);
            T result = (T) unmarshaller.unmarshal(file);
                
            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public <T> void marshallUtil(String xmlPath, T entityClass) {
        try {
            JAXBContext cxt = JAXBContext.newInstance(entityClass.getClass());
            Marshaller marshaller = cxt.createMarshaller();
            marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File(rootPath, xmlPath);
            System.out.println("Path: " + file.getAbsolutePath());
            marshaller.marshal(entityClass, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> String marshallWithoutFile(T entityClass) {
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
    
    public ByteArrayOutputStream convertToPDF(String xmlPath, String xsltInputPath, String pdfOutputPath) throws IOException{
        File xsltFile = new File(rootPath, xsltInputPath);
        StreamSource xmlSource = new StreamSource(new File(rootPath, xmlPath));
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
    
    public void convertToFO(String xmlPath, String xsltInputPath, String foOutputPath)  throws IOException, FOPException, TransformerException {
        File xsltFile = new File(rootPath, xsltInputPath);
        StreamSource xmlSource = new StreamSource(new File(rootPath, xmlPath));
        OutputStream out;
        out = new java.io.FileOutputStream(new File(rootPath, foOutputPath));
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
