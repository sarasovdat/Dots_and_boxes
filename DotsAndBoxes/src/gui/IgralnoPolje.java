package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logika.Igra;
import logika.Plosca;
import logika.Smer;
import logika.Stanje;
import logika.Box;
import logika.Crta;


@SuppressWarnings("serial")
public class IgralnoPolje extends JPanel implements MouseListener {
	private GlavnoOkno okno;
	
	/**
	 * Relativni radij pike
	 */
	private final static double RADIJ_PIKE = 0.1;
	
	/**
	 * Relativna debelina crte
	 */
	private final static double DEBELINA_CRTE = 0.05;
	
	/**
	 * Prazen prostor od pik do roba polja
	 */
	private final static double PRAZEN_PROSTOR_DO_ROBA = 30;
	
	public IgralnoPolje (GlavnoOkno okno) {
			super();
			setBackground(Color.white);
			this.okno = okno;
			this.addMouseListener(this);
	}
	
	

	@Override
	public Dimension getPreferredSize() {
		return new Dimension (400, 400);
	}

	
	/**
	 * 
	 * @return relativna velikost boxa
	 */
	private double velikostBoxa() {
		return Math.min(getWidth() - 2 * PRAZEN_PROSTOR_DO_ROBA, getHeight() - 2 * PRAZEN_PROSTOR_DO_ROBA) 
				/ Math.min(Plosca.VISINA, Plosca.SIRINA);
	}
	
	/**
	 * 
	 * @param g
	 * @param i
	 * @param j
	 * Narise piko
	 */
	private void narisiPiko (Graphics2D g, int i, int j) {
		double velikostBoxa = velikostBoxa();
		g.setColor(Color.gray);
		g.drawOval(i, j, (int)(RADIJ_PIKE * velikostBoxa), (int)(RADIJ_PIKE * velikostBoxa));
		g.fillOval(i, j, (int)(RADIJ_PIKE * velikostBoxa), (int)(RADIJ_PIKE * velikostBoxa));
	}
	
	/**
	 * 
	 * @param g
	 * @param i
	 * @param j
	 * @param c
	 */
	private void narisiVodoravno (Graphics2D g, int i, int j, Color c) {
		double velikostBoxa = velikostBoxa();
		g.setColor(c);
		g.setStroke(new BasicStroke((float)(velikostBoxa * DEBELINA_CRTE)));
		int x1 = (int) (i + PRAZEN_PROSTOR_DO_ROBA + 2 * RADIJ_PIKE * velikostBoxa);
		int x2 = (int) (i + velikostBoxa + PRAZEN_PROSTOR_DO_ROBA - RADIJ_PIKE * velikostBoxa);
		int y = (int) (j + PRAZEN_PROSTOR_DO_ROBA + (RADIJ_PIKE / 2) * velikostBoxa); 
		g.drawLine(x1, y, x2, y);
	}
	
	/**
	 * 
	 * @param g
	 * @param i
	 * @param j
	 * @param c
	 */
	private void narisiNavpicno (Graphics2D g, int i, int j, Color c) {
		double velikostBoxa = velikostBoxa();
		g.setColor(c);
		g.setStroke(new BasicStroke((float)(velikostBoxa * DEBELINA_CRTE)));
		int y1 = (int) (j + PRAZEN_PROSTOR_DO_ROBA + 2 * RADIJ_PIKE * velikostBoxa);
		int y2 = (int) (j + velikostBoxa + PRAZEN_PROSTOR_DO_ROBA - RADIJ_PIKE * velikostBoxa);
		int x = (int) (i + PRAZEN_PROSTOR_DO_ROBA + (RADIJ_PIKE / 2) * velikostBoxa); 
		g.drawLine(x, y1, x, y2);
	}
	
	/**
	 * 
	 * @param g
	 * @param i
	 * @param j
	 * V box, ki se zapre, narise X ustrezne barve (odvisno kdo ga zapre)
	 */
	private void narisiX (Graphics2D g, int i, int j, Color c) {
		double velikostBoxa = velikostBoxa();
		double sirina = velikostBoxa * (1.0 - DEBELINA_CRTE - 2.0 * PRAZEN_PROSTOR_DO_ROBA);
		double x = velikostBoxa * (i + 0.5* DEBELINA_CRTE + PRAZEN_PROSTOR_DO_ROBA);
		double y = velikostBoxa * (j + 0.5* DEBELINA_CRTE + PRAZEN_PROSTOR_DO_ROBA);
	}
	
	
	// # TODO funkcija narisi vodoravno in navpicno crto

	/**
	 * Rise na igralno polje
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g; 	
		double velikostBoxa = velikostBoxa();
		Plosca plosca = okno.getPlosca();
		
		// PIKE
		for (int i = 0; i <= Plosca.SIRINA; i ++) {
			for (int j = 0; j <= Plosca.VISINA; j ++) {
				narisiPiko(g2, (int)(i * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA), (int)(j * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA));
			}
		}

		// CRTE
		// Vodoravne crte
		Crta [][] vodoravneCrte = plosca.getVodoravneCrte();
		for (int i = 0; i < (Plosca.VISINA + 1); i ++) {
			for (int j = 0; j < Plosca.SIRINA; j ++) {
				switch(vodoravneCrte[i][j]){
				case RDEC: narisiVodoravno(g2, i * (int)velikostBoxa, j * (int)velikostBoxa, Color.RED); break;
				case MODER: narisiVodoravno(g2, i * (int)velikostBoxa, j * (int)velikostBoxa, Color.BLUE); break;
				default: break;
				}
			}
		}

		// Navpicne crte
		Crta [][] navpicneCrte = plosca.getNavpicneCrte();
		for (int i = 0; i < Plosca.VISINA; i ++) {
			for (int j = 0; j < (Plosca.SIRINA + 1); j ++) {
				switch (navpicneCrte[i][j]) {
				case RDEC: narisiNavpicno(g2, i * (int)velikostBoxa, j * (int)velikostBoxa, Color.RED); break;
				case MODER: narisiNavpicno(g2, i * (int)velikostBoxa, j * (int)velikostBoxa, Color.BLUE); break;
				default: break;
				}
			}
		}


		// BOXI - X
		Box [][] box = plosca.getPolje();
		for (int i = 0; i < Plosca.VISINA; i ++) {
			for (int j = 0; j < Plosca.SIRINA; j ++) {
				switch (box[i][j]) {
				case RDEC: narisiX(g2, i * (int)velikostBoxa, j * (int)velikostBoxa, Color.RED); break;
				case MODER: narisiX(g2, i * (int)velikostBoxa, j * (int)velikostBoxa, Color.BLUE); break;
				default: break;
				}	
			}
		}	
	}
	
	// TODO
	// Na koncu naj poklice klikni.Polje(smer, i, j)
	// Iz x in y morva dobit smer, i, j
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int velikostBoxa = (int) velikostBoxa();	
		
		int a = x / (velikostBoxa + (int)PRAZEN_PROSTOR_DO_ROBA);
		int b = y / (velikostBoxa + (int)PRAZEN_PROSTOR_DO_ROBA);
		
		// Dovoljeni kliki
		if (x < velikostBoxa * Plosca.SIRINA + 2 * (int)PRAZEN_PROSTOR_DO_ROBA &&
				y < velikostBoxa * Plosca.VISINA + 2 * (int)PRAZEN_PROSTOR_DO_ROBA){
		
			for (int i = 0; i < (Plosca.VISINA + 1); i ++) {
				for (int j = 0; j < Plosca.SIRINA; j ++) {
					int x1 = (int) (i * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA + 2 * RADIJ_PIKE * velikostBoxa);
					int x2 = (int) (i * velikostBoxa + velikostBoxa + PRAZEN_PROSTOR_DO_ROBA - RADIJ_PIKE * velikostBoxa);
					int y0 = (int) (j * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA + (RADIJ_PIKE / 2) * velikostBoxa); 
					// To ni ok, ker dovoljen prostor racuna glede na y, ki smo ga dobili od klika, in ne glede na y0 
					// (ne dela za y0)
					int y1 = y - (int)DEBELINA_CRTE * velikostBoxa * 2;
					int y2 = y + (int)DEBELINA_CRTE * velikostBoxa * 2;
					
					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						Smer s = Smer.DESNO;
						okno.klikniPolje(s, a, b);
					}
				}
			}
			for (int i = 0; i < Plosca.VISINA; i ++) {
				for (int j = 0; j < (Plosca.SIRINA + 1); j ++) {
					int y1 = (int) (j * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA + 2 * RADIJ_PIKE * velikostBoxa);
					int y2 = (int) (j * velikostBoxa + velikostBoxa + PRAZEN_PROSTOR_DO_ROBA - RADIJ_PIKE * velikostBoxa);
					int x0 = (int) (i * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA + (RADIJ_PIKE / 2) * velikostBoxa); 
					// Enako kot zgoraj
					int x1 = x - (int)DEBELINA_CRTE * velikostBoxa * 2;
					int x2 = x + (int)DEBELINA_CRTE * velikostBoxa * 2;
					
					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						Smer s = Smer.DOL;
						okno.klikniPolje(s, a, b);
					}
				}
			}
		}	
				
		// Zacasno izpisujemo, kje se je zgodil klik, da vemo, kaj se dogaja 
		System.out.print(a);
		System.out.print(b);
		// okno.klikniPolje(Smer.DOL, i, j);
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
