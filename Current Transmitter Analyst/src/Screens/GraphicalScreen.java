/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import Screens.GraphCT;
import AppStart.CTAppIntiate;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.Style;
import jssc.SerialPortList;

/**
 *
 * @author UNICOM-SOFTWARE
 */
public class GraphicalScreen {
    
    public static void createGraphicalScreen()
    {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setBounds(5, 5, 1300, 600);
        frame.setTitle("Current Transmitter Analyst");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel jp_Container = new JPanel(null);
        jp_Container.setBounds(0, 0, 1300, 600);
        jp_Container.setBackground(Color.LIGHT_GRAY);
    
        JPanel jp_Communication = createCommunicationScreen();
        jp_Communication.setBounds(5, 5, 230, 260);
        jp_Communication.setBackground(Color.LIGHT_GRAY);
        jp_Container.add(jp_Communication);
        
        Border border2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED),"CT Graph");
        JPanel jpGraph = GraphCT.createChartPanel();
        jpGraph.setBorder(border2);
        jpGraph.setBackground(Color.LIGHT_GRAY);
        jpGraph.setBounds(240, 5, 1020, 500);
        jp_Container.add(jpGraph);
        
        JButton connectAndPlot = new JButton("Connect And Plot");
        connectAndPlot.setBackground(Color.DARK_GRAY);
        connectAndPlot.setForeground(Color.WHITE);
        connectAndPlot.addActionListener( (ActionEvent event) -> 
        {
            CTAppIntiate.connectAndPlot();
        });
        connectAndPlot.setBounds(20, 270, 200, 50);
        connectAndPlot.setFont(new Font("Serif", Font.BOLD, 21 ));
        jp_Container.add(connectAndPlot);
        
        frame.add(jp_Container);
        frame.setVisible(true);
    }
    
    public static String sb1,pt1,port1,brate,dbit,pty,sbit,prt,trans_add;
    public static String[] portNames;
    public static String[] baudrate = {"128000", "115200", "57600", "38400", "19200","14400","9600","7200","4800","2400","1800","1200"};
    public static String[] databits = {"4","5","6","7","8"};
    public static String[] parity = {"NONE", "ODD", "EVEN"};
    public static String[] stopbits = {"NONE", "ONE", "TWO"};
    
    public static JPanel commPanel = null,graphPanel = null;
    public static JButton btn_save; 
    public static JLabel lbl_port,lbl_br,lbl_db,lbl_pt,lbl_sb,lbl_transmiterAddress;
    public static JComboBox combo_port,combo_baudrate,combo_databits,combo_parity,combo_stopbits;
    public static JTextField txtTransmiterAddress;

    public static JPanel createCommunicationScreen()
    {
        
        try {
            portNames = SerialPortList.getPortNames();
        } catch (Exception e) {
            System.out.println("No Port Found");
        }
           
            DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
            dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 

            Border border1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.YELLOW)
                    ,"Communication Settings");
            commPanel = new JPanel(null);
            commPanel.setBorder(border1);
            
            lbl_transmiterAddress = new JLabel("Slave Address",JLabel.RIGHT);
            txtTransmiterAddress = new JTextField(4);

            lbl_port = new JLabel("Port",JLabel.RIGHT);
            lbl_br = new JLabel("Baudrate",JLabel.RIGHT);
            lbl_db = new JLabel("Databits",JLabel.RIGHT);
            lbl_pt = new JLabel("Parity",JLabel.RIGHT);
            lbl_sb = new JLabel("Stopbits",JLabel.RIGHT);

            combo_port = new JComboBox(portNames);    
            combo_port.setRenderer(dlcr);
            System.out.println("No. of Ports Available : "+portNames.length);

            combo_baudrate = new JComboBox(baudrate);
            combo_baudrate.setSelectedIndex(2);
            combo_baudrate.setRenderer(dlcr);

            combo_databits = new JComboBox(databits);
            combo_databits.setSelectedIndex(4);
            combo_databits.setRenderer(dlcr);

            combo_parity = new JComboBox(parity);
            combo_parity.setSelectedIndex(0);
            combo_parity.setRenderer(dlcr);

            combo_stopbits = new JComboBox(stopbits);
            combo_stopbits.setSelectedIndex(1);
            combo_stopbits.setRenderer(dlcr);

            btn_save = new JButton("Save");
            btn_save.addActionListener( (ActionEvent event) -> {
                //saveCommunicationSettings();
            });

            int width = 100;
            int height = 30;
            int space = 10;
            int x1 = 0;
            int x2 = x1 + width + space; 
            int y = 20;

            lbl_transmiterAddress.setBounds(x1, y, width, height);
            txtTransmiterAddress.setBounds(x2, y, width, height);      y = y + 40 ;
            lbl_port.setBounds(x1,y,width,height);  combo_port.setBounds(x2,y,width,height);        y = y + 40 ;
            lbl_br.setBounds(x1,y,width,height);    combo_baudrate.setBounds(x2,y,width,height);    y = y + 40 ;    
            lbl_db.setBounds(x1,y,width,height);    combo_databits.setBounds(x2,y,width,height);    y = y + 40 ;
            lbl_pt.setBounds(x1,y,width,height);    combo_parity.setBounds(x2,y,width,height);      y = y + 40 ;
            lbl_sb.setBounds(x1,y,width,height);    combo_stopbits.setBounds(x2,y,width,height);    y = y + 40 ;
                                                    btn_save.setBounds(x2,y,width,height);

            commPanel.add(lbl_transmiterAddress);
            commPanel.add(txtTransmiterAddress);
            commPanel.add(lbl_port);
            commPanel.add(combo_port);
            commPanel.add(lbl_br);
            commPanel.add(combo_baudrate);
            commPanel.add(lbl_db);
            commPanel.add(combo_databits);
            commPanel.add(lbl_pt);
            commPanel.add(combo_parity);
            commPanel.add(lbl_sb);
            commPanel.add(combo_stopbits);
            //commPanel.add(btn_save);         
        
        return commPanel;
    }
    
}
