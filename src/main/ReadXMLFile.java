package main;

//import org.w3c.dom.Document;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
import java.util.List;


public class ReadXMLFile {

    private String fileName;
    private SAXHandler handler;

    //static File xmlFile = new File ("C:/Users/henri/git/ProjPOO2019/data1.xml");

    //fileName = xmlFile.getName();
    public ReadXMLFile(){
        super();
    }

    /** method used to read a file and getting the nessesary data prepared
     * @param pathname pathname of the file to open
* */
    public void inits(String pathname){
        File xmlFile = new File(pathname);
        fileName=xmlFile.getName();

        try {
            SAXParserFactory fact = SAXParserFactory.newInstance();
            SAXParser saxParser = fact.newSAXParser();

            handler = new SAXHandler();
            saxParser.parse(new File(fileName),handler);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** This method returns the initial int parameters regarding the simulation
     * @return List of integers, position 0 = ant colony size, position 1 = nest node
     * */
    public List<Integer> getIntParameteres(){
        return handler.getParamInt();
    }
    /** This method returns the initial float parameters regarding the simulation
     * @return List of floats,position 0=final instant,position 1=plevel(gamma),position 2=eta(evaporationTime),position 3=rho(EvaporationValue)
     * */
    public List<Float> getFloatParameters(){
        return handler.getParamFloat();
    }
    /** This method returns the list for the value of node1 for all the edges found on the file
     * @return List of node1 of all the edges
     * */
    public List<Integer> node1list(){
        return handler.getNodes();
    }
    /** This method returns the list for the value of node2 for all the edges found on the file
     * @return List of node2 of all the edges
     * */
    public List<Integer> node2list(){
        return handler.getTargetNode();
    }
    /** This method returns the list for the value of weights for all the edges found on the file
     * @return List of weighs of all the edges
     * */
    public List<Float> weights(){
        return handler.getEdges();
    }
    /** This method returns a list of variables related to ant moves
     * @return List of floats, position 0=alpha, positon 1=beta, position 2= gamma
     * */
    public List<Float>getMoves(){
        return handler.getMoves();
    }
}