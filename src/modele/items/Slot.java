package modele.items;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Slot {

	private int capaMax;
	private IntegerProperty stock;
	private Items objet;
	private boolean libre;
	
	public Slot() {
		this.libre=true;
		this.stock = new SimpleIntegerProperty();
		this.capaMax = 15 ;
	}
	
	public void clear() {
		this.objet = null;
	}
	
	public Items getObjet() {
		if (this.objet != null) {
			return this.objet;
		}
		return null;
	}
	
	public void ajoutStock() {
		this.stock.set(this.stock.getValue().intValue() + 1);
	}
	
	public void enleveStock() {
		if(this.stock.getValue().intValue() > 0) {
			this.stock.set(this.stock.getValue().intValue() - 1);
		}
	}
	
	public int getCapacite() {
		return this.capaMax;
	}
	
	public void setItem(Items obj) {
		this.objet = obj;
		ajoutStock();
	}
	
	public int getStock() {
		return this.stock.getValue().intValue();
	}
	
	public IntegerProperty getStockroperty() {
		return this.stock;
	}
	
	public boolean rempli() {
		
		if (this.stock.getValue().intValue() == this.capaMax) {
			this.libre = false;
		}
		
		if (this.libre == false) {
			return true;
		}
		else return false;
	}
	
	public boolean possedeItem() {
		
		if (this.objet != null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public String toString() {
		return this.capaMax+" " + this.stock +"  "+ this.libre +" "+ this.objet;
	}
	
	
}

