
package Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;
import java.time.chrono.MinguoDate;
import javax.swing.JPanel;


// in this segment i extend JPanel in other be able to drag it to the main page 
public class backGround extends JPanel{

    /**
     * @return the minate
     */
    public float getMinate() {
        return minate;
    }

    /**
     * @param minate the minate to set
     */
    public void setMinate(float minate) {
        this.minate = minate;
        repaint();;
    }

    public backGround() {
        setOpaque(false); //remove opaque 
    }
    // here we set a size for the header 
    private int header = 50;
    
    private float minate;
    
    // add paint, rendering hints for the shape smootness and Graphics2D

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // adding os shapes and also setting the colors and also adding float for the animatuion 
        int height = (int) (getHeight()* (1f- easeInOutQuad(minate)));
        g2.setColor(new Color(141,251,55));
        g2.fill(createShape(height, 55, 100, 80,40,70));
        
        g2.setColor(Color.green);
        g2.fill(createShape(height, 65,30,54,90));
        
         int heigh = (int) (getHeight()* (1f- easeInOutBack(minate)));
         heigh +=header;
         g2.setColor(new Color(240,240,240));
         g2.fillRect(0, heigh, getWidth(), getHeight());
        g2.dispose();
        super.paint(g); 
    }
    // next we add another components with its slide animation 
    private double easeInOutQuad(double x){
           double v= x < 0.5 ? 2 * x * x : 1 - Math.pow(-2 * x + 2, 2) / 2;
           return (double)v;
    }
    // here we add easing animations in other for the componets to have some stylish designs 
    private double easeInOutBack(double x ){
        double c1 = 1.70158;
        double c2 = c1 * 1.525;
        double v = x < 0.5
          ? (Math.pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2
          : (Math.pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2;
        return (double)v;
    }    
    // adding of the shape and its initial sizes 
    private Shape createShape(int location, int sizes, int ...forPoint){
        int width = getWidth();
        int height = getHeight();
        int ss = width/forPoint.length;
        int size = location;
        
        // adding of the general path for the initial size in other to append it
        GeneralPath p = new GeneralPath();
        int space = 0;
        int sk = 0;
        int sz = location-sizes;
        for(int forPoints: forPoint){
            forPoints = size - forPoints;
            int s = space+ss/2;
            p.append(new CubicCurve2D.Double(sk, sz, s, sz, s, forPoints, sk+ss, forPoints), true);
            space +=ss;
            sk +=ss;
            sz = forPoints;
                        
        }
        p.lineTo(width, sz);
        p.lineTo(width, height);
        p.lineTo(0, height);
        return p;
                
    }
    
}
