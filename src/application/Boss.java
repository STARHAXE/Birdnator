package application;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Boss{

	Image boss = new Image("file:ImageData\\exoboss.gif");
	ImageView bossV = new ImageView(boss);
	Sprite Bspr = new Sprite(boss);
	public int enemC = 0;
	public long score = 0;
	public int Bhp = 80;
	public boolean bossex = true;
	public void SpawnB(Character main, Group G, Stage P){

		
		bossV.setFitHeight(450);
		bossV.setFitWidth(450);
		bossV.preserveRatioProperty().set(true);
		Bspr.width = bossV.getFitWidth() - 80;
		Bspr.height = bossV.getFitHeight() - 50;
		bossV.setLayoutX(1000);
		bossV.setLayoutY(720 - Bspr.height);
		 AnimationTimer t = new AnimationTimer(){

				@Override
				public void handle(long timestamp) {
					if(bossex == true){
					bossV.setLayoutX(bossV.getLayoutX() - 0.5);
					Bspr.setPosition(bossV.getLayoutX(), bossV.getLayoutY());
					Bspr.resize(-35, -35, 0, 0);
					if(main.shots.isEmpty() == false){
						
						for(int i = 0; i < main.shots.size(); i++){
							
							colisionB(main.shots.get(i), G);
						}
					} 
					if(bossV.getLayoutX() < (0 - (bossV.getFitWidth() + 20))){
						
						this.stop();
						Bspr.deleteEnemy();
					} else main.EnemyCount += enemC;
						
					} else {
						
						main.Score += 500;
						this.stop();
					}
				}
					
				};
		 t.start();
	}
	
	public void colisionB(Shot S, Group G){
    	
    	if(S.Exists == true){
    	if (Bspr.intersects(S.shotS) == true && bossex == false) {
    		
    		
    		enemC = 1;
    		S.Exists = false;
    		Image Explo = new Image("file:ImageData\\explosion.gif");
    		ImageView Quick = new ImageView(Explo);
    		Quick.setLayoutX(bossV.getLayoutX());
    		Quick.setLayoutY(bossV.getLayoutY());
    		Quick.setFitHeight(400);
    		Quick.setFitWidth(400);
    		Quick.preserveRatioProperty().set(true);
    		
    		Timeline timeline = new Timeline(
	                new KeyFrame(Duration.ZERO, new KeyValue(Quick.imageProperty(), Explo)),
	                new KeyFrame(Duration.millis(1500), new KeyValue(Quick.imageProperty(), null))
	                );
    		
    		G.getChildren().remove(bossV);
    		G.getChildren().remove(S.shotView);
    		G.getChildren().add(Quick);
    		timeline.play();
    		Bspr.deleteEnemy();
    	} else if (Bspr.intersects(S.shotS) == true && bossex == true){
    		
    		
    		if(S.Exists == true){
    			
    			Bhp --;
    			S.Exists = false;
    		}
    		G.getChildren().remove(S.shotView);
    		S.shotS.deleteShot();
    		Image Explo = new Image("file:ImageData\\explosion.gif");
    		ImageView Quick = new ImageView(Explo);
    		Quick.setLayoutX(bossV.getLayoutX());
    		Quick.setLayoutY(bossV.getLayoutY());
    		if (Bhp <= 0){
    			
    			bossex = false;
    			bossV.setImage(Explo);
    			
    		}
    		Quick.setFitHeight(125);
    		Quick.setFitWidth(125);
    		Quick.preserveRatioProperty().set(true);
    		
    		Timeline timeline = new Timeline(
	                new KeyFrame(Duration.ZERO, new KeyValue(Quick.imageProperty(), Explo)),
	                new KeyFrame(Duration.millis(750), new KeyValue(Quick.imageProperty(), null))
	                );
    		G.getChildren().remove(S.shotView);
    		S.shotS.deleteShot();
    	}

    	}
		
    	}
}
