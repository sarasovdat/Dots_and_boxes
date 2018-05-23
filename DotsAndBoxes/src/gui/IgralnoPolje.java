package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logika.Plosca;
import logika.Smer;
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
	
	/**
	 * Prazen prostor okoli X (X se narise v zapolnjen kvadratek)
	 */
	private final static double PROSTOR_OKOLI_X = 0.3;
	
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
	 * @param sir
	 * @param vis
	 * Narise piko
	 */
	private void narisiPiko (Graphics2D g, int sir, int vis) {
		double velikostBoxa = velikostBoxa();
		g.setColor(Color.gray);
		g.drawOval(sir, vis, (int)(RADIJ_PIKE * velikostBoxa), (int)(RADIJ_PIKE * velikostBoxa));
		g.fillOval(sir, vis, (int)(RADIJ_PIKE * velikostBoxa), (int)(RADIJ_PIKE * velikostBoxa));
	}
	
	/**
	 * 
	 * @param g
	 * @param sir
	 * @param vis
	 * @param c
	 */
	private void narisiVodoravno (Graphics2D g, int sir, int vis, Color c) {
		double velikostBoxa = velikostBoxa();
		g.setColor(c);
		g.setStroke(new BasicStroke((float)(velikostBoxa * DEBELINA_CRTE)));
		int x1 = (int) (sir + PRAZEN_PROSTOR_DO_ROBA + 2 * RADIJ_PIKE * velikostBoxa);
		int x2 = (int) (sir + velikostBoxa + PRAZEN_PROSTOR_DO_ROBA - RADIJ_PIKE * velikostBoxa);
		int y = (int) (vis + PRAZEN_PROSTOR_DO_ROBA + (RADIJ_PIKE / 2) * velikostBoxa); 
		g.drawLine(x1, y, x2, y);
	}
	
	/**
	 * 
	 * @param g
	 * @param sir
	 * @param vis
	 * @param c
	 */
	private void narisiNavpicno (Graphics2D g, int sir, int vis, Color c) {
		double velikostBoxa = velikostBoxa();
		g.setColor(c);
		g.setStroke(new BasicStroke((float)(velikostBoxa * DEBELINA_CRTE)));
		int y1 = (int) (vis + PRAZEN_PROSTOR_DO_ROBA + 2 * RADIJ_PIKE * velikostBoxa);
		int y2 = (int) (vis + velikostBoxa + PRAZEN_PROSTOR_DO_ROBA - RADIJ_PIKE * velikostBoxa);
		int x = (int) (sir + PRAZEN_PROSTOR_DO_ROBA + (RADIJ_PIKE / 2) * velikostBoxa); 
		g.drawLine(x, y1, x, y2);
	}
	
	/**
	 * 
	 * @param g
	 * @param sir
	 * @param vis
	 * V box, ki se zapre, narise X ustrezne barve (odvisno kdo ga zapre)
	 */
	private void narisiX (Graphics2D g, int sir, int vis, Color c) {
		double velikostBoxa = velikostBoxa();
		int sirina = (int)(velikostBoxa * (1.0 - DEBELINA_CRTE - 2.0 * PROSTOR_OKOLI_X));
		int x = (int)((sir + velikostBoxa * (0.5 * DEBELINA_CRTE + PROSTOR_OKOLI_X)) + PRAZEN_PROSTOR_DO_ROBA + 5);
		int y = (int)((vis + velikostBoxa * (0.5 * DEBELINA_CRTE + PROSTOR_OKOLI_X)) + PRAZEN_PROSTOR_DO_ROBA + 5);
		g.setColor(c);
		g.setStroke(new BasicStroke((float)(velikostBoxa * DEBELINA_CRTE / 1.5)));
		g.drawLine(x, y, (x + sirina), (y + sirina));
		g.drawLine((x + sirina), y, x, (y + sirina));
	}
	
	
	/**
	 * Rise na igralno polje
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g; 	
		double velikostBoxa = velikostBoxa();
		Plosca plosca = okno.getPlosca();
		
		// PIKE
		for (int vis = 0; vis <= Plosca.VISINA; vis ++) {
			for (int sir = 0; sir <= Plosca.SIRINA; sir ++) {
				narisiPiko(g2, (int)(sir * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA), 
						(int)(vis * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA));
			}
		}

		// CRTE
		
		// Vodoravne crte
		Crta [][] vodoravneCrte = plosca.getVodoravneCrte();
		for (int vis = 0; vis < (Plosca.VISINA + 1); vis ++) {
			for (int sir = 0; sir < Plosca.SIRINA; sir ++) {
				switch(vodoravneCrte[vis][sir]){
				case RDEC: narisiVodoravno(g2, sir * (int)velikostBoxa, vis * (int)velikostBoxa, Color.RED); break;
				case MODER: narisiVodoravno(g2, sir * (int)velikostBoxa, vis * (int)velikostBoxa, Color.BLUE); break;
				default: break;
				}
			}
		}

		// Navpicne crte
		Crta [][] navpicneCrte = plosca.getNavpicneCrte();
		for (int vis = 0; vis < Plosca.VISINA; vis ++) {
			for (int sir = 0; sir < (Plosca.SIRINA + 1); sir ++) {
				switch (navpicneCrte[vis][sir]) {
				case RDEC: narisiNavpicno(g2, sir * (int)velikostBoxa, vis * (int)velikostBoxa, Color.RED); break;
				case MODER: narisiNavpicno(g2, sir * (int)velikostBoxa, vis * (int)velikostBoxa, Color.BLUE); break;
				default: break;
				}
			}
		}


		// BOXI - X
		Box [][] box = plosca.getPolje();
		for (int vis = 0; vis < Plosca.VISINA; vis ++) {
			for (int sir = 0; sir < Plosca.SIRINA; sir ++) {
				switch (box[vis][sir]) {
				case RDEC: narisiX(g2, sir * (int)velikostBoxa, vis * (int)velikostBoxa, Color.RED); break;
				case MODER: narisiX(g2, sir * (int)velikostBoxa, vis * (int)velikostBoxa, Color.BLUE); break;
				//case PRAZNO: narisiX(g2, sir * (int)velikostBoxa, vis * (int)velikostBoxa, Color.BLACK); break;
				default: break;
				}	
			}
		}	
	}
	
	// Na koncu naj poklice klikni.Polje(smer, vis, sir)
	// (x, y) -> (a, b) -> klikni.Polje(smer, b, a) 
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int velikostBoxa = (int) velikostBoxa();	
		
		// KOORDINATE KLIKA (a, b) -> a po sirini, b po visini
		int a = Math.min((x - (int)PRAZEN_PROSTOR_DO_ROBA) / (velikostBoxa), Plosca.SIRINA);
		int b = Math.min((y  - (int)PRAZEN_PROSTOR_DO_ROBA) / (velikostBoxa), Plosca.VISINA);
		
		// Dovoljeni kliki
		if ((x < velikostBoxa * Plosca.SIRINA + 2 * (int)PRAZEN_PROSTOR_DO_ROBA &&
				y < velikostBoxa * Plosca.VISINA + 2 * (int)PRAZEN_PROSTOR_DO_ROBA)){
		
			// VODORAVNE CRTE
			for (int vis = 0; vis < (Plosca.VISINA + 1); vis ++) {
				for (int sir = 0; sir < (Plosca.SIRINA + 1); sir ++) {
					int x1 = (int) (sir * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA + 2 * RADIJ_PIKE * velikostBoxa);
					int x2 = (int) ((sir + 1) * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA - RADIJ_PIKE * velikostBoxa);
					int y0 = (int) (vis * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA + (RADIJ_PIKE / 2) * velikostBoxa); 
					// To ni ok, ker dovoljen prostor racuna glede na y, ki smo ga dobili od klika, in ne glede na y0 
					// (ne dela za y0)
					
					int y1 = y0 - (int)(DEBELINA_CRTE * velikostBoxa * 1);
					int y2 = y0 + (int)(DEBELINA_CRTE * velikostBoxa * 1);
					
					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						Smer s = Smer.DESNO;
						okno.klikniPolje(s, b, a);
					}
				}
			}
			
			// NAVPICNE CRTE
			for (int vis = 0; vis < Plosca.VISINA; vis ++) {
				for (int sir = 0; sir < (Plosca.SIRINA + 1); sir ++) {
					int y1 = (int) (vis * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA + 2 * RADIJ_PIKE * velikostBoxa);
					int y2 = (int) (vis * velikostBoxa + velikostBoxa + PRAZEN_PROSTOR_DO_ROBA - RADIJ_PIKE * velikostBoxa);
					int x0 = (int) (sir * velikostBoxa + PRAZEN_PROSTOR_DO_ROBA + (RADIJ_PIKE / 2) * velikostBoxa); 
					// Enako kot zgoraj
					int x1 = x0 - (int)(DEBELINA_CRTE * velikostBoxa * 1) - (int)(RADIJ_PIKE * velikostBoxa);
					int x2 = x0 + (int)(DEBELINA_CRTE * velikostBoxa * 1) + (int)(RADIJ_PIKE + velikostBoxa);
					
					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						Smer s = Smer.DOL;
						okno.klikniPolje(s, b, a);
					}
				}
			}
		}	
		
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
