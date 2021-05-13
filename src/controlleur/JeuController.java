package controlleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modele.Blocks.Blocks;
import javafx.util.Duration;
import modele.Jeu.Jeu;


public class JeuController implements Initializable {

	@FXML
	private HBox main;
	@FXML
	private ImageView Img_SlotMain;
	@FXML
	private ImageView ennemy;


	// 1er Slot
	@FXML
	private VBox slot1;
	@FXML
	private Label Txt_Slot1;
	@FXML
	private ImageView Img_Slot1;

	// 2eme slot
	@FXML
	private VBox slot2;
	@FXML
	private Label Txt_Slot2;
	@FXML
	private ImageView Img_Slot2;

	// 3eme slot
	@FXML
	private VBox slot3;
	@FXML
	private Label Txt_Slot3;
	@FXML
	private ImageView Img_Slot3;

	// 4eme slot
	@FXML
	private VBox slot4;
	@FXML
	private Label Txt_Slot4;
	@FXML
	private ImageView Img_Slot4;

	// 5eme slot
	@FXML
	private VBox slot5;
	@FXML
	private Label Txt_Slot5;
	@FXML
	private ImageView Img_Slot5;

	// 6eme slot
	@FXML
	private VBox slot6;
	@FXML
	private Label Txt_Slot6;
	@FXML
	private ImageView Img_Slot6;

	// 7eme slot
	@FXML
	private VBox slot7;
	@FXML
	private Label Txt_Slot7;
	@FXML
	private ImageView Img_Slot7;

	// 8eme slot
	@FXML
	private VBox slot8;
	@FXML
	private Label Txt_Slot8;
	@FXML
	private ImageView Img_Slot8;

	private Jeu jeu;

	@FXML
	private ImageView perso;

	@FXML
	private TilePane terrain;

	@FXML
	private Pane fond;

	@FXML
	private Timeline gameLoop;

	private int temps;

	@FXML
	private KeyEvent key;

	private int slotSelected = -1;



	/***
	 * Methode qui initialise tout l'affichage.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.jeu = new Jeu();

		perso = new ImageView("file:src/ImagesProjet/Cloud_perso/Cloud_MV2_Droite.png");
		fond.getChildren().add(perso);

		ennemy = new ImageView("file:src/ImagesProjet/Ressources/wolfy.png");
		fond.getChildren().add(ennemy);

		// Listener sur la liste observable de blocs
		jeu.getMap().getListe().addListener(new EcouteurMap(terrain, jeu));

		

		// Bind de déplacement
		perso.translateXProperty().bind(jeu.getPerso().getXProperty());
		perso.translateYProperty().bind(jeu.getPerso().getYProperty());


		ennemy.translateXProperty().bind(jeu.getLoup().getXProperty());
		ennemy.translateYProperty().bind(jeu.getLoup().getYProperty());

		/**
		 * Changement des stocks des slots
		 */
		Txt_Slot1.textProperty().bind(jeu.getPerso().getInventaire().getSlot(0).getStockroperty().asString());
		Txt_Slot2.textProperty().bind(jeu.getPerso().getInventaire().getSlot(1).getStockroperty().asString());
		Txt_Slot3.textProperty().bind(jeu.getPerso().getInventaire().getSlot(2).getStockroperty().asString());
		Txt_Slot4.textProperty().bind(jeu.getPerso().getInventaire().getSlot(3).getStockroperty().asString());
		Txt_Slot5.textProperty().bind(jeu.getPerso().getInventaire().getSlot(4).getStockroperty().asString());
		Txt_Slot6.textProperty().bind(jeu.getPerso().getInventaire().getSlot(5).getStockroperty().asString());
		Txt_Slot7.textProperty().bind(jeu.getPerso().getInventaire().getSlot(6).getStockroperty().asString());
		Txt_Slot8.textProperty().bind(jeu.getPerso().getInventaire().getSlot(7).getStockroperty().asString());

		fond.setOnKeyPressed(e -> Deplacer(e));
		terrain.setOnMouseClicked(e -> actionOnClic(e));
		// Game loop
		initLoop();
		gameLoop.play();

		// Creation de la map
		// Lecture de la map
		// Affichage de la map

		jeu.getMap().lectureFichier();
		afficherMap();

		// Taille de la map

		terrain.setPrefWidth(100 * 32);
		terrain.setPrefHeight(33 * 32);

		// Evenement souris & clavier

	}

	/***
	 * Methode qui permet l'affichage de la map
	 */
	public void afficherMap() {

		for (int i = 0; i < jeu.getMap().getLargeur() * jeu.getMap().getHauteur(); i++) {

			if (jeu.getMap().getListe().get(i).getId() == 0) {
				ImageView img = new ImageView("file:src/ImagesProjet/Map_Texture/wind.png");
				terrain.getChildren().add(img);
			}
			if (jeu.getMap().getListe().get(i).getId() == 1) {
				ImageView img = new ImageView("file:src/ImagesProjet/Map_Texture/grass.png");
				terrain.getChildren().add(img);
			}
			if (jeu.getMap().getListe().get(i).getId() == 2) {

				ImageView img = new ImageView("file:src/ImagesProjet/Map_Texture/dirt.png");
				terrain.getChildren().add(img);
			}

			if (jeu.getMap().getListe().get(i).getId() == 3) {

				ImageView img = new ImageView("file:src/ImagesProjet/Map_Texture/stone.png");
				terrain.getChildren().add(img);
			}
		}

	}

	/***
	 * Methode qui permet au joueur de se deplacer
	 */
	public void Deplacer(KeyEvent event) {

		switch (event.getCode()) {

		case Q:
			if (jeu.checkCollision(-24, 0) == true) {
				if (jeu.checkCollision(-24, -32)) {
					perso.setImage(new Image("file:src/ImagesProjet/Cloud_perso/Cloud_MV2_Gauche.png"));
					jeu.getPerso().deplacerGauche();

				}
			}

			break;
		case D:
			if (jeu.checkCollision(24, 0) == true) {
				if (jeu.checkCollision(24, -32)) {
					perso.setImage(new Image("file:src/ImagesProjet/Cloud_perso/Cloud_MV2_Droite.png"));
					jeu.getPerso().deplacerDroite();

				}
			}

			break;
		case SPACE:
			if (jeu.checkCollision(0, -50) == true) {
				if (jeu.checkCollision(0, 33) == false) {
					jeu.getPerso().jump();
				}
			}
			break;

		default:
			break;

		}

	}


	/***
	 * Methode qui gere le choix du slot
	 */
	@FXML
	void ChoixMain(MouseEvent event) {
	}

	@FXML
	void ChoixSlot1(MouseEvent event) {
		ChangeSlot(0);
	}

	@FXML
	void ChoixSlot2(MouseEvent event) {
		ChangeSlot(1);
	}

	@FXML
	void ChoixSlot3(MouseEvent event) {
		ChangeSlot(2);
	}

	@FXML
	void ChoixSlot4(MouseEvent event) {
		ChangeSlot(3);
	}

	@FXML
	void ChoixSlot5(MouseEvent event) {
		ChangeSlot(4);
	}

	@FXML
	void ChoixSlot6(MouseEvent event) {
		ChangeSlot(5);
	}

	@FXML
	void ChoixSlot7(MouseEvent event) {
		ChangeSlot(6);
	}

	@FXML
	void ChoixSlot8(MouseEvent event) {
		ChangeSlot(7);
	}

	public void ChangeSlot(int idSlot) {
		slotSelected = idSlot;
		if (jeu.getPerso().getInventaire().getListe().get(idSlot).possedeItem() == true) {
			jeu.getPerso().setMain(jeu.getPerso().getInventaire().getListe().get(idSlot).getObjet());
			ChangeSlotTexture(Img_SlotMain, jeu.getPerso().getInventaire().getListe().get(idSlot).getObjet().getId());
		}
	}


	/***
	 * Methode qui permet le changement de texture pour chaque block
	 */
	public void ChangeSlotTexture(ImageView img, int idBlock) {
		switch (idBlock) {
		case 0: {
			img.setImage(new Image("file:src/ImagesProjet/Map_Texture/wind.png"));
			break;
		}
		case 1: {
			img.setImage(new Image("file:src/ImagesProjet/Map_Texture/grass.png"));
			break;
		}
		case 2: {
			img.setImage(new Image("file:src/ImagesProjet/Map_Texture/dirt.png"));
			break;
		}
		case 3: {
			img.setImage(new Image("file:src/ImagesProjet/Map_Texture/stone.png"));
			break;
		}
		default:
			break;
		}
	}


	/***
	 * Methode qui permet au joueur de choisir un slot
	 */
	public void ChoixInventaire() {
		slot1.setOnMouseClicked(e -> ChoixSlot1(e));
		slot2.setOnMouseClicked(e -> ChoixSlot2(e));
		slot3.setOnMouseClicked(e -> ChoixSlot3(e));
		slot4.setOnMouseClicked(e -> ChoixSlot4(e));
		slot5.setOnMouseClicked(e -> ChoixSlot5(e));
		slot6.setOnMouseClicked(e -> ChoixSlot6(e));
		slot7.setOnMouseClicked(e -> ChoixSlot7(e));
		slot8.setOnMouseClicked(e -> ChoixSlot8(e));
	}


	/***
	 * Methode qui permet au joueur de poser un block
	 * 
	 * @param x  Coordonnee x du block pose
	 * @param y  Coordonnee y du block pose
	 * @param XJ Coordonnee x du joueur
	 * @param YJ Coordonnee x du joueur
	 */

	public void PoserBlock(int x, int y, int XJ, int YJ) {

		if (jeu.getPerso().getMain().getObjet() != null) {
			if (jeu.getPerso().getMain().getObjet().getId() <= 3) {

				// Verification de la distance du block pos�
				if (XJ - 2 <= x / 32 && x / 32 <= XJ + 3) {
					if (YJ - 2 <= y / 32 && y / 32 <= YJ + 3) {

						if (jeu.getPerso().getInventaire().getSlot(slotSelected).getStock() == 0) {

							ChangeSlotTexture(Img_SlotMain,0);
							switch (slotSelected) {
							case 0:
								ChangeSlotTexture(Img_Slot1,0);
								break;
							case 1:
								ChangeSlotTexture(Img_Slot2,0);
								break;
							case 2:
								ChangeSlotTexture(Img_Slot3,0);
								break;
							case 3:
								ChangeSlotTexture(Img_Slot4,0);
								break;
							case 4:
								ChangeSlotTexture(Img_Slot5,0);
								break;
							case 5:
								ChangeSlotTexture(Img_Slot6,0);
								break;
							case 6:
								ChangeSlotTexture(Img_Slot7,0);
								break;
							case 7:
								ChangeSlotTexture(Img_Slot8,0);
								break;
							default:
								break;
							}
							jeu.getPerso().getInventaire().getSlot(slotSelected).clear();
							jeu.getPerso().getMain().clear();
						} else {
							jeu.getMap().ajouterBlock(x, y, jeu.getPerso().getMain().getObjet().getId(),
									jeu.getPerso().getInventaire().getSlot(slotSelected));
						}

					}
				}
			}
		} else {
			destructionBloc(x, y);
		}
	}


	/***
	 * Methode qui gere la les clic
	 */
	public void actionOnClic(MouseEvent e) {

		int XJ, YJ;

		XJ = jeu.getPerso().getX() / 32;
		YJ = jeu.getPerso().getY() / 32;
		int x, y;

		x = (int) e.getX();
		y = (int) e.getY();

		if (e.getButton() == MouseButton.PRIMARY) {
			PoserBlock(x, y, XJ, YJ);
		}
		if (e.getButton() == MouseButton.SECONDARY) {
			if (jeu.getPerso().getMain().possedeItem() == true) {
				Img_SlotMain.setImage(new Image("file:src/ImagesProjet/Map_Texture/wind.png"));
				jeu.getPerso().getMain().clear();
			}
		}
	}

	/***
	 * Methode qui permet la destruction des block dans la map et l'incremente dans l'inventaire
	 * @param x  Coordonnee x du block cliquer voulant etre detruit
	 * @param y  Coordonnee y du block cliquer voulant etre detruit
	 */
	public void destructionBloc(int x, int y) {
		int XJ, YJ;

		XJ = jeu.getPerso().getX() / 32;
		YJ = jeu.getPerso().getY() / 32;

		if (XJ - 2 <= x / 32 && x / 32 <= XJ + 3) {
			if (YJ - 2 <= y / 32 && y / 32 <= YJ + 3) {
				Blocks b = jeu.getMap().supprimerBlock(x, y);

				if (jeu.getMap().recupIdBlock(b).dropable()) {

					jeu.getPerso().addInv(b);

					/**
					 * Changement des images des slots de l'inventaire
					 */
					for (int i = 0; i < jeu.getPerso().getInventaire().getListe().size(); i++) {
						if (jeu.getPerso().getInventaire().getListe().get(i).getObjet() != null) {
							switch (i) {
							case 0: {
								ChangeSlotTexture(Img_Slot1,
										jeu.getPerso().getInventaire().getListe().get(i).getObjet().getId());
								break;
							}

							case 1: {
								ChangeSlotTexture(Img_Slot2,
										jeu.getPerso().getInventaire().getListe().get(i).getObjet().getId());
								break;
							}

							case 2: {
								ChangeSlotTexture(Img_Slot3,
										jeu.getPerso().getInventaire().getListe().get(i).getObjet().getId());
								break;
							}
							case 3: {
								ChangeSlotTexture(Img_Slot4,
										jeu.getPerso().getInventaire().getListe().get(i).getObjet().getId());
								break;
							}
							case 4: {
								ChangeSlotTexture(Img_Slot5,
										jeu.getPerso().getInventaire().getListe().get(i).getObjet().getId());
								break;
							}
							case 5: {
								ChangeSlotTexture(Img_Slot6,
										jeu.getPerso().getInventaire().getListe().get(i).getObjet().getId());
								break;
							}
							case 6: {
								ChangeSlotTexture(Img_Slot7,
										jeu.getPerso().getInventaire().getListe().get(i).getObjet().getId());
								break;
							}
							case 7: {
								ChangeSlotTexture(Img_Slot8,
										jeu.getPerso().getInventaire().getListe().get(i).getObjet().getId());
								break;
							}
							default: {
								break;
							}
							}
						}
					}
					// Doit écouter la liste (listener)
				}
			}

		}
	}

	/***
	 * Boucle du jeu
	 */
	public void initLoop() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(
				// on définit le FPS (nbre de frame par seconde)
				Duration.seconds(0.017),
				// on définit ce qui se passe à chaque frame
				// c'est un eventHandler d'ou le lambda
				(ev -> {
					if (temps % 2 == 0) {
						if (jeu.checkCollision(0, 33) == true) {
							jeu.getPerso().deplacerBas();
						}

						if (jeu.getPerso().getSaute()) {
							jeu.getPerso().jump();
						}
						if (jeu.checkCollisionEnnemy(0, 33) == true && jeu.checkCollisionEnnemy(-32, 33) == true && jeu.checkCollisionEnnemy(32, 33) == true) {
							jeu.getLoup().deplacerBas();

						}
						if (jeu.checkCollisionEnnemy(-32, 0) == false) {
								jeu.getLoup().deplacerLoup(jeu.getPerso());
								jeu.getLoup().jump();
						}
						
						if (jeu.checkCollisionEnnemy(64, 0) == false) {
								jeu.getLoup().deplacerLoup(jeu.getPerso());
								jeu.getLoup().jump();
						}
						jeu.getLoup().deplacerLoup(jeu.getPerso());


					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);

	}

}
