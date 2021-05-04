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

/**
* A class used by the GUI to help display roads
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class RoadGUI {

	private Polygon thePolygon;
	private Group theRoot;
	public int x;
	public int y;
	

	/**
	* The sole constructor for the RoadGUI class
	*
	* @param root the Group this road belongs to
	* @param polygon the Polygon used to represent this road
	*/
	public RoadGUI(Group root, Polygon polygon) {
		theRoot = root;
		thePolygon = polygon;
		makeVisible();
	}
	
	/**
	* make the road visible on the board
	*/
	public void makeVisible() {
		theRoot.getChildren().add(thePolygon);
		thePolygon.setOnMouseClicked(mouseEvent -> whenClicked());
	}
	
	/**
	* remove the road from the board
	*/
	public void remove() {
		theRoot.getChildren().remove(thePolygon);
	}
	
	/**
	* what to do when the road is clicked
	*/
	public void whenClicked() {
		System.out.println("Do this when clicked");
	}

	/**
	* Changes this road's color to red, orange, white, or blue
	*
	* @param color the color to change this road to
	*/
	public void makeColor(String color) {
		if (color.equals("red")) {
			thePolygon.setFill(javafx.scene.paint.Color.RED);
		} else if (color.equals("orange")) {
			thePolygon.setFill(javafx.scene.paint.Color.ORANGE);
		} else if (color.equals("white")) {
			thePolygon.setFill(javafx.scene.paint.Color.WHITE);
		} else if (color.equals("blue")) {
			thePolygon.setFill(javafx.scene.paint.Color.BLUE);
		}
	}


}
