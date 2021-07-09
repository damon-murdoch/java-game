package javagame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Transparency;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class JavaGame extends JFrame implements Runnable{
    int x,y, xDirection, yDirection,tilesize = 32,windowSizeX,windowSizeY,mapSizeX,mapSizeY;
    Color newColor = new Color(0,0,0,32);
        
    // Sprites
        // Background sprites
        ImageIcon i = new ImageIcon("");
        
    
    
        public void run(){
            
            while(true){
                try{
                
                    move();
                    
                    Thread.sleep(5);
                
                }
                catch(Exception e){
                    
                    System.out.println("Error");
                
                }
            }
        }
        
	private Image dbImage;
	private Graphics dbg;
        Image sprTest;
        
        public void move(){
         x += xDirection;
         y += yDirection;
        if (x <= 0)
            x = 0;
        if(x >= 608)
            x = 608;
        if (y <= 32)
            y = 32;
        if (y >= 480)
            y = 480;
        }
        public void setXDir(int xDir){
            xDirection = xDir;
        }
        
        public void setYDir(int yDir){
            yDirection = yDir;
        }

	public class AL extends KeyAdapter {
	
		public void keyPressed(KeyEvent e) {
		
			int keyCode = e.getKeyCode(); 
			
			if (keyCode == e.VK_LEFT){
                            setXDir(-1);
                        }
                            if(keyCode == e.VK_RIGHT){
                            setXDir(1);
                        }
                            if(keyCode == e.VK_UP){
                            setYDir(-1);
                        }
			if(keyCode == e.VK_DOWN){
                            setYDir(+1);
                        }
		}
		
		public void keyReleased(KeyEvent e){
                    int keyCode = e.getKeyCode();
                    if (keyCode == e.VK_LEFT){
                            setXDir(0);
                        }
                            if(keyCode == e.VK_RIGHT){
                            setXDir(0);
                        }
                            if(keyCode == e.VK_UP){
                            setYDir(0);
                        }
			if(keyCode == e.VK_DOWN){
                            setYDir(0);
                        }
		}
	}

	public JavaGame() {
            String currentDirectory = (System.getProperty("user.dir"));
            String workingDirectory = (Paths.get("").toAbsolutePath().toString());
            
            
            ImageIcon i = new ImageIcon(workingDirectory + "\\src\\javagame\\sprTest.gif");
            ImageIcon bg1 = new ImageIcon("");
            sprTest = i.getImage();
        
            addKeyListener(new AL());	
            
            int potato = (4 * 2^(-2));
                
            setTitle("" + potato);
            setSize(640,480);
            setResizable(false);
            setVisible(true);
            //setIcon("");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                             
            x = 360;
            y = 240;                    
	
            // Map size
            mapSizeX = 1024;
            mapSizeY = 1024;
            
        
        }

	public void paint (Graphics g){
                // Step 1 - Drawing background
                ImageIcon bg;
                Image sprBG;
                g.setColor(Color.red); 
                 
                dbImage = createImage(getWidth(),getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage,0,0,this);
                        
                //g.setColor(newColor);
                //g.drawRect(3, 25, 32, 32);

	}
	
	public void paintComponent(Graphics g) {     
            g.setColor(newColor);
            //g.drawRect(3, 25, 32, 32);
            //g.drawRect(3+1+tilesize,25,32,32);
            
            
            
            for(int column = 0; column<=mapSizeY / 32; column++){
                for(int row = 0; row<=mapSizeX / 32; row++){
                    //         x,y,sizeX,sizeY
                    g.drawRect(3+(row*32),25+(column*32),tilesize,tilesize);
                }
            }
            
            
            g.drawImage(sprTest,x,y,this);
            
            repaint();
	}	
	
	public static void main(String[] args){
            JavaGame jg = new JavaGame();
            
            // Threads
            Thread t1 = new Thread(jg);
            t1.start();
	}
        
}