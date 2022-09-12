package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ConnectionClass {
	//jdbc:mysql://localhost:3306/?user=root
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	static LocalDateTime now = LocalDateTime.now();

	static Statement st;
	static ResultSet rs;
	static Connection con;
	static ResultSet res;

	final static String dbUrl = "jdbc:mysql://127.0.0.1:3306/FitnessApp";
	final static String user = "root";
	final static String pass = "partizan95";
	
	static int UserID;
	

	
	
	
	public static void connect() { // method to connect to the database

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/FitnessApp", "root", "partizan95"); // Get a
																											// Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected");

		} catch (Exception e) {
			System.out.println("Error did not connect ");
		}
	}
	
	public static void addUser() {  //Method to create Account
		
	
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/FitnessApp", "root", "partizan95"); // Get a
			// Connection
			st = con.createStatement(); // Create a statement to view			
			String sql = "insert into Users "
						+ "(Username,Password,Height,DateOfBirth)"
						+ "values ('" + MainController.NewUsername + "','" + MainController.NewConfirmPassword+ "','" + MainController.NewHeight + "','" + MainController.myDate + "')";

			st.executeUpdate(sql);
			System.out.println("update compltete");

		} catch (Exception e) {
			System.out.println("Error the update failed");
		}
		
	}
	
	public static void getUserID() {   	//Method to get the ID of the user by using their Username
	
	System.out.println(MainController.Uname);	
	try {
		String sql = "Select ID from Users WHERE Username = '" + MainController.Uname + "';";
	
			st = con.createStatement();  //create a statement
			res = st.executeQuery(sql);
			
			while(res.next()) {
			 UserID = res.getInt("ID");
			 System.out.println("The id is" + UserID);
			System.out.println("Database worked perfect");
			}
			
			

	}
			
		
		catch (Exception e) {
			System.out.println("Error the select failed");
		}
			
		}
	
	public static void logWeight() {		//method for logging the weight to the Database
		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/FitnessApp", "root", "partizan95"); // Get a
			// Connection
			st = con.createStatement(); // Create a statement to view			
			String sql = "insert into Weight "
						+ "(ID,weight,bodyfat,date)"
						+ "values ('" + UserID + "','" + MainController.Weight+ "','" + MainController.Bodyfat + "','" + MainController.now + "')";

			st.executeUpdate(sql);
			System.out.println("update compltete");

		} catch (Exception e) {
			System.out.println("Error the update failed");
		}
	}
	
	public static void logCalories() {
		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/FitnessApp", "root", "partizan95"); // Get a
			// Connection
			st = con.createStatement(); // Create a statement to view			
			String sql = "insert into Calories "
						+ "(ID,Calories,date)"
						+ "values ('" + UserID + "','" + MainController.Calories + "','" + MainController.now + "')";

			st.executeUpdate(sql);
			System.out.println("update compltete");

		} catch (Exception e) {
			System.out.println("Error the update failed");
		}
		
	}
	
	public static void logExercise() {
		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/FitnessApp", "root", "partizan95"); // Get a
			// Connection
			st = con.createStatement(); // Create a statement to view			
			String sql = "insert into ExerciseLog "
						+ "(ID,,Exercise,Weight,Reps,date)"
						+ "values ('" + UserID + "','" + MainController.ExerciseChoice + "','" +  "','" + MainController.ExerciseWeight + "','" + MainController.ExerciseReps + "','" + MainController.now + "')";

			st.executeUpdate(sql);
			System.out.println("update compltete");

		} catch (Exception e) {
			System.out.println("Error the update failed");
		}
		
		
	}
	

	
	
	
	
	
	
	
	
	
	
	}










