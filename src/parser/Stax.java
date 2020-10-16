package parser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Stax {
    public static void main(String[] args) {
        boolean bName = false;
        boolean bProducer = false;
        boolean bType = false;
        boolean bCpu = false;
        boolean bCost = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader("src/xml/computer_xsd.xml"));

            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch(event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("gameclub")) {
                            System.out.println("Start Element : gameclub");

                        } else if (qName.equalsIgnoreCase("name")) {
                            bName = true;
                        } else if (qName.equalsIgnoreCase("producer")) {
                            bProducer = true;
                        } else if (qName.equalsIgnoreCase("type")) {
                            bType = true;
                        } else if (qName.equalsIgnoreCase("cpu")) {
                            bCpu = true;
                        }else if (qName.equalsIgnoreCase("cost")) {
                            bCost = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(bName) {
                            System.out.println("Model: " + characters.getData());
                            bName = false;
                        }
                        if(bProducer) {
                            System.out.println("Producer: " + characters.getData());
                            bProducer = false;
                        }
                        if(bType) {
                            System.out.println("Type: " + characters.getData());
                            bType = false;
                        }
                        if(bCpu) {
                            System.out.println("Cpu: " + characters.getData());
                            bCpu = false;
                        }
                        if(bCost) {
                            System.out.println("Cost: " + characters.getData());
                            bCost = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if(endElement.getName().getLocalPart().equalsIgnoreCase("computers")) {
                            System.out.println("End Element : computers");
                            System.out.println();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}