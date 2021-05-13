package modele.Entity;

import javafx.beans.property.IntegerProperty;

public abstract class Entity {


	private int pv;
	private int attaque;
	private int defense;

	public Entity(int pv, int att, int def) {
		this.pv = pv;
		this.attaque = att;
		this.defense = def;
	}

	public int getPV() {
		return this.pv;
	}

	public int getAttaque() {
		return this.attaque;
	}

	public int getDefense() {
		return this.defense;
	}
	
	public abstract IntegerProperty getXProperty();
	
	public abstract IntegerProperty getYProperty();
	
	public abstract void setXProperty(int x);
	
	public abstract void setYProperty(int y);
	
	public abstract  int getY();
	
	public abstract int getX();
	
	public abstract void deplacerGauche();

	public abstract void deplacerDroite();
	
	public abstract void deplacerBas();	
	
	public abstract void jump();
	
	public String toString() {
		return "x " + this.getX() + "y " + this.getY();
	}


}


