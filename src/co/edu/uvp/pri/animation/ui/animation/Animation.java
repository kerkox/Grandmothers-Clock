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

    private int minute = 90;
    private int balanceo= 135;
    private boolean balanceoMax=false;

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

        for (int i = 0; i < 360; i += 6) {
            int x1 = (int) (radius * Math.cos(Math.toRadians(i)));
            int y1 = (int) (radius * Math.sin(Math.toRadians(i)));
            int x2 = (int) ((radius - 5) * Math.cos(Math.toRadians(i)));
            int y2 = (int) ((radius - 5) * Math.sin(Math.toRadians(i)));
            g.drawLine(cx + x1, cy - y1, cx + x2, cy - y2);
        }

        radius = 25;
        int angle = 150;
        int x = (int) (radius * Math.cos(Math.toRadians(angle)));
        int y = (int) (radius * Math.sin(Math.toRadians(angle)));
        g.drawLine(cx, cy, cx + x, cy - y);

        radius = 40;
        x = (int) (radius * Math.cos(Math.toRadians(this.minute)));
        y = (int) (radius * Math.sin(Math.toRadians(this.minute)));
        g.drawLine(cx, cy, cx + x, cy - y);
        
        //Linea de pendulo
        //Variables del pendulo
        int cpx = cx; //Centro pendulo origen 
        int cpy = cy+80; //Centro pendulo origen 
        radius = 40;
        x = (int) (radius * Math.cos(Math.toRadians(this.balanceo)));
        y = (int) (radius * Math.sin(Math.toRadians(this.balanceo)));
        this.balanceoMax = (this.balanceo==45);
        
        
        g.drawLine(cpx, cpy, cpx+x, cpy+y);

    }

    private void nextFrame() {
        this.minute -= 6;
    }
    
    private void balancear(){
        if(this.balanceoMax){
            this.balanceo += 1;     
        }else{
            this.balanceo -= 1;     
        }
        
    }

    public void init() {
        if (this.timer == null) {
            this.timer = new Timer(1000, (ActionEvent e) -> {
                nextFrame();
                repaint();
            });

        }
        if (this.timerPendulo == null) {
            this.timerPendulo = new Timer(50, (ActionEvent e) -> {
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
        this.minute=90;
        this.init();
    }

}
