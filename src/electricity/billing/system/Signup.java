package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {

    JButton back, create;
    Choice accountType;
    JTextField meter,username, name;
    JPasswordField password;
    Signup(){
        super("Electricity Billing System");
//        setSize(700, 400);
//        setLocation(450, 150);

        setBounds(450, 150,700,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(30,30,625,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create Account",TitledBorder.LEADING,TitledBorder.TOP, null,new Color(172,216,230)));
//        panel.setBorder(new TitleBorder(new LineBorder(Color.BLUE)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34,139,34));
        add(panel);

        JLabel heading = new JLabel("Create Account");
        heading.setBounds(80,50,140,20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(heading);

        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(240,50,150,20);
        panel.add(accountType);

        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(80,90,140,20);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);

        meter = new JTextField();
        meter.setBounds(240,90,150,20);
        meter.setVisible(false);
        panel.add(meter);


        JLabel lblusername = new JLabel("User Name");
        lblusername.setBounds(80,130,140,20);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblusername);

        username = new JTextField();
        username.setBounds(240,130,150,20);
        panel.add(username);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(80,170,140,20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblname);

        name = new JTextField();
        name.setBounds(240,170,150,20);
        panel.add(name);

        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {

            }

            @Override
            public void focusLost(FocusEvent fe) {
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("Select * from login where meter_no='"+meter.getText()+"'");

                    while(rs.next()){
                        name.setText((rs.getString("name")));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(80,210,140,20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblpassword);

        password = new JPasswordField();
        password.setBounds(240,210,150,20);
        panel.add(password);

        accountType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                String user = accountType.getSelectedItem();
                if(user.equals("Customer")){
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                }else{
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });

        create =new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(100,250,120,26);
        create.addActionListener(this);
        panel.add(create);

        back =new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(250,250,120,26);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(390,30,250,250);
        panel.add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == create){
            String atype = accountType.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String smeter = meter.getText();

            if( susername.trim().isEmpty() || sname.trim().isEmpty() || spassword.trim().isEmpty() ){
                JOptionPane.showMessageDialog(null,"Fill the Content Properly.");
                username.setText("");
                name.setText("");
                password.setText("");
                meter.setText("");
            }else{
                try{
                    Conn c = new Conn();

                    String query =null;
                    if(atype.equals("Admin")) {
                        query="insert into login values('" + smeter + "','" + susername + "','" + sname + "','" + spassword + "','" + atype + "')";
                    }else{
                        query = "update login set username= '"+susername+"', password= '"+spassword+"', user = '"+atype+"' where meter_no='"+smeter+"'";

                    }
                    c.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null,"Account Created Successfully");
                    setVisible(false);
                    new Login();

                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }


        } else if(e.getSource() == back){
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
