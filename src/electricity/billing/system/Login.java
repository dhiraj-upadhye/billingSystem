package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, cancel,signup;
    JTextField username;
    JPasswordField passward;
    Choice logginin;
    Login(){
        super("Electricity Billing System");

        setSize(640, 300);
        setLocation(400, 200);

        Container c = getContentPane();
        c.setLayout(null); // Set layout to null for absolute positioning

        getContentPane().setBackground(Color.WHITE);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300, 20, 100, 20);
        c.add(lblusername);

        username = new JTextField();
        username.setBounds(400, 20, 150, 20);
        c.add(username);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 60, 100, 20);
        c.add(lblpassword);

        passward = new JPasswordField();
        passward.setBounds(400, 60, 150, 20);
        c.add(passward);

        JLabel logginginas = new JLabel("Logging in as");
        logginginas.setBounds(300, 100, 100, 20);
        c.add(logginginas);

        logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(400, 100, 150, 20);
        c.add(logginin);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(18,15,Image.SCALE_DEFAULT);
        login = new JButton("Login",new ImageIcon(i2));
        login.setBounds(330, 160, 100, 20);
        login.addActionListener(this);
        c.add(login);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(450, 160, 100, 20);
        cancel.addActionListener(this);
        c.add(cancel);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        signup = new JButton("Signup", new ImageIcon(i6));
        signup.setBounds(330, 190, 220, 20); // Adjust position to avoid overlap
        signup.addActionListener(this);
        c.add(signup);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0,0,250,250);
        c.add(image);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == login){
            String susername = username.getText();
            String spassword = passward.getText();
            String user = logginin.getSelectedItem();

            if(susername.trim().isEmpty() || spassword.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill the Content Properly.");
                username.setText("");
                passward.setText("");
            }else{
                try{
                    Conn c = new Conn();
                    String query = "Select * from login where username='"+susername+"' and password='"+spassword+"' and user='"+user+"'";
                    ResultSet rs = c.s.executeQuery(query);

                    if(rs.next()){
                        String meter = rs.getString("meter_no");
                        setVisible(false);
                        new Project(user, meter);
                    }else{
                        JOptionPane.showMessageDialog(null, "Invalid Login");
                        username.setText("");
                        passward.setText("");
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        } else if(ae.getSource() == cancel){
            setVisible(false);
        } else if(ae.getSource() == signup){
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}





