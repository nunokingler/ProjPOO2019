import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {

    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("C:/Users/Cavaco/IdeaProjects/ProjPOO2019/out/production/ProjPOO2019/data1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

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