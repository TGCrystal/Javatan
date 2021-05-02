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


public class RoadGUI { 

	private Polygon thePolygon;
	private Group theRoot;

	public RoadGUI(Group root, Polygon polygon) {
		theRoot = root;
		thePolygon = polygon;
		theRoot.getChildren().add(polygon);
	}

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