
public class  Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        PEC pec = PecHolder.pec;
       // Simulation s = new Simulation(20,0,0,50,1);

        //Instancia as coisas aqui
        //s.addNode();

        ReadXMLFile read = new ReadXMLFile();
        
        
        //Simulation simul = new Simulation(f_inst, evap1, evap2, ant_colsize, nest_node);
  
        try {
            Event ev = pec.nextEvent();
            double time = ev.getTime();
            float simTime = 20;
            while (time < simTime) {
                ev.doEvent();
                ev = pec.nextEvent();
                time = ev.getTime();
            }
        }catch(NoEventException ev){
            ev.getMessage();
            ev.printStackTrace();
        }
        System.out.println("test");
    }
}
