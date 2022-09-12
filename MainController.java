package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class MainController implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;


	
	//Login Screen Details
	@FXML
	private Label welcome;  //Welcome label on login screen
	@FXML
	private Label NameHere;  //label on login screen
	@FXML
	private Label LogoHere;  //label on login screen
	@FXML

	private TextField txtUsername;  //textfield to enter username in login screen
	@FXML
	private TextField txtPassword;  //textfield to enter password in login screen


	//Create Account Details
	@FXML 
	private Label createAccountlbl;  //main label on the create account page
	@FXML 
	private DatePicker DobBox;   //date of birth picker on create account page
	@FXML
	private TextField Height;  //The Users Height
	@FXML
	private TextField CreateUsernametxt;  //textbox to enter username in the create account page
	@FXML
	private TextField CreatePassword;  //textfield to create a password in the create account page
	@FXML
	private TextField ConfirmPassword;  //textfield to confirm entered password in the create account page
	@FXML 
	private Button ConfirmCreateAccount; //Button to confirm on the create account page

	public static String NewUsername;  //String to store the create username

	public static String NewPassword;  //String to store the new users password

	public static String NewConfirmPassword;   //String for the users confirmed password

	public static int NewHeight;   //Int for the new height
	//main screen
	@FXML
	private Label Usernamelbl;   				//label to show username on main screen
	public static LocalDate myDate;				//Date Entered by the User on create Account Screen

	public static String Uname;

	//Log Weight Screen
	@FXML
	private TextField Weighttxt;
	@FXML
	private TextField Bodyfattxt;
	@FXML 
	private Button LogWeightBtn;

	public static float Weight;
	public static float Bodyfat;  //Variables to store the input of the weight and bodyfat textfields

	public static LocalDateTime now;		//Variable to store the current date


	//Log Calories Screen
	@FXML
	private TextField Caloriestxt;	//Textfield to enter Calories
	public static float Calories;  //varibale to store daily calories
	
	
	//For The Log Weight Screen
	//weight and reps textfields
	@FXML
	private TextField ExerciseWeighttxt;
	@FXML
	private TextField Repstxt;
	@FXML 
	private Button LogExercisebtn;
	public static float ExerciseWeight;		//this will store the Exercise weight
	public static int ExerciseReps;			//This will store the Exercise Reps
	



	
	public static String ExerciseChoice;
	@FXML 
	private ChoiceBox<String> ExerciseChoiceBox;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
	
		ExerciseChoiceBox.getItems().add("Bench");
		ExerciseChoiceBox.getItems().add("Squat");
		ExerciseChoiceBox.getItems().add("Deadlift");
	}

	
	
	
	//for the button in the main menu
	@FXML
	private BorderPane bp;

	@FXML
	private AnchorPane ap;

	@FXML
	private void home(MouseEvent event) {
		bp.setCenter(ap);
	}
	@FXML
	private void weight(MouseEvent event) {

		loadPage("bmi");

	}
	@FXML
	private void calories(MouseEvent event) {

		loadPage("Calories");

	}

	@FXML
	private void workout(MouseEvent event) {

		loadPage("workouts");

	}
	
	@FXML
	private void WeeklyReport(MouseEvent event) {

		loadPage("WeeklyReport");

	}
	
	@FXML
	private void YearlyReport(MouseEvent event) {

		loadPage("YearReport");

	}


	private void loadPage(String page) {

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {

			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
		}

		bp.setCenter(root);
	}

	//Method to verify login information	
	public void Login(ActionEvent event) throws Exception {   


		try {

			if (txtUsername.getText() != null && txtPassword.getText() != null) {
				Uname = txtUsername.getText();
				System.out.println(Uname);
				ConnectionClass.connect();
				String sqlt = "Select * from Users Where Username='" + txtUsername.getText()  + "' and Password='" + txtPassword.getText() + "'";
				ConnectionClass.rs = ConnectionClass.st.executeQuery(sqlt);
				if (ConnectionClass.rs.next()) {
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();			
					Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
					Scene scene = new Scene(root,700,450);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage.setScene(scene);
					stage.show();

				} else {

					System.out.println("Error wrong password and username");
				}
			} 

		} catch (SQLException err) {
			System.out.println("Error in input");
		}

	}



	public void ShowCreateAccount(ActionEvent event) throws Exception {  //method to display the create account screen

		Parent root = FXMLLoader.load(getClass().getResource("/application/CreateAccount.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void getDate (ActionEvent event) {
		myDate = DobBox.getValue();

	}

	public void CreateAccount(ActionEvent event) throws Exception {  //method to verify create account details when create button is pressed



		NewHeight = Integer.parseInt(Height.getText());  //here i will pare the string in the textfield to an int and store it in new height
        NewUsername = CreateUsernametxt.getText();
		NewPassword = CreatePassword.getText();
		NewConfirmPassword = ConfirmPassword.getText();



		if( NewPassword.equals(NewConfirmPassword) &&  NewUsername != null && NewPassword != null && NewConfirmPassword != null && DobBox !=null) {
			System.out.println("Successfully entry of details");
			ConnectionClass.addUser();


		} else {

			System.out.println("Error in Input Please Fill in all of the fields");

		}
	}





	public void displayUsername() {
		String username = txtUsername.getText();	//String to store username of user	
		Usernamelbl.setText("Hello: " + username);
	}



	public void getDate() {		//method to get the current date on the system
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		now = LocalDateTime.now();  
		System.out.println(dtf.format(now));  
	}





	public void WriteWeigt()			//method for logging the weight into the Database

	{


		if(Weighttxt != null && Bodyfattxt != null) {
			Weight = Float.parseFloat(Weighttxt.getText());
			Bodyfat =  Float.parseFloat(Bodyfattxt.getText());
			getDate();
			ConnectionClass.getUserID();
			ConnectionClass.logWeight();
		}
		else {
			System.out.println("Error please enter a weight and bodyfat");

		}
	}

	public void WriteCalories() {

		if(Caloriestxt !=null) {
			Calories =  Float.parseFloat(Caloriestxt.getText());
			getDate();
			ConnectionClass.getUserID();
			ConnectionClass.logCalories();

		}else {

		}
	}
	
	public void WriteExercise() {

		if(ExerciseWeighttxt !=null && Repstxt !=null) {
			ExerciseWeight = Float.parseFloat(ExerciseWeighttxt.getText());
			ExerciseReps = Integer.parseInt(Repstxt.getText());
			//need to add my choicebox selection here to get which exercise
			getDate();
			//System.out.println(ExerciseChoice);			
			ConnectionClass.getUserID();
			ConnectionClass.logExercise();

		}else {

		}
	}
	
	/*public void WriteSquats() {
		if(Squattxt !=null) {
			SquatWeight = Float.parseFloat(Squattxt.getText());
			SquatReps = Integer.parseInt(SquatRepstxt.getText());
			getDate();
			ConnectionClass.getUserID();
			ConnectionClass.logSquat();

		}else {

		}
		
		
	}*/
	
	/*public void WrieDeadlifts() {
		if(Deadtxt !=null) {
			DeadWeight = Float.parseFloat(Deadtxt.getText());
			DeadReps = Integer.parseInt(DeadRepstxt.getText());
			getDate();
			ConnectionClass.getUserID();
			
			
			
			
			
			
			
			
			
			
			
			ConnectionClass.logDeadlift();

		}else {

		}
		
		
	}*/
	
	
	public void showBench() {
		 
	}


	
	
	
	







}
