/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author BNL
 */
class PassengerGenerator extends Thread {
        private final String planeName;
        private int currentGateNumber;
        private final Random random;
        int x =ThreadLocalRandom.current().nextInt(31,51); 
        int y =ThreadLocalRandom.current().nextInt(31,51);  
        public PassengerGenerator(String planeName, int currentGateNumber) {
            this.planeName = planeName;
            this.currentGateNumber = currentGateNumber;
            this.random = new Random();
            
        }

        public void disembark() {
           
            for (int i = 1; i < x; i++) {
                try {
                    Thread.sleep(random.nextInt(500));
                    System.out.printf("GATE %d: Passenger %d is disembarking from PLANE %s\n", currentGateNumber, i, planeName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
        public void embark(LinkedList listFree) {
             System.out.printf("GATE %d: Passengers are starting to embark from PLANE %s\n", currentGateNumber, planeName);
            for (int i = 1; i < y; i++) {
                try {
                    Airport.NUM_PASSENGERS++;
                    Thread.sleep(random.nextInt(500));
                    System.out.printf("GATE %d: Passenger %d is embarking from PLANE %s\n", currentGateNumber, i, planeName);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           listFree.add(currentGateNumber);
        }
    }
