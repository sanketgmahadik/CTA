
package Communication;

import jssc.SerialPortException;

public class CloseConnection {
  
    public CloseConnection() throws SerialPortException 
    {
        closePortConnection();       
    }

    public static void closePortConnection() throws SerialPortException
    {
        System.out.println("Closing Communication Port");
        boolean opened = PortConnection.getSerialPortConnection().isOpened();
        System.out.println("Current Port Connection Opened : "+opened);
        
        if (opened)
        {
            PortConnection.getSerialPortConnection().closePort();
            boolean closed = PortConnection.getSerialPortConnection().isOpened();
            System.out.println("Port Connection Closed."+closed);
            PortConnection.setSerialPortConnectionToNull();
        }
    }
}
