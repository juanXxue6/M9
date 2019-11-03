import java.util.*;
import java.util.concurrent.*;

public class act4 {

	static class Caixer implements Runnable {
		//Un cop dintre la classe, iniciem l'array amb el que tarden els articles i definim el númeor de client
		private int[] temps = { 2, 3, 4, 5, 6, 7, 8 };
		private int numClient;
		//Contructor per la classe
		public Caixer(int i) {
			numClient = i;
		}

		@Override
		public void run() {
			//Generem aleatoriament el número d'articles d'aquest client
			int articlesRandom = (int) (Math.random() * (30 - 1 + 1)) + 1;
			int tempsRandom;
			//Mostrem el client
			System.out.println("Creat client " + numClient + " amb " + articlesRandom + " articles.");
			System.out.println("Client " + numClient + " passa per caixa...");
			for (int j = 1; j < articlesRandom + 1; j++) {
				//Generem per cada article un temps de l'array d'abans, aleatoriament
				tempsRandom = (int) (Math.random() * (6 - 0 + 1));
				try {
					//Fem que pari conforme el temps
					Thread.sleep(temps[tempsRandom] * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Si ha acabat, que mostri finalitzat
				if (j == articlesRandom) {
					System.out.println("Client " + numClient + " article " + j + "/" + articlesRandom + " ("
							+ temps[tempsRandom] + " segons) FINALITZAT");
				} else {
					//Si no, que segueixi amb el mateix format
					System.out.println("Client " + numClient + " article " + j + "/" + articlesRandom + " ("
							+ temps[tempsRandom] + " segons)");
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// Creem l'executor i passem per paràmetre els fils que volem generar
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(20);

		// Num de clients (canviar el número limit pel numero que volguem
		for (int i = 1; i < 501; i++) {
			//Creem la tasca, i l'executem
			Caixer task = new Caixer(i);
			executor.scheduleWithFixedDelay(task, 0, 3, TimeUnit.SECONDS);	
		}
		
		executor.awaitTermination(20, TimeUnit.SECONDS);
		executor.shutdown();

	}
}