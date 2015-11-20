package co.edu.uvp.pri.animation.ui.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Animation extends JComponent {

    private Timer timer;
    private Timer timerPendulo;

    private int second = 90;
    private int minute = 90;
    private int hour = -210;
    private int balanceo= 115;
    private int direccionBalanceo=-1;
    
    @Override
    public void paintComponent(Graphics g) {
//****************************************
        //MARCO RELOJ
        int width = this.getWidth();
        int height = this.getHeight();

        g.setColor(new Color(164, 120, 88));
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.fillRect(20, height / 4, width - 40, height * 3 / 4 - 20);

        g.fillOval(20, 20, width - 40, height / 2);
        
        
//****************************************
        //RELOJ
        
        int cx = width / 2;
        int cy = height / 4;
        int radius = 60;

        g.setColor(Color.black);
        g.drawOval(cx - radius, cy - radius,
                radius * 2, radius * 2);
        int size;
        for (int i = 0; i < 360; i += 6) {
            size =5;
            int x1 = (int) (radius * Math.cos(Math.toRadians(i)));
            int y1 = (int) (radius * Math.sin(Math.toRadians(i)));
            //Variable para aumentar la longitud de los valores claves del reloj
            if(i%30==0){
                size = 15;
            } 
            int x2 = (int) ((radius - size) * Math.cos(Math.toRadians(i)));
            int y2 = (int) ((radius - size) * Math.sin(Math.toRadians(i)));
            
            g.drawLine(cx + x1, cy - y1, cx + x2, cy - y2);
            
           
        }
        
        //**********************
        //Horario

        radius = 20;
        
        int x = (int) (radius * Math.cos(Math.toRadians(this.hour)));
        int y = (int) (radius * Math.sin(Math.toRadians(this.hour)));
        g.drawLine(cx, cy, cx + x, cy - y);

        //**********************
        //Minutero
        g.setColor(Color.blue);
        radius = 30;
        x = (int) (radius * Math.cos(Math.toRadians(this.minute)));
        y = (int) (radius * Math.sin(Math.toRadians(this.minute)));
        g.drawLine(cx, cy, cx + x, cy - y);
        
        //**********************
        //Segundero
        g.setColor(Color.RED);
        radius = 40;
        x = (int) (radius * Math.cos(Math.toRadians(this.second)));
        y = (int) (radius * Math.sin(Math.toRadians(this.second)));
        g.drawLine(cx, cy, cx + x, cy - y);
        
        //******************************        
        //Linea de pendulo
        g.setColor(Color.black);
        //Variables del pendulo
        int cpx = cx; //Centro pendulo origen 
        int cpy = cy+80; //Centro pendulo origen 
        radius = 80; // Longitud del radio del pendulo
        x = (int) (radius * Math.cos(Math.toRadians(this.balanceo)));
        y = (int) (radius * Math.sin(Math.toRadians(this.balanceo)));
        
        g.drawLine(cpx, cpy, cpx+x, cpy+y);
        
        //******************************        
        
        g.fillOval((cpx+x)-20, (cpy+y), 40, 40);
        

    }

    private void nextSecond() {
        this.second -= 6;
        if(this.second==-270){
            this.second=90;
            nextMinute();
        }
        
    }
   private void nextMinute(){
       this.minute -=6;
        if(this.minute==-270){
            this.minute=90;
            nextHour();
        }
   }
   
   private void nextHour(){
       this.hour -=6;
       if(this.hour==-270){
            this.hour=90;
         
        }
   }
       
    
    private void balancear(){
        if(this.balanceo==65) direccionBalanceo=1;
        if(this.balanceo==115) direccionBalanceo=-1;    
        this.balanceo += direccionBalanceo;     
    }

    public void init() {
        if (this.timer == null) {
            this.timer = new Timer(1000, (ActionEvent e) -> {
                nextSecond();
                repaint();
            });

        }
        if (this.timerPendulo == null) {
            this.timerPendulo = new Timer(20, (ActionEvent e) -> {
                balancear();
                repaint();
            });

        }
        this.timer.start();
        this.timerPendulo.start();
    }

    public void pause() {
        this.timer.stop();
        this.timerPendulo.stop();
                
    }

    public void restart() {
        this.pause();
        // Iniciar variables
        this.second=90;
        this.balanceo=115;
        this.init();
    }

}
