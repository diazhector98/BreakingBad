/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author hectordiazaceves
 */
public class PowerUp extends Item {
    
    private int speed;
    private int width;
    private int height;
    private Game game;
    private Animation animation;
    private Player player;
    

    public PowerUp(int x, int y, int width, int height, Game g, Player p) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.speed = 4;
        this.game = g;
        this.player = p;
        this.animation = new Animation(Assets.powerUpSprites, 100);
        
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public void setHeight(int height) {
        this.height = height;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

        
    public boolean intersectaPlayer(Object obj) {
        Rectangle perimetro = getPerimetro();
        //Se revisa si el jugador ha colisionado con algun enemigo
        return obj instanceof Player && getPerimetro().intersects(((Player) obj).getPerimetro());
    }
    
    public String toString(){
    
        return "" + getX() + "," + getY();
    }
    
    public void load(int newX, int newY){
        setX(newX);
        setY(newY);
    }
    

    @Override
    public void tick() {
        
        setY(getY() + getSpeed());
        this.animation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
    
    
    
}
