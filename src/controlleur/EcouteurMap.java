package controlleur;


import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import modele.Blocks.Blocks;
import modele.Jeu.Jeu;

public class EcouteurMap implements ListChangeListener<Blocks> {

	private TilePane terrain;
	private Jeu jeu;

	public EcouteurMap(TilePane terrain, Jeu jeu) {
		this.terrain = terrain;
		this.jeu=jeu;
	}

	@Override
	public void onChanged(Change<? extends Blocks> e) {

		while (e.next()) {

			if(e.wasReplaced()) {

				if (jeu.getMap().getListe().get(e.getFrom()).getId() == 0) {
					terrain.getChildren().set(e.getFrom(), new ImageView("file:src/ImagesProjet/Map_Texture/wind.png"));
				}
				else if (jeu.getMap().getListe().get(e.getFrom()).getId() == 1) {
					terrain.getChildren().set(e.getFrom(), new ImageView("file:src/ImagesProjet/Map_Texture/grass.png"));

				}else if (jeu.getMap().getListe().get(e.getFrom()).getId() == 2) {
					terrain.getChildren().set(e.getFrom(), new ImageView("file:src/ImagesProjet/Map_Texture/dirt.png"));
				}else {
					terrain.getChildren().set(e.getFrom(), new ImageView("file:src/ImagesProjet/Map_Texture/stone.png"));
				}


			}

			/*if (e.wasRemoved()) {
				for (int i = 0; i < e.getRemoved().size(); i++) {
					//terrain.getChildren().set(e.getFrom(), new ImageView("file:src/ImagesProjet/Map_Texture/wind.png"));
					System.out.println(e.getRemoved().get(i).getId());
					if(e.getRemoved().get(i).getId() != 0) {
						jeu.getPerso().getInventaire().AddSlot(1);
					}
				}		

			}/* else if (e.wasAdded()) {

				for (int i = 0; i < e.getAddedSubList().size(); i++) {
					if (e.getAddedSubList().get(i).getId() == 1) {
						terrain.getChildren().set(e.getFrom(),new ImageView("file:src/ImagesProjet/Map_Texture/grass.png"));

					}

					else if (e.getAddedSubList().get(i).getId() == 2) {
						terrain.getChildren().set(e.getFrom(),new ImageView("file:src/ImagesProjet/Map_Texture/dirt.png"));

					} else {

						terrain.getChildren().set(e.getFrom(),new ImageView("file:src/ImagesProjet/Map_Texture/stone.png"));

					}

				}

			}*/

		}

	}



}