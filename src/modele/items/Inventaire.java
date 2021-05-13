package modele.items;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventaire {

	private ObservableList<Slot> inventory = FXCollections.observableArrayList();

	private int nombreSlot;

	public Inventaire(int nombreSlot) {
		this.nombreSlot = nombreSlot;
		for (int i = 0; i < nombreSlot; i++) {
			inventory.add(new Slot());
		}

	}
	
	public void AddSlot(int IdSlot) {
		this.inventory.get(IdSlot).ajoutStock();
	}

	
	public Slot getSlot(int IdSlot) {
		return this.inventory.get(IdSlot);
	}
	
	public ObservableList<Slot> getListe() {
		return this.inventory;
	}

	public Slot SlotLibre() {
		for (int i = 0; i < nombreSlot; i++) {
			if (inventory.get(i).possedeItem() == false) {
				return inventory.get(i);
			}

		}
		return null;
	}

	// Verifie si l'item passer en param�tre existe d�j� dans l'inventaire et nous
	// renvoie le slot.
	public Slot verifSlot(Items b) {
		Slot retour=null;
		for (int i = 0; i < nombreSlot; i++) {
			if(inventory.get(i).getObjet() != null) {
				if (inventory.get(i).getObjet().getId() == b.getId()) {
					retour = inventory.get(i);
				}
			}
			
			
			
		}
		return retour;
	}

}