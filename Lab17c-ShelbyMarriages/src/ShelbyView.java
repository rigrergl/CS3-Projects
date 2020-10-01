import java.awt.*;
import javax.swing.*;

/*
  this is the View component
  the view component is responsible for displaying the output in a graphical window (a JPanel)
  this can be as complicated as the teacher wants.  The student interface is only through the
  updateView method, passing the data structure to be displayed.
*/

class ShelbyView extends JPanel
{

	private int[] values;
    private static final String[] monthNames =
		{"", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
             "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static int SPACING = 30, ULOFFSET = 10, BASELINE = 150;


    // entry point from model, requests grid be redisplayed
    public void updateView( int[] md )
    {
        values = md;
        repaint();
    }

	// the model calls updateView, which makes a request to repaint, and the OS
	// makes a call to paintComponent to do the actual drawing

	public void paintComponent( Graphics g )
    {
      super.paintComponent( g );
      Graphics2D g2 = ( Graphics2D ) g;
      g2.setColor( Color.black );
      g2.drawString( "Shelby County Kentucky Marriages 1792 - 1800, by Month", 50, 30 );
      for ( int i = 1; i <= 12; i++ )
      {
         g2.setColor( getRandomColor() ) ;
         g2.fillRect( ULOFFSET + SPACING * i, BASELINE - values[ i ], SPACING - 2, values[ i ] );
         g2.setColor( Color.black );
         g2.drawString( monthNames[ i ], ULOFFSET + SPACING * i, BASELINE + 20 );
      }
    }

	private Color getRandomColor()
    {
      return new Color( 55 + ( int ) ( 180 * Math.random() ),
                        55 + ( int ) ( 180 * Math.random() ),
                        55 + ( int ) ( 180 * Math.random() ) );
    }

}
