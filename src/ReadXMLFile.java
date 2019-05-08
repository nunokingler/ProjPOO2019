import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.*;
import java.util.*;

public class ReadXMLFile {

    static String fileName;


    //static File xmlFile = new File ("C:/Users/henri/git/ProjPOO2019/data1.xml");

    //fileName = xmlFile.getName();

    public static void read_xml(){
        File xmlFile = new File("C:/Users/henri/git/ProjPOO2019/data1.xml");
        fileName=xmlFile.getName();
        
         int temp1, temp2=0;
        
        System.out.println("Beginning the parsing of " + fileName); 

        
        try {
            SAXParserFactory fact = SAXParserFactory.newInstance();
            SAXParser saxParser = fact.newSAXParser();

            // parse the XML document with this handler
            SAXHandler handler = new SAXHandler();
            saxParser.parse(new File(fileName), handler);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

            // NodeList nList1 = doc.getElementsByTagName("node");

            ArrayList<Integer> IntParameters = handler.getParamInt();
            ArrayList<Float> FloatParameters = handler.getParamFloat();

            List<Integer> Nodes = handler.getNodes();
            List<Integer> Target_nodes = handler.getTargetNode();
            List<Float> Edges = handler.getEdges();
            
            //Gama?
            
            Simulation simul = new Simulation(FloatParameters.get(0), FloatParameters.get(2),FloatParameters.get(3), IntParameters.get(0),IntParameters.get(1));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}