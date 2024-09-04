package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.concurrent.ExecutionException;

public class PayBill extends JFrame implements ActionListener {
    Choice cmonth;
    String meter;
    JButton pay, back;
    PayBill(String meter){
        this.meter=meter;
        setLayout(null);
        setBounds(300,150,900,600);

        JLabel heading= new JLabel("Electricity Bill");
        heading.setBounds(145,15,200,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        add(heading);

        JLabel lblmeternumber= new JLabel("Meter Number");
        lblmeternumber.setBounds(40,80,200,24);
        add(lblmeternumber);

        JLabel meternumber= new JLabel("");
        meternumber.setBounds(250,80,200,24);
        add(meternumber);

        JLabel lblname= new JLabel("Name");
        lblname.setBounds(40,130,200,24);
        add(lblname);

        JLabel labelname= new JLabel("");
        labelname.setBounds(250,130,200,24);
        add(labelname);

        JLabel lblmonth= new JLabel("Month");
        lblmonth.setBounds(40,180,200,24);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(250,180,200,24);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);

        JLabel lblunit= new JLabel("Units");
        lblunit.setBounds(40,230,200,24);
        add(lblunit);

        JLabel labelunits= new JLabel("");
        labelunits.setBounds(250,230,200,24);
        add(labelunits);

        JLabel lbltotalbill= new JLabel("Total Bill");
        lbltotalbill.setBounds(40,280,200,24);
        add(lbltotalbill);

        JLabel labeltotalbill= new JLabel("");
        labeltotalbill.setBounds(250,280,200,24);
        add(labeltotalbill);

        JLabel lblstatus= new JLabel("Status");
        lblstatus.setBounds(40,330,200,24);
        add(lblstatus);

        JLabel labelstatus= new JLabel("");
        labelstatus.setBounds(250,330,200,24);
        labelstatus.setForeground(Color.RED);
        add(labelstatus);

        try{
            Conn c= new Conn();
            ResultSet rs=c.s.executeQuery("Select * from customer where meter_no='"+meter+"'");

            while(rs.next()){
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }

            rs=c.s.executeQuery("Select * from bill where meter_no='"+meter+"' and month='January'");

            while(rs.next()){
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                try{
                    Conn c= new Conn();

                    ResultSet rs=c.s.executeQuery("Select * from bill where meter_no='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");

                    while(rs.next()){
                        labelunits.setText(rs.getString("units"));
                        labeltotalbill.setText(rs.getString("totalbill"));
                        labelstatus.setText(rs.getString("status"));
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100,400,100,25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(250,400,100,25);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2  = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image=new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == pay){
            try{
                Conn c=new Conn();
                c.s.executeUpdate("update bill set status='Paid' where meter_no='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");
            }catch(Exception e){
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
