import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//import org.xml.sax.*;
//import java.util.*;

public class ReadXMLFile {

    private String fileName;
    private SAXHandler handler;

    //static File xmlFile = new File ("C:/Users/henri/git/ProjPOO2019/data1.xml");

    //fileName = xmlFile.getName();
    public ReadXMLFile(){
        super();
    }


    public void inits(){
        File xmlFile = new File("C:/Users/henri/git/ProjPOO2019/data1.xml");
        fileName=xmlFile.getName();

        try {
            SAXParserFactory fact = SAXParserFactory.newInstance();
            SAXParser saxParser = fact.newSAXParser();

            // parse the XML document with this handler
            handler = new SAXHandler();
            //saxParser.parse(xmlFile,handler);//new File(fileName), handler);
            saxParser.parse(new File(fileName),handler);

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


            System.out.println("----------------------------");



         /*   Node nNode1 = nList1.item(temp1);
            System.out.println("\nCurrent Element : " + nNode1.getNodeName());

            for (temp1 = 0; temp1 < nList1.getLength(); temp1++) {

                if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode1;
                    System.out.println("NodeIDX : " + eElement.getAttribute("nodeidx"));
                    NodeList nList2 = doc.getElementsByTagName("weight");
               	    Node nNode2= nList2.item(temp2);
                    for (temp2 = 0; temp2 < nList2.getLength(); temp2++) {
                    	if (nNode2.getNodeType() == Node.ELEMENT_NODE)
                    		eElement = (Element) nNode2;
                    		System.out.println("Target Node : " + eElement.getAttribute("targetnode") + " Weight: " + eElement.getAttribute("weight") );
                    }
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Integer> getIntParameteres(){
        return handler.getParamInt();
    }
    public List<Float> getFloatParameters(){
        return handler.getParamFloat();
    }
    public List<Integer> node1list(){
        return handler.getNodes();
    }
    public List<Integer> node2list(){
        return handler.getTargetNode();
    }
    public List<Float> weights(){
        return handler.getEdges();
    }
    public List<Float>getMoves(){
        return handler.getMoves();
    }
}