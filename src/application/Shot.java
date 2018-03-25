package application;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shot{

	Image shotI = new Image("file:ImageData\\shot.gif");
	ImageView shotView = new ImageView(shotI);
	Sprite shotS = new Sprite(shotI);
	boolean Exists = true;
	private final double V = 18.5;
	
	public void shoot(ImageView mainChar, Group G){
		
		shotView.setLayoutX(mainChar.getLayoutX() + 90);
		shotView.setLayoutY(mainChar.getLayoutY() + 45);
		shotView.setFitHeight(80);
		shotView.setFitWidth(80);
		shotView.preserveRatioProperty().set(true);
		shotS.resize(20, 20, 0, 0);
		shotS.width = 35;
		shotS.height = 35;
		G.getChildren().add(shotView);
		
		 AnimationTimer t = new AnimationTimer(){

				@Override
				public void handle(long timestamp) {
					
					if(Exists == true){
					shotView.setLayoutX(shotView.getLayoutX() + V);
					shotS.setPosition(shotView.getLayoutX(), shotView.getLayoutY());

					if(shotView.getLayoutX() > (1360)){
						
						this.stop();
						shotS.deleteShot();
						G.getChildren().remove(shotView);
					}
					} else {
						this.stop();
						shotS.deleteShot();
						G.getChildren().remove(shotView);
					}
				}
					
				};
		
		t.start();
		}
					
}
	

