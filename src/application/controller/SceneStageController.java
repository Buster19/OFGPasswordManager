package application.controller;

import javafx.stage.Stage;

public class SceneStageController {
	/**
	 * This global variable contains the root stage that was passed by the setStage method
	 */
	private Stage stage;
	/**
	 * This method gets the root stage and makes it equal with the stage that can be used
	 * across each scene
	 * @param primaryStage the root stage that is passed
	 */
	public void setStage(Stage primaryStage) {
		this.stage = primaryStage;
		
	}
	/**
	 * This method gets the root stage that was passed in the setStage method
	 * and allows the new scene to use the root stage.
	 * @return the stage that was passed through the setStage method
	 */
	public Stage getStage() {
		return this.stage;
	}
}
