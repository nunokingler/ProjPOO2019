import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler{

    private String currentValue=null;
    private List<Integer> nodes = null;
    private int node1, node2;
    private List<Float> edges = null; 
    private float edge;
    
    private float evap1;
    private float evap2;
    
   // private float node1;
   // private float node2;
    
    
    static String fileName; 
    
    @Override
    public void startDocument() throws SAXException {
    	
    	System.out.println("Beginning the parsing of " + fileName); 
        nodes = new ArrayList<Integer>();
        edges = new ArrayList<Float>();

    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue = new String(ch, start, length);
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if (qName.equalsIgnoreCase("simulation")) {
            
            if(attributes.getLength() > 0){
            	
                String f_instant = attributes.getValue("finalinst");
                String p_level = attributes.getValue("plevel");
                String s_antcol = attributes.getValue("antcolsize");
                
                //Simulation simul  = new Simulation(Float.valueOf(f_instant),Float.valueOf(p_level), Integer.valueOf(s_antcol));
                System.out.println("Final Instant: " + Float.valueOf(f_instant) + " Colony Size: " + Integer.valueOf(s_antcol) + " Pheromone level: " + Float.valueOf(p_level));
            }    
        }
    	else if (qName.equalsIgnoreCase("graph")) {
    		if(attributes.getLength() > 0){
    			String n_nodes = attributes.getValue("nbnodes");
    			String n_nest = attributes.getValue("nestnode");
    			
                System.out.println("Number of nodes: " + Integer.valueOf(n_nodes) + " Nest node: " + Integer.valueOf(n_nest));

    			
                //Graph graph = new Graph(Integer.valueOf(n_nodes),Integer.valueOf(n_nest), Float.valueOf(in_eta), Float.valueOf(in_rho));
    		}
    	}
    	else if (qName.equalsIgnoreCase("node")) {
    		    		
    		if(attributes.getLength() > 0){
    			
    			String id_node = attributes.getValue("nodeidx");
    			
    			node1=Integer.valueOf(id_node);
    			
                System.out.println("Node ID: " + Integer.valueOf(id_node));
               // node1 = new Node(Integer.valueOf(id_node));
				nodes.add(node1);

      		}
    	}
    	else if("weight".equals(qName)) {

        	 if(attributes.getLength() > 0){
        		 
        		 String t_node = attributes.getValue("targetnode");
        		// node2=Integer.valueOf(t_node);
        		// node2 = new Node(Integer.valueOf(t_node));
        		 
        		 System.out.print("Target node: " + Integer.valueOf(t_node));
        	}
        }
    	else if (qName.equalsIgnoreCase("move")) {
    		if(attributes.getLength() > 0){
    		
    			String in_alpha = attributes.getValue("alpha");
    			String in_beta = attributes.getValue("beta");
    			String in_delta = attributes.getValue("delta");
    			
                System.out.println("Alpha: " + Float.valueOf(in_alpha)  + " Beta: " + Float.valueOf(in_beta) + " Delta: " + Float.valueOf(in_delta));
    		}
    	}
    	else if (qName.equalsIgnoreCase("evaporation")) {
    		if(attributes.getLength() > 0){
    			
    			String in_eta = attributes.getValue("eta");
    			String in_rho = attributes.getValue("rho");
    			evap1=Float.valueOf(in_eta);
    			evap2=Float.valueOf(in_rho);
    			
                System.out.println("Eta: " + evap1 + " Rho: " + evap2);
                
    		}	
    		
    	}        		 

    	
    }

	public void endElement(String namespaceURI,String localName,String qname){

		switch(qname){
		
		   case "weight":      float peso = Float.valueOf(currentValue);
           					   System.out.print(" Peso: " + peso + "\n");
		   					   //edge = new Edge(peso,evap1, evap2, node1,node2);
		   					  
		   					   edges.add(peso);
		   					   
		                       break;
		                       
		}
	}
	
	  public List<Integer> getNodes() {
	        return nodes;
	    }

	  public List<Float> getEdges() {
	        return edges;
	    }
	
	public void endDocument(){
	
		 System.out.println("Parsing concluded");
		} 
}