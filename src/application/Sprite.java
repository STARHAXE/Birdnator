package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Sprite {

    private Image image;
    public double positionX;
    public double positionY;
    public double width;
    public double height;

    public Sprite(Image image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        positionX = 0;
        positionY = 0;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }
    
    public Rectangle2D getBoundary() {
    	
    	return  new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Sprite spr) {
        return spr.getBoundary().intersects(this.getBoundary());   
        }
    
    public void ChckHBox(Group OP){
    	
    	Rectangle n = new Rectangle(positionX, positionY, width, height);
    	n.setStroke(Color.AQUA);
    	n.setFill(null);
    	OP.getChildren().add(n);
        }
    
    public void resize(double X, double Y, double W, double H){
    	
    	this.positionX += X;
    	this.positionY += Y;
    	this.width += W;
    	this.height += H;
    }
    
    public void deleteEnemy(){
    	
    	this.positionX = 2000;
    	this.positionY = 2000;
    	this.width = 1;
    	this.height = 1;
    }
    
    public void deleteShot(){
    	
    	this.positionX = 2500;
    	this.positionY = 2000;
    	this.width = 1;
    	this.height = 1;
    }
    
    public void deleteMain(){
    	
    	this.positionX = 2000;
    	this.positionY = 2500;
    	this.width = 1;
    	this.height = 1;
    }
    }