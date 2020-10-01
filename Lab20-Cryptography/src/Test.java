import javax.swing.*;

public class Test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String username = JOptionPane.showInputDialog(null, "User Name");
                System.out.println("Username: " + username);

                JPasswordField passwordField = new JPasswordField(8);
                // a bit quirky, we need to tab to the field
                JOptionPane.showMessageDialog(null, passwordField, "Password",
                        JOptionPane.QUESTION_MESSAGE);
                char[] password = null;
                password = passwordField.getPassword();
                System.out.println("Password: " + String.valueOf(password));
            }
        });
    }
}