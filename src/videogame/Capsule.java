package videogame;
import java.awt.Graphics;
import java.awt.Rectangle;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge Ramos
 */
public class Capsule extends Item {

   
    private int width;
    private int height;
    private int vidas;
    private boolean powerUp;
    private int index;
    private Game game;


    public Capsule(int x, int y , int width,int height,int vidas,Game game, boolean power) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.game = game;
        this.vidas=vidas;
        this.powerUp = power;
        this.index = index;
    }
    

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public int getVidas(){
        return vidas;
    }
    public void setVidas(int vidas){
        this.vidas=vidas;
    } 
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
      
    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isPowerUp() {
        return powerUp;
    }

    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public String toString() {
        
        return "" + getX() + "," + getY() + "," + getVidas() + "," + isPowerUp();
    }
    
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {

        switch (vidas) {
            case 4:
                g.drawImage(Assets.capsule4, getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 3:
                g.drawImage(Assets.capsule3, getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 2:
                g.drawImage(Assets.capsule2, getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 1:
                g.drawImage(Assets.capsule1, getX(), getY(), getWidth(), getHeight(), null);
                break;
        }
    }
}
