/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocol;


import Communication.CloseConnection;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 *
 * @author Sanket - Unicom
 */
public class CommandTimeOut extends TimerTask {

    public static int timeOutCount;
    
    @Override
    public void run() 
    {
        if (timeOutCount > 2) 
        {
            CommandCodes.MODBUS_TRANSMISION_FLAG = CommandCodes.READY;
            System.out.println( "Transmission Flag : "+ CommandCodes.getTheStatus(CommandCodes.MODBUS_TRANSMISION_FLAG) );
            timeOutCount = 0;
            System.out.println( "\tTime Out... please check device not connected." );
            cancel();  
//            try {
//                CloseConnection.closePortConnection();
//            } catch (SerialPortException ex) {
//                Logger.getLogger(CommandTimeOut.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } 
        else 
        {
            System.out.println("\t***** Command TimeOut Count : " + timeOutCount + " *****");
            timeOutCount++;
        }
        
    }
    
}
