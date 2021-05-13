package modele.Jeu;


import modele.Blocks.Carte;
import modele.Entity.Loup;
import modele.Entity.Personnage;

public class Jeu {

	private Personnage perso;
	private Carte map;
	private Loup wolf;

	public Jeu() {
		this.perso = new Personnage(300, 900);
		this.map = new Carte();
		this.wolf = new Loup(0,0);
	}

	
	
	
	public Carte getMap() {
		return this.map;
	}

	public Personnage getPerso() {
		return this.perso;
	}
	public Loup getLoup() {
		return this.wolf;
	}


	public Personnage positionSuivantePerso(Personnage perso) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < map.getListe().size(); i++) {
			x += 32;
			if (y * 100 + x == 100)
				y++;

			perso = new Personnage(x, y);

		}
		return perso;

	}

	public boolean checkCollision (int x, int y) {
		if(map.getListe().get(map.Indice(perso.getX()+x, perso.getY()+y)).getTraversable()) 
			return true;
	
		else 
			return false;
		
	}
	
	public boolean checkCollisionEnnemy(int x, int y) {
		if(map.getListe().get(map.Indice(wolf.getX()+x, wolf.getY()+y)).getTraversable()) {
			return true;
		}else { 
			return false;
		}
	}
		
		
		
		
		
		
		
}
