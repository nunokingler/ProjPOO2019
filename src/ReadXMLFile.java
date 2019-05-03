import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.*;
import java.util.*;

public class ReadXMLFile extends DefaultHandler{
	
	static String fileName;
	
	static File xmlFile = new File ("Users/henri/git/ProjPOO2019/bin/data1.xml");
	
		public void startDocument() {
			System.out.println("Parsing of " + fileName);
		}
	
		public void endDocument() {
			System.out.println("Parsing concluded");	
		}
	
		public void startElement(String uri, String name, String tag, Attributes atts) {
			System.out.print("Element <" + tag + "> ");	
		}
	
		public void characters(char[]ch,int start,int length){
			System.out.print(new String(ch,start,length));
		}

		public static void main(String argv[]) throws Exception{
			fileName=xmlFile.getName();
			
			SAXParserFactory fact = SAXParserFactory.newInstance();
			SAXParser saxParser = fact.newSAXParser();

			// parse the XML document with this handler
			DefaultHandler handler = new ReadXMLFile();
			saxParser.parse(new File(fileName), handler); 
		}
}