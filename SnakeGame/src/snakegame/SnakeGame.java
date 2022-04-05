
package snakegame;

import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame{
    SnakeGame(){
        
     
     add(new Board());
     pack();
     setLocationRelativeTo(null);
     setTitle("Snake Game");
     setResizable(false);
        
    }
        

    
    public static void main(String[] args) {
       new SnakeGame().setVisible(true);
       
    
        
    }
    
}
