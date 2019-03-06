/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jorge
 */
public class Animation {
  private int speed;
  private int index;
  private long lastTime;
  private long timer;
  private BufferedImage[]frames;
  /**
   * Initialize variables
   * @param frames to set different frames of animation 
   * @param speed to set speed of the animations
   */
  public Animation(BufferedImage[]frames,int speed)
  {
      this.frames=frames;
      this.speed=speed;
      index=0;
      timer=0;
      lastTime=System.currentTimeMillis();
  }
    /**
     * Returns frame to use
     * @return an <code>Buffered Image</code> value with the frame
     */
  public BufferedImage getCurrentFrame()
  {
      return frames[index];
  }
    /**
     * Updates animation based on timer 
     */
  
  public void tick()
  {
      timer+=System.currentTimeMillis()-lastTime;
      lastTime=System.currentTimeMillis();
      if(timer>speed)
      {
          index++;
          timer=0;
          if(index>=frames.length)
          {
              index=0;
          }
      }
  }
}
