/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author quangphuong
 */
public class Generator {
    public static void main(String[] args) {
        try {
            SchemaCompiler compiler = XJC.createSchemaCompiler();
            compiler.forcePackageName(AppConstant.generatePackage);
            
            compiler.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println("error: " + saxpe.getMessage());
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println("fatal: " + saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println("warning: " + saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println("info: " + saxpe.getMessage());
                }
            });
            
            File schema = new File(AppConstant.calendarSchema);
            InputSource inputSource = new InputSource(schema.toURI().toString());
            compiler.parseSchema(inputSource);
            S2JJAXBModel model = compiler.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(AppConstant.rootContext));
        } catch(Exception e) {
          e.printStackTrace();
        }
    }
}
