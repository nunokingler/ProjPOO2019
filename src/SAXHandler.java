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
        
    	if (qName.equalsIgnoreCase("simulation")) {
            
            if(attributes.getLength() > 0){
            	
                String f_instant = attributes.getValue("finalinst");
                String p_level = attributes.getValue("plevel");
                String s_antcol = attributes.getValue("antcolsize");
                
                //Simulation simul  = new Simulation(Float.valueOf(f_instant),Float.valueOf(p_level), Integer.valueOf(s_antcol));
                System.out.println("Root element : simulation" );
                System.out.println("Final Instant: " + Float.valueOf(f_instant) + "Colony Size " + Integer.valueOf(s_antcol) + "Pheromone level :" + Float.valueOf(p_level));
            }    
        }
    	else if (qName.equalsIgnoreCase("graph") && qName.equalsIgnoreCase("evaporation")) {
    			String n_nodes = attributes.getValue("nbnodes");
    			String n_nest = attributes.getValue("nestnode");
    			String in_eta = attributes.getValue("eta");
    			String in_rho = attributes.getValue("rho");
    			
                //Graph graph = new Graph(Integer.valueOf(n_nodes),Integer.valueOf(n_nest), Float.valueOf(in_eta), Float.valueOf(in_rho));
    	}
    }
 
    public List<Node> getNodes() {
        return nodes;
    }
}