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
 * @author antoniomejorado
 */
public class Player extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    private Animation animation;
    private int speed;
    private int powerUpCounter;
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
        this.animation = new Animation(Assets.barSprites, 100);
        powerUpCounter = 0;
    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
     /**
     * getPerimetro
     * Método de acceso que regresa el objeto de la clase Rectangle del jugador 
     * que contiene su posición y sus bordes en la pantalla
     * @return r es el objeto de la clase Rectangle del jugador
     */

    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public int getPowerUpCounter() {
        return powerUpCounter;
    }

    public void setPowerUpCounter(int powerUpCounter) {
        this.powerUpCounter = powerUpCounter;
    }
    
    
    
    public void powerUp(){
        powerUpCounter = 500;
    }
    
    public void load(int newPowerUpCounter, int newPosX){
        setPowerUpCounter(newPowerUpCounter);
        setX(newPosX);
    }
    
    public String toString()
    {
        return ""+powerUpCounter+","+getX();
    }

    @Override
    public void tick() {
        // moving player depending on flags
        
        if(powerUpCounter > 0){
            setSpeed(15);
            powerUpCounter--;
        } else {
            setSpeed(5);
        }

        if (game.getKeyManager().left) {
           setX(getX() - getSpeed());
        }
        if (game.getKeyManager().right) {
           setX(getX() + getSpeed());
        }
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
        }
    }

    @Override
    public void render(Graphics g) {
        if (getSpeed() == 15) {
            g.drawImage(Assets.playerPowerUp, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);

        }
    }
}
