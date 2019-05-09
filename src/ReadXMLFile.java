import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
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
        File xmlFile = new File("C:/Users/Cavaco/IdeaProjects/ProjPOO2019/resources/data1.xml");
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
            Document doc=null;

//            doc = dBuilder.parse(xmlFile);

//            doc.getDocumentElement().normalize();

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