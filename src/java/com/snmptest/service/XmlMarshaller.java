/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmptest.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author MMayor
 */
@Service
public class XmlMarshaller implements IXmlMarshaller {

    public String[] marshallXML(MultipartFile file) throws ParserConfigurationException, IOException, SAXException {
        InputStream is = file.getInputStream();

      

        return null;
    }
}
