package parser;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Sax {

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/xml/computer_xsd.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class UserHandler extends DefaultHandler {

    boolean bName = false;
    boolean bProducer = false;
    boolean bType = false;
    boolean bCpu = false;
    boolean bCost = false;

    @Override
    public void startElement(
            String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("gameclub")) {
            System.out.println("Computers");
        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("producer")) {
            bProducer = true;
        } else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        }
        else if (qName.equalsIgnoreCase("cpu")) {
            bCpu = true;
        }else if (qName.equalsIgnoreCase("cost")) {
            bCost = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) {

        if (qName.equalsIgnoreCase("gameclub")) {
            System.out.println("End Element :" + qName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (bName) {
            System.out.println("Model: " + new String(ch, start, length));
            bName = false;
        } else if (bProducer) {
            System.out.println("Producer: " + new String(ch, start, length));
            bProducer = false;
        } else if (bType) {
            System.out.println("Type: " + new String(ch, start, length));
            bType = false;
        } else if (bCpu) {
            System.out.println("Cpu: " + new String(ch, start, length));
            bCpu = false;
        } else if (bCost) {
            System.out.println("Cost: " + new String(ch, start, length));
            bCost = false;
        }
    }
}