package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/** 4/1/2022
 * This is the Credentials class that process each credentials that is passed through the construct
 * @authors Garrett Ashley, Evan Ashley, Eddie Morales, Eduardo Riveragarza
 *
 */
public class Credentials {
	private SimpleStringProperty email;
    private SimpleStringProperty password;
	private SimpleStringProperty url;
	private SimpleStringProperty notes;
	

	/**
	 * This is a constructor that takes in each credentials and processes it into an object
	 * @param email of the account
	 * @param password of the account
	 * @param url of the website
	 * @param notes any notes to use when using that account
	 */

	public Credentials(String email, String password, String url, String notes) {
		this.email = new SimpleStringProperty(email);
		this.password =  new SimpleStringProperty(password);
		this.url =  new SimpleStringProperty(url);
		this.notes =  new SimpleStringProperty(notes);
	}

	/**
	 * This method gets the username or email of the selected object
	 * @return the email of the account
	 */
	public String getEmail() {
		return email.get();
	}
	
	/**
	 * This sets a new email or username of the selected object
	 * @return the new email or username of the account
	 */
	public void setEmail(String email) {
		this.email.set(email);
	}

	/**
	 * This method gets the password of the selected object
	 * @return the password of the account
	 */
	
	public String getPassword() {
		return password.get();
	}
	/**
	 * This sets a new password of the selected object
	 * @return the new password of the account
	 */
	
	public void setPassword(String password) {
		this.password.set(password);
	}
	/**
	 * This method gets the url of the selected object
	 * @return the url of the website
	 */
	public String getUrl() {
		return url.get();
	}
	/**
	 * This sets a new URL of the selected object
	 * @return the new URL of the account
	 */
	
	public void setUrl(String url) {
		this.url.set(url);
	}
	/**
	 * This method gets the notes of the selected object
	 * @return the notes to note about the account
	 */
	public String getNotes() {
		return notes.get();
	}
	/**
	 * This sets a new note of the selected object
	 * @return the new notes of the account
	 */
	public void setNotes(String notes) {
		this.notes.set(notes);
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	public StringProperty passwordProperty() {
		return password;
	}
	public StringProperty urlProperty() {
		return url;
	}
	public StringProperty notesProperty() {
		return notes;
	}






}
