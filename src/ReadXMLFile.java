import javax.xml.parsers.*;
//import org.xml.sax.*;
import org.xml.sax.helpers.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.*;
//import java.util.*;

public class ReadXMLFile {
	
	static String fileName;
	
	static File xmlFile = new File ("C:/Users/henri/git/ProjPOO2019/resources/data1.xml");
	
	//fileName = xmlFile.getName();
	
		public static void main(String argv[]) throws Exception{
			//File xmlFile = new File("C:/Users/henri/git/ProjPOO2019/resources/data1.xml");
			fileName=xmlFile.getName();
			
			try {
			SAXParserFactory fact = SAXParserFactory.newInstance();
			SAXParser saxParser = fact.newSAXParser();

			// parse the XML document with this handler
			DefaultHandler handler = new SAXHandler();
			saxParser.parse(new File(fileName), handler); 

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            int temp1=0;
            int temp2=0;

            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

            NodeList nList1 = doc.getElementsByTagName("node");

            System.out.println("----------------------------");

            Node nNode1 = nList1.item(temp1);

                System.out.println("\nCurrent Element : " + nNode1.getNodeName());

                if (nNode1.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode1;

                    System.out.println("NodeIDX : " + eElement.getAttribute("nodeidx"));

                    NodeList nList2 = doc.getElementsByTagName("weight");
               	    Node nNode2= nList2.item(temp2);

                    for (temp2 = 0; temp2 < nList2.getLength(); temp2++) {

                    	if (nNode2.getNodeType() == Node.ELEMENT_NODE)

                    		eElement = (Element) nNode2;
                    		System.out.println("Target Node : " + eElement.getAttribute("targetnode"));


                    	 //System.out.println("TargetNode : " + eElement.getElementsByTagName("targetnode").item(0).getTextContent());

                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
  }
}