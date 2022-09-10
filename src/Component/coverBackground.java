
package Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class coverBackground extends JPanel{

    public coverBackground() {
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
        int height =getHeight();
         g2.setColor(new Color(142,111,32));
         g2.fill(createShape(height, 80, 50,80,20,40));
          g2.setColor(new Color(12,11,232));
        g2.fill(createShape(height, 40, 20,60,20,40));
         
        
       
        g2.dispose();
        
        super.paint(g); 
    }
    
    
  private Shape createShape(int location, int straight, int ...total){
      int width = getWidth();
      int height = getHeight();
      int sq = width/total.length;
      int size = location;
      GeneralPath p = new GeneralPath();
      int space =0;
      int sp = 0;
      int sk = location - straight;
      for(int to:total){
          to = size - to;
          int s = space+sq/2;
          p.append(new CubicCurve2D.Double(sp, sk, s, sk, s, to, sp+sq, to), true);
          space += sq;
          sp += sq;
          sk = to;
      }
      p.lineTo(width, sk);
      p.lineTo(width, height);
      p.lineTo(0, height);
      return p;
  }
}