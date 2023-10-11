package airport;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import java.util.concurrent.TimeUnit;

public class Runway {
    private Semaphore runway = new Semaphore(1);
    private Semaphore gates = new Semaphore(3);
    private int numPlanes = 0;

    synchronized public void check(String name,boolean shortage){
        while (numPlanes == 3) {
                try {
                    if(shortage == true)
                        System.out.printf("%s IS IN A CRITICALLY LOW FUEL STATE! \n", name);
                    
                    System.out.printf("%s is waiting for a gate and runway to land\n", name);
                    wait(); // wait until there is space on the airport grounds
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
    synchronized public void land(Airplane airplane, String name,boolean shortage, LinkedList listAirplane, LinkedList listEmergency) throws InterruptedException {
            check(name,shortage);
            try {
                System.out.printf("## There Are Gates that are still Empty ##\n",name);
                if(listEmergency.isEmpty() == false && shortage == true){
                    System.out.printf("## PLANE %s HAS A SHORTAGE ON FUEL! ##\n",name);
                    listEmergency.poll();
                }
                else if(listEmergency.isEmpty() == false && shortage == false) {
                   check(name,shortage);
                }
                else if(listEmergency.isEmpty() == true && shortage == false) {
                   listAirplane.poll();
                }
                System.out.printf("%s is waiting for a gate and runway to land\n", name);
                runway.acquire();
                numPlanes++;
                notifyAll();
                System.out.printf("Control Tower assigns Plane" + name + " To Gate a free gate\n");
                System.out.printf("%s is landing in the runway\n", name);
                System.out.printf("%s has landed and is taxiing to a gate\n", name);
                gates.acquire();
                runway.release();
                gates.release();
                
               // notify waiting threads that there is space on the airport grounds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    

    synchronized public void takeoff(Airplane airplane, String name,int free) {
        try {
            System.out.printf("%s is waiting for  a runway to take off\n", name);
            
                runway.acquire();
                System.out.printf("%s is taking off from runway\n", name);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now(); 
                System.out.println(name +" has taken off " + dtf.format(now) + "\n");
                runway.release();
                numPlanes--;
                notifyAll(); // notify waiting threads that there is space on the airport grounds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}  

//    public synchronized void addPlane() {
//        while (numPlanes == 3) {
//            try {
//                wait(); // wait until there is space on the airport grounds
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

       
//        notifyAll(); // notify waiting threads that there is space on the airport grounds
//    }
//}