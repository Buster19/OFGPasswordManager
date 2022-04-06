package application;
	
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.controller.MainController;
import application.controller.SceneStageController;
import application.model.Encryption;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

/** 4/1/2022
 * This is the Main class that contains the first scene and the primary stage of the program
 * @authors Garrett Ashley, Evan Ashley, Eddie Morales, Eduardo Riveragarza
 *
 */
public class Main extends Application {
	/**
	 *This method loads in the necessary files to display the objects and sets the dimensions of the stage.
	 *It is use to invoke the lauch method in the main method
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Main.fxml"));
		    Parent root = (Parent)loader.load();
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			SceneStageController ssc = new SceneStageController();
			ssc.setStage(primaryStage);
			primaryStage.setTitle("Off The Grid");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * invokes launch method that starts the program
	 * @throws Exception in case of a fatal error
	 */
	public static void main(String[] args) throws Exception {
		/*
		Used for testing...
		Scanner sc = new Scanner(System.in);
		List<String> contents = new ArrayList<String>();
		int i = 0;
		while(i < 10) {
			System.out.println("Enter user name or email for account");
			contents.add(sc.next()+",");
			System.out.println("Enter password");
			contents.add(sc.next()+",");
			System.out.println("Enter the url for the account");
			contents.add(sc.next()+",");
			System.out.println("Enter any notes");
			contents.add(sc.next()+",");
			System.out.println("Enter user name or email for account");
			contents.add(sc.next()+",");
			System.out.println("Enter password");
			contents.add(sc.next()+",");
			System.out.println("Enter the url for the account");
			contents.add(sc.next()+",");
			System.out.println("Enter any notes");
			contents.add(sc.next()+",");
			i++;
		}
		Encryption file = new Encryption();
		file.encryptFile("password",contents);
		*/
		launch(args);
	}
}
