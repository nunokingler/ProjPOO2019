import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler{

	private ArrayList<Node> nodes = null;
    private String elementValue;
    static String fileName; 
    
    @Override
    public void startDocument() throws SAXException {
    	System.out.println("Beginning the parsing of " + fileName); 
        nodes = new ArrayList<>();
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("node")) {
            
            if(attributes.getLength() > 0){
                String n_idx = attributes.getValue("nodeidx");
                Node new_node = new Node(Integer.valueOf(n_idx));
            }
        }
    }
 
    public List<Node> getNodes() {
        return nodes;
    }
}