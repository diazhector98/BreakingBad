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
public class Projectile extends Item {

    private int speedX;
    private int speedY;
    private int width;
    private int height;
    private Game game;
    private int speed;
    private Animation movimiento; 

    public Projectile(int x, int y, int speedX, int speedY, int width, int height, Game game) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speedX = speedX;
        this.speedY = speedY;
        speed = 4;
        this.movimiento=new Animation(Assets.proyectil,100);
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

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
    
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public boolean intersecta(Object obj) {
        Rectangle perimetro = getPerimetro();
        //Se revisa si el jugador ha colisionado con algun enemigo
        return obj instanceof Player && getPerimetro().intersects(((Player) obj).getPerimetro());
    }
    
    public void handleWallCollisions(){
        int gameWidth = game.getWidth();
        int gameHeight = game.getHeight();
        
        if(getX() > gameWidth || getX() < 0){
            setSpeedX(-1 * getSpeedX());
        }
        
        if(getY() < 0 || getY() > gameHeight){
            setSpeedY(-1 * getSpeedY());
        }
    }
    
    @Override
    public void tick() {
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());
        
        if(intersecta(game.getPlayer())){
            //setSpeedX( -1 * getSpeedX());
            setSpeedY( - 1 * getSpeedY());
        }
        
        handleWallCollisions();
        this.movimiento.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(movimiento.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
