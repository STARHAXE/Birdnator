package application;
	
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
			Scenario SMain = new Scenario();
			SMain.SetMenu(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
