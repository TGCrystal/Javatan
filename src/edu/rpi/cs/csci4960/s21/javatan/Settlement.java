package edu.rpi.cs.csci4960.s21.javatan;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
* A class used by the GUI to help display settlements
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Settlement {
	private double xCoord;
	private double yCoord;
	/**
	* The button used to place the settlement
	*/
	public Button placeSettlementBtn;
	/**
	* The image used to represent the single house settlement
	*/
	public ImageView houseImg;
	/**
	* The image used to represent the city
	*/
	public ImageView cityImg;
	private Group theRoot;
	private Type type = Type.NONE;

	private enum Type {NONE, HOUSE, CITY};

	private int row;
	private int col;

	/**
	* The sole constructor for the Settlement class
	*
	* @param root the Group that this settlement belongs to
	* @param xCoord the x coordinate of this settlement
	* @param yCoord the y coordinate of this settlement
	* @param row the row index of this settlement
	* @param col the column index of this settlement
	*/
	public Settlement(Group root, double xCoord, double yCoord, int row, int col) {
		theRoot = root;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.row = row;
		this.col = col;
	}

	/**
	* Used to return a tuple containing the row and column indices of this settlement
	*
	* @return a tuple with the row as the first value and the column as the second
	*/
	public Tuple<Integer, Integer> getRowAndCol() {
		return new Tuple<>(row, col);
	}
	// private static void chooseFieldType() {
	// 	String[] types = new String[] {"forest", "forest", "forest", "field", "field", "field", "pasture", "pasture", "pasture", "pasture"};
	// }

	/**
	* Create the button used to handle events when it is clicked
	*/
	public void placeButton() {
		placeSettlementBtn = new Button();
		placeSettlementBtn.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-color: transparent;");
		placeSettlementBtn.setMinWidth(30);

		placeSettlementBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				GUI.client.handleThisPlayerClickingSettlement(row, col);
			}
		});
		placeSettlementBtn.setLayoutX(xCoord);
		placeSettlementBtn.setLayoutY(yCoord);
		theRoot.getChildren().add(placeSettlementBtn);
	}

	/**
	* If the player owns this settlement, upgrade it, otherwise claim ownership and make it a house
	*
	* @param color the color of the player attempting to process this settlement
	*/
	public void upgradeOrAssignToPlayer(String color) {
		switch (type) {
		case NONE:
			makeAHouse(color);
			break;
		case HOUSE:
			makeCity(color);
		default:
			break;
		}
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
		houseImg.setLayoutX(xCoord);
		houseImg.setLayoutY(yCoord);
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
		cityImg.setLayoutX(xCoord);
		cityImg.setLayoutY(yCoord);
		theRoot.getChildren().add(cityImg);
	}


}