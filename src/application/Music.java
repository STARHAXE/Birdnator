package application;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {

	AudioClip sound1 = new AudioClip(this.getClass().getResource("file:///C:/Users/MATHEO/Downloads/Crypteque.mp3").toExternalForm());
	Media sound2 = new Media("file:musics\\Dedede.mp3");
	Media sound3 = new Media("file:musics\\GameOver.mp3");
	Media sound4 = new Media("file:musics\\GourmetRace.mp3");
	Media sound5 = new Media("file:musics\\Nitrogen.mp3");
	Media sound6 = new Media("file:musics\\Volcano.mp3");
	Media sound7 = new Media("file:musics\\Weapon.mp3");
	
	public void playSound(int i){
		
		switch(i){
		
		case 1:{
			
			//MediaPlayer mediaplay = new MediaPlayer(sound1);
			//mediaplay.play();
			sound1.play();
		}break;
		case 2:{
			
			MediaPlayer mediaplay = new MediaPlayer(sound2);
			mediaplay.play();
		}break;
		case 3:{
			
			MediaPlayer mediaplay = new MediaPlayer(sound3);
			mediaplay.play();
		}break;
		case 4:{
			
			MediaPlayer mediaplay = new MediaPlayer(sound4);
			mediaplay.play();
		}break;
		case 5:{
			
			MediaPlayer mediaplay = new MediaPlayer(sound5);
			mediaplay.play();
		}break;
		case 6:{
			
			MediaPlayer mediaplay = new MediaPlayer(sound6);
			mediaplay.play();
		}break;
		case 7:{
			
			MediaPlayer mediaplay = new MediaPlayer(sound7);
			mediaplay.play();
		}break;

		}
	}
}
