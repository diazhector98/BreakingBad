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
    public static BufferedImage carRightSprites;    
    public static BufferedImage carLeftSprites;
    
    public static BufferedImage carRight[];

    
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/bck.jpeg");
        player = ImageLoader.loadImage("/images/barra.png");
        carRightSprites = ImageLoader.loadImage("/images/carRight.png");
        carLeftSprites = ImageLoader.loadImage("/images/carLeft.png");
        sprites=ImageLoader.loadImage("/images/bolaroja.png");
        capsula=ImageLoader.loadImage("/images/capsule.png");
        /// Se recorta el sprite sheet y se agarran las tres imagenes necesarias para la animacion
        SpreadSheet spritesheet =new SpreadSheet(sprites);
        proyectil=new BufferedImage[36];
        for(int i=0;i<36;i++)
        {
            proyectil[i]=spritesheet.crop(i*100, 0, 100, 100);
        }
        
        
        //Sprites del carro, derecha e izquierda
        
        SpreadSheet carRightSpriteSheet = new SpreadSheet(carRightSprites);
        carRight = new BufferedImage[16];
        
        //Cada imagen de carro es de 370 X 175 pixeles
        //Distancia entre carros verticalmente es 50
        //Distancia entre carros horizontalmente es 20
        
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                carRight[4 * i + j] = carRightSpriteSheet.crop(j * 370 + 20 * j, 190 * i, 370, 200);
            }
        }
        
        
    }
    
}
