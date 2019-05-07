
public class  Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        PEC pec = PecHolder.pec;

        //Instancia as coisas aqui

        
        ReadXMLFile();
        
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
