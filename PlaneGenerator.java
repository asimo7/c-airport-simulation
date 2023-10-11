/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author BNL
 */



public class PlaneGenerator implements Runnable{
//    public LinkedList<Airplane> airplaneQueue;
    public Runway runway;
    public Gate gate;
    String name;
    LinkedList listAirplane;
    LinkedList listEmergency;
    LinkedList listTime;
    public PlaneGenerator (Runway runway, Gate gate, LinkedList<Airplane> listAirplane, LinkedList<Airplane> listEmergency, LinkedList<Airplane> listTime){
        this.runway = runway;
        this.gate = gate;
        this.listAirplane = listAirplane;
        this.listEmergency = listEmergency;
        this.listTime = listTime;
    }
    @Override
    public void run() {
        int airplaneID = 1;
        while (airplaneID != 7) {
            boolean shortage = false;
            if (airplaneID == 6) {
                shortage = true;
            }
        name = "AP" + String.valueOf(airplaneID);
        Airplane airplane = new Airplane(name,runway,gate,shortage,listAirplane,listEmergency,listTime);
        if (shortage == false){
            listAirplane.add(airplane);
            System.out.printf("## Normal list: PLANE %s joined! ##\n",name);
        }
        else {
            listEmergency.add(airplane);
            System.out.printf("## Emergency list: PLANE %s joined! ##\n",name);
        }
        airplane.start();
        airplaneID++;
        Airport.NUM_AIRPLANES++;
        long duration = 0;
        try
        {
            duration = (long)(Math.random()*2);
            TimeUnit.SECONDS.sleep(duration);
        }catch (InterruptedException iex) {
				iex.printStackTrace();
			}
        }
    }
}