package application.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import application.model.Credentials;
import application.model.Encryption;
import javafx.animation.RotateTransition;
import javafx.animation.StrokeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/** 4/1/2022
 * This is the main controller that manages the data that is requested in the
 * main menu and also manages the visuals in the main menu.
 * @authors Garrett Ashley, Evan Ashley, Eddie Morales,Eduardo Riveragarza
 *
 */
public class MainController implements Initializable {
	/**
	 * all different circle objects that are shown on the screen.
	 * Wach will be set to a different color and will have an animation
	 * 
	 */
	@FXML
	private Circle circle,circle1,circle2,circle3;
	
	/**
	 * This text display the main title on the screen. AkA the name of our project.
	 */
	@FXML
	private Text mainTitle;
	
	/**
	 * This shape is a custom shape made in svg path builder and it looks like a key hole that is in
	 * the middle of the circle.
	 */
	@FXML
	private SVGPath Key;
	
	/**
	 * This is the root pane that holds all the objects that are stacked on it. 
	 */
	@FXML
	private BorderPane MainPane;
	
	/**
	 * These buttons are in the 3 smaller circles and have different functionality when pressed.
	 */
	@FXML
	private Button locate,newFile,settings;
	
	/**
	 * This is the stage that will get the root stage called in the Main.java class, so we
	 * don't have to create a new stage, but instead, use the already existing one.
	 */
	private Stage stage;
	
	/**
	 * This will turn true when the file that contains a .ofg extension is found
	 */
	private boolean containsOfg = false;
	
	/**
	 * This will turn true when the file that contains a .ofg extension is found
	 */
	private boolean containsEnc = false;
	
	/**
	 * This String array list will contain the name of the absolute paths of each file in the valid selected directory.
	 */
	private List<String> filePath = new ArrayList<String>();
	
	/**
	 * This Observable class array named Credentials will contain an Arraylist of credential objects that will be from'
	 * the encrypted file. This will be the return value for the decrypted method and will be used to display the contents in
	 * a table.
	 */
	@FXML
	private ObservableList<Credentials> products = FXCollections.observableArrayList();
	
	/**
	 * This method will take in all the determined parameters and play the rotated animation 
	 * accordingly.
	 * @param node This will be any node that wants to be rotated. Our usage is the circles
	 * @param reverse to see if the animation wants to be played in reverse
	 * @param angle to see if the animation will rotate at a certain angle
	 * @param time the amount of time each rotation will take
	 * @param rate how fast the rotations will take to plav.
	 * @param cycle how many times the animation will be played
	 * 
	 * 
	 */
	public void playRotationAnimation(Node node, Boolean reverse,int angle,double d,int rate, int cycle) {
		RotateTransition rotation = new RotateTransition(Duration.seconds(d),node);
		rotation.setAutoReverse(reverse);
		rotation.setByAngle(angle);
		rotation.setDelay(Duration.seconds(0));
		rotation.setRate(rate);
		rotation.setCycleCount(cycle);
		rotation.play();
	}
	
	/**
	 * This method will take in the preferred circle and color, which will then be
	 * processed to be filled on call. The cycle will on be one time since the desired color
	 * will be filled. This animation also syncs up with the 3 smaller circle rotations.
	 * @param c the determined circle that wants the strokes to be filled
	 * @param color the color of preference the user want to use
	 */
	private void fillAnimation(Circle c, Color color) {
		StrokeTransition stroke = new StrokeTransition(); 
	    stroke.setAutoReverse(false);   
	    stroke.setCycleCount(1);  
	    stroke.setDuration(Duration.millis(300));       
	    stroke.setToValue(color);    
	    stroke.setShape(c);  
	    stroke.play();
	}
	
	/**
	 * This method detects when the mouse entered one of the three smaller circles that
	 * contain the buttons. it will proceed to play the circle rotation animation and will 
	 * play the stroke color fill animation aka the fillAnimation method.
	 * @param event this event is when the mouse enters the circle diameter
	 */
	@FXML 
	private void mouseEnteredButton(MouseEvent event){
		if(event.getSource() == locate) {
			playRotationAnimation(circle1,false,360,0.5,1,1);
			fillAnimation(circle1,Color.TOMATO);
		}
		else if(event.getSource() == newFile) {
			playRotationAnimation(circle2,false,360,0.5,1,1);
			fillAnimation(circle2,Color.CORNFLOWERBLUE);
		}
		else if(event.getSource() == settings) {
			playRotationAnimation(circle3,false,360,0.5,1,1);
			fillAnimation(circle3,Color.WHEAT);
		}
	}
	/**
	 * this method detects when the mouse exits the button that is in the circle.(The mouse and circle
	 * basically match the same diameter, but technically its the button that contains the action). It will 
	 * proceed to play the circle rotation animation but in reverse,and will set the color to be grey to notify that
	 * the mouse is not in the area of the circle anymore.
	 * @param event this event is when the mouse exists the circle diameter
	 */
	@FXML
	private void mouseExitedButton(MouseEvent event){
		if(event.getSource() == locate) {
			playRotationAnimation(circle1,true,-360,0.5,1,1);
			fillAnimation(circle1,Color.GRAY);
		}
		else if(event.getSource() == newFile) {
			playRotationAnimation(circle2,true,-360,0.5,1,1);
			fillAnimation(circle2,Color.GRAY);
		}
		else if(event.getSource() == settings) {
			playRotationAnimation(circle3,false,-360,0.5,1,1);
			fillAnimation(circle3,Color.GRAY);
		}
	}
	
	/**
	 * This method will detect when the mouse is being dragged and will accept files that
	 * want to be handled.
	 * @param event this event is when the mouse drag is detected
	 */
	@FXML
	private void dragDetected(DragEvent event) {
		if(event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	/**
	 * This method will handle whenever a file has been dropped onto the program. It will only accept one folder at a time.
	 * These files are contained in a folder and only will be processed when its stored in a folder. If more than one folder or
	 * file is dropped, then it will raise an error. else it will continue to check the individual files in the folder.
	 * @param event the event is the drag drop event which detect when a file has been dropped on the the program
	 * @throws Exception this will throw an exception when a fatal error has occurred
	 */
	@FXML
	private void handleDrop(DragEvent event) throws Exception{
		List<File> files = event.getDragboard().getFiles();
		if (files.size() > 1) {
			circle.setStroke(Color.RED);
			Key.setStroke(Color.RED);
			playRotationAnimation(circle,false,360,10,1,10);
			raiseAlert(AlertType.ERROR,"Invalid operation.","Please locate or drag the files your password contents are stored in."); 
			files.clear();
			event.consume();
			return;
		}
		else {
		handleFile(files.get(0));
		files.clear();
		event.consume();
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
	 * This method is the alternative to the drag and drop feature, just incase you don't
	 * have the file with you and it's located else where. It will get the root stage and display the 
	 * operating systems file manager to retrieve a folder only. It will then proceed to the handleFiles method
	 * @param event this event is when button is pressed on action.
	 */
	@FXML
	private void locateFile(ActionEvent event) {
	    try {
	        // create a File chooser
	    	stage = (Stage) MainPane.getScene().getWindow();
	    	DirectoryChooser chooser = new DirectoryChooser();
	        chooser.setTitle("Open Directory");
	        File selectedFile = chooser.showDialog(stage);
	        if (selectedFile != null) {
	        	 handleFile(selectedFile);
	        }
	 
	    }catch (Exception e) {
	 
	        System.out.println(e.getMessage());
	    }
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
	private void handleFile(File files) {
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
				playRotationAnimation(circle,false,360,10,1,10);
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
			playRotationAnimation(circle,false,360,10,1,10);
			raiseAlert(AlertType.ERROR,"Invalid files","The files in the directory are invalid. "
					+ "They must contain .ofg or .enc"); 
			}
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
	
	/**
	 * This method display a popup box that asks for a password. It uses the Passwordfield to protect the 
	 * Inputed password for security. It displays a logo and the text prompt for the user to enter the password.
	 * @return it returns the result of the inputed password the user entered from the dialog box
	 */
	public String passwordPrompt() {
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
	@FXML
	private void switchToFileCreation(ActionEvent event) throws IOException {
		URL file = new File("src/application/view/FileCreation.fxml").toURI().toURL();
		Parent root = FXMLLoader.load(file);
		Scene scene = new Scene(root,600,600);
		scene.getStylesheets().add("application/application.css");
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();	
	}
	
	/**
	 *This method initializes when the scene is done being generated. the font of the main title is located
	 *and displayed. Something's were successfully done in CSS, but other things like fonts were an issue. So
	 *it was easier to invoke it in java code instead.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        try {
        	Font font = Font.loadFont(new FileInputStream(new File("src/application/fonts/Electrolize-Regular.ttf")), 40);
        	mainTitle.setFont(font);
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
	}

	public void setStage(Stage primaryStage) {
		this.stage = primaryStage;
		
	}
}
