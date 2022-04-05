
package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Board extends JPanel implements ActionListener {
   private Image apple,dot,head;
   private  int dots;
   private final int DOT_SIZE=10;
   private final int ALL_DOT=900;
   
   private final int x[]=new int[ALL_DOT];
    private final int y[]=new int[ALL_DOT];
    
    private final int RANDOM_POS=29;
    private int apple_x;
    private int apple_y;
    private Timer time;
    
    private boolean leftdir=false;
    private boolean rightdir=true;//b/c in starting it will move to right
    private boolean updir=false;
    private boolean downdir=false;
    private boolean ingame=true;
    
    Board(){
        
        addKeyListener(new TAdapter());
        
        
        
        setPreferredSize(new Dimension(300,300));
        setBackground(Color.BLACK);
         
        setFocusable(true);//b/c we use key listener
        loadImage();
        
        initGame();
        
        
    }
    
    public void loadImage(){
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/apple.png"));
        apple=i1.getImage();
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("icons/dot.png"));
        dot=i2.getImage();
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("icons/head.png"));
        head=i3.getImage();
        
    }
    public void initGame(){
        dots=3;
        for(int i=0; i<dots;i++){
            
          x[i]=50- i*DOT_SIZE;
          y[i]=50;
            
        } 
        
        Applelocate();
       time=new Timer(140,this);
       time.start();
        
    }  
        
        
    public void Applelocate(){
        
        int p=(int)(Math.random()*RANDOM_POS);//0 and 1; 0.6*20=12;
        apple_x=(p*DOT_SIZE);//12*10=120; if we take 50 in case of 20 then 12*50=600 but we have frame of only 300*300;chose between 1 to 30;30 islie mhi lenge b/c apple la size 10 h to total 310 ho jayega
          p=(int)(Math.random()*RANDOM_POS);
        apple_y=(p* DOT_SIZE);
        
    }
    public void checkapple(){
        if((x[0]==apple_x)&&( y[0]==apple_y)){
            dots++;
        
        Applelocate();
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(ingame){
        g.drawImage(apple, apple_x, apple_y,this);
        for(int z=0;z<dots;z++){
           if(z==0){
               g.drawImage(head, x[z],y[z] , this);
           } else{
               g.drawImage(dot, x[z],y[z] , this);
           }
            
        }
    
        Toolkit.getDefaultToolkit().sync();
        
    }else{
            gameOver(g);
        }
    }          
    public void gameOver(Graphics g){
        String msg="Game Over";
        Font font=new Font("Tahoma",Font.BOLD,14);
        FontMetrics metrices=getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg,( 300 - metrices.stringWidth(msg))/2, 300/2);
    }            
    
    public void checkcollision(){
        for(int z=dots; z>0;z--){
            if((z>4 )&&(x[0]==x[z])&&(y[0]==y[z])){
              ingame=false;  
            }
        }
        
        if(y[0]>=300)
            ingame=false;
         if(x[0]>=300)
            ingame=false;  
         if(x[0]<0)
             ingame=false;
         if(y[0]<0)
             ingame=false;
        if(!ingame){
            time.stop();
        }
    }
    public void move(){
        for(int z=dots;z>0;z--){
            x[z]=x[z-1];
            y[z]=y[z-1];
            
        }
        if(leftdir)
            x[0]=x[0]-DOT_SIZE;
         if(rightdir)
             x[0]=x[0]+DOT_SIZE;
          if(updir)
              y[0]=y[0]-DOT_SIZE;
          if(downdir)
              y[0]=y[0]+DOT_SIZE;
           
        
    }
   public void actionPerformed(ActionEvent ae){
       if(ingame){
           checkapple();
           checkcollision();
           move();
       }
       repaint();//b/c we cahnge the look mtlb ki snake kisize badh rhi    
       }
   
       
   
    
    private class TAdapter extends KeyAdapter{
        
        public void keyPressed(KeyEvent e){
          int key=  e.getKeyCode();
          if(key==KeyEvent.VK_LEFT && (!rightdir)){
          leftdir=true;
          updir=false;
          downdir=false;
          
        }
          if(key==KeyEvent.VK_RIGHT && (!leftdir)){
          rightdir=true;
          updir=false;
          downdir=false;
          
        }
          if(key==KeyEvent.VK_UP && (!downdir)){
          leftdir=false;
          updir=true;
          rightdir=false;
        }
          if(key==KeyEvent.VK_DOWN && (!updir)){
          leftdir=false;
          rightdir=false;
          downdir=true;
    }
    
    
        }
    }
}
