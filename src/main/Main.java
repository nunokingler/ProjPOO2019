package main;

import event.Event;
import exception.AntsAlreadyLoadedExeption;
import exception.NoEventException;
import exception.ThisEdgeAlreadyExists;
import pec.PEC;
import pec.PecHolder;
import simulation.Simulation;

import java.util.List;

public class  Main {

    public static void main(String[] args) {
        PEC pec = PecHolder.pec;

        ReadXMLFile read = new ReadXMLFile();
        String pathname=args[0];//"C:/Users/Cavaco/IdeaProjects/ProjPOO2019/resources/data1.xml";//
        read.inits(pathname);

        List<Integer> inters= read.getIntParameteres();//pos 0= antcolsize, pos 1=nestNode
        List<Float> floaters = read.getFloatParameters();//pos 0= finalinst, pos 1=plevel, pos 2 = eta(n),pos 3= rho(p)

        Simulation simul = new Simulation(floaters.get(0), floaters.get(2), floaters.get(1));//instantiate simulation


        List<Integer> node1=read.node1list(),node2=read.node2list();//get edge related variables
        List<Float> weights= read.weights();

        if(node1.size()!=node2.size() || node1.size()!=weights.size()){
            System.out.println("SOMETHING WENT VERY VERY WRONG");//tldr file reading failled
        }
        for(int i=0;i<node1.size();i++){//for each edge
            try {
                simul.addEdge(node1.get(i),node2.get(i),weights.get(i));//add it to the graph
            } catch (ThisEdgeAlreadyExists thisEdgeAlreadyExists) {
                //thisEdgeAlreadyExists.printStackTrace();
            }
        }
        try {
            simul.LoadAnts(inters.get(0),inters.get(1));//graph is complete, time to start the ants
        } catch (AntsAlreadyLoadedExeption antsAlreadyLoadedExeption) {
            antsAlreadyLoadedExeption.printStackTrace();
        }

        List<Float> moveParam=read.getMoves();//setting simulation related parameters
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
