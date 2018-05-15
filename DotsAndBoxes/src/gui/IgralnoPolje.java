package gui;

import java.awt.Color;
import java.awt.Dimension;
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
	private final static double PRAZEN_PROSTOR_DO_ROBA = 0.1;
	
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
	 * @param s
	 * @return barva igralca, ki je na potezi
	 */
	public static Color barvaIgralcaNaPotezi(Stanje s) {
		if (s == Stanje.NA_POTEZI_RDEC) {
			Color c = Color.red;
		} else if (s == Stanje.NA_POTEZI_MODER) {
			Color c = Color.blue;
		}
		return c;
	}
	
	/**
	 * 
	 * @return relativna velikost boxa
	 */
	private double velikostBoxa() {
		return Math.min(getWidth(), getHeight()) / Math.min(Plosca.VISINA, Plosca.SIRINA);
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
		g.drawOval(i, j, (int)(RADIJ_PIKE * velikostBoxa), (int)(RADIJ_PIKE * velikostBoxa));
		g.fillOval(i, j, (int)(RADIJ_PIKE * velikostBoxa), (int)(RADIJ_PIKE * velikostBoxa));
		g.setColor(Color.gray);
	}
	
	private void narisiVodoravno (Graphics2D g, int i, int j) {	
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



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
