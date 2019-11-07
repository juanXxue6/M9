class TiradaDaus {
	private int tiradaDau;

	public TiradaDaus(int e) {
		tiradaDau = e;
	}

	public synchronized int getSumaTirada() {
		return tiradaDau;
	}

	public synchronized void setSumaTirada(int e) {
		tiradaDau += e;
	}
}

public class Actividad5_apartat3 implements Runnable {

	private TiradaDaus xobj;

	public Actividad5_apartat3(TiradaDaus m) {
		xobj = m;
	}

	public void run() {
		try {
			Thread.sleep(1000);
			int resultatDau = (int) (Math.random() * 6) + 1;
			xobj.setSumaTirada(resultatDau);
			System.out.println("Tirada fil " + Thread.currentThread().getName() + ": " + resultatDau);
		} catch (InterruptedException e) {
		}

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		TiradaDaus ans = new TiradaDaus(0);

		Actividad5_apartat3 obj1 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj2 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj3 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj4 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj5 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj6 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj7 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj8 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj9 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj10 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj11 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj12 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj13 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj14 = new Actividad5_apartat3(ans);
		Actividad5_apartat3 obj15 = new Actividad5_apartat3(ans);
		Thread fil_1 = new Thread(obj1);
		fil_1.setName("Dau 1");
		Thread fil_2 = new Thread(obj2);
		fil_2.setName("Dau 2");
		Thread fil_3 = new Thread(obj3);
		fil_3.setName("Dau 3");
		Thread fil_4 = new Thread(obj4);
		fil_1.setName("Dau 4");
		Thread fil_5 = new Thread(obj5);
		fil_2.setName("Dau 5");
		Thread fil_6 = new Thread(obj6);
		fil_3.setName("Dau 6");
		Thread fil_7 = new Thread(obj7);
		fil_1.setName("Dau 7");
		Thread fil_8 = new Thread(obj8);
		fil_2.setName("Dau 8");
		Thread fil_9 = new Thread(obj9);
		fil_3.setName("Dau 9");
		Thread fil_10 = new Thread(obj10);
		fil_1.setName("Dau 10");
		Thread fil_11 = new Thread(obj11);
		fil_2.setName("Dau 11");
		Thread fil_12 = new Thread(obj12);
		fil_3.setName("Dau 12");
		Thread fil_13 = new Thread(obj13);
		fil_1.setName("Dau 13");
		Thread fil_14 = new Thread(obj14);
		fil_2.setName("Dau 14");
		Thread fil_15 = new Thread(obj15);
		fil_3.setName("Dau 15");
				
		fil_1.start();
		fil_2.start();
		fil_3.start();
		fil_4.start();
		fil_5.start();
		fil_6.start();
		fil_7.start();
		fil_8.start();
		fil_9.start();
		fil_10.start();
		fil_11.start();
		fil_12.start();
		fil_13.start();
		fil_14.start();
		fil_15.start();
		
		
		fil_1.join(); 
		fil_2.join();
		fil_3.join();
		fil_4.join(); 
		fil_5.join();
		fil_6.join();
		fil_7.join(); 
		fil_8.join();
		fil_9.join();
		fil_10.join(); 
		fil_11.join();
		fil_12.join();
		fil_13.join();
		fil_14.join();
		fil_15.join();

		System.out.println("Total tirada: " + ans.getSumaTirada());
		System.out.println("Final Fil Principal");

	}

}
