/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    private Projectile projectile; // to manage projectile object
    private LinkedList<Capsule> capsules;  // to manage capsules in a Linked List
    private LinkedList<PowerUp> powerUps; //to manage the power ups
    private int capsuleHits;
    private boolean endGame;
    private Vector vec;
    private String arr[];

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        capsules = new LinkedList<Capsule>();
        powerUps = new LinkedList<PowerUp>();
        capsuleHits = 0;
        endGame = false;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());        
        Assets.init();
        player = new Player(0, getHeight() - 100, 1, 200, 50, this);
        projectile = new Projectile(getWidth() / 2, getHeight() / 2, 7, 7, 60, 60, this, player);
        /// Se crean varias capsulas 
        int columns = 10;
        int rows = 5;
        for (int i = 0; i < columns; i++) {
            for (int y = 0; y < rows; y++) {
                int vidas = (int) (Math.random() * 3) + 2;
                double powerUpCoin = Math.random();
                boolean hasPowerUp = powerUpCoin > 0.75;
                capsules.add(new Capsule(50 + i * 80, 25 + 30 * y, 75, 25, vidas, this, hasPowerUp));
            }
        }
        display.getJframe().addKeyListener(keyManager);
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    private void tick() {
        if (!endGame)/// Si no se destruyen todas las capsulas el juego sigue corriendo
        {
            if (keyManager.pause == false) /// Mientras el usario no presione el boton de pausa el juego sigue corriendo 
            {
                // avancing player with colision
                player.tick();
                projectile.tick();
                for (int i = 0; i < capsules.size(); i++) {
                    Capsule c = capsules.get(i);
                    c.tick();
                    if (projectile.hitCapsule(c)) {
                        
                        c.setVidas(c.getVidas() - 1);
                        if (c.getVidas() == 0) {
                            if(c.isPowerUp()){
                                PowerUp p = new PowerUp(c.getX(), c.getY(), 30,30, this, player);
                                powerUps.add(p);
                            }
                            capsules.remove(i);
                        }
                        capsuleHits++;
                        /// Si se destruyen todas las capsulas el juego se acaba
                        if (capsules.size() == 0) {
                            
                            endGame = true;
                        }
                        /// Collision del projectile
                        projectile.handleCapsuleCollision();
                        
                    }
                }

                //Tick de los power ups
                
                for(int i = 0; i < powerUps.size(); i++){
                    PowerUp p = powerUps.get(i);
                    p.tick();
                    if(p.intersectaPlayer(player)){
                        player.powerUp();
                        powerUps.remove(i);
                    }
                }
            }
        }
        keyManager.tick();
    }
    
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, getWidth(), getHeight(), null);
            player.render(g);
            projectile.render(g);
            /// Se pintan las capsulas que estan en la linked list
            for (int i = 0; i < capsules.size(); i++) {
                Capsule capsule = capsules.get(i);
                capsule.render(g);
            }

            ///Se pintan los power ups
            for (int i = 0; i < powerUps.size(); i++) {
                PowerUp p = powerUps.get(i);
                p.render(g);
            }

            /// Si se rompen todas las capsulas el juego se acaba y se llama al metodo 
            /// Game Over
            if (endGame) {
                GameOver();
            }
            /// Si el usario presiona la tecla'p' se llama al metodo pausa
            if (keyManager.pause == true) {
                Pause();
            }
            if(keyManager.save==true)
            {
                try {

                     
                      vec.add(new Player(player.getX(),player.getY(),player.getDirection(),player.getWidth(),player.getHeight(),this));
                      //Graba el vector en el archivo.
                      grabaArchivo();
                } catch(IOException e) {

                      System.out.println("ErroR");
                }
            }

            bs.show();
            g.dispose();
        }
        
    }

    private void Pause() {
        /// Pinta la pausa en pantalla  
        g.setColor(Color.red);
        g.drawString("PAUSE", 500, 500);
        
    }

    private void GameOver() {
        /// Pinta el game over en pantalla
        g.setColor(Color.red);
        g.drawString("GAME OVER", 500, 500);
        g.drawString("PRESS R TO RESTART GAME", 500, 550);
        /// Si el usuario le da click al boton de la 'r' se renicia el juego replicando el metodo 
        /// init pero sin la creacion de una nueva pantalla
        if (keyManager.restart == true) {
            /// Se libera el tick 
            endGame = false;
            Assets.init();
            /// Se crea la barra y el projectil
            player = new Player(0, getHeight() - 100, 1, 200, 25, this);
            projectile = new Projectile(getWidth() / 2, getHeight() / 2, 5, 5, 60, 60, this, player);
            /// Se reinicia el numero de capsule hits
            capsuleHits = 0;
            /// Se crean varias capsulas 
            int columns = 10;
            int rows = 5;
            for (int i = 0; i < columns; i++) {
                for (int y = 0; y < rows; y++) {
                    int vidas = (int) (Math.random() * 3) + 2;
                    double powerUpCoin = Math.random();
                    boolean hasPowerUp = powerUpCoin > 0.75;
                    capsules.add(new Capsule(50 + i * 80, 25 + 30 * y, 75, 25, vidas, this, hasPowerUp));
                }
            }            
        }
        
    }
 public void leeArchivo() throws IOException {
                                                          
                BufferedReader fileIn;
                try {
                        fileIn = new BufferedReader(new FileReader("/images/save.txt"));
                }catch(FileNotFoundException e){
                        File save = new File("/images/save.txt");
                        PrintWriter fileOut = new PrintWriter(save);
                        fileOut.println(player.getSpeed());
                        fileOut.close();
                        fileIn = new BufferedReader(new FileReader("/images/save.txt"));
                }
                String dato = fileIn.readLine();
                while(dato != null) {  
                                                        
                      arr = dato.split(",");
                      int num = (Integer.parseInt(arr[0]));
                      String nom = arr[1];
                      vec.add(new Player(player.getX(),player.getY(),player.getDirection(),player.getWidth(),player.getHeight(),this));
                      dato = fileIn.readLine();
                }
                fileIn.close();
        }
       public void grabaArchivo() throws IOException {
                                                          
                PrintWriter fileOut = new PrintWriter(new FileWriter("/images/save.txt"));
                for (int i = 0; i < vec.size(); i++) {

                    Player x;
                    x = (Player) vec.get(i);
                    fileOut.println(x.toString());
                }
                fileOut.close();
        }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }            
        }
    }
    
}
