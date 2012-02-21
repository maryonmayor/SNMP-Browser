/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmptest.controller;

import com.snmptest.service.IXmlMarshaller;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

/**
 *
 * @author MMayor
 */
@Controller
@RequestMapping("/xml/")
public class MarshallController {

    private IXmlMarshaller xmlParser;

    @Autowired
    @Qualifier("xmlParser")
    public void setXmlMarshaller(IXmlMarshaller xmlParser) {
        this.xmlParser = xmlParser;
    }

    @RequestMapping(value = "index.htm", method = RequestMethod.GET)
    public String uploadXMLForm() {


        return "form/upload_xml";
    }

        @RequestMapping(value = "index.htm", method = RequestMethod.POST)
     public String uploadXML(@RequestParam("xml") MultipartFile file) throws SAXException, ParserConfigurationException, IOException, Exception {

        xmlParser.marshallXML(file);
        return "form/upload_xml";
    }
}
