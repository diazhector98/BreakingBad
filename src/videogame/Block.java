/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author hectordiazaceves
 */
public class Block extends Item {
    
    private int width;
    private int height;
    private int health;
    /**
     * 
     * @param x position on x
     * @param y position on y 
     * @param width width of the block 
     * @param height height of the block 
     */
    public Block(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
    /**
     * To get the width of the Block 
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    /**
     * To set the width of the Block
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * To get the height of the Block
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    /**
     * To set the height of the Block 
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    

    @Override
    public void tick() {

    }
    /**
     * Draw image of the Block 
     * @param g 
     */
    @Override
    public void render(Graphics g) {
       g.drawImage(Assets.block, getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
