import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class LoginForm {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel mainLabel = new JLabel("Login Form");
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    public LoginForm(){
        frame.setTitle("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainLabel.setBounds(300,50,300,50);
        mainLabel.setFont(new Font(null,Font.BOLD,25));

        panel.setLayout(null);
        userTextField.setBorder(new MatteBorder(0,0,1,0, Color.BLACK));
        userTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userTextField.getText().equals("Enter username")) {
                    userTextField.setText("");
                    userTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userTextField.getText().isEmpty()) {
                    userTextField.setForeground(Color.GRAY);
                    userTextField.setText("Enter username");
                }
            }
        });

        passwordField.setBorder(new MatteBorder(0,0,1,0, Color.BLACK));
        passwordField.setText("Enter password");

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordField.getText().equals("Enter password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().isEmpty() || passwordField.getPassword().equals("Enter password")) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText("Enter password");
                    //if (passwordField.getEchoChar()=='\u2022')
                        //passwordField.setEchoChar((char) 0);
                }
            }
        });

        panel.setBounds(200,125,400,300);
        panel.setBackground(new Color(255,255,255,128));

        usernameLabel.setBounds(50,25,100,50);
        userTextField.setBounds(125,25,200,50);

        passwordLabel.setBounds(50,125,100,50);
        passwordField.setBounds(125,125,200,50);

        loginButton.setFont(new Font(null,Font.BOLD,18));
        loginButton.setBounds(100,225,200,50);
        loginButton.setBorder(new RoundBorder(15));
        loginButton.setFocusable(false);

        panel.add(usernameLabel);
        panel.add(userTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        frame.add(mainLabel);
        frame.add(panel);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
    static class RoundBorder implements Border{
        private final int radius;

        RoundBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            //g.drawRoundRect(x,y,width-1,height-1,radius,radius);

            Graphics2D graphics2D = (Graphics2D) g.create();

            RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(x,y,width-1,height-1,radius*2,radius*2);
            graphics2D.draw(roundRectangle2D);
            graphics2D.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius,radius,radius,radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
