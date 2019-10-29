import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Actividad3 extends RecursiveTask<Long> {

	
	public static long calculaFibonacci(long numero) {
        double calcul = java.lang.Math.cos(54879854);
        if (numero==0) { return 0; }
        else if (numero==1) { return 1; }
        else { 
            return (calculaFibonacci(numero-2) + calculaFibonacci(numero-1)); 
        }
    }

	
	
	
	long numero;
    public Actividad3(long numero){
        this.numero=numero;
    }    
    @Override
    protected Long compute() {
        // ATENCIO **1** double calcul = java.lang.Math.cos(54879854);
        if(numero <= 1) return numero;
        Actividad3 fib1 = new Actividad3(numero-1);
        //fib1.fork();
        Actividad3 fib2 = new Actividad3(numero-2);
        fib2.fork();
	 return fib1.compute()+ fib2.join();
	 }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Calculat:  " + pool.invoke(new Actividad3(35)));    

		

	}

}
