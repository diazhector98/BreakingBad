/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage sprites;    // to stores sprites for proyectil
    public static BufferedImage proyectil[]; // arreglo para hacer animacion de proyectil
    

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/bck.png");
        player = ImageLoader.loadImage("/images/barra.png");
        sprites=ImageLoader.loadImage("/images/ball.png");
        SpreadSheet spritesheet =new SpreadSheet(sprites);
        proyectil=new BufferedImage[3];
        for(int i=0;i<3;i++)
        {
            proyectil[i]=spritesheet.crop(i*99, 0, 99, 64);
        }
    }
    
}
