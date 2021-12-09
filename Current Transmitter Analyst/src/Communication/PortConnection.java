/**
 *
 * @author Unicom Software
 */
package Communication;

import Screens.GraphicalScreen;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import jssc.SerialPort;

public class PortConnection 
{   
    private static SerialPort serialPortInstance;
    public static String jsonPortName;
    
    private PortConnection(){}

    public static synchronized SerialPort getSerialPortConnection()
    {
        
        jsonPortName = (String) GraphicalScreen.combo_port.getSelectedItem();

        if (serialPortInstance == null)
        {                   
            serialPortInstance = new SerialPort(jsonPortName);
            System.out.println(serialPortInstance.getPortName()+" : port is Occupied.");
        }  
        
        return serialPortInstance;
    }

    public static void setSerialPortConnectionToNull()
    {
        System.out.println("Setting Current Connection with "+serialPortInstance.getPortName()+" to NULL");
        serialPortInstance = null;          
    }
    
    public static boolean checkPortIsOpen()
    {
        boolean checkPort = PortConnection.getSerialPortConnection().isOpened();
        if (checkPort){}
        else
        {                   
            String message = "Port Not Opened / Check your port.";
            JOptionPane.showMessageDialog(new JFrame(), message, "Wrong Port", JOptionPane.ERROR_MESSAGE);
            PortConnection.setSerialPortConnectionToNull();
        }
        return checkPort;
    }
//    public static String[] portNamesStringArray; 
//    public static void portOpening()
//    {
//        String portName = CommSettings.getPort();
//            
//        portNamesStringArray = SerialPortList.getPortNames();
//
//        if (Arrays.asList(portNamesStringArray).contains(portName))
//        {
//            try
//            {               
//                OpenPortConnection op = new OpenPortConnection();                   
//            }
//            catch(Exception e)
//            {
//                System.out.println("Exception : when Connect button click");
//            }
//
//            System.out.println("Connection Successful");
//
//            if (PortConnection.getSerialPortConnection().isOpened())
//            {
//
//            }
//            else
//            {                   
//                String message = "Port Not Opened / Check your port.";
//                JOptionPane.showMessageDialog(new JFrame(), message, "Wrong Port", JOptionPane.ERROR_MESSAGE);
//                PortConnection.setSerialPortConnectionToNull();
//            }
//        }
//        else
//        {
//            String message = "Please Setup the Port First";
//            JOptionPane.showMessageDialog(new JFrame(), message, "Setup Port", JOptionPane.ERROR_MESSAGE);
//        }       
//    }
    
    public static void wait(int miliseconds)
    {
        try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException ex) {
                Logger.getLogger(PortConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
