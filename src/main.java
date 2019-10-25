
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class main {

    public static void main(final String... args) throws InterruptedException, ExecutionException {
        //mostrem hora actual abans d’execució
    Calendar calendario = new GregorianCalendar();
    System.out.println("Inici: "+ calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE) +
        ":" + calendario.get(Calendar.SECOND));
    // Cmabiamos de 2 a 4 hilos
    final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(4);
    // Crea objecte Runnable.
    final Runnable ob = new main().new ExecutaFil();
    
    // programamos el hilo, poniendo que la primera vez que se ejecute lo haga a los 5 segundos, 
    //y despues lo hace cada seis segundos 
    
    schExService.scheduleWithFixedDelay(ob, 5, 6, TimeUnit.SECONDS);
    //Espera 8 segons per acabar
    schExService.awaitTermination(8, TimeUnit.SECONDS);
    //tanquem el executor.
    schExService.shutdownNow();
    System.out.println("Completat");
    }

    // Fil Runnable
    class ExecutaFil implements Runnable {
        @Override
        public void run() {
        	//Mostrem les dades
            Calendar calendario = new GregorianCalendar();
            System.out.println("Hora execució tasca: "+
                calendario.get(Calendar.HOUR_OF_DAY) + ":" +
                calendario.get(Calendar.MINUTE) + ":" +
                calendario.get(Calendar.SECOND));
            System.out.println("Tasca en execució");
            System.out.println("Execució acabada");
            }
        }
    }