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

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		HBox hbox = new HBox();

		GridPane root = new GridPane();

		root.setPadding(new Insets(20));
		root.setHgap(25);
		root.setVgap(15);

		Label matriceA = new Label("Matrice A");

		// Put on cell (0,0), span 2 column, 1 row.
		root.add(matriceA, 0, 0, 2, 1);

		//Create the integers for creating the grid.
		int n = 4;
		int m = 5;
		int rowA;
		int columnA;

		for(rowA = 1; rowA < m; rowA++) {
			for(columnA = 0; columnA < n; columnA++) {
				TextField box = new TextField("0");
				root.add(box,columnA,rowA);
			}
		}

		Label matriceB = new Label("Matrice B");

		// Put on cell (0,5), span 2 column, 1 row.
		root.add(matriceB, 0, 5, 2, 1);

		//Create the integers for creating the grid.
		int o = 10;
		int rowB;
		int columnB;

		for(rowB = 6; rowB < o; rowB++) {
			for(columnB = 0; columnB < n; columnB++) {
				TextField box = new TextField("0");
				root.add(box,columnB,rowB);
			}
		}

		Label result = new Label("Résultat de matrice A + matrice B :");

		// Put on label (0,10), span 2 column, 1 row.
		root.add(result, 0, 10, 3, 1);

		Button buttonResult = new Button();
		buttonResult.setText("Calculer");
		buttonResult.setMaxWidth(Double.MAX_VALUE);
		buttonResult.setOnAction( e -> {
			
			buttonResult.setText("Calculer");

			int col;
			int row;

			for (col = 0; col < 4; col++) {
				for (row = 1; row < 5; row++) {
					try {
					int one = Integer.valueOf(((TextField)getNodeFromGridPane(root, col, row)).getText());
					int two = Integer.valueOf(((TextField)getNodeFromGridPane(root, col, row + 5)).getText());
					int three = one + two;
					((Label)getNodeFromGridPane(root, col, row + 10)).setText(String.valueOf(three));
					} catch (Exception exception) {
						buttonResult.setText("Erreur");
					}
				}
			}


		});

		// Put on button (3,10), span 1 column, 1 row.
		root.add(buttonResult, 3, 10, 1, 1);

		//Create the integers for creating the grid.
		int p = 15;
		int rowC;
		int columnC;

		for(rowC = 11; rowC < p; rowC++) {
			for(columnC = 0; columnC < n; columnC++) {
				Label resultLabel = new Label("0");
				root.add(resultLabel,columnC,rowC);
			}
		}

		hbox.getChildren().addAll(root);

		Scene scene = new Scene(hbox, 500, 750);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Addition des matrices A et B");
		primaryStage.show();

	};

	private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}

}
