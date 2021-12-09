
package Communication;



import Screens.GraphicalScreen;
import jssc.SerialPort;
import jssc.SerialPortException;

public class OpenPortConnection {

    int baudrate, databits, parity, stopbit , transmitterAddress;
    String portName,str_Parity,str_StopBit;
    
    public OpenPortConnection()
    {  
        
        transmitterAddress = Integer.parseInt(GraphicalScreen.txtTransmiterAddress.getText());
        baudrate    = Integer.parseInt(GraphicalScreen.baudrate[GraphicalScreen.combo_baudrate.getSelectedIndex()]);
        databits    = Integer.parseInt(GraphicalScreen.databits[GraphicalScreen.combo_databits.getSelectedIndex()]);
        str_Parity = (String) GraphicalScreen.combo_parity.getSelectedItem();
        if(str_Parity.contains("NONE"))    {   parity = 0;  }
        if(str_Parity.contains("ODD"))     {   parity = 1;  }
        if(str_Parity.contains("EVEN"))    {   parity = 2;  }
        str_StopBit = (String) GraphicalScreen.combo_stopbits.getSelectedItem();
        if(str_StopBit.contains("ONE"))    {   stopbit = 1;  }
        if(str_StopBit.contains("TWO"))    {   stopbit = 2;  }
        if(str_StopBit.contains("NONE"))   {   stopbit = 0;  }
        
        System.out.println(baudrate+" "+databits+" "+parity+" "+stopbit+" ");
        
        try 
        {
            PortConnection.getSerialPortConnection().openPort();//Open serial port
            
            PortConnection.getSerialPortConnection().setParams(baudrate, databits, stopbit, parity);//Set params.
            
            int mask = SerialPort.MASK_RXCHAR;//Prepare mask
            
            PortConnection.getSerialPortConnection().setEventsMask(mask);//Set mask
            
            //Add SerialPortEventListener
            PortConnection.getSerialPortConnection().
                    addEventListener(new SerialPortReader(PortConnection.getSerialPortConnection()));
            
            System.out.println("Inside the Class-*OPEN-CONNECTION*");
        }
        catch (SerialPortException ex) 
        {
            //ex.printStackTrace();
            System.out.println("////// Error in Opening Connection from Class-*OPEN-CONNECTION*//////");
            
            try 
            {
                System.out.println("Forcefully Closing Port bcoz of error: cause port is open");
                
                if (PortConnection.getSerialPortConnection().isOpened())
                {
                    PortConnection.getSerialPortConnection().closePort();
                }
                
            } 
            catch (SerialPortException ex1) 
            {
                System.out.println("Exception in Forcefully Closing connection.");
            }
        }       
    }
}

