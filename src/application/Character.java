package application;

import java.util.LinkedList;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.stage.Stage;


public class Character {

	private Image charIMG1 = new Image("file:ImageData\\braviary.gif");
	private Image charIMG2 = new Image("file:ImageData\\glisc.gif");
	private Image charIMG3 = new Image("file:ImageData\\rayqua.gif");
	ImageView mainChar;
	Sprite mChar;
	boolean Exists = true;
	private double V = 14.5;
	public long SpawnRate = 405;
	public int EnemySpawn = 70;
	public int EnemyCount = 0;
	public int WaveCount = 0;
	public long Score = 0;
	private double dirX = 1;
	private double dirY = 1;
	public int  hp = 5;
	LinkedList<Shot> shots = new LinkedList<Shot>();
	private int Shots_count = 0;
	
    private final BooleanProperty U_P = new SimpleBooleanProperty(false);
    private final BooleanProperty D_P = new SimpleBooleanProperty(false);
    private final BooleanProperty R_P = new SimpleBooleanProperty(false);
    private final BooleanProperty L_P = new SimpleBooleanProperty(false);
    private final BooleanProperty E_P = new SimpleBooleanProperty(false);
    
    private final BooleanBinding UR_P = U_P.and(R_P);
    private final BooleanBinding UL_P = U_P.and(L_P);
    private final BooleanBinding DR_P = D_P.and(R_P);
    private final BooleanBinding DL_P = D_P.and(L_P);
    
    public void showBird(Group sce, Scene S){
    	
    	mainChar.setTranslateZ(mainChar.getBoundsInLocal().getWidth() / 2.0);
        mainChar.setRotationAxis(Rotate.Y_AXIS);
        mainChar.setRotate(180);
        mainChar.setFitHeight(175);
        mainChar.setFitWidth(175);
        mainChar.preserveRatioProperty().set(true);
        mChar.width = mainChar.getFitWidth();
        mChar.height = mainChar.getFitHeight();
        mChar.resize(0, 0, -40, -20);
        if(Exists){
        	
        	S.setOnKeyPressed(new EventHandler<KeyEvent>(){
  			  @Override
  			  public void handle(KeyEvent event){
  				  
  				  if(event.getCode() == KeyCode.RIGHT){
  					  
  					  R_P.set(true);
  					  dirX = 1;
  				  }
  				  if(event.getCode() == KeyCode.LEFT){
					  
  					  L_P.set(true);
  					  dirX = -1;
				  }
  				  if(event.getCode() == KeyCode.DOWN){
					  
  					  D_P.set(true);
  					  dirY = 1;
				  }
  				  if(event.getCode() == KeyCode.UP){
					  
  					  U_P.set(true);
  					  dirY = -1;
				  }
  				  if(event.getCode() == KeyCode.SPACE){
  					  
  					  E_P.set(true);
  				  }
  				
  			  }
  				  
  			  });
        	
        	S.setOnKeyReleased(new EventHandler<KeyEvent>(){
  			  @Override
  			  public void handle(KeyEvent event){

  				  if (event.getCode() == KeyCode.RIGHT) {
  					  
  					  R_P.set(false);
  				  } if (event.getCode() == KeyCode.LEFT) {
  					  
  					  L_P.set(false);
  				  } if (event.getCode() == KeyCode.UP){
  					  
  					  U_P.set(false);
  				  } if (event.getCode() == KeyCode.DOWN){
  					  
  					  D_P.set(false); 				    
  				  } if(event.getCode() == KeyCode.SPACE){
  					  
  					  E_P.set(false);
  				  } 
  			  	}
  			   });
        	
        	 AnimationTimer t = new AnimationTimer(){

 				@Override
 				public void handle(long timestamp) {
 					
 					if(U_P.get()){
 						
 						if(mainChar.getLayoutY() <= 0){
 							
 							dirY = 0;
 						} else {
 							
 							mainChar.setLayoutY(mainChar.getLayoutY() + (V * dirY));
 							mChar.setPosition(mainChar.getLayoutX(), mainChar.getLayoutY());
 						}
 					}
 					if(R_P.get()){
 						
 						if(mainChar.getLayoutX() + mainChar.getFitWidth() >= 1280){
 							
 							dirX = 0;
 						} else {
 							
 							mainChar.setLayoutX(mainChar.getLayoutX() + (V * dirX));
 							mChar.setPosition(mainChar.getLayoutX(), mainChar.getLayoutY());
 						}
 					}
 					if(L_P.get()){
 						
 						if(mainChar.getLayoutX() <= 0){
 							
 							dirX = 0;
 						} else {
 							
 							mainChar.setLayoutX(mainChar.getLayoutX() + (V * dirX));
 							mChar.setPosition(mainChar.getLayoutX(), mainChar.getLayoutY());
 						}
 					}
 					if(D_P.get()){
 						
 						if(mainChar.getLayoutY() + mainChar.getFitHeight() >= 720){
 							
 							dirY = 0;
 						} else {
 							
 							mainChar.setLayoutY(mainChar.getLayoutY() + (V * dirY));
 							mChar.setPosition(mainChar.getLayoutX(), mainChar.getLayoutY());
 						}
 					}
 				}
 				};
 				
 				E_P.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							shots.add(new Shot());
							shots.getLast().shoot(mainChar, sce);
							
						}else {
							
						}
					}
				});
 				
 				D_P.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							t.start();
						}else {
							t.stop();
						}
					}
				});

			    L_P.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							t.start();
						}else {
							t.stop();
						}
					}
				});

			    U_P.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							t.start();
						}else {
							t.stop();
						}
					}
				});

			    R_P.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
					    
						if(arePressed){
							t.start();
						}else {
							t.stop();
						}
					}
				});
			    
			    UR_P.addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
				    
						if(arePressed){
							t.start();
						}else {
							t.stop();
						}
					}
			    });
		    
			    UL_P.addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
				    
						if(arePressed){
							t.start();
						}else {
							t.stop();
						}
					}
			    });
		    
			    DR_P.addListener(new ChangeListener<Boolean>() {
			    	@Override
			    	public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
				    
			    		if(arePressed){
			    			t.start();
			    		}else {
			    			t.stop();
			    		}
			    	}
			    });
		    
			    DL_P.addListener(new ChangeListener<Boolean>() {
			    	@Override
			    	public void changed(ObservableValue<? extends Boolean> obs, Boolean werePresed, Boolean arePressed) {
				    
			    		if(arePressed){
			    			t.start();
			    		}else {
			    			t.stop();
			    		}
			    	}
			    });	
        }
        }
    

    public void SetChar(int i){
    	
    	switch(i){
    	case 1:{
    		
    		mainChar = new ImageView(charIMG1);
    		mChar = new Sprite(charIMG1);
    	}break;
    	case 2:{
    		
    		mainChar = new ImageView(charIMG2);
    		mChar = new Sprite(charIMG2);
    	}break;
		case 3:{
	
    		mainChar = new ImageView(charIMG3);
    		mChar = new Sprite(charIMG3);
		}break;
    	}
    }
    
}
