package application;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Timer {
 
 Label lb;
 private Integer starttime=15;
 Integer seconds= starttime;
 
 public Timer(Integer op){
	 
	 this.starttime = op;
	 this.seconds = op;
 }
 public void start() {
 
  lb = new Label();
  lb.setText("Time: " + starttime);
  lb.setFont(Font.font(30));
  lb.setTextFill(Color.WHITESMOKE);
  
   doTime();
  
  lb.setLayoutX(0);
  lb.setLayoutY(670);
 }
 
 private void doTime() {
  Timeline time= new Timeline();
  
  
  KeyFrame frame= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

   @Override
   public void handle(ActionEvent event) {
    
    
    seconds--;
    lb.setText("Time: " + seconds.toString());
    if(seconds<=0){
     time.stop();
    }
    
    
   }
   
   
  });
  
  time.setCycleCount(Timeline.INDEFINITE);
  time.getKeyFrames().add(frame);
  if(time!=null){
   time.stop();
  }
  time.play();
  
  
 }

	 
 }
