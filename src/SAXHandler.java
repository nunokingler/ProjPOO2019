import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler{

	private ArrayList<Node> nodes = null;
    private String elementValue;
    
    @Override
    public void startDocument() throws SAXException {
        nodes = new ArrayList<>();
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("node")) {
            node = new Node();
            
            if(attributes.getLength() > 0)
            {
                String n_idx = attributes.getValue("nodeidx");
                node.setGraduated(Integer.valueOf(n_idx));
            }
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("node")) {
            nodes.add(node);
        }
        
        if (qName.equalsIgnoreCase("weight")) {
            node.setId(Integer.valueOf(elementValue));
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }
 
    public List<Node> getNodes() {
        return nodes;
    }
}
	
}
