package application.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.model.Credentials;
import application.model.Encryption;
import application.view.Animations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

public class FileHandlingController {
	/**
	 * This Observable class array named Credentials will contain an Arraylist of credential objects that will be from'
	 * the encrypted file. This will be the return value for the decrypted method and will be used to display the contents in
	 * a table.
	 */
	private ObservableList<Credentials> products = FXCollections.observableArrayList();
	/**
	 * This will turn true when the file that contains a .ofg extension is found
	 */
	private boolean containsOfg = false;
	
	/**
	 * This will turn true when the file that contains a .enc extension is found
	 */
	private boolean containsEnc = false;
	
	/**
	 * This String array list will contain the name of the absolute paths of each file in the valid selected directory.
	 */
	private List<String> filePath = new ArrayList<String>();
	/**
	 * The circle will be set to a different color and will have an animation
	 */
	private Circle circle;
	/**
	 * This shape is a custom shape made in svg path builder and it looks like a key hole that is in
	 * the middle of the circle.
	 */
	private SVGPath Key;
	/**
	 * This class will call any custom animation that wants to be played
	 */
	private Animations animation = new Animations();
	/**
	 * @param circle pass a circle object thats been initialized
	 * @param key a custom shape that been initialized, preferably a key.
	 */
	public FileHandlingController(Circle circle, SVGPath key) {
		this.circle = circle;
		this.Key = key;
	}
	/**
	 * This method handles the files that are passed in through the drag and drop feature
	 * or the locate file button. it will check to see if the directory passed only contains a length of two which will then
	 * proceed to check each file in the checkfile method and will return a boolean if the file is valid. After that it will get the
	 * absolute path of each file and will be processed as a string and then passed into the decrypt method for data handling. It will also
	 * contain a password prompt for the user to enter. The encrypt method requires a password to be entered. if the first condition fails, then
	 * it will raise an error, which the user will have to try again.
	 * @param files the specific index of the files
	 */
	protected void handleFileDecryption(File files) {
		if(files.isDirectory() && files.listFiles().length  == 2){
			File[] listOfFiles = files.listFiles();
				
			for (File file : listOfFiles) {
				boolean isCorrectFile = checkTheFile(file);
				if(isCorrectFile == true)
					continue;
				else if(isCorrectFile == false) {
					circle.setStroke(Color.RED);
					raiseAlert(AlertType.ERROR,"Invalid files","The files in the directory are invalid. "
							+ "They must contain .ofg or .enc"); 
					containsOfg = false;
					containsEnc = false;
					return;
				}
			}
			containsOfg = false;
			containsEnc = false;
			animation.playRotationAnimation(circle,false,360,10,1,10);
			Encryption encryption = new Encryption();
			try {
				products = encryption.decryptFile(passwordPrompt(), filePath.get(0), filePath.get(1));
				circle.setStroke(Color.GREEN);
				Key.setStroke(Color.GREEN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			circle.setStroke(Color.RED);
			Key.setStroke(Color.RED);
			animation.playRotationAnimation(circle,false,360,10,1,10);
			raiseAlert(AlertType.ERROR,"Invalid files","The files in the directory are invalid. "
					+ "They must contain .ofg or .enc"); 
		}
	}
	/**
	 * This method will take it a file from the file List and will go through conditional statements to see if the file
	 * is the correct file. regular expressions are used to check the file extension name. It only will take enc or ofg file extension
	 * @param file is the specific index of the files list data structure and will be processed one at a time
	 * @return it will return whether this file has checked the validity
	 */
	private boolean checkTheFile(File file) {
		if(file.isFile() == true && containsOfg == false || containsEnc == false) {
			if(file.getName().matches(".*\\.ofg")) {
				containsOfg = true;
				System.out.println(containsOfg);
				filePath.add(file.getAbsolutePath());
				return containsOfg;
			}
			else if(file.getName().matches(".*\\.enc")) {
				containsEnc = true;
				filePath.add(file.getAbsolutePath());
				return containsEnc;
			}
		}
		return false;
	} 
	/**
	 * This method display a popup box that asks for a password. It uses the Passwordfield to protect the 
	 * Inputed password for security. It displays a logo and the text prompt for the user to enter the password.
	 * @return it returns the result of the inputed password the user entered from the dialog box
	 */
	private String passwordPrompt() {
	    Dialog<String> dialog = new Dialog<>();
	    SVGPath svg = new SVGPath();
	    dialog.setTitle("Password for Encrypted file");
	    dialog.setHeaderText("Please enter password for your folder.");
	    svg.setContent("M 40 25 A 5 5 0 1 1 45 25 L 50 40 L 35 40 Z");
	    dialog.setGraphic(svg);
	    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	    PasswordField password = new PasswordField();
	    HBox content = new HBox();
	    content.setAlignment(Pos.CENTER_LEFT);
	    content.setSpacing(10);
	    content.getChildren().addAll(new Label("Password:"), password);
	    dialog.getDialogPane().setContent(content);
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == ButtonType.OK) {
	            return password.getText();
	        }
	        return null;
	    });

	    Optional<String> result = dialog.showAndWait();
		return result.get();
	}
	/**
	 * This method is responsible for displaying the type of alert, header, and context of an alert.
	 * it will also set the color of the key circle to a neutral color previously being red for an error.
	 * @param alertType the type of alert the user wants to display
	 * @param header the header of the text
	 * @param content whatever the context will be of the alert
	 */
	public void raiseAlert(AlertType alertType,String header, String content) {
		Alert alert = new Alert(alertType);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
		alert.setOnCloseRequest((e -> {circle.setStroke(Color.GRAY);Key.setStroke(Color.GRAY);}));
		
	}
}
