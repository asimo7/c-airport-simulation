/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package airport;


import java.util.LinkedList;


/**
 *
 * @author BNL
 */
public class Airport {
    public static int NUM_AIRPLANES = 0;
    public static int NUM_PASSENGERS = 0;
    public static long TOTAL_TIME = 0;
    public static int TOTAL_THREAD = 6;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("There is only 1 Runway and 3 Parking Sports");
        Runway runway = new Runway();
        Gate gate = new Gate(0);
        LinkedList<Airplane> listTime = new LinkedList<>();
        LinkedList<Airplane> listAirplane = new LinkedList<>();
        LinkedList<Airplane> listEmergency= new LinkedList<>();
        PlaneGenerator planeGenerator = new PlaneGenerator(runway, gate, listAirplane,listEmergency,listTime);
        Thread thag = new Thread(planeGenerator);
        thag.start();

        
    }
    
}
