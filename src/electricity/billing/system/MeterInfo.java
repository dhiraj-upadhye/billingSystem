package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MeterInfo extends JFrame implements ActionListener {
    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblmeter;
    String meternumber;
    Choice meterlocation, metertype, phasecode, billtype;

    MeterInfo(String meternumber){
        this.meternumber = meternumber;
        setSize(700,500);
        setLocation(400,200);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading =new JLabel("Meter Information");
        heading.setBounds(180,20,200,25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        p.add(heading);

        JLabel lblname =new JLabel("Meter Number");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);

        JLabel lblmeternumber =new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
        p.add(lblmeternumber);

        JLabel lblmeterno =new JLabel("Meter Location");
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);

        meterlocation = new Choice();
        meterlocation.add("Inside");
        meterlocation.add("Outside");
        meterlocation.setBounds(240,120,200,20);
        p.add(meterlocation);

        JLabel lblphone =new JLabel("Meter Type");
        lblphone.setBounds(100,160,100,20);
        p.add(lblphone);

        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240,160,200,20);
        p.add(metertype);


        JLabel lbladdress =new JLabel("Phase Code");
        lbladdress.setBounds(100,200,100,20);
        p.add(lbladdress);

        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240,200,200,20);
        p.add(phasecode);

        JLabel lblcity =new JLabel("Bill Type");
        lblcity.setBounds(100,240,100,20);
        p.add(lblcity);

        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240,240,200,20);
        p.add(billtype);

        JLabel lblstate =new JLabel("Days");
        lblstate.setBounds(100,280,100,20);
        p.add(lblstate);

        JLabel lblstates =new JLabel("30 Days");
        lblstates.setBounds(240,280,100,20);
        p.add(lblstates);


        JLabel lblemail =new JLabel("Note");
        lblemail.setBounds(100,320,100,20);
        p.add(lblemail);

        JLabel lblemails =new JLabel("By Default Bill is Calculatedd for 30 Days Only.");
        lblemails.setBounds(240,320,500,20);
        p.add(lblemails);



        next = new JButton("Submit");
        next.setBounds(220,380,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        setLayout(new BorderLayout());
        add(p, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image,"West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == next){
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";

            String query = "insert into meter_info values ('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"','"+days+"')";
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully.");
                setVisible(false);


            }catch(Exception e){
                e.printStackTrace();
            }

        }else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new MeterInfo("");
    }
}
