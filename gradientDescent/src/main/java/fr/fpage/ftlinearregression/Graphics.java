package fr.fpage.ftlinearregression;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class Graphics extends JPanel {

    private JFrame jFrame;
    private String legendX, legendY;
    private float maxX, maxY;
    private List<? extends GraphicPoint> graphicPoints;
    private GradientDescent desc = null;

    public Graphics(String legendX, String legendY, List<? extends GraphicPoint> graphicPoints, GradientDescent desc)
    {
        this.legendX = legendX;
        this.legendY = legendY;
        this.graphicPoints = graphicPoints;
        this.desc = desc;
        this.maxValues();
        this.prepareJFrame();
        this.jFrame.getContentPane().add(this);
        this.jFrame.setVisible(true);
    }

    private void maxValues() {
        this.maxX = desc == null ?0:1;
        this.maxY = desc == null ?0:1;
        for (GraphicPoint point : graphicPoints) {
            if (maxX < point.getValue1())
                maxX = point.getValue1();
            if (maxY < point.getValue2())
                maxY = point.getValue2();
        }
        if (desc != null) {
            int supX = 1;
            int supY = 1;
            while (maxX - supX > 0)
                supX *= 10;
            while (maxY - supY > 0)
                supY *= 10;
            while (supX / 2 > maxX)
                supX /= 2;
            while (supY / 2 > maxY)
                supY /= 2;
            maxX = supX;
            maxY = supY;
        }
    }

    @Override
    public void paint(java.awt.Graphics g) {
        g.drawLine(50, 30, 50, 830);
        g.drawLine(50, 830, 850, 830);
        g.drawLine(150, 825, 150, 835);
        g.drawLine(250, 825, 250, 835);
        g.drawLine(350, 825, 350, 835);
        g.drawLine(450, 825, 450, 835);
        g.drawLine(550, 825, 550, 835);
        g.drawLine(650, 825, 650, 835);
        g.drawLine(750, 825, 750, 835);
        g.drawLine(850, 825, 850, 835);
        g.drawLine(45, 30, 55, 30);
        g.drawLine(45, 130, 55, 130);
        g.drawLine(45, 230, 55, 230);
        g.drawLine(45, 330, 55, 330);
        g.drawLine(45, 430, 55, 430);
        g.drawLine(45, 530, 55, 530);
        g.drawLine(45, 630, 55, 630);
        g.drawLine(45, 730, 55, 730);
        g.drawString(legendX, 865, 835);
        g.drawString(legendY, 30, 20);

        for (int x = 1; x <= 10; x++)
            g.drawString("" + ((int) (this.maxX * 0.125 * x)), 35 + (100*x), 850);
        for (int x = 1; x <= 10; x++)
            g.drawString("" + ((int) (this.maxY * 0.125 * x)), 10, 835 - (100*x));
        g.drawString("0", 40, 840);
  /*      for (GraphicPoint point : this.graphicPoints) {
            Car car = ((Car) point);
            g.fillOval(this.getGraphXCoord(car.getNormalizeKm()), this.getGraphYCoord(car.getNormalizePrice()), 5, 5);
        }*/
        for (GraphicPoint point : this.graphicPoints) {
            g.fillOval(this.getGraphXCoord(point.getValue1()), this.getGraphYCoord(point.getValue2()), 5, 5);
        }
        if (this.desc != null) {
            g.drawLine(
                    this.getGraphXCoord(-100000, true),
                    this.getGraphYCoord(this.desc.getTh0() + this.desc.getTh1() * -100000, true),
                    this.getGraphXCoord(300000, true),
                    this.getGraphYCoord(this.desc.getTh0() + this.desc.getTh1() * 300000, true));
        }
    }

    private int getGraphXCoord(float x)
    {
        return this.getGraphXCoord(x, false);
    }

    private int getGraphXCoord(float x, boolean ratio)
    {
        if (ratio)
            return ((int) (800 * x / 1*(Car.maxKm/maxX))+50);
        else
            return ((int) (800 * x / this.maxX)+50);
    }

    private int getGraphYCoord(float y)
    {
        return this.getGraphYCoord(y, false);
    }

    private int getGraphYCoord(float y, boolean ratio)
    {
        if (ratio)
            return ((int) (830 - (800 * y / 1*(Car.maxPrice/maxY))));
        else
            return ((int) (830 - (800 * y / this.maxY)));
    }

    private void prepareJFrame()
    {
        this.jFrame = new JFrame();
        this.jFrame.setTitle("Graphics");
        this.jFrame.setSize(900, 900);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }

}
