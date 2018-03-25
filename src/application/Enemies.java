package application;

import java.io.File;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Enemies {

	Sprite NMI;
	ImageView nmView; 
	Image N1 = new Image("file:ImageData\\bulletbill.gif");
	private double V;
	private boolean Exists = true;
	public int enemC = 0;
	public long score = 0;
	String pathGO = "musics/GameOver.mp3";
	Media mediaGO = new Media(new File(pathGO).toURI().toString());
	public void SetPRand(){
		
		Random Rand = new Random();
		double py = 580 * Rand.nextDouble();
		double px = 1400;
		NMI = new Sprite(N1);
		NMI.positionX = px;
		NMI.positionY = py;
		nmView = new ImageView(N1);
		nmView.setLayoutX(px);
		nmView.setLayoutY(py);
		nmView.setFitWidth(250);
		nmView.setFitHeight(150);
		nmView.preserveRatioProperty().set(true);
		NMI.width = nmView.getFitWidth();
		NMI.height = nmView.getFitHeight();
		V = 35.0 * Rand.nextDouble();
	}
	
	public void IncSpeed(){
		
		V = V + 2.5;
	}
	
	public void Spawn(Character main, Group G, Stage P, Timer tim, MediaPlayer op){
		
		 AnimationTimer t = new AnimationTimer(){

				@Override
				public void handle(long timestamp) {
					if(Exists == true){
					nmView.setLayoutX(nmView.getLayoutX() - V);
					NMI.setPosition(nmView.getLayoutX(), nmView.getLayoutY());
					main.Score += colision(main, G, P, tim, op);
					if(main.shots.isEmpty() == false){
						
						for(int i = 0; i < main.shots.size(); i++){
							
							main.Score += colision(main.shots.get(i), G);
						}
					} 
					if(nmView.getLayoutX() < (0 - (nmView.getFitWidth() + 20))){
						
						this.stop();
						NMI.deleteEnemy();
					} else main.EnemyCount += enemC;
						
					}
				}
					
				};
		 t.start();
	}
	
    public int colision(Shot S, Group G){
    	
    	int counter = 0; 
    	if(S.Exists == true){
    	if (NMI.intersects(S.shotS) == true) {
    		
    		Exists = false;
    		counter = 20;
    		enemC = 1;
    		S.Exists = false;
    		Image Explo = new Image("file:ImageData\\explosion.gif");
    		ImageView Quick = new ImageView(Explo);
    		Quick.setLayoutX(nmView.getLayoutX());
    		Quick.setLayoutY(nmView.getLayoutY());
    		Quick.setFitHeight(120);
    		Quick.setFitWidth(120);
    		Quick.preserveRatioProperty().set(true);
    		
    		Timeline timeline = new Timeline(
	                new KeyFrame(Duration.ZERO, new KeyValue(Quick.imageProperty(), Explo)),
	                new KeyFrame(Duration.millis(705), new KeyValue(Quick.imageProperty(), null))
	                );
    		
    		G.getChildren().remove(nmView);
    		G.getChildren().remove(S.shotView);
    		G.getChildren().add(Quick);
    		timeline.play();
    		NMI.deleteEnemy();
    	}else counter = 0;

    	}
		return counter;
    	}
    	
    	public int colision(Character main, Group G, Stage P, Timer tim, MediaPlayer op){
        	
    		int counter = 0;
        	if(main.Exists == true && main.hp > 0){
        	if (NMI.intersects(main.mChar) == true) {
        		
        		main.hp --;
        		Exists = false;
        		Image Explo = new Image("file:ImageData\\explosion.gif");
        		ImageView Quick = new ImageView(Explo);
        		Quick.setLayoutX(nmView.getLayoutX());
        		Quick.setLayoutY(nmView.getLayoutY());
        		Quick.setFitHeight(240);
        		Quick.setFitWidth(240);
        		Quick.preserveRatioProperty().set(true);
        		
        		Timeline timeline = new Timeline(
    	                new KeyFrame(Duration.ZERO, new KeyValue(Quick.imageProperty(), Explo)),
    	                new KeyFrame(Duration.millis(705), new KeyValue(Quick.imageProperty(), null))
    	                );
        		
        		G.getChildren().remove(nmView);
        		G.getChildren().add(Quick);
        		timeline.play();
        		NMI.deleteEnemy();
        		counter = 20;
        	}else counter = 0;
    }	if(main.hp <= 0){
    	
    	 
    	op.stop();
    	MediaPlayer GO = new MediaPlayer(mediaGO);
    	GO.play();
    	GO.setStopTime(Duration.seconds(10));
    	tim.seconds = 256; 
    	G.getChildren().clear();
    	Image E = new Image("file:ImageData\\explosion.gif");
    	ImageView O = new ImageView(E);
    	O.setFitHeight(720);
    	O.setFitWidth(1280);
    	O.preserveRatioProperty().set(true);
    	Label lb = new Label("GAME OVER");

    	HBox hb = new HBox();
    	hb.setLayoutX(423);
    	hb.setMaxWidth(423);
    	hb.setMinWidth(423);
    	lb.setFont(Font.font("Courier", 36));
    	Button ex = new Button("EXIT");
    	ex.setFont(Font.font("Courier", 36));
    	hb.getChildren().addAll(lb, ex);
    	G.getChildren().addAll(O, hb);
    	ex.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				GO.stop();
				P.close();
			}
				
			});


    }
        	return counter;
    }
    	
    	public int Scorer(int op){
    		
    		return op;
    	}
}
