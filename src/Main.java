
public class  Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        PEC pec = PecHolder.pec;

        //Instancia as coisas aqui

        Event ev=pec.nextEvent();
        double time= ev.getTime();
        float simTime=20;
        while(time<simTime){
            ev.doEvent();
            ev=pec.nextEvent();
            time=ev.getTime();
        }
        System.out.println("test");
    }
}
