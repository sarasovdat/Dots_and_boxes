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
import logika.Stanje;
import logika.Box;


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
	private final static double DEBELINA_CRTE = 0.2;
	
	/**
	 * Relativen prazen prostor od pik do roba polja
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
	
/*	*//**
	 * 
	 * @param s
	 * @return barva igralca, ki je na potezi
	 *//*
	public static Color barvaIgralcaNaPotezi(Stanje s) {
		if (s == Stanje.NA_POTEZI_RDEC) {
			Color c = Color.red;
		} else if (s == Stanje.NA_POTEZI_MODER) {
			Color c = Color.blue;
		}
		return c;
	}*/
	
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
	
	private void narisiCrto (Graphics2D g, int x1, int y1, int x2, int y2) {
		double velikostBoxa = velikostBoxa();
		g.setStroke(new BasicStroke((float)(velikostBoxa * DEBELINA_CRTE)));
		g.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * 
	 * @param g
	 * @param i
	 * @param j
	 * V box, ki se zapre, narise X ustrezne barve (odvisno kdo ga zapre)
	 */
	private void narisiX (Graphics2D g, int i, int j) {
		double velikostBoxa = velikostBoxa();
		double sirina = velikostBoxa * (1.0 - DEBELINA_CRTE - 2.0 * PRAZEN_PROSTOR_DO_ROBA);
		double x = velikostBoxa * (i + 0.5* DEBELINA_CRTE + PRAZEN_PROSTOR_DO_ROBA);
		double y = velikostBoxa * (j + 0.5* DEBELINA_CRTE + PRAZEN_PROSTOR_DO_ROBA);
		//g.setColor(arg0);
	}
	
	
	// # TODO funkcija narisi vodoravno in navpicno crto

	/**
	 * Izrisuje na zaslon
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g; 	
		double velikostBoxa = velikostBoxa();
		
		// PIKE
		for (int i = 0; i <= Plosca.SIRINA; i ++) {
			for (int j = 0; j <= Plosca.VISINA; j ++) {
				narisiPiko(g2, (int)(i * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA), (int)(j * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA));
			}
		}
		
		// CRTE
		
	}
	
	// #TODO
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int velikostBoxa = (int) velikostBoxa();	
		
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
