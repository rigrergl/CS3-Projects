import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
    this is the Controller component
	teacher-provided: creates the environment for the model and the view.
	no student code here.
 */

public class Shelby extends JFrame
{

	private ShelbyView  view;
	private ShelbyModel model;

	public Shelby(String fileName)
	{
		super("Shelby County Marriages");

		// build the view
		view = new ShelbyView();
		view.setBackground(Color.white);
		Container c = getContentPane();
		c.add(view, BorderLayout.CENTER);

		// build the model
		model = new ShelbyModel( fileName, view );
		model.solve();
	}

	public static void main(String[] args)
	{
		Shelby scm = new Shelby("marriages.txt");
		scm.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		}
				);
		scm.setSize(450, 200);
		//scm.show();
		scm.setVisible(true);
	}
}
