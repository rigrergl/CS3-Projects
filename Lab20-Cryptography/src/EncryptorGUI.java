import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;

public final class EncryptorGUI {
	static Picture currentPicture;
	static EncryptorActionListener actionListener = new EncryptorActionListener();
	
	static final String OPEN_STRING = "Open...";
	static final String TRANSFORM_STRING = ("Transform...");

	static JFrame window;
	static JMenuBar menuBar;
	static JMenu menu;
	static JMenuItem open;
	static JMenuItem save;
	static JMenuItem transform;

	public static void main(String[] args) {
		window = new JFrame();
		window.setTitle("LFSR Image Encryptor/Decryptor - by Rigre Garciandia Jr");
		window.setSize(800, 150);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		menu = new JMenu("File");
		open = new JMenuItem(OPEN_STRING);
		save = new JMenuItem(Picture.SAVE_STRING);
		transform = new JMenuItem(TRANSFORM_STRING);
		
		open.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		save.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		transform.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		

		menu.add(open);
		menu.add(save);
		menu.add(transform);
		menu.setVisible(true);
		window.setJMenuBar(menuBar);
		menuBar.add(menu);

		window.setVisible(true);

		open.addActionListener(actionListener);
		save.addActionListener(actionListener);
		transform.addActionListener(actionListener);

	}

	static String PASSWORD = null;
	static Integer TAP = null;

	private static void transform() {
		JPasswordField passwordField = new JPasswordField();
		// a bit quirky, we need to tab to the field
		JOptionPane.showMessageDialog(null, passwordField, "Password", JOptionPane.QUESTION_MESSAGE);
		char[] passwordChars = null;

//		while (PASSWORD == null) {
			passwordChars = passwordField.getPassword();
			PASSWORD = String.valueOf(passwordChars);
//		}
			
		if(PASSWORD == null || PASSWORD.length() == 0) {
			JOptionPane.showMessageDialog(null, "Password cannot be empty");
			return;
		}
			

		String tap = JOptionPane.showInputDialog("Enter Tap (suggested: " + (PASSWORD.length() * PhotoMagicDeluxe.BITS_PER_CHAR - 2) + ")");
		
		if (tap != null) {
			try {
			TAP = Integer.valueOf(tap);}
			catch (NumberFormatException e) {
				System.out.println("Tap Must Be a Number");
				JOptionPane.showMessageDialog(null, "Tap needs to be a number");
				return;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Error: no tap");
			return;
		}
		
		
		if(TAP >= PASSWORD.length() * 6 || TAP < 0) {
			System.out.println("NULL");
			JOptionPane.showMessageDialog(null, "Tap is out of bounds. Try approaching the suggested value for that password");
			return;
		}

		
		
		currentPicture = PhotoMagicDeluxe.transform(currentPicture, PASSWORD, TAP);
		currentPicture.show(window, menu, save);

		PASSWORD = null;
		TAP = -1;
	}

	private static void open() {
		System.out.println("opening");
		FileDialog chooser = new FileDialog(window, "Use a .png or .jpg extension", FileDialog.LOAD);
		chooser.setVisible(true);
		if (chooser.getFile() != null) {
//			save(chooser.getDirectory() + File.separator + chooser.getFile());
			currentPicture = new Picture(chooser.getDirectory() + File.separator + chooser.getFile());
			currentPicture.show(window, menu, save);
		}
	}
	
	public static void save() {
		FileDialog chooser = new FileDialog(window, "Use a .png or .jpg extension", FileDialog.SAVE);
		chooser.setVisible(true);
		if (chooser.getFile() != null) {
			currentPicture.save(chooser.getDirectory() + File.separator + chooser.getFile());
		}
	}

	private static class EncryptorActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			switch (command) {
			case OPEN_STRING:
				open();
				break;
			case TRANSFORM_STRING:
				if (currentPicture != null) {
					transform();
				}
				else {
					JOptionPane.showMessageDialog(null, "No Image to transform");
				}
				break;
			case Picture.SAVE_STRING:
				save();
			}
		}
	}

}
