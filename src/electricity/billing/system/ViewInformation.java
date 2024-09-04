package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewInformation extends JFrame implements ActionListener {

    JButton cancel;
    String meter;
    ViewInformation(String meter){
        this.meter = meter;
        setBounds(350,150,850,650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250,10,500,30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,80,100,24);
        add(lblname);

        JLabel name = new JLabel("");
        name.setBounds(250,80,200,24);
        add(name);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100,140,100,24);
        add(lblmeternumber);

        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250,140,100,24);
        add(meternumber);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100,200,100,24);
        add(lbladdress);

        JLabel address = new JLabel("");
        address.setBounds(250,200,100,24);
        add(address);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100,260,100,24);
        add(lblcity);

        JLabel city = new JLabel("");
        city.setBounds(250,260,100,24);
        add(city);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500,80,100,24);
        add(lblstate);

        JLabel state = new JLabel("");
        state.setBounds(610,80,100,24);
        add(state);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500,140,100,24);
        add(lblemail);

        JLabel email = new JLabel("");
        email.setBounds(610,140,200,24);
        add(email);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500,200,100,24);
        add(lblphone);

        JLabel phone = new JLabel("");
        phone.setBounds(610,200,100,24);
        add(phone);

        try{
            Conn c=new Conn();
            ResultSet rs =c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");

            while(rs.next()){
                name.setText((rs.getString("name")));

                address.setText((rs.getString("address")));

                city.setText((rs.getString("city")));

                state.setText((rs.getString("state")));

                email.setText((rs.getString("email")));

                phone.setText((rs.getString("phone")));

                meternumber.setText((rs.getString("meter_no")));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(350,340,100,26);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20,350,600,300);
        add(image);

        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }

    public static void main(String[] args) {
        new ViewInformation("");
    }
}
