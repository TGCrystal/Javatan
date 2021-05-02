package edu.rpi.cs.csci4960.s21.javatan;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.shape.Polygon; 
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Settlement { 
	private double x;
	private double y;
	public Button placeSettlementBtn;
	public ImageView houseImg;
	public ImageView cityImg;
	private Group theRoot;

	public Settlement(Group root, double xCoord, double yCoord) {
		theRoot = root;
		x = xCoord;
		y = yCoord;
	}

	// private static void chooseFieldType() {
	// 	String[] types = new String[] {"forest", "forest", "forest", "field", "field", "field", "pasture", "pasture", "pasture", "pasture"};
	// }

	public void placeButton() {
		placeSettlementBtn = new Button();
		//placeSettlementBtn.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-color: transparent;");
		placeSettlementBtn.setMinWidth(30);

		placeSettlementBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				System.out.println("testing this");
			}
		});
		placeSettlementBtn.setLayoutX(x);
    	placeSettlementBtn.setLayoutY(y);

        theRoot.getChildren().add(placeSettlementBtn);
	}

	public void makeAHouse(String color) {
		theRoot.getChildren().remove(placeSettlementBtn);
		Image anImage = null;
		if (color.equals("red")) {
			anImage = new Image("redHouse.png", 25, 25, false, false);
		} else if (color.equals("orange")) {
			anImage = new Image("orangeHouse.png", 25, 25, false, false);
		} else if (color.equals("white")) {
			anImage = new Image("whiteHouse.png", 25, 25, false, false);
		} else if (color.equals("blue")) {
			anImage = new Image("blueHouse.png", 25, 25, false, false);
		}
		houseImg = new ImageView(anImage);
		houseImg.setLayoutX(x);
    	houseImg.setLayoutY(y);
		theRoot.getChildren().add(houseImg);

	}

	public void makeCity(String color) {
		theRoot.getChildren().remove(houseImg);
		Image anImage = null;
		if (color.equals("red")) {
			anImage = new Image("redCity.png", 25, 25, false, false);
		} else if (color.equals("orange")) {
			anImage = new Image("orangeCity.png", 25, 25, false, false);
		} else if (color.equals("white")) {
			anImage = new Image("whiteCity.png", 25, 25, false, false);
		} else if (color.equals("blue")) {
			anImage = new Image("blueCity.png", 25, 25, false, false);
		}
		cityImg = new ImageView(anImage);
		cityImg.setLayoutX(x);
    	cityImg.setLayoutY(y);
		theRoot.getChildren().add(cityImg);
	}

	
}