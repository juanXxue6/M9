package activitat7;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.*;

public class NauEspaial extends javax.swing.JFrame {

ThreadGroup shots = new ThreadGroup("parent shot group");
	 
	public NauEspaial() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300,
				Short.MAX_VALUE));
		pack();
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(NauEspaial.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		NauEspaial f = new NauEspaial();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Naus Espaials");
		f.setContentPane(new PanelNau());
		f.setSize(430, 560);
		f.setVisible(true);
	}
}

class PanelNau extends JPanel implements Runnable, KeyListener {
	

	
	private int numNaus = 10;
	Nau[] nau;
	Nau nauPropia;
	Shot[] shots = new Shot[5];
	private static int contador = 0;
	Shot shot;
	int Y;

	public PanelNau() {
		nau = new Nau[numNaus];
		for (int i = 0; i < nau.length; i++) {
			Random rand = new Random();
			int velocitat = (rand.nextInt(3) + 5) * 10;
			int posX = rand.nextInt(100) + 30;
			int posY = rand.nextInt(100) + 30;
			int dX = rand.nextInt(3) + 1;
			int dY = rand.nextInt(3) + 1;
			String nomNau = Integer.toString(i);
			nau[i] = new Nau(nomNau, posX, posY, dX, dY, velocitat);

		}

		// Creo la nau propia
		nauPropia = new Nau("NauNostra", 200, 450, 0, 0, 100);

		// Creo fil per anar pintant cada 0,1 segons el joc per pantalla
		Thread n = new Thread( this);
		n.start();

		// Creo listeners per a que el fil principal del programa gestioni
		// esdeveniments del teclat
		addKeyListener(this);
		setFocusable(true);

	}

	public void run() {
		System.out.println("Inici fil repintar");
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			} // espero 0,1 segons
				// System.out.println("Repintant");
			repaint();
		}
	}

	@SuppressWarnings("deprecation")
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < nau.length; ++i)
			if (nau[i] != null) {
				nau[i].pinta(g);
			}

		nauPropia.pinta(g);
		try {
			matarNave();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < shots.length; i++) {
			if (shots[i] != null) {
				Y = shots[i].getY();
				if (Y <= 0) {
					shots[i].setSeguir(false);
					shots[i] = null;
					
				} else {
					shots[i].pintaShot(g);

				}
			}
		}
	}

	public static void baixarContador() {
		contador--;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int c) {
		contador = c;
	}

	public synchronized void newShot() {

		if (contador < 5) {
			if (shots[contador] == null) {
				shots[contador] = new Shot(nauPropia.getX() + 22,
						nauPropia.getY() - 27, nauPropia.velocitat());
			}

		}

		contador++;
	}

	// Metodes necesaris per gestionar esdeveniments del teclat
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" +
		// e.getKeyChar());
		if (e.getKeyCode() == 37) {
			nauPropia.esquerra();
		} // System.out.println("a l'esquerra"); }
		if (e.getKeyCode() == 39) {
			nauPropia.dreta();
		} // System.out.println("a la dreta"); }

		if (e.getKeyCode() == 32) {
			newShot();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			nauPropia.parar();
		} // System.out.println("a l'esquerra"); }
		if (e.getKeyCode() == 39) {
			nauPropia.parar();
		}
	}

	public  void matarNave() throws InterruptedException {
		double proximidad;
		int xNave;
		int xDisparo;
		int yNave;
		int yDisparo;
		int contadorFinalGame = 0;

		for (int i = 0; i < nau.length; i++) {

			for (int j = 0; j < shots.length; j++) {

				if (shots[j] != null && nau[i] != null) {
					xNave = nau[i].getX();
					xDisparo = shots[j].getX();
					yNave = nau[i].getY();
					yDisparo = shots[j].getY();

					proximidad = Math.sqrt(Math.pow((xNave - xDisparo), 2)
							+ Math.pow((yNave - yDisparo), 2));

					if (proximidad < 20) {
						shots[j].setSeguir(false); 
						nau[i].setSeguir(false);
						nau[i] = null;
						shots[j] = null;
						
						for (int f = 0; f < nau.length; f++) {
							if(nau[f] == null){
								contadorFinalGame++;

							}
							
							if(contadorFinalGame == nau.length){
								System.out.println("HAS GUANYAT!!!");
								Thread.sleep(2000);
								System.exit(0);
							}
						}
					}
				}
			}
		}
	}
}

class Shot extends Thread {
	ThreadGroup shots  = new ThreadGroup("parent shots group");
	private int x;
	private int y;
	private int v;
	private int i = 0;
	private Image image;
	private boolean seguir = true;

	public Shot(int x, int y, int v) {

		this.x = x;
		this.y = y;

		this.v = v;

		image = new ImageIcon(Nau.class.getResource("red_shot.png")).getImage();

		Thread t = new Thread(shots, this);
		t.start();
	}


	
	
	public void run() {
		while (seguir) {
			// System.out.println("Movent nau numero " + this.nomNau);
			try {
				Thread.sleep(this.v);
			} catch (Exception e) {
			}
			moure();
		}
	}
	
	public void setSeguir(boolean s){
		this.seguir = s;
	}

	public int getY() {
		return this.y;
	}

	public int getX() {
		return this.x;
	}

	public synchronized void pintaShot(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.image, this.x, this.y, null);

	}

	public synchronized void moure() {
		int dsy = 30;
		y = y - dsy;
		if (y <= 0) {
			if (i < 1) {
				PanelNau.setContador(0);
				i++;
			}
		}
	}
}

class Nau extends Thread {
	ThreadGroup naus  = new ThreadGroup("parent nau group");
	private String nomNau;
	private int x, y;
	private int dsx, dsy, v;
	private int tx = 10;
	private int ty = 10;
	private boolean seguir = true;

	private String img = "nau.png";
	private Image image;

	public Nau(String nomNau, int x, int y, int dsx, int dsy, int v) {
		this.nomNau = nomNau;
		this.x = x;
		this.y = y;
		this.dsx = dsx;
		this.dsy = dsy;
		this.v = v;

		if (this.nomNau == "NauNostra") {
			image = new ImageIcon(Nau.class.getResource("nau.png")).getImage();
		} else {
			image = new ImageIcon(Nau.class.getResource("ufo.png")).getImage();
		}

		Thread t = new Thread(naus, this);
		t.start();
	}

	public int velocitat() {
		return v;
	}

	public void setSeguir(boolean s){
		this.seguir = s;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public synchronized void moure() {
		x = x + dsx;
		y = y + dsy;
		// si arriva als marges ...
		if (x >= 350 - tx || x <= tx)
			dsx = -dsx;
		if (y >= 450 - ty || y <= ty)
			dsy = -dsy;
	}

	public synchronized void pinta(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.image, x, y, null);

	}

	public void run() {
		while (seguir) {
			// System.out.println("Movent nau numero " + this.nomNau);
			try {
				Thread.sleep(this.v);
			} catch (Exception e) {
			}
			moure();
		}
	}

	public void esquerra() {
		this.dsx = -10;
	}

	public void dreta() {
		this.dsx = 10;
	}

	public void parar() {
		this.dsx = 0;
	}
}