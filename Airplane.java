/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package airport;

import static airport.Airport.NUM_AIRPLANES;
import static airport.Airport.NUM_PASSENGERS;
import static airport.Airport.TOTAL_TIME;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author BNL
 */
public class Airplane extends Thread {
    
    private String name;
    private Runway runway;
    private Gate gate;
    boolean shortage;
    LinkedList listAirplane;
    LinkedList listEmergency;
    LinkedList listTime;
    public Airplane(String name, Runway runway, Gate gate,boolean shortage, LinkedList listAirplane,LinkedList listEmergency,LinkedList listTime) {
        this.name = name;
        this.runway = runway;
        this.gate = gate;
        this.shortage = shortage;
        this.listAirplane = listAirplane;
        this.listEmergency = listEmergency;
        this.listTime = listTime;
    }   

    @Override
    public void run() {
        try {
            long startTime = System.currentTimeMillis();
            runway.land(this,name,shortage,listAirplane,listEmergency);
            int temp = gate.process(this,name);
            runway.takeoff(this,name,temp);
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;
            listTime.add(timeElapsed);
            Airport.TOTAL_TIME =  Airport.TOTAL_TIME + timeElapsed;
            System.out.println("Airplane "+ name +" time in milliseconds: " + timeElapsed);
            Airport.TOTAL_THREAD--;
            if(Airport.TOTAL_THREAD == 0)
                displayStatistics(listTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void displayStatistics(LinkedList listTime) {
        System.out.println("\n=================== Stats ===================");
        System.out.println("Number of planes served: " + NUM_AIRPLANES);
        System.out.println("Total number of passengers boarded: " + NUM_PASSENGERS);
        System.out.println("Maximum waiting time for a plane: " + Collections.max(listTime) + "ms");
        System.out.println("Average waiting time for a plane: " + TOTAL_TIME/Long.valueOf(NUM_AIRPLANES) + "ms");
        System.out.println("Minimum waiting time for a plane: " + Collections.min(listTime) + "ms");
        System.out.println("===============================================");
    }

      
}
