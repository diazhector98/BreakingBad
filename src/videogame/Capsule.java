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
    private Game game;


    public Capsule(int x, int y , int width,int height,Game game) {
       super(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.game = game;
       
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
    
      
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
      
    public void setHeight(int height) {
        this.height = height;
    }
    @Override
    public void tick() {
       
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.capsula, getX(), getY(), getWidth(), getHeight(), null);
    }
}