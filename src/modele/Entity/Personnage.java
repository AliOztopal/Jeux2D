package modele.Entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import modele.items.Inventaire;
import modele.items.Items;
import modele.items.Slot;

public class Personnage extends Entity {

	private Inventaire sac;
	private Slot main;
	
	private IntegerProperty x;
	private IntegerProperty y;
	private int deplacementy;
	private int deplacementx;

	private int rangeMax;
	
	
	private int hSaut;
	private int jump;
	private boolean saute;
	
	public Personnage(int x, int y) {
		super(200, 20, 15);
		this.x = new SimpleIntegerProperty();
		this.y = new SimpleIntegerProperty();
		this.sac= new Inventaire(8);
		this.deplacementx = 12;
		this.deplacementy = 12;
		this.hSaut = 64;
		this.jump = 1;
		this.rangeMax=2;
		this.saute=false;
		this.main = new Slot();
	}

	public boolean getSaute() {
		return this.saute;
	}
	
	public IntegerProperty getXProperty() {
		return this.x;
	}

	public int getX() {
		return this.x.get();
	}

	public void setXProperty(int x) {
		this.x.set(x);
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
	
	public int getRangeMax() {
		return this.rangeMax; 
	}

	public Slot getMain() {
		return this.main;
	}
	
	public void setMain(Items obj) {
		this.main.setItem(obj);
	}
	
	public Inventaire getInventaire() {
		return this.sac;
	}
	
	
	
	public void deplacerGauche() {
		this.setXProperty(-deplacementx + this.getX());
	}

	public void deplacerDroite() {
		this.setXProperty(this.deplacementx + this.getX());
	}

	public void deplacerBas() {

		this.setYProperty(this.deplacementy + this.getY());
	}

	
	public void jump() {

		saute=true;
 

		if (jump != 0) {
			if (jump == 2) {
				this.setYProperty(-this.hSaut + this.getY());
				
				jump++;
			}
			if (jump == 4) {
				this.setYProperty(-this.hSaut /2 + this.getY());
				
				jump++;
			}
			if (jump == 6) {
				this.setYProperty(-this.hSaut /4 + this.getY());
				saute=false;
				jump=0;
			}
			jump++;
			
		}
	}
	
	//Lors du cassage de bloc l'item sera ajout� dans l'inventaire 
	public void addInv(Items b) {
		Slot slotcourant = sac.verifSlot(b);
		
		//Le bloc existe d�j� dans l'inventaire
		if (slotcourant != null && slotcourant.getStock() < slotcourant.getCapacite()) {
			slotcourant.ajoutStock();
		}else {
			//Le Block n'existe pas
			sac.SlotLibre().setItem(b);
		}
	}
	

	
}
