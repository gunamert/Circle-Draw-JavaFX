//Mert Gunay 
//08.04.2017
//Program read the .txt file then find the max distance between circles center and show it with javafx
package mert;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Path;
import java.util.Scanner;

public class Mert_Gunay extends Application {

	public static double[][] readCircles(String filename) throws Exception {
		java.io.File file = new java.io.File(filename); // read the .txt file
		Scanner input = new Scanner(file);

		double[][] array = new double[1000][3];
		int i = 0;
		while (input.hasNext()) {
			String temp = input.next();
			array[i][0] = input.nextDouble();
			array[i][1] = input.nextDouble();
			array[i][2] = input.nextDouble();
			i++;
		}
		return array;
	}

	public void start(Stage primaryStage) throws Exception {
		String myString = "circles3.txt";
		double[][] circle = readCircles(myString); // send the .txt file to method
		Pane pane = new Pane();

		int width_scene = 1000; // create window size
		int height_scene = 1000;
		double[] circle1 = new double[3];
		double[] circle2 = new double[3];
		double maxDist = 0.0;
		for (int i = 0; i < circle.length; i++)// find max distance formula
			for (int j = i + 1; j < circle.length; j++) {
				if (circle[i][2] != 0 && circle[j][2] != 0) {
					double dist = Math.pow(
							Math.pow((circle[i][0] - circle[j][0]), 2) + Math.pow((circle[i][1] - circle[j][1]), 2),
							0.5);
					if (dist > maxDist) {
						maxDist = dist;
						circle1[0] = circle[i][0];
						circle1[1] = circle[i][1];
						circle1[2] = i;
						circle2[0] = circle[j][0];
						circle2[1] = circle[j][1];
						circle2[2] = j;
					}
				}
			}
		// Print the max distance to the screen
		System.out.println("Maximum Distance: The distance between circles " + String.format("%.0f", (circle1[2] + 1))
				+ " and " + String.format("%.0f", (circle2[2] + 1)) + " is " + String.format("%.2f", maxDist));

		// Create a circle using .txt file inputs
		for (int i = 0; i < circle.length; i++) {

			Circle circle3 = new Circle();
			circle3.setCenterX(circle[i][0]);
			circle3.setCenterY(circle[i][1]);
			circle3.setRadius(circle[i][2]);
			circle3.setOpacity(10.0);
			circle3.setStroke(Color.BLACK);
			circle3.setFill(Color.WHITE);
			pane.getChildren().add(circle3);
			double textX = circle[i][0];
			double textY = circle[i][1];
			Text text = new Text(textX, textY, "" + (i + 1));
			text.setFont(Font.font("Arial", 14));
			text.setFill(Color.RED);

			pane.getChildren().addAll(text);
		}
		// create line between max distance circles
		Path path = new Path();
		MoveTo moveTo = new MoveTo();
		moveTo.setX(circle1[0]);
		moveTo.setY(circle1[1]);

		LineTo lineTo = new LineTo();
		lineTo.setX(circle2[0]);
		lineTo.setY(circle2[1]);

		path.getElements().add(moveTo);
		path.getElements().add(lineTo);
		path.setStrokeWidth(1);
		path.setStroke(Color.RED);

		double textX2 = (circle1[0] + circle2[0]) / 2;
		double textY2 = (circle1[1] + circle2[1]) / 2;

		Text text2 = new Text(textX2, textY2, "" + String.format("%.2f", maxDist));
		text2.setFont(Font.font("Arial", 14));
		text2.setFill(Color.RED);
		pane.getChildren().addAll(text2);
		pane.getChildren().add(path);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, width_scene, height_scene);
		primaryStage.setTitle("Maximum Circle Distance"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(true);
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}

