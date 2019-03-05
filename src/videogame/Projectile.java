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
    private int collisionCounter;
    private Player player;

    public Projectile(int x, int y, int speedX, int speedY, int width, int height, Game game, Player p) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speedX = speedX;
        this.speedY = speedY;
        this.movimiento = new Animation(Assets.proyectil, 100);
        this.player = p;
        this.speed = 10;
        collisionCounter = 0;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    

    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean intersecta(Object obj) {
        Rectangle perimetro = getPerimetro();
        //Se revisa si el jugador ha colisionado con algun enemigo
        return obj instanceof Player && getPerimetro().intersects(((Player) obj).getPerimetro());
    }

    public boolean hitCapsule(Object obj) {
        return obj instanceof Capsule && getPerimetro().intersects(((Capsule) obj).getPerimetro());
    }

    public void handleWallCollisions() {
        int gameWidth = game.getWidth();
        int gameHeight = game.getHeight();

        if (getX() + getWidth() > gameWidth || getX() < 0) {
            setSpeedX(-1 * getSpeedX());
        }

        if (getY() < 0 || getY() + getHeight() + 10 > gameHeight) {
            setSpeedY(-1 * getSpeedY());
        }
    }

    public void handleCapsuleCollision() {
        if (collisionCounter == 0) {
            setSpeedY(-1 * getSpeedY());
            collisionCounter = 25;
        }
    }
    
    
    public void handlePlayerCollision() {
        
        
        
        int posProjectileX = getX() + getWidth() / 2;
        int posPlayerX = player.getX();
        
        int diff = posProjectileX - posPlayerX;
        double point = (double)diff / (double)player.getWidth();
        
        double degrees = (1 - point) * 180;
        double radians = degrees * 3.14 / 180;
        
        System.out.println(Double.toString(radians));
        
        int newXSpeed = (int) (speed * Math.cos(radians));
        int newYSpeed = (int) (speed * Math.sin(radians));
        
        System.out.println("X Speed: " + Integer.toString(newXSpeed));
        System.out.println("Y Speed: " + Integer.toString(newYSpeed));
        
        setSpeedY(- 1 * newYSpeed);
        setSpeedX(newXSpeed);
        /*
        if (getX() < getPlayer().getWidth() / 3 + getPlayer().getX()) {
            if (getSpeedX() > 0) {
                setSpeedX(-1 * getSpeedX());
            }
        } else if (getX() > getPlayer().getWidth() * 2 / 3 + getPlayer().getX()){
            if (getSpeedX() < 0) {
                setSpeedX(-1 * getSpeedX());
            }
        }
*/
    }

    @Override
    public void tick() {
        if(collisionCounter > 0){
            collisionCounter--;
        }
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());
        this.movimiento.tick();
        if (intersecta(game.getPlayer())) {
            //setSpeedX( -1 * getSpeedX());
            handlePlayerCollision();
        }
        handleWallCollisions();

        //Checar si ha colisionado con alguna capsula
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(movimiento.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
