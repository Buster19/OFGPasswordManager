package application.view;

import javafx.animation.RotateTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Animations {
	
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
	public void fillAnimation(Circle c, Color color) {
		StrokeTransition stroke = new StrokeTransition(); 
	    stroke.setAutoReverse(false);   
	    stroke.setCycleCount(1);  
	    stroke.setDuration(Duration.millis(300));       
	    stroke.setToValue(color);    
	    stroke.setShape(c);  
	    stroke.play();
	}
	

}
