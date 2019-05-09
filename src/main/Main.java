package main;

import simulation.*;
import exception.*;
import java.util.List;

import event.Event;
import pec.PEC;
import pec.PecHolder;

public class  Main {

    public static void main(String[] args) {
        PEC pec = PecHolder.pec;
       // Simulation s = new Simulation(20,0,0,50,1);

        //Instancia as coisas aqui
        //s.addNode();

        ReadXMLFile read = new ReadXMLFile();

        read.inits();

        List<Integer> inters= read.getIntParameteres();//pos 0= antcolsize, pos 1=nestNode
        List<Float> floaters = read.getFloatParameters();//pos 0= finalinst, pos 1=plevel, pos 2 = eta(n),pos 3= rho(p)

        Simulation simul = new Simulation(floaters.get(0), floaters.get(2), floaters.get(1));


        List<Integer> node1=read.node1list(),node2=read.node2list();
        List<Float> weights= read.weights();

        if(node1.size()!=node2.size() || node1.size()!=weights.size()){
            System.out.println("SOMETHING WENT VERY VERY WRONG");
        }
        for(int i=0;i<node1.size();i++){
            try {
                simul.addEdge(node1.get(i),node2.get(i),weights.get(i));
            } catch (NotThisEdge_exeption notThisEdge_exeption) {
                notThisEdge_exeption.printStackTrace();
            } catch (ThisEdgeAlreadyExists thisEdgeAlreadyExists) {
                thisEdgeAlreadyExists.printStackTrace();
            }
        }
        simul.LoadAnts(/*inters.get(0)*/1,inters.get(1));

        List<Float> moveParam=read.getMoves();
        simul.setAlpha(moveParam.get(0));
        simul.setBeta(moveParam.get(1));
        simul.setDelta(moveParam.get(2));
        simul.setGamma(floaters.get(1));

        try {
            Event ev = pec.nextEvent();
            double time = ev.getTime();
            float simTime = floaters.get(0);
            while (time <= simTime) {
                //System.out.println("Time is "+time);
                ev.doEvent();
                ev = pec.nextEvent();
                time = ev.getTime();
            }
        }catch(NoEventException ev){
            ev.getMessage();
            ev.printStackTrace();
        }
    }
}
