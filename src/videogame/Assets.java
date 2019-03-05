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
    public static BufferedImage block;
    public static BufferedImage capsula; // to store capsula image

       
    //Capsule images dependiendo de la vidas
    public static BufferedImage capsule4;
    public static BufferedImage capsule3;
    public static BufferedImage capsule2;
    public static BufferedImage capsule1;



    
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/bck.jpeg");
        player = ImageLoader.loadImage("/images/yellowRectangle.png");
        sprites=ImageLoader.loadImage("/images/bolaroja.png");
        capsula=ImageLoader.loadImage("/images/capsule.png");
        /// Se recorta el sprite sheet y se agarran las tres imagenes necesarias para la animacion
        SpreadSheet spritesheet =new SpreadSheet(sprites);
        proyectil=new BufferedImage[36];
        for(int i=0;i<36;i++)
        {
            proyectil[i]=spritesheet.crop(i*100, 0, 100, 100);
        }
       
        capsule4 = ImageLoader.loadImage("/images/capsule4.png");
        capsule3 = ImageLoader.loadImage("/images/capsule3.png");
        capsule2 = ImageLoader.loadImage("/images/capsule2.png");
        capsule1 = ImageLoader.loadImage("/images/capsule1.png");

        
        
    }
    
}
