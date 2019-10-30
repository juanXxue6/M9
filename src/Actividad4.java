import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Actividad4 extends RecursiveTask<Long> 
{ 
	long numero;
	
	public Actividad4(long numero) {
		this.numero = numero;
	}
	
    // A recursive function to find nth catalan number 
	protected Long compute() {
		long res = 0;
		long res2 = 0;
		long numeroAux = numero;
		
		if (numero <= 1) {
			return numero;
		}


        for (int i = 0; i < numeroAux; i++) {
        	if (numero <= 1) {
        		res += 1;
        	}else {
            res += (i) ; 
        	}
        } 
        
        for (int i = 0; i < numeroAux; i++) { 
        	if (numero <= 1) {
        		res2 += 1;
        	}else {
            res2 +=  ((int)numero - i - 1); 
        	}
        } 
		
		Actividad4 cat1 = new Actividad4(res);
		cat1.fork();
		
		Actividad4 cat2 = new Actividad4(res2);
		cat2.fork();
		

		
		return cat1.compute() * cat2.join();
		
	} 
	
	
    int catalan(int n) { 
        int res = 0; 
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
		int number = 3;
        Actividad4 cn = new Actividad4(number); 
        
        for (number = number; number < 4; number++) { 
           // System.out.println("Numero generado " + cn.catalan(number) + " " + "Temps utilitzat: " + (System.currentTimeMillis() - timePool)); 
    		System.out.println();
        	System.out.println("Calculat:  "
    				+ pool.invoke(new Actividad4(number)));
        
        } 
        System.out.println();
        System.out.println("Temps utilitzat en total : " + (System.currentTimeMillis() - timePool));
    }

	

} 
