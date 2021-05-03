package edu.rpi.cs.csci4960.s21.javatan;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.event.*;

/**
* The core GUI class used to launch the Client program
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class GUI extends Application {
	/**
	* The networking client used by the GUI to connect to the server
	*/
	protected static Client client;
	private static ArrayList<ArrayList<Settlement>> settlements = new ArrayList<>();
	private static ArrayList<ArrayList<Polygon>> polygons = new ArrayList<>();
	private static ArrayList<ArrayList<ArrayList<Double>>> tileCenters = new ArrayList<>();
	private static ArrayList<ArrayList<RoadGUI>> roads = new ArrayList<>();


	// private static void chooseFieldType() {
	// 	String[] types = new String[] {"forest", "forest", "forest", "field", "field", "field", "pasture", "pasture", "pasture", "pasture"};
	// }

	/**
	* Used to launch the GUI
	*
	* @param args command line arguments
	*/
	public static void main(String args[]) {
		launch(args);
	}

	private static ArrayList<Double> getHexTileCoords(int x, int y) {
		if (x == 2) {
			return tileCenters.get(x).get(y);
		} else {
			return tileCenters.get(x).get(y - 1);
		}
	}

	private static Polygon getHexTile(int x, int y) {
		if (x == 2) {
			return polygons.get(x).get(y);
		} else {
			return polygons.get(x).get(y - 1);
		}

	}

	/**
	* Sets the road at a given position to a given color
	*
	* @param x the x position of the road
	* @param y the y position of the road
	* @param color the color to set the road to
	*/
	public static void setColorOfRoadGUI(int x, int y, String color) {
		getRoadGUI(x, y).makeColor(color);
	}

	/**
	* Set a settlement at a given position to a given color
	*
	* @param x the x position of the settlement
	* @param y the y position of the settlement
	* @param color the color to set the settlement to
	*/
	public static void setColorOfSettlement(int x, int y, String color) {
		for (int i = 0; i < settlements.size(); i++) {
			ArrayList<Settlement> row = settlements.get(i);
			for (int j = 0; j < row.size(); j++) {
				Tuple<Integer, Integer> t = row.get(j).getRowAndCol();
				if (t.t == x && t.k == y) {
					row.get(j).upgradeOrAssignToPlayer(color);
				}
			}
		}
	}

	private static void setColorOfHexTile(int x, int y, String color) {
		if (color.equals("darkgreen")) {
			getHexTile(x, y).setFill(javafx.scene.paint.Color.DARKGREEN);
		} else if (color.equals("lightgreen")) {
			getHexTile(x, y).setFill(javafx.scene.paint.Color.LIGHTGREEN);
		} else if (color.equals("brown")) {
			getHexTile(x, y).setFill(javafx.scene.paint.Color.BROWN);
		} else if (color.equals("orange")) {
			getHexTile(x, y).setFill(javafx.scene.paint.Color.ORANGE);
		} else if (color.equals("gray")) {
			getHexTile(x, y).setFill(javafx.scene.paint.Color.GRAY);
		} else if (color.equals("yellow")) {
			getHexTile(x, y).setFill(javafx.scene.paint.Color.YELLOW);
		}
	}


	private static void drawGrid(Group root, double locTopLeftX, double locTopLeftY, double radius) {
		//String[] tileColors = new String[] {}
		double currentX = locTopLeftX;
		double currentY = locTopLeftY;
		for (int i = 0; i < 19; i++) {
			if (i <= 2) {
				//top row of grid
				Polygon polygon = new Polygon();
				setPolygonSides(polygon, currentX, currentY, radius, 6);
				ArrayList<Double> temp = new ArrayList<>();
				temp.add(currentX);
				temp.add(currentY);
				tileCenters.get(0).add(temp);
				polygons.get(0).add(polygon);

				if (i != 2) {
					currentX += 155;
				} else {
					currentX = locTopLeftX - 77.5;
					currentY += 134;
				}
				root.getChildren().add(polygon);
			} else if (i > 2 && i <= 6) {
				System.out.println("got here");
				//second row of grid
				Polygon polygon = new Polygon();
				setPolygonSides(polygon, currentX, currentY, radius, 6);
				ArrayList<Double> temp = new ArrayList<>();
				temp.add(currentX);
				temp.add(currentY);
				tileCenters.get(1).add(temp);
				polygons.get(1).add(polygon);


				if (i != 6) {
					currentX += 155;
				} else {
					currentX = locTopLeftX - 155;
					currentY += 134;
				}
				root.getChildren().add(polygon);

			} else if (i > 6 && i <= 11) {
				//third row of grid
				Polygon polygon = new Polygon();
				setPolygonSides(polygon, currentX, currentY, radius, 6);
				ArrayList<Double> temp = new ArrayList<>();
				temp.add(currentX);
				temp.add(currentY);
				tileCenters.get(2).add(temp);
				polygons.get(2).add(polygon);

				if (i != 11) {
					currentX += 155;
				} else {
					currentX = locTopLeftX - 77.5;
					currentY += 134;
				}
				root.getChildren().add(polygon);
			} else if (i > 11 && i <= 15) {
				//fourth row of grid
				Polygon polygon = new Polygon();
				setPolygonSides(polygon, currentX, currentY, radius, 6);
				ArrayList<Double> temp = new ArrayList<>();
				temp.add(currentX);
				temp.add(currentY);
				tileCenters.get(3).add(temp);
				polygons.get(3).add(polygon);

				if (i != 15) {
					currentX += 155;
				} else {
					currentX = locTopLeftX;
					currentY += 134;
				}
				root.getChildren().add(polygon);

			} else {
				//last row of grid
				Polygon polygon = new Polygon();
				setPolygonSides(polygon, currentX, currentY, radius, 6);
				ArrayList<Double> temp = new ArrayList<>();
				temp.add(currentX);
				temp.add(currentY);
				tileCenters.get(4).add(temp);
				polygons.get(4).add(polygon);

				currentX += 155;
				root.getChildren().add(polygon);

			}
		}
	}


	// private static Settlement getSettlement(int x, int y) {
	// 	if (x == 0 || x == 5) {
	// 		return settlements[x][y-2];
	// 	} else if (x == 1 || x == 4) {
	// 		return settlements[x][y-1];
	// 	} else if (x == 2 || x == 3) {
	// 		return settlements[x][y];
	// 	}
	// 	return null;
	// }

	/**
	* Gets the road at the provided location
	*
	* @param x the x position of the road
	* @param y the y position of the road
	* @return the RoadGUI object located at the provided position
	*/
	public static RoadGUI getRoadGUI(int x, int y) {
		if (x == 0 || x == 10) {
			return roads.get(x).get(y - 2);
		} else if (x == 1 || x == 9) {
			if (y == 2) {
				return roads.get(x).get(0);
			} else if (y == 4) {
				return roads.get(x).get(1);
			} else if (y == 6) {
				return roads.get(x).get(2);
			} else if (y == 8) {
				return roads.get(x).get(3);
			}
		} else if (x == 2 || x == 8) {
			return roads.get(x).get(y - 1);
		} else if (x == 3 || x == 7) {
			if (y == 1) {
				return roads.get(x).get(0);
			} else if (y == 3) {
				return roads.get(x).get(1);
			} else if (y == 5) {
				return roads.get(x).get(2);
			} else if (y == 7) {
				return roads.get(x).get(3);
			} else if (y == 9) {
				return roads.get(x).get(4);
			}
		} else if (x == 4 || x == 6) {
			return roads.get(x).get(y);
		} else if (x == 5) {
			if (y == 0) {
				return roads.get(x).get(0);
			} else if (y == 2) {
				return roads.get(x).get(1);
			} else if (y == 4) {
				return roads.get(x).get(2);
			} else if (y == 6) {
				return roads.get(x).get(3);
			} else if (y == 8) {
				return roads.get(x).get(4);
			} else if (y == 10) {
				return roads.get(x).get(5);
			}
		}
		return null;
	}



	private static void setPolygonSides(Polygon polygon, double centerX, double centerY, double radius, int sides) {
		polygon.getPoints().clear();
		final double angleStep = Math.PI * 2 / sides;
		double angle = 0; // assumes one point is located directly beneat the center point
		for (int i = 0; i < sides; i++, angle += angleStep) {
			polygon.getPoints().addAll(
			    Math.sin(angle) * radius + centerX, // x coordinate of the corner
			    Math.cos(angle) * radius + centerY // y coordinate of the corner
			);
		}
	}

	@Override
	public void start(Stage stage) {
		client = new Client();


		//getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		polygons.add(new ArrayList<Polygon>());
		polygons.add(new ArrayList<Polygon>());
		polygons.add(new ArrayList<Polygon>());
		polygons.add(new ArrayList<Polygon>());
		polygons.add(new ArrayList<Polygon>());

		tileCenters.add(new ArrayList<ArrayList<Double>>());
		tileCenters.add(new ArrayList<ArrayList<Double>>());
		tileCenters.add(new ArrayList<ArrayList<Double>>());
		tileCenters.add(new ArrayList<ArrayList<Double>>());
		tileCenters.add(new ArrayList<ArrayList<Double>>());




		//Creating a Group object
		Group root = new Group();

		drawGrid(root, 250, 100, 80);

		ArrayList<Settlement> firstRow = new ArrayList<>();

		Settlement set1 = new Settlement(root, 165.6, 52.8, 0, 2);
		set1.placeButton();
		Settlement set2 = new Settlement(root, 236.8, 12.0, 0, 3);
		set2.placeButton();
		Settlement set3 = new Settlement(root, 309.6, 47.2, 0, 4);
		set3.placeButton();
		Settlement set4 = new Settlement(root, 390.4, 14.4, 0, 5);
		set4.placeButton();
		Settlement set5 = new Settlement(root, 469.6, 52.0, 0, 6);
		set5.placeButton();
		Settlement set6 = new Settlement(root, 548.0, 14.4, 0, 7);
		set6.placeButton();
		Settlement set7 = new Settlement(root, 619.2, 52.8, 0, 8);
		set7.placeButton();

		firstRow.add(set1);
		firstRow.add(set2);
		firstRow.add(set3);
		firstRow.add(set4);
		firstRow.add(set5);
		firstRow.add(set6);
		firstRow.add(set7);

		ArrayList<Settlement> secondRow = new ArrayList<>();

		Settlement set8 = new Settlement(root, 94.4, 187.2, 1, 1);
		set8.placeButton();
		Settlement set9 = new Settlement(root, 163.2, 143.2, 1, 2);
		set9.placeButton();
		Settlement set10 = new Settlement(root, 235.2, 176.8, 1, 3);
		set10.placeButton();
		Settlement set11 = new Settlement(root, 316.0, 135.2, 1, 4);
		set11.placeButton();
		Settlement set12 = new Settlement(root, 390.4, 176.0, 1, 5);
		set12.placeButton();
		Settlement set13 = new Settlement(root, 471.2, 137.6, 1, 6);
		set13.placeButton();
		Settlement set14 = new Settlement(root, 545.6, 178.4, 1, 7);
		set14.placeButton();
		Settlement set15 = new Settlement(root, 620.0, 138.4, 1, 8);
		set15.placeButton();
		Settlement set16 = new Settlement(root, 694.4, 188.8, 1, 9);
		set16.placeButton();

		secondRow.add(set8);
		secondRow.add(set9);
		secondRow.add(set10);
		secondRow.add(set11);
		secondRow.add(set12);
		secondRow.add(set13);
		secondRow.add(set14);
		secondRow.add(set15);
		secondRow.add(set16);

		ArrayList<Settlement> thirdRow = new ArrayList<>();

		Settlement set17 = new Settlement(root, 14.4, 320.0, 2, 0);
		set17.placeButton();
		Settlement set18 = new Settlement(root, 85.6, 273.6, 2, 1);
		set18.placeButton();
		Settlement set19 = new Settlement(root, 154.4, 310.4, 2, 2);
		set19.placeButton();
		Settlement set20 = new Settlement(root, 233.6, 270.4, 2, 3);
		set20.placeButton();
		Settlement set21 = new Settlement(root, 311.2, 309.6, 2, 4);
		set21.placeButton();
		Settlement set22 = new Settlement(root, 389.6, 270.4, 2, 5);
		set22.placeButton();
		Settlement set23 = new Settlement(root, 468.8, 312.8, 2, 6);
		set23.placeButton();
		Settlement set24 = new Settlement(root, 544.8, 270.4, 2, 7);
		set24.placeButton();
		Settlement set25 = new Settlement(root, 624.0, 311.2, 2, 8);
		set25.placeButton();
		Settlement set26 = new Settlement(root, 696.0, 271.2, 2, 9);
		set26.placeButton();
		Settlement set27 = new Settlement(root, 773.6, 318.4, 2, 10);
		set27.placeButton();

		thirdRow.add(set17);
		thirdRow.add(set18);
		thirdRow.add(set19);
		thirdRow.add(set20);
		thirdRow.add(set21);
		thirdRow.add(set22);
		thirdRow.add(set23);
		thirdRow.add(set24);
		thirdRow.add(set25);
		thirdRow.add(set26);
		thirdRow.add(set27);

		ArrayList<Settlement> fourthRow = new ArrayList<>();


		Settlement set28 = new Settlement(root, 16.0, 400.8, 3, 0);
		set28.placeButton();
		Settlement set29 = new Settlement(root, 84.8, 440.0, 3, 1);
		set29.placeButton();
		Settlement set30 = new Settlement(root, 156.0, 404.8, 3, 2);
		set30.placeButton();
		Settlement set31 = new Settlement(root, 236.0, 444.8, 3, 3);
		set31.placeButton();
		Settlement set32 = new Settlement(root, 314.4, 405.6, 3, 4);
		set32.placeButton();
		Settlement set33 = new Settlement(root, 390.4, 446.4, 3, 5);
		set33.placeButton();
		Settlement set34 = new Settlement(root, 468.0, 404.8, 3, 6);
		set34.placeButton();
		Settlement set35 = new Settlement(root, 544.8, 444.8, 3, 7);
		set35.placeButton();
		Settlement set36 = new Settlement(root, 623.2, 404.0, 3, 8);
		set36.placeButton();
		Settlement set37 = new Settlement(root, 700.0, 444.8, 3, 9);
		set37.placeButton();
		Settlement set38 = new Settlement(root, 769.6, 397.6, 3, 10);
		set38.placeButton();

		fourthRow.add(set28);
		fourthRow.add(set29);
		fourthRow.add(set30);
		fourthRow.add(set31);
		fourthRow.add(set32);
		fourthRow.add(set33);
		fourthRow.add(set34);
		fourthRow.add(set35);
		fourthRow.add(set36);
		fourthRow.add(set37);
		fourthRow.add(set38);

		ArrayList<Settlement> fifthRow = new ArrayList<>();

		Settlement set39 = new Settlement(root, 91.2, 535.2, 4, 1);
		set39.placeButton();
		Settlement set40 = new Settlement(root, 158.4, 574.4, 4, 2);
		set40.placeButton();
		Settlement set41 = new Settlement(root, 236.0, 536.0, 4, 3);
		set41.placeButton();
		Settlement set42 = new Settlement(root, 310.4, 578.4, 4, 4);
		set42.placeButton();
		Settlement set43 = new Settlement(root, 389.6, 536.8, 4, 5);
		set43.placeButton();
		Settlement set44 = new Settlement(root, 468.8, 578.4, 4, 6);
		set44.placeButton();
		Settlement set45 = new Settlement(root, 547.2, 538.4, 4, 7);
		set45.placeButton();
		Settlement set46 = new Settlement(root, 619.2, 577.6, 4, 8);
		set46.placeButton();
		Settlement set47 = new Settlement(root, 694.4, 534.4, 4, 9);
		set47.placeButton();

		fifthRow.add(set39);
		fifthRow.add(set40);
		fifthRow.add(set41);
		fifthRow.add(set42);
		fifthRow.add(set43);
		fifthRow.add(set44);
		fifthRow.add(set45);
		fifthRow.add(set46);
		fifthRow.add(set47);

		ArrayList<Settlement> sixthRow = new ArrayList<>();

		Settlement set48 = new Settlement(root, 168.0, 667.2, 4, 2);
		set48.placeButton();
		Settlement set49 = new Settlement(root, 240.0, 706.4, 4, 3);
		set49.placeButton();
		Settlement set50 = new Settlement(root, 313.6, 667.2, 4, 4);
		set50.placeButton();
		Settlement set51 = new Settlement(root, 394.4, 708.0, 4, 5);
		set51.placeButton();
		Settlement set52 = new Settlement(root, 467.2, 666.4, 4, 6);
		set52.placeButton();
		Settlement set53 = new Settlement(root, 548.0, 705.6, 4, 7);
		set53.placeButton();
		Settlement set54 = new Settlement(root, 618.4, 668.0, 4, 8);
		set54.placeButton();

		sixthRow.add(set48);
		sixthRow.add(set49);
		sixthRow.add(set50);
		sixthRow.add(set51);
		sixthRow.add(set52);
		sixthRow.add(set53);
		sixthRow.add(set54);

		settlements.add(firstRow);
		settlements.add(secondRow);
		settlements.add(thirdRow);
		settlements.add(fourthRow);
		settlements.add(fifthRow);
		settlements.add(sixthRow);



		//make roads

		Polygon roadPoly1 = new Polygon();

		roadPoly1.getPoints().addAll(new Double[] {
		                                 181.6, 59.2,
		                                 165.6, 42.4,
		                                 235.2, 0.8,
		                                 248.8, 18.4
		                             });

		RoadGUI road1 = new RoadGUI(root, roadPoly1);

		Polygon roadPoly2 = new Polygon();

		roadPoly2.getPoints().addAll(new Double[] {
		                                 251.2, 16.0,
		                                 259.2, 4.0,
		                                 326.4, 38.4,
		                                 316.8, 59.2
		                             });

		RoadGUI road2 = new RoadGUI(root, roadPoly2);

		Polygon roadPoly3 = new Polygon();

		roadPoly3.getPoints().addAll(new Double[] {
		                                 335.2, 60.8,
		                                 323.2, 44.8,
		                                 392.0, 4.8,
		                                 403.2, 19.2
		                             });

		RoadGUI road3 = new RoadGUI(root, roadPoly3);

		Polygon roadPoly4 = new Polygon();

		roadPoly4.getPoints().addAll(new Double[] {
		                                 418.4, 1.6,
		                                 404.0, 18.4,
		                                 472.8, 59.2,
		                                 483.2, 42.4
		                             });

		RoadGUI road4 = new RoadGUI(root, roadPoly4);

		Polygon roadPoly5 = new Polygon();

		roadPoly5.getPoints().addAll(new Double[] {
		                                 490.4, 59.2,
		                                 559.2, 19.2,
		                                 548.8, 4.8,
		                                 484.8, 42.4
		                             });

		RoadGUI road5 = new RoadGUI(root, roadPoly5);

		Polygon roadPoly6 = new Polygon();

		roadPoly6.getPoints().addAll(new Double[] {
		                                 572.8, 1.6,
		                                 560.0, 20.0,
		                                 629.6, 57.6,
		                                 640.0, 43.2
		                             });

		RoadGUI road6 = new RoadGUI(root, roadPoly6);

		Polygon roadPoly7 = new Polygon();

		roadPoly7.getPoints().addAll(new Double[] {
		                                 180.0, 61.6,
		                                 180.8, 139.2,
		                                 160.0, 137.6,
		                                 160.0, 60.0
		                             });

		RoadGUI road7 = new RoadGUI(root, roadPoly7);

		Polygon roadPoly8 = new Polygon();

		roadPoly8.getPoints().addAll(new Double[] {
		                                 319.2, 60.8,
		                                 335.2, 60.8,
		                                 335.2, 137.6,
		                                 319.2, 137.6
		                             });

		RoadGUI road8 = new RoadGUI(root, roadPoly8);

		Polygon roadPoly9 = new Polygon();

		roadPoly9.getPoints().addAll(new Double[] {
		                                 474.4, 60.8,
		                                 491.2, 61.6,
		                                 491.2, 137.6,
		                                 474.4, 140.8
		                             });

		RoadGUI road9 = new RoadGUI(root, roadPoly9);

		Polygon roadPoly10 = new Polygon();

		roadPoly10.getPoints().addAll(new Double[] {
		                                  628.8, 60.8,
		                                  652.0, 60.8,
		                                  648.8, 135.2,
		                                  629.6, 137.6
		                              });

		RoadGUI road10 = new RoadGUI(root, roadPoly10);


		Polygon roadPoly11 = new Polygon();

		roadPoly11.getPoints().addAll(new Double[] {
		                                  103.2, 193.6,
		                                  170.4, 156.0,
		                                  162.4, 138.4,
		                                  95.2, 180.0
		                              });

		RoadGUI road11 = new RoadGUI(root, roadPoly11);

		Polygon roadPoly12 = new Polygon();

		roadPoly12.getPoints().addAll(new Double[] {
		                                  180.8, 140.0,
		                                  172.0, 154.4,
		                                  239.2, 192.8,
		                                  247.2, 179.2
		                              });

		RoadGUI road12 = new RoadGUI(root, roadPoly12);


		Polygon roadPoly13 = new Polygon();

		roadPoly13.getPoints().addAll(new Double[] {
		                                  250.4, 178.4,
		                                  259.2, 192.0,
		                                  326.4, 155.2,
		                                  318.4, 140.0
		                              });

		RoadGUI road13 = new RoadGUI(root, roadPoly13);


		Polygon roadPoly14 = new Polygon();

		roadPoly14.getPoints().addAll(new Double[] {
		                                  336.8, 140.8,
		                                  326.4, 156.0,
		                                  395.2, 192.0,
		                                  400.8, 178.4
		                              });

		RoadGUI road14 = new RoadGUI(root, roadPoly14);


		Polygon roadPoly15 = new Polygon();

		roadPoly15.getPoints().addAll(new Double[] {
		                                  405.6, 177.6,
		                                  414.4, 191.2,
		                                  481.6, 156.0,
		                                  472.0, 140.0
		                              });

		RoadGUI road15 = new RoadGUI(root, roadPoly15);


		Polygon roadPoly16 = new Polygon();

		roadPoly16.getPoints().addAll(new Double[] {
		                                  490.4, 140.8,
		                                  480.0, 157.6,
		                                  549.6, 193.6,
		                                  559.2, 180.8
		                              });

		RoadGUI road16 = new RoadGUI(root, roadPoly16);


		Polygon roadPoly17 = new Polygon();

		roadPoly17.getPoints().addAll(new Double[] {
		                                  561.6, 178.4,
		                                  568.0, 193.6,
		                                  636.0, 152.8,
		                                  628.0, 140.0
		                              });

		RoadGUI road17 = new RoadGUI(root, roadPoly17);


		Polygon roadPoly18 = new Polygon();

		roadPoly18.getPoints().addAll(new Double[] {
		                                  645.6, 138.4,
		                                  636.8, 156.0,
		                                  704.8, 196.0,
		                                  713.6, 176.8
		                              });

		RoadGUI road18 = new RoadGUI(root, roadPoly18);


		Polygon roadPoly19 = new Polygon();

		roadPoly19.getPoints().addAll(new Double[] {
		                                  81.6, 194.4,
		                                  103.2, 196.0,
		                                  103.2, 272.0,
		                                  87.2, 272.8
		                              });

		RoadGUI road19 = new RoadGUI(root, roadPoly19);


		Polygon roadPoly20 = new Polygon();

		roadPoly20.getPoints().addAll(new Double[] {
		                                  241.6, 196.0,
		                                  258.4, 193.6,
		                                  259.2, 272.8,
		                                  242.4, 272.0
		                              });

		RoadGUI road20 = new RoadGUI(root, roadPoly20);


		Polygon roadPoly21 = new Polygon();

		roadPoly21.getPoints().addAll(new Double[] {
		                                  396.0, 198.4,
		                                  412.8, 195.2,
		                                  412.0, 274.4,
		                                  396.0, 274.4
		                              });

		RoadGUI road21 = new RoadGUI(root, roadPoly21);


		Polygon roadPoly22 = new Polygon();

		roadPoly22.getPoints().addAll(new Double[] {
		                                  550.4, 196.8,
		                                  567.2, 196.8,
		                                  570.4, 272.8,
		                                  552.0, 272.8
		                              });

		RoadGUI road22 = new RoadGUI(root, roadPoly22);


		Polygon roadPoly23 = new Polygon();

		roadPoly23.getPoints().addAll(new Double[] {
		                                  706.4, 196.0,
		                                  721.6, 196.0,
		                                  720.0, 270.4,
		                                  706.4, 272.8
		                              });

		RoadGUI road23 = new RoadGUI(root, roadPoly23);


		Polygon roadPoly24 = new Polygon();

		roadPoly24.getPoints().addAll(new Double[] {
		                                  16.8, 304.8,
		                                  80.8, 269.6,
		                                  93.6, 289.6,
		                                  24.8, 328.0
		                              });

		RoadGUI road24 = new RoadGUI(root, roadPoly24);


		Polygon roadPoly25 = new Polygon();

		roadPoly25.getPoints().addAll(new Double[] {
		                                  104.0, 276.0,
		                                  94.4, 288.8,
		                                  164.0, 329.6,
		                                  172.0, 315.2
		                              });

		RoadGUI road25 = new RoadGUI(root, roadPoly25);


		Polygon roadPoly26 = new Polygon();

		roadPoly26.getPoints().addAll(new Double[] {
		                                  172.8, 313.6,
		                                  180.0, 328.0,
		                                  248.8, 288.0,
		                                  240.0, 275.2
		                              });

		RoadGUI road26 = new RoadGUI(root, roadPoly26);


		Polygon roadPoly27 = new Polygon();

		roadPoly27.getPoints().addAll(new Double[] {
		                                  259.2, 275.2,
		                                  250.4, 288.8,
		                                  316.8, 324.0,
		                                  327.2, 312.8
		                              });

		RoadGUI road27 = new RoadGUI(root, roadPoly27);


		Polygon roadPoly28 = new Polygon();

		roadPoly28.getPoints().addAll(new Double[] {
		                                  337.6, 324.8,
		                                  330.4, 312.8,
		                                  392.0, 274.4,
		                                  406.4, 289.6
		                              });

		RoadGUI road28 = new RoadGUI(root, roadPoly28);


		Polygon roadPoly29 = new Polygon();

		roadPoly29.getPoints().addAll(new Double[] {
		                                  415.2, 274.4,
		                                  408.0, 289.6,
		                                  473.6, 326.4,
		                                  484.0, 312.0
		                              });

		RoadGUI road29 = new RoadGUI(root, roadPoly29);


		Polygon roadPoly30 = new Polygon();

		roadPoly30.getPoints().addAll(new Double[] {
		                                  484.8, 312.0,
		                                  491.2, 325.6,
		                                  557.6, 292.0,
		                                  548.0, 273.6
		                              });

		RoadGUI road30 = new RoadGUI(root, roadPoly30);


		Polygon roadPoly31 = new Polygon();

		roadPoly31.getPoints().addAll(new Double[] {
		                                  568.8, 274.4,
		                                  558.4, 288.8,
		                                  628.0, 328.0,
		                                  636.8, 311.2
		                              });

		RoadGUI road31 = new RoadGUI(root, roadPoly31);


		Polygon roadPoly32 = new Polygon();

		roadPoly32.getPoints().addAll(new Double[] {
		                                  637.6, 311.2,
		                                  645.6, 326.4,
		                                  712.8, 288.8,
		                                  704.8, 273.6
		                              });

		RoadGUI road32 = new RoadGUI(root, roadPoly32);


		Polygon roadPoly33 = new Polygon();

		roadPoly33.getPoints().addAll(new Double[] {
		                                  725.6, 271.2,
		                                  713.6, 289.6,
		                                  783.2, 328.0,
		                                  791.2, 311.2
		                              });

		RoadGUI road33 = new RoadGUI(root, roadPoly33);


		Polygon roadPoly34 = new Polygon();

		roadPoly34.getPoints().addAll(new Double[] {
		                                  25.6, 327.2,
		                                  8.0, 328.0,
		                                  9.6, 405.6,
		                                  24.8, 407.2
		                              });

		RoadGUI road34 = new RoadGUI(root, roadPoly34);


		Polygon roadPoly35 = new Polygon();

		roadPoly35.getPoints().addAll(new Double[] {
		                                  164.0, 328.8,
		                                  179.2, 329.6,
		                                  179.2, 406.4,
		                                  164.0, 408.8
		                              });

		RoadGUI road35 = new RoadGUI(root, roadPoly35);


		Polygon roadPoly36 = new Polygon();

		roadPoly36.getPoints().addAll(new Double[] {
		                                  318.4, 332.0,
		                                  336.8, 328.8,
		                                  336.0, 405.6,
		                                  315.2, 408.8
		                              });

		RoadGUI road36 = new RoadGUI(root, roadPoly36);


		Polygon roadPoly37 = new Polygon();

		roadPoly37.getPoints().addAll(new Double[] {
		                                  472.0, 328.8,
		                                  491.2, 328.0,
		                                  492.0, 408.0,
		                                  472.8, 404.8
		                              });

		RoadGUI road37 = new RoadGUI(root, roadPoly37);


		Polygon roadPoly38 = new Polygon();

		roadPoly38.getPoints().addAll(new Double[] {
		                                  629.6, 329.6,
		                                  644.8, 328.8,
		                                  645.6, 404.8,
		                                  626.4, 409.6
		                              });

		RoadGUI road38 = new RoadGUI(root, roadPoly38);


		Polygon roadPoly39 = new Polygon();

		roadPoly39.getPoints().addAll(new Double[] {
		                                  783.2, 326.4,
		                                  798.4, 325.6,
		                                  800.8, 406.4,
		                                  784.8, 404.8
		                              });

		RoadGUI road39 = new RoadGUI(root, roadPoly39);


		Polygon roadPoly40 = new Polygon();

		roadPoly40.getPoints().addAll(new Double[] {
		                                  27.2, 407.2,
		                                  18.4, 424.8,
		                                  86.4, 462.4,
		                                  92.8, 449.6
		                              });

		RoadGUI road40 = new RoadGUI(root, roadPoly40);


		Polygon roadPoly41 = new Polygon();

		roadPoly41.getPoints().addAll(new Double[] {
		                                  93.6, 447.2,
		                                  103.2, 464.0,
		                                  168.8, 422.4,
		                                  164.8, 407.2
		                              });

		RoadGUI road41 = new RoadGUI(root, roadPoly41);


		Polygon roadPoly42 = new Polygon();

		roadPoly42.getPoints().addAll(new Double[] {
		                                  180.0, 407.2,
		                                  168.8, 423.2,
		                                  238.4, 464.8,
		                                  248.8, 448.0
		                              });

		RoadGUI road42 = new RoadGUI(root, roadPoly42);


		Polygon roadPoly43 = new Polygon();

		roadPoly43.getPoints().addAll(new Double[] {
		                                  250.4, 446.4,
		                                  256.8, 462.4,
		                                  324.0, 423.2,
		                                  316.8, 406.4
		                              });

		RoadGUI road43 = new RoadGUI(root, roadPoly43);

		Polygon roadPoly44 = new Polygon();

		roadPoly44.getPoints().addAll(new Double[] {
		                                  336.8, 405.6,
		                                  327.2, 424.0,
		                                  396.8, 462.4,
		                                  401.6, 444.8
		                              });

		RoadGUI road44 = new RoadGUI(root, roadPoly44);

		Polygon roadPoly45 = new Polygon();

		roadPoly45.getPoints().addAll(new Double[] {
		                                  404.0, 445.6,
		                                  413.6, 459.2,
		                                  479.2, 424.0,
		                                  472.0, 406.4
		                              });

		RoadGUI road45 = new RoadGUI(root, roadPoly45);

		Polygon roadPoly46 = new Polygon();

		roadPoly46.getPoints().addAll(new Double[] {
		                                  492.8, 407.2,
		                                  480.8, 424.8,
		                                  550.4, 460.0,
		                                  558.4, 444.8
		                              });

		RoadGUI road46 = new RoadGUI(root, roadPoly46);

		Polygon roadPoly47 = new Polygon();

		roadPoly47.getPoints().addAll(new Double[] {
		                                  560.0, 449.6,
		                                  568.8, 460.0,
		                                  636.8, 421.6,
		                                  625.6, 406.4
		                              });

		RoadGUI road47 = new RoadGUI(root, roadPoly47);

		Polygon roadPoly48 = new Polygon();

		roadPoly48.getPoints().addAll(new Double[] {
		                                  646.4, 408.0,
		                                  638.4, 420.0,
		                                  704.0, 460.8,
		                                  715.2, 448.8
		                              });

		RoadGUI road48 = new RoadGUI(root, roadPoly48);

		Polygon roadPoly49 = new Polygon();

		roadPoly49.getPoints().addAll(new Double[] {
		                                  716.0, 444.8,
		                                  783.2, 408.0,
		                                  793.6, 421.6,
		                                  724.8, 460.0
		                              });

		RoadGUI road49 = new RoadGUI(root, roadPoly49);


		Polygon roadPoly50 = new Polygon();

		roadPoly50.getPoints().addAll(new Double[] {
		                                  81.6, 463.2,
		                                  104.0, 464.8,
		                                  104.0, 540.8,
		                                  82.4, 540.8
		                              });
		RoadGUI road50 = new RoadGUI(root, roadPoly50);

		Polygon roadPoly51 = new Polygon();

		roadPoly51.getPoints().addAll(new Double[] {
		                                  240.8, 463.2,
		                                  256.8, 464.0,
		                                  256.8, 537.6,
		                                  240.0, 540.0
		                              });

		RoadGUI road51 = new RoadGUI(root, roadPoly51);

		Polygon roadPoly52 = new Polygon();

		roadPoly52.getPoints().addAll(new Double[] {
		                                  396.8, 463.2,
		                                  412.8, 460.0,
		                                  413.6, 538.4,
		                                  397.6, 540.8
		                              });

		RoadGUI road52 = new RoadGUI(root, roadPoly52);

		Polygon roadPoly53 = new Polygon();

		roadPoly53.getPoints().addAll(new Double[] {
		                                  550.4, 463.2,
		                                  567.2, 464.0,
		                                  568.0, 539.2,
		                                  552.0, 538.4
		                              });

		RoadGUI road53 = new RoadGUI(root, roadPoly53);

		Polygon roadPoly54 = new Polygon();

		roadPoly54.getPoints().addAll(new Double[] {
		                                  704.0, 464.0,
		                                  724.8, 461.6,
		                                  724.8, 538.4,
		                                  703.2, 540.0
		                              });

		RoadGUI road54 = new RoadGUI(root, roadPoly54);

		Polygon roadPoly55 = new Polygon();

		roadPoly55.getPoints().addAll(new Double[] {
		                                  166.4, 578.4,
		                                  154.4, 597.6,
		                                  90.4, 560.8,
		                                  107.2, 542.4
		                              });

		RoadGUI road55 = new RoadGUI(root, roadPoly55);

		Polygon roadPoly56 = new Polygon();

		roadPoly56.getPoints().addAll(new Double[] {
		                                  172.8, 579.2,
		                                  179.2, 593.6,
		                                  248.8, 557.6,
		                                  240.0, 540.0
		                              });

		RoadGUI road56 = new RoadGUI(root, roadPoly56);


		Polygon roadPoly57 = new Polygon();

		roadPoly57.getPoints().addAll(new Double[] {
		                                  317.6, 576.0,
		                                  308.0, 589.6,
		                                  249.6, 558.4,
		                                  258.4, 541.6
		                              });

		RoadGUI road57 = new RoadGUI(root, roadPoly57);

		Polygon roadPoly58 = new Polygon();

		roadPoly58.getPoints().addAll(new Double[] {
		                                  331.2, 575.2,
		                                  338.4, 591.2,
		                                  395.2, 562.4,
		                                  388.0, 547.2
		                              });

		RoadGUI road58 = new RoadGUI(root, roadPoly58);

		Polygon roadPoly59 = new Polygon();

		roadPoly59.getPoints().addAll(new Double[] {   //done
		                                  419.2, 543.2,
		                                  410.4, 560.0,
		                                  468.0, 591.2,
		                                  472.8, 575.2
		                              });

		RoadGUI road59 = new RoadGUI(root, roadPoly59);

		Polygon roadPoly60 = new Polygon();

		roadPoly60.getPoints().addAll(new Double[] {    //done
		                                  493.6, 576.0,
		                                  496.8, 589.6,
		                                  556.8, 559.2,
		                                  542.4, 542.4
		                              });

		RoadGUI road60 = new RoadGUI(root, roadPoly60);

		Polygon roadPoly61 = new Polygon();

		roadPoly61.getPoints().addAll(new Double[] {       //done
		                                  576.0, 544.0,
		                                  566.4, 563.2,
		                                  620.8, 592.8,
		                                  632.0, 571.2
		                              });

		RoadGUI road61 = new RoadGUI(root, roadPoly61);

		Polygon roadPoly62 = new Polygon();

		roadPoly62.getPoints().addAll(new Double[] {     //done
		                                  638.4, 580.0,
		                                  644.8, 595.2,
		                                  716.0, 556.0,
		                                  706.4, 541.6
		                              });

		RoadGUI road62 = new RoadGUI(root, roadPoly62);

		Polygon roadPoly63 = new Polygon();

		roadPoly63.getPoints().addAll(new Double[] {     //done
		                                  179.2, 599.2,
		                                  154.4, 599.2,
		                                  156.0, 669.6,
		                                  180.0, 672.0
		                              });

		RoadGUI road63 = new RoadGUI(root, roadPoly63);

		Polygon roadPoly64 = new Polygon();

		roadPoly64.getPoints().addAll(new Double[] {     //done
		                                  320.0, 599.2,
		                                  335.2, 600.8,
		                                  333.6, 674.4,
		                                  318.4, 672.8
		                              });

		RoadGUI road64 = new RoadGUI(root, roadPoly64);

		Polygon roadPoly65 = new Polygon();

		roadPoly65.getPoints().addAll(new Double[] {   //done
		                                  474.4, 600.8,
		                                  489.6, 605.6,
		                                  492.8, 672.8,
		                                  473.6, 671.2
		                              });

		RoadGUI road65 = new RoadGUI(root, roadPoly65);

		Polygon roadPoly66 = new Polygon();

		roadPoly66.getPoints().addAll(new Double[] {      //done
		                                  626.4, 600.0,
		                                  643.2, 600.8,
		                                  645.6, 672.8,
		                                  626.4, 673.6
		                              });

		RoadGUI road66 = new RoadGUI(root, roadPoly66);

		Polygon roadPoly67 = new Polygon();

		roadPoly67.getPoints().addAll(new Double[] {    //done
		                                  180.8, 674.4,
		                                  172.8, 693.6,
		                                  232.8, 728.0,
		                                  240.8, 709.6
		                              });

		RoadGUI road67 = new RoadGUI(root, roadPoly67);

		Polygon roadPoly68 = new Polygon();

		roadPoly68.getPoints().addAll(new Double[] {      //done
		                                  251.2, 713.6,
		                                  260.8, 727.2,
		                                  324.8, 694.4,
		                                  312.8, 675.2
		                              });

		RoadGUI road68 = new RoadGUI(root, roadPoly68);

		Polygon roadPoly69 = new Polygon();

		roadPoly69.getPoints().addAll(new Double[] {       //done
		                                  340.0, 679.2,
		                                  333.6, 700.8,
		                                  392.8, 726.4,
		                                  398.4, 712.0
		                              });

		RoadGUI road69 = new RoadGUI(root, roadPoly69);

		Polygon roadPoly70 = new Polygon();

		roadPoly70.getPoints().addAll(new Double[] {   //done
		                                  407.2, 710.4,
		                                  415.2, 726.4,
		                                  476.8, 691.2,
		                                  468.8, 676.0
		                              });

		RoadGUI road70 = new RoadGUI(root, roadPoly70);

		Polygon roadPoly71 = new Polygon();

		roadPoly71.getPoints().addAll(new Double[] {
		                                  494.4, 676.8,
		                                  484.0, 696.0,
		                                  547.2, 728.0,
		                                  556.0, 713.6
		                              });

		RoadGUI road71 = new RoadGUI(root, roadPoly71);

		Polygon roadPoly72 = new Polygon();

		roadPoly72.getPoints().addAll(new Double[] {
		                                  625.6, 677.6,
		                                  640.0, 692.8,
		                                  568.0, 724.0,
		                                  560.8, 715.2
		                              });

		RoadGUI road72 = new RoadGUI(root, roadPoly72);



		ArrayList<RoadGUI> theFirstRow = new ArrayList<>();

		theFirstRow.add(road1);
		theFirstRow.add(road2);
		theFirstRow.add(road3);
		theFirstRow.add(road4);
		theFirstRow.add(road5);
		theFirstRow.add(road6);

		ArrayList<RoadGUI> theSecondRow = new ArrayList<>();

		theSecondRow.add(road7);
		theSecondRow.add(road8);
		theSecondRow.add(road9);
		theSecondRow.add(road10);

		ArrayList<RoadGUI> theThirdRow = new ArrayList<>();

		theThirdRow.add(road11);
		theThirdRow.add(road12);
		theThirdRow.add(road13);
		theThirdRow.add(road14);
		theThirdRow.add(road15);
		theThirdRow.add(road16);
		theThirdRow.add(road17);
		theThirdRow.add(road18);

		ArrayList<RoadGUI> theFourthRow = new ArrayList<>();

		theFourthRow.add(road19);
		theFourthRow.add(road20);
		theFourthRow.add(road21);
		theFourthRow.add(road22);
		theFourthRow.add(road23);

		ArrayList<RoadGUI> theFifthRow = new ArrayList<>();

		theFifthRow.add(road24);
		theFifthRow.add(road25);
		theFifthRow.add(road26);
		theFifthRow.add(road27);
		theFifthRow.add(road28);
		theFifthRow.add(road29);
		theFifthRow.add(road30);
		theFifthRow.add(road31);
		theFifthRow.add(road32);
		theFifthRow.add(road33);

		ArrayList<RoadGUI> theSixthRow = new ArrayList<>();

		theSixthRow.add(road34);
		theSixthRow.add(road35);
		theSixthRow.add(road36);
		theSixthRow.add(road37);
		theSixthRow.add(road38);
		theSixthRow.add(road39);

		ArrayList<RoadGUI> theSeventhRow = new ArrayList<>();

		theSeventhRow.add(road40);
		theSeventhRow.add(road41);
		theSeventhRow.add(road42);
		theSeventhRow.add(road43);
		theSeventhRow.add(road44);
		theSeventhRow.add(road45);
		theSeventhRow.add(road46);
		theSeventhRow.add(road47);
		theSeventhRow.add(road48);
		theSeventhRow.add(road49);

		ArrayList<RoadGUI> theEigthRow = new ArrayList<>();

		theEigthRow.add(road50);
		theEigthRow.add(road51);
		theEigthRow.add(road52);
		theEigthRow.add(road53);
		theEigthRow.add(road54);

		ArrayList<RoadGUI> theNinthRow = new ArrayList<>();

		theNinthRow.add(road55);
		theNinthRow.add(road56);
		theNinthRow.add(road57);
		theNinthRow.add(road58);
		theNinthRow.add(road59);
		theNinthRow.add(road60);
		theNinthRow.add(road61);
		theNinthRow.add(road62);

		ArrayList<RoadGUI> theTenthRow = new ArrayList<>();

		theTenthRow.add(road63);
		theTenthRow.add(road64);
		theTenthRow.add(road65);
		theTenthRow.add(road66);

		ArrayList<RoadGUI> theEleventhRow = new ArrayList<>();

		theEleventhRow.add(road67);
		theEleventhRow.add(road68);
		theEleventhRow.add(road69);
		theEleventhRow.add(road70);
		theEleventhRow.add(road71);
		theEleventhRow.add(road72);

		roads.add(theFirstRow);
		roads.add(theSecondRow);
		roads.add(theThirdRow);
		roads.add(theFourthRow);
		roads.add(theFifthRow);
		roads.add(theSixthRow);
		roads.add(theSeventhRow);
		roads.add(theEigthRow);
		roads.add(theNinthRow);
		roads.add(theTenthRow);
		roads.add(theEleventhRow);








		// root.getChildren().addAll(roadPoly1, roadPoly2, roadPoly3, roadPoly4,
		// roadPoly5, roadPoly6, roadPoly7, roadPoly8, roadPoly9, roadPoly10, roadPoly11,
		// roadPoly12, roadPoly13, roadPoly14, roadPoly15, roadPoly16, roadPoly17, roadPoly18,
		// roadPoly19, roadPoly20, roadPoly21, roadPoly22, roadPoly23, roadPoly24, roadPoly25,
		// roadPoly26, roadPoly27, roadPoly28, roadPoly29, roadPoly30, roadPoly31, roadPoly32,
		// roadPoly33, roadPoly34, roadPoly35, roadPoly36, roadPoly37, roadPoly38, roadPoly39,
		// roadPoly40, roadPoly41, roadPoly42, roadPoly43, roadPoly44, roadPoly45, roadPoly46,
		// roadPoly47, roadPoly48, roadPoly49, roadPoly50, roadPoly51, roadPoly52, roadPoly53,
		// roadPoly54, roadPoly55, roadPoly56, roadPoly57, roadPoly58, roadPoly59, roadPoly60,
		// roadPoly61, roadPoly62, roadPoly63, roadPoly64, roadPoly65, roadPoly66, roadPoly67,
		// roadPoly68, roadPoly69, roadPoly70, roadPoly71, roadPoly72);


		//Creating a scene object
		Scene scene = new Scene(root);

		scene.setOnMouseClicked(e -> {
			System.out.println(e.getX() + ", " + e.getY() + ",");
		});

		//Setting title to the Stage
		stage.setTitle("Java Catan");

		//Adding scene to the stage
		stage.setScene(scene);

		//Displaying the contents of the stage
		stage.show();
	}

}