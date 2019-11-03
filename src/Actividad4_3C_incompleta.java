import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Actividad4_3C_incompleta extends RecursiveTask<Long> 
{ 
	long numero;
	int contador = 0;
	
	public Actividad4_3C_incompleta(long numero) {
		this.numero = numero;
		
		
	}
	
    //incompleta, se deberia usar para calcular mediante el fork el numero de catalan
	protected Long compute() {
		int a = 0;
		int b = 0;

		if (numero <= 1)
			return numero;
		
		Actividad4_3C_incompleta cat1 = new Actividad4_3C_incompleta(numero - 1);

		Actividad4_3C_incompleta cat2 = new Actividad4_3C_incompleta(catalan((int)numero - 2));

		return cat1.compute() * cat2.join();
	} 
	
	//ralizamos el sumatorio para obtener el numero de catalan
    int catalan(int n) { 
        int res = 0; 
       // double calcul = java.lang.Math.cos(54879854); //para ponerle penalizacion
        // Base case 
        if (n <= 1) { 
            return 1; 
        } 
        for (int i = 0; i < n; i++) { 
            res += catalan(i) * catalan(n - i - 1); 
        } 
        return res; 
    } 
    
    


    
    public static void main(String[] args) { 
    	long timePool = System.currentTimeMillis();
		long timeMetode = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool();
		int number = 0; // ponemos el primer valor para calcular de los  numero de catalan
		int _final = 10; //valor maximo del numero de catalan a calcular
        Actividad4_3C_incompleta cn = new Actividad4_3C_incompleta(number); 
        
        //ponemos un contador para que nos enseñe los 10 primeros numeros de catalan de los 10 primeros numeros
        for (number = number; number < _final; number++) { 
        	System.out.println("-----");
        	System.out.println("numero de catalan de   " + number + ": " + cn.catalan(number) + " " + "Temps utilitzat: " + (System.currentTimeMillis() - timePool)); 
            System.out.println("Temps utilitzat en total : " + (System.currentTimeMillis() - timePool));
            System.out.println("-----");
        }
        
      /*  System.out.println("Calculo de numero de catalan para el numero " + number + ": "
				+ pool.invoke(new Actividad4_3C_incompleta(number)));
        System.out.println();*/
        

    }

	

} 
