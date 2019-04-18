package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MyFirstJavaFX extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {

		//On crée le layout de base
		HBox hbox = new HBox();

		//On crée le layout qui contiendra les éléments de l'UI
		GridPane root = new GridPane();

		//On fait un peu de mise en page
		root.setPadding(new Insets(20));
		root.setHgap(25);
		root.setVgap(15);

		//On crée le premier label et on le met en tête du GridPane
		Label matriceA = new Label("Matrice A");
		//Ce label ce place sur la colonne 0, la ligne 0, et occupe 2 colonnes sur 1 ligne
		root.add(matriceA, 0, 0, 2, 1);

		//On crée les paramètres de la première matrice
		int n = 4;
		int m = 5;
		int rowA;
		int columnA;

		//On crée la première matrice
		for(rowA = 1; rowA < m; rowA++) {
			for(columnA = 0; columnA < n; columnA++) {
				TextField box = new TextField("0");
				root.add(box,columnA,rowA);
			}
		}

		//On crée le second label et on le met à la suite de la première matrice
		Label matriceB = new Label("Matrice B");
		//Ce label ce place sur la colonne 0, la ligne 5, et occupe 2 colonnes sur 1 ligne
		root.add(matriceB, 0, 5, 2, 1);

		//On crée les paramètres de la seconde matrice
		int o = 10;
		int rowB;
		int columnB;

		//On crée la seconde matrice
		for(rowB = 6; rowB < o; rowB++) {
			for(columnB = 0; columnB < n; columnB++) {
				TextField box = new TextField("0");
				root.add(box,columnB,rowB);
			}
		}

		//On crée le troisième label et on le met à la suite de la seconde matrice
		Label result = new Label("Résultat de matrice A + matrice B :");
		//Ce label ce place sur la colonne 0, la ligne 10, et occupe 3 colonnes sur 1 ligne
		root.add(result, 0, 10, 3, 1);

		//On crée le bouton de calcul et son action
		Button buttonResult = new Button();
		buttonResult.setText("Calculer");
		//Ce bouton ce place sur la colonne 3, la ligne 10, et occupe 1 colonne sur 1 ligne
		root.add(buttonResult, 3, 10, 1, 1);
		//Ce bouton occupera toute la largeur de la cellule où il est placé
		buttonResult.setMaxWidth(Double.MAX_VALUE);

		buttonResult.setOnAction( e -> {

			//On réinitialise le texte (en cas d'erreur sur une utilisation précédente)
			buttonResult.setText("Calculer");

			//On crée les paramètres pour le calcul
			int col;
			int row;

			//On calcule
			for (col = 0; col < 4; col++) {
				for (row = 1; row < 5; row++) {
					//Le try /catch évite les plantages si l'utilisateur n'entre pas un nombre entier
					try {
						//On récupère la valeur de la première cellule (méthode en fin de classe)
						int one = Integer.valueOf(((TextField)getNodeFromGridPane(root, col, row)).getText());
						//On récupère la valeur de la cellule 5 lignes plus bas (méthode en fin de classe)
						int two = Integer.valueOf(((TextField)getNodeFromGridPane(root, col, row + 5)).getText());
						//On les additionne
						int three = one + two;
						//On met à jour la valeur de la cellule 10 lignes plus bas
						((Label)getNodeFromGridPane(root, col, row + 10)).setText(String.valueOf(three));
					} catch (Exception exception) {
						//Si l'entrée n'est pas un nombre entier, on change le texte du bouton
						buttonResult.setText("Erreur");
					}
				}
			}


		});

		//On crée les paramètres de la matrice d'affichage des résultats
		int p = 15;
		int rowC;
		int columnC;

		//On crée la matrice d'affichage des résultats
		for(rowC = 11; rowC < p; rowC++) {
			for(columnC = 0; columnC < n; columnC++) {
				Label resultLabel = new Label("0");
				root.add(resultLabel,columnC,rowC);
			}
		}

		//On ajoute le GridPane dans la Hbox
		hbox.getChildren().addAll(root);

		//On place le layout de base sur la scène et la scène sur le stage (qu'on affiche)
		Scene scene = new Scene(hbox, 500, 750);

		primaryStage.setScene(scene);
		//On définit un titre
		primaryStage.setTitle("Addition des matrices A et B");
		primaryStage.show();

	};

	//Cette méthode permet de récupérer la valeur d'une cellule selon ses coordonnées
	private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
