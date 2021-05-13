package modele.Entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Loup extends Entity {

	private IntegerProperty x;
	private IntegerProperty y;
	private int deplacementy;
	private int deplacementx;
	private boolean saute;
	private int jump;
	private int hSaut;

	public Loup(int x, int y) {
		super(50, 10, 5);
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.jump = 1;
		this.deplacementx = 12;
		this.deplacementy = 12;
		this.setSaute(false);
		this.hSaut = 64;
	}
	

	public boolean isSaute() {
		return saute;
	}

	public void setSaute(boolean saute) {
		this.saute = saute;
	}


	public IntegerProperty getXProperty() {
		return this.x;
	}

	public int getX() {
		return this.x.get();
	}

	public IntegerProperty getYProperty() {
		return this.y;
	}

	public int getY() {
		return this.y.get();
	}

	public void setYProperty(int y) {
		this.y.set(y);
	}

	public void setXProperty(int x) {
		this.x.set(x);
	}
	
	
	
	public void deplacerGauche() {
		this.setXProperty(-deplacementx + this.getX());
	}

	public  void deplacerDroite() {
		this.setXProperty(this.deplacementx + this.getX());

	}

	public void deplacerBas() {

		this.setYProperty(this.deplacementy + this.getY());
	}

	public void jump() {

		setSaute(true);


		if (jump != 0) {
			if (jump == 2) {
				this.setYProperty(-this.hSaut  + this.getY());

				jump++;
			}
			if (jump == 4) {
				this.setYProperty(-this.hSaut / 2 + this.getY());

				jump++;
			}
			if (jump == 6) {
				this.setYProperty(-this.hSaut / 4 + this.getY());
				setSaute(false);
				jump=0;
			}
			jump++;

		}
	}

	public void deplacerLoup(Personnage perso) {

		if (this.getY() < (perso.getY()+32) && this.getY() > (perso.getY()-32)) {
			if(this.getX() < perso.getX()) {
				deplacerDroite();
			}
			else if (this.getX() > perso.getX()) {
				deplacerGauche();
			}
		} 	
	}




}
