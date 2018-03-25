package application;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Scenario {

	Image MenuI = new Image("file:ImageData\\menug.gif");
	Image S1 = new Image("file:ImageData\\cascade_scenario.gif");
	Image S2 = new Image("file:ImageData\\farm_scenario.gif");
	Image S3 = new Image("file:ImageData\\night_scenario.gif");
	Image S4 = new Image("file:ImageData\\woods_scenario.gif");
	Image POW = new Image("file:ImageData\\power.gif");
	Image title = new Image("file:ImageData\\bird.png");
	ImageView pw1 = new ImageView(new Image("file:ImageData\\pw1.png"));
	ImageView pw2 = new ImageView(new Image("file:ImageData\\pw2.png"));
	ImageView pw3 = new ImageView(new Image("file:ImageData\\pw3.png"));
	ImageView SET = new ImageView();
	MediaPlayer mediaPlayer;
	String pathG = "musics/GourmetRace.mp3";
	Media mediaG = new Media(new File(pathG).toURI().toString());
	String pathP = "musics/Weapon.mp3";
	Media mediaP = new Media(new File(pathP).toURI().toString());
	String pathV = "musics/Volcano.mp3";
	Media mediaV = new Media(new File(pathV).toURI().toString());

	int RandSceO = 2;
	long FINALSCORE = 0;
	int WAVEC = 1;
	int HPD = 0;
	public Scenario(){
		
	}
	
	public void SetMenu(Stage P){
		
		Group Back = new Group();
		Scene SA = new Scene(Back, 1280, 720);
		String path = "musics/Nitrogen.mp3";
		Media media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		SET.setImage(MenuI);
		SET.setFitHeight(850);
		SET.setFitWidth(1400);
		SET.preserveRatioProperty().set(true);
		Button Start = new Button("COMENZAR\n EL JUEGO");
		Start.setLayoutX(600);
		Start.setLayoutY(680);
		Start.setStyle("-fx-font: 28 arial;");
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Ancient Alien Bird",
			        "War Bird",
			        "Venom Bird"
			    );
		final ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setItems(options);
		comboBox.setPromptText("ESCOJA UN AVE");
		comboBox.setStyle("-fx-font: 28 arial;");
		comboBox.setLayoutX(300);
		comboBox.setLayoutY(300);
		comboBox.setStyle("-fx-font: 28 arial;");
		Button cred = new Button("CRÉDITOS");
		Button exit = new Button("SALIR");
		Button how = new Button("CÓMO JUGAR");
		cred.setStyle("-fx-font: 28 arial;");
		exit.setStyle("-fx-font: 28 arial;");
		how.setStyle("-fx-font: 28 arial;");
		HBox n = new HBox();
		ImageView niiu = new ImageView(title);
		niiu.setLayoutX(850);
		niiu.setFitHeight(200);
		niiu.setFitWidth(400);
		niiu.preserveRatioProperty().set(true);
		n.setLayoutY(360);
		n.getChildren().addAll(comboBox, Start, cred, how, exit);
		Back.getChildren().addAll(SET, n, niiu);
		comboBox.setOnAction( event ->{
			
			String O = comboBox.getSelectionModel().getSelectedItem();
			if(O == "Ancient Alien Bird"){
				
				RandSceO = 3;
			} else if (O == "War Bird"){
				
				RandSceO = 1;
			} else if (O == "PeliBird"){
				
				RandSceO = 2;
			}
		});
		
		P.setScene(SA);
		P.show();

		Start.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					
					SET.preserveRatioProperty().set(false);
					RandScenario(P, 0);
				}
					
				});
		exit.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				P.close();
			}
				
			});
		cred.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				creditos(P);
			}
				
			});
		how.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				HOWTO(P);
			}
				
			});
	}
	
	public void RandScenario(Stage P, int power){
		
		Random R = new Random();
		int Rc = R.nextInt(4) + 1;
		if(WAVEC < 5){
		switch(Rc){
		case 1:{
			
			
			P.close();
			mediaPlayer.stop();
			MediaPlayer Game = new MediaPlayer(mediaG);
			Game.play();
			int P1 = 0;
			int P2 = 0;
			if(power == 3){
				
				HPD ++;
			}else if (power == 2){
				
				P2 = 3;
			}else if (power == 1){
				
				P1 = 120;
			}
			WAVEC ++;
			BooleanProperty starter = new SimpleBooleanProperty(false);
			Group Sce = new Group();
			Scene aux = new Scene(Sce, 1280, 740);
	 	
			SET.setImage(S1);
			SET.setFitHeight(740);
			SET.setFitWidth(1280);
			Character main = new Character();
			main.WaveCount++;
			main.hp += HPD;
			main.SetChar(RandSceO);
			main.showBird(Sce, aux);
        	Label HitPoint = new Label();
        	HitPoint.setLayoutX(0);
        	HitPoint.setLayoutY(0);
        	HitPoint.setText("HP: " + main.hp);
        	HitPoint.setTextFill(Color.WHITESMOKE);
        	HitPoint.setFont(Font.font("Courier", 30));
        	Label Score = new Label();
        	Score.setText("SCORE: " + FINALSCORE);
        	Score.setLayoutX(1000);
        	Score.setLayoutY(0);
        	Score.setTextFill(Color.WHITESMOKE);
        	Score.setFont(Font.font("Courier", 30)); 
        	Timer time = new Timer(15 + P2);
        	time.start(); 			
        	Sce.getChildren().addAll(SET, main.mainChar, Score, HitPoint, time.lb);
			LinkedList<Enemies> ENE = new  LinkedList<Enemies>();
			
            	Timeline timeline = new Timeline(
	                new KeyFrame(Duration.millis(405 - P1), e -> {
	                  	ENE.add(new Enemies());

	                  	ENE.getLast().SetPRand();
	                  	Sce.getChildren().addAll(ENE.getLast().nmView);
	                  	ENE.getLast().Spawn(main, Sce, P, time, Game);
	                  	ENE.getLast().colision(main, Sce, P, time, Game);
	                 
	                  	FINALSCORE += main.Score;
	                  	HitPoint.setText("HP: " + main.hp);
	                  	Score.setText("SCORE: " + FINALSCORE);
	
	                  	if(time.seconds == 0){
	                  		starter.set(true);
	                  	}
	                })
	                
            			);
            	timeline.setDelay(Duration.millis(200 - P1));
            	timeline.setCycleCount(main.EnemySpawn);
            	timeline.play();
 				starter.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							HPD = main.hp - 5;
							timeline.stop();
							Game.stop();
							POWS(P);
						}}
					}
				);

			P.setScene(aux);
			P.show();
		}break;
		case 2:{
			
			P.close();
			int P1 = 0;
			int P2 = 0;
			if(power == 3){
				
				HPD ++;
			}else if (power == 2){
				
				P2 = 3;
			}else if (power == 1){
				
				P1 = 120;
			}
			WAVEC ++;
			BooleanProperty starter = new SimpleBooleanProperty(false);
			Group Sce = new Group();
			Scene aux = new Scene(Sce, 1280, 740);
			mediaPlayer.stop();
			MediaPlayer Game = new MediaPlayer(mediaG);
			Game.play();
			SET.setImage(S2);
			SET.setFitHeight(740);
			SET.setFitWidth(1280);
			Character main = new Character();
			main.WaveCount++;
			main.hp += HPD;
			main.SetChar(RandSceO);
			main.showBird(Sce, aux);
        	Label HitPoint = new Label();
        	HitPoint.setLayoutX(0);
        	HitPoint.setLayoutY(0);
        	HitPoint.setText("HP: " + main.hp);
        	HitPoint.setTextFill(Color.WHITESMOKE);
        	HitPoint.setFont(Font.font("Courier", 30));
        	Label Score = new Label();
        	Score.setText("SCORE: " + FINALSCORE);
        	Score.setLayoutX(1000);
        	Score.setLayoutY(0);
        	Score.setTextFill(Color.WHITESMOKE);
        	Score.setFont(Font.font("Courier", 30)); 
        	Timer time = new Timer(15 + P2);
        	time.start(); 			
        	Sce.getChildren().addAll(SET, main.mainChar, Score, HitPoint, time.lb);
			LinkedList<Enemies> ENE = new  LinkedList<Enemies>();
			
            	Timeline timeline = new Timeline(
	                new KeyFrame(Duration.millis(405 - P1), e -> {
	                  	ENE.add(new Enemies());

	                  	ENE.getLast().SetPRand();
	                  	Sce.getChildren().addAll(ENE.getLast().nmView);
	                  	ENE.getLast().Spawn(main, Sce, P, time, Game);
	                  	ENE.getLast().colision(main, Sce, P, time, Game);
	                 
	                  	FINALSCORE += main.Score;
	                  	HitPoint.setText("HP: " + main.hp);
	                  	Score.setText("SCORE: " + FINALSCORE);
	
	                  	if(time.seconds == 0){
	                  		starter.set(true);
	                  	}
	                })
	                
            			);
            	timeline.setDelay(Duration.millis(200 - P1));
            	timeline.setCycleCount(main.EnemySpawn);
            	timeline.play();
 				starter.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							HPD = main.hp - 5;
							timeline.stop();
							Game.stop();
							POWS(P);
						}}
					}
				);

			P.setScene(aux);
			P.show();
		}break;
		case 3:{
			
			P.close();
			int P1 = 0;
			int P2 = 0;
			if(power == 3){
				
				HPD ++;
			}else if (power == 2){
				
				P2 = 3;
			}else if (power == 1){
				
				P1 = 120;
			}
			WAVEC ++;
			BooleanProperty starter = new SimpleBooleanProperty(false);
			Group Sce = new Group();
			Scene aux = new Scene(Sce, 1280, 740);
			mediaPlayer.stop();
			MediaPlayer Game = new MediaPlayer(mediaG);
			Game.play();
			SET.setImage(S3);
			SET.setFitHeight(740);
			SET.setFitWidth(1280);
			Character main = new Character();
			main.WaveCount++;
			main.hp += HPD;
			main.SetChar(RandSceO);
			main.showBird(Sce, aux);
        	Label HitPoint = new Label();
        	HitPoint.setLayoutX(0);
        	HitPoint.setLayoutY(0);
        	HitPoint.setText("HP: " + main.hp);
        	HitPoint.setTextFill(Color.WHITESMOKE);
        	HitPoint.setFont(Font.font("Courier", 30));
        	Label Score = new Label();
        	Score.setText("SCORE: " + FINALSCORE);
        	Score.setLayoutX(1000);
        	Score.setLayoutY(0);
        	Score.setTextFill(Color.WHITESMOKE);
        	Score.setFont(Font.font("Courier", 30)); 
        	Timer time = new Timer(15 + P2);
        	time.start(); 			
        	Sce.getChildren().addAll(SET, main.mainChar, Score, HitPoint, time.lb);
			LinkedList<Enemies> ENE = new  LinkedList<Enemies>();
			
            	Timeline timeline = new Timeline(
	                new KeyFrame(Duration.millis(405 - P1), e -> {
	                  	ENE.add(new Enemies());

	                  	ENE.getLast().SetPRand();
	                  	Sce.getChildren().addAll(ENE.getLast().nmView);
	                  	ENE.getLast().Spawn(main, Sce, P, time, Game);
	                  	ENE.getLast().colision(main, Sce, P, time, Game);
	                 
	                  	FINALSCORE += main.Score;
	                  	HitPoint.setText("HP: " + main.hp);
	                  	Score.setText("SCORE: " + FINALSCORE);
	
	                  	if(time.seconds == 0){
	                  		starter.set(true);
	                  	}
	                })
	                
            			);
            	timeline.setDelay(Duration.millis(200 - P1));
            	timeline.setCycleCount(main.EnemySpawn);
            	timeline.play();
 				starter.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							HPD = main.hp - 5;
							timeline.stop();
							Game.stop();
							POWS(P);
						}}
					}
				);

			P.setScene(aux);
			P.show();
		}break;
		case 4:{
			
			P.close();
			int P1 = 0;
			int P2 = 0;
			if(power == 3){
				
				HPD ++;
			}else if (power == 2){
				
				P2 = 3;
			}else if (power == 1){
				
				P1 = 120;
			}
			WAVEC ++;
			BooleanProperty starter = new SimpleBooleanProperty(false);
			Group Sce = new Group();
			Scene aux = new Scene(Sce, 1280, 740);
			mediaPlayer.stop();
			MediaPlayer Game = new MediaPlayer(mediaG);
			Game.play();
			SET.setImage(S4);
			SET.setFitHeight(740);
			SET.setFitWidth(1280);
			Character main = new Character();
			main.WaveCount++;
			main.hp += HPD;
			main.SetChar(RandSceO);
			main.showBird(Sce, aux);
        	Label HitPoint = new Label();
        	HitPoint.setLayoutX(0);
        	HitPoint.setLayoutY(0);
        	HitPoint.setText("HP: " + main.hp);
        	HitPoint.setTextFill(Color.WHITESMOKE);
        	HitPoint.setFont(Font.font("Courier", 30));
        	Label Score = new Label();
        	Score.setText("SCORE: " + FINALSCORE);
        	Score.setLayoutX(1000);
        	Score.setLayoutY(0);
        	Score.setTextFill(Color.WHITESMOKE);
        	Score.setFont(Font.font("Courier", 30)); 
        	Timer time = new Timer(15 + P2);
        	time.start(); 			
        	Sce.getChildren().addAll(SET, main.mainChar, Score, HitPoint, time.lb);
			LinkedList<Enemies> ENE = new  LinkedList<Enemies>();
			
            	Timeline timeline = new Timeline(
	                new KeyFrame(Duration.millis(405 - P1), e -> {
	                  	ENE.add(new Enemies());

	                  	ENE.getLast().SetPRand();
	                  	Sce.getChildren().addAll(ENE.getLast().nmView);
	                  	ENE.getLast().Spawn(main, Sce, P, time, Game);
	                  	ENE.getLast().colision(main, Sce, P, time, Game);
	                 
	                  	FINALSCORE += main.Score;
	                  	HitPoint.setText("HP: " + main.hp);
	                  	Score.setText("SCORE: " + FINALSCORE);
	
	                  	if(time.seconds == 0){
	                  		starter.set(true);
	                  	}
	                })
	                
            			);
            	timeline.setDelay(Duration.millis(200 - P1));
            	timeline.setCycleCount(main.EnemySpawn);
            	timeline.play();
 				starter.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							HPD = main.hp - 5;
							Game.stop();
							timeline.stop();
							POWS(P);
						}}
					}
				);

			P.setScene(aux);
			P.show();
		}break;
		}
			
		} else {
			
			P.close();
			MediaPlayer Bp = new MediaPlayer(mediaV);
			Bp.play();
			WAVEC = 1;
			BooleanProperty starter = new SimpleBooleanProperty(false);
			Group Sce = new Group();
			Scene aux = new Scene(Sce, 1280, 720);
			SET.setImage(new Image("file:ImageData\\boss.gif"));
			SET.setFitHeight(720);
			SET.setFitWidth(1280);
			Character main = new Character();
			main.WaveCount++;
			main.hp += HPD;
			main.SetChar(RandSceO);
			main.showBird(Sce, aux);
        	Label HitPoint = new Label();
        	HitPoint.setLayoutX(0);
        	HitPoint.setLayoutY(0);
        	HitPoint.setText("HP: " + main.hp);
        	HitPoint.setTextFill(Color.WHITESMOKE);
        	HitPoint.setFont(Font.font("Courier", 30));
        	Label Score = new Label();
        	Score.setText("SCORE: " + FINALSCORE);
        	Score.setLayoutX(1000);
        	Score.setLayoutY(0);
        	Score.setTextFill(Color.WHITESMOKE);
        	Score.setFont(Font.font("Courier", 30)); 
        	Timer time = new Timer(15);
        	time.start();
        	Boss BAWS = new Boss();
			BAWS.SpawnB(main, Sce, P);
			Sce.getChildren().addAll(SET, main.mainChar, Score, HitPoint, time.lb, BAWS.bossV);
			LinkedList<Enemies> ENE = new  LinkedList<Enemies>();
			
            //for(int i = 0; i < main.EnemyCount; i++){
          	
            	Timeline timeline = new Timeline(
	                new KeyFrame(Duration.millis(405), e -> {
	                  	ENE.add(new Enemies());

	                  	ENE.getLast().SetPRand();
	                  	Sce.getChildren().addAll(ENE.getLast().nmView);
	                  	ENE.getLast().Spawn(main,Sce, P, time, Bp);
	                  	ENE.getLast().colision(main, Sce, P, time, Bp);
	                 
	                  	FINALSCORE += main.Score;
	                  	HitPoint.setText("HP: " + main.hp);
	                  	Score.setText("SCORE: " + FINALSCORE);
	                  	
	                  	if(time.seconds == 0){
	                  		starter.set(true);
	                  	}
	                })
	                
            			);
            	timeline.setDelay(Duration.millis(200));
            	timeline.setCycleCount(main.EnemySpawn);
            	timeline.play();
 				starter.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							
							timeline.stop();
							HPD = main.hp - 5;
							Bp.stop();
							POWS(P);
						}}
					}
				);
			//}
			P.setScene(aux);
			P.show();
		}
	}
	

	public void POWS(Stage P){
		
		P.close();
		MediaPlayer POWp = new MediaPlayer(mediaP);
		POWp.play();
		BooleanProperty starter = new SimpleBooleanProperty(false);
		Timer timer = new Timer(5);
		timer.start();
		Group Sce = new Group();
		Scene aux = new Scene(Sce, 1280, 720);
		SET.setImage(POW);
		SET.setFitHeight(720);
		SET.setFitWidth(1280);
		HBox pow = new HBox();
		HBox desc = new HBox();
		desc.setLayoutX(223);
		desc.setLayoutY(240);
		pow.setLayoutX(423);
		pow.setLayoutY(480);
		Label l1 = new Label ("1.- Por muchos siglos resolvió dudas y \n problemas entre personas \n al rededor del mundo. Si deseas\n usarlo de seguro te llevará a nuevos entendimientos\n de lo que en verdad es el dolor y el sufrimiento.");
		Label l2 = new Label("2.- Es como una amante que suchaste a tu \n mejor amigo. Tiene sus \n ventajas, pero en tu ébrio fondo sabes\n que el lakaso te llegará con odio y fuerza en la\n mañana del lunes en la U.");
		Label l3 = new Label("3.- Se dice que en la UPB este es un código\n  secreto para entrar al\n Wi-Fi, pero nadie sabe exactamente qué\n hace porque todos pasan más tiempo estudiando y\n chupando en lugar de vivir la U.");
		l1.setTextFill(Color.WHITESMOKE);
		l2.setTextFill(Color.WHITESMOKE);
		l3.setTextFill(Color.WHITESMOKE);
		Button b1 = new Button();
		Button b2 = new Button();
		Button b3 = new Button();
		b1.setGraphic(pw1);
		b2.setGraphic(pw2);
		b3.setGraphic(pw3);
		pow.getChildren().addAll(b1, b2, b3);
		desc.getChildren().addAll(l1, l2, l3);
		Sce.getChildren().addAll(SET, desc, pow, timer.lb);


    	Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(25), e -> {
                  	
                  	if(timer.seconds == 0){
                  		starter.set(true);
                  	}
                  	})
                
        			);
        	timeline.setDelay(Duration.millis(20));
        	timeline.setCycleCount(500);
        	timeline.play();
    		b1.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

    			@Override
    			public void handle(MouseEvent event) {
    				
    				POWp.stop();
    				RandScenario(P, 1);
    				timeline.stop();
    			}
    				
    			});
    		b2.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

    			@Override
    			public void handle(MouseEvent event) {
    				
    				POWp.stop();
    				RandScenario(P, 2);
    				timeline.stop();
    			}
    				
    			});
    		b3.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

    			@Override
    			public void handle(MouseEvent event) {
    				
    				POWp.stop();
    				RandScenario(P, 3);
    				timeline.stop();
    			}
    				
    			});
			starter.addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
				    
					if(arePressed){
						
						timeline.stop();
						POWp.stop();
						RandScenario(P, 0);
					}}
				}
			);
		P.setScene(aux);
		P.show();
	}    
	
	public void creditos(Stage P){
		
		P.close();
		Group G = new Group();
		Scene aux = new Scene(G, 1280, 720);
    	ImageView O = new ImageView(S4);
    	Image m = new Image("file:ImageData\\MATH.jpg");
    	Image k = new Image("file:ImageData\\KATE.jpg");
    	ImageView mV = new ImageView(m);
    	ImageView kV = new ImageView(k);
    	mV.setFitHeight(300);
    	mV.setFitWidth(300);
    	mV.preserveRatioProperty().set(true);
    	kV.setFitHeight(300);
    	kV.setFitWidth(300);
    	kV.preserveRatioProperty().set(true);
    	O.setFitHeight(720);
    	O.setFitWidth(1280);
    	O.preserveRatioProperty().set(true);
    	Label lb = new Label("CREDITOS");
    	Label mb = new Label("Matheo Alave Vargas\n haxeo11@gmail.com\n +591 70743589"); 
    	Label kb = new Label("Katherine Villarroel\n kathe181015@gmail.com\n +591 69468152"); 
    	HBox hb = new HBox();
    	hb.setLayoutX(0);
    	lb.setTextFill(Color.WHITESMOKE);
    	mb.setTextFill(Color.WHITESMOKE);
    	kb.setTextFill(Color.WHITESMOKE);
    	lb.setFont(Font.font("Courier", 36));
    	mb.setFont(Font.font("Courier", 24));
    	kb.setFont(Font.font("Courier", 24));
    	Button ex = new Button("EXIT");
    	ex.setFont(Font.font("Courier", 36));
    	hb.getChildren().addAll(lb, ex, mV, mb, kV, kb);
    	G.getChildren().addAll(O, hb);
    	ex.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				P.close();
			}
				
			});
    	
    	P.setScene(aux);
    	P.show();
	}
	
	public void HOWTO(Stage P){
		
		P.close();
		Group G = new Group();
		Scene aux = new Scene(G, 1280, 720);
    	ImageView O = new ImageView(S2);
    	O.setFitHeight(720);
    	O.setFitWidth(1280);
    	Label lb = new Label("COMO JUGAR");
    	Label mb = new Label("Presione las flechas para poder moverse\n y para disparar presione ESPACIO. \n Recuerde que usted posee cinco vidas \n y si las pierde, terminó su juego. \n ¡Usted es un ave a la que le están \n por robar sus huevos y necesita \n protegerlos a toda costa!"); 
    	Label kb = new Label("El juego consiste en superar la mayor \n cantidad de oleadas posibles sin \n morir, cada cierta cantidad de oleadas \n se mostrará un jefe y al terminar\n cada oleada se le dará la opción de \n escoger un power-up secreto\n (nadie sabe exactamente qué hace).\n ¡DISFRUTE EL JUEGO!"); 
    	HBox hb = new HBox();
    	hb.setLayoutX(0);
    	lb.setTextFill(Color.WHITESMOKE);
    	mb.setTextFill(Color.WHITESMOKE);
    	kb.setTextFill(Color.WHITESMOKE);
    	lb.setFont(Font.font("Courier", 36));
    	mb.setFont(Font.font("Courier", 24));
    	kb.setFont(Font.font("Courier", 24));
    	Button ex = new Button("EXIT");
    	ex.setFont(Font.font("Courier", 36));
    	hb.getChildren().addAll(ex, lb, mb, kb);
    	G.getChildren().addAll(O, hb);
    	ex.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				P.close();
			}
				
			});
    	
    	P.setScene(aux);
    	P.show();
	}
	
}
