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
            
            Vector nodes = new Vector(); //= Nodes.get(temp1);
            Vector target_nodes = new Vector(); //System.out.println("\nCurrent Element : " + Nodes.get(temp1));
            Vector edges = new Vector();
            
            Vector intp = new Vector();
            Vector floatp = new Vector(); //Syst);
            
            nodes.addAll(Nodes);
            target_nodes.addAll(Target_nodes);
            edges.addAll(Edges);
            
            intp.addAll(IntParameters);
            floatp.addAll(FloatParameters);
            
            Simulation simul = new Simulation(intp.get(0),intp.get(1));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}