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
/**
 * 
 * @param x Position on X
 * @param y Position on Y
 * @param width Width of the Capsule
 * @param height Height of the Capsule
 * @param vidas Life of the Capsule
 * @param game Game
 * @param power Boolean to know if capsule has a power
 */

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
    
    /**
     * Get the width of the Capsule 
     * @return <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    /**
     * Set the width of the Capsule
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * Get the height of the Capsule
     * @return <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    /**
     * Get the live of the Capsule
     * @return <code>int</code> value with the lives
     */
    public int getVidas(){
        return vidas;
    }
    /**
     * Set the live of the Capsule
     * @param vidas 
     */
    public void setVidas(int vidas){
        this.vidas=vidas;
    }
    /**
     * Get the perimeter of the Capsule
     * @return <code>Rectangle</code> value with the perimeter
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    /**
     * Set the height of the Capsule
     * @param height 
     */ 
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * Get if the capsule has a power up
     * @return <code>boolean</code> value with the boolean of the power up
     */
    public boolean isPowerUp() {
        return powerUp;
    }
    /**
     * Set the value of the power up
     * @param powerUp 
     */
    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }
    /**
     * Get the index of the capsule
     * @return <code>int</code> value with the index
     */
    public int getIndex() {
        return index;
    }
    /**
     * Set the index of the capsule
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     * Converts variables to string in order to save them
     * @return <code>String</code> with the value of all variables
     */
    public String toString() {
        
        return "" + getX() + "," + getY() + "," + getVidas() + "," + isPowerUp();
    }
    
    
    @Override
    public void tick() {
        
    }
    /**
     * Draws the capsule with different image depending on the number of lives
     * @param g 
     */
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
