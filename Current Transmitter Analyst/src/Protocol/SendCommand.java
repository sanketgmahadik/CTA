/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocol;


import AppStart.CTAppIntiate;
import Communication.PortConnection;
import static Protocol.CommandCodes.COMPLETE;
import static Protocol.CommandCodes.MODBUS_TRANSMISION_FLAG;
import static Protocol.CommandCodes.READY;
import static Protocol.CommandCodes.READ_HOLDING_REGISTER;
import java.util.Arrays;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;
import static Protocol.CommandCodes.WRITE_REGISTER;

/**
 *
 * @author Sanket - Unicom
 */
public class SendCommand {
    
    public static Timer singleCommandTimer;
    
    public static int requestFunctionCode; 
    public static int requestCommandAddress;
    public static int requestCommandDataLength;
    public static int responseMsgDataLength;

    public static long commandStartTime;
    
    public static void sendCommand(int[] x)
    {
        System.out.println("Request Message Data Length : "+ x.length);
        
        requestFunctionCode = x[1];
        System.out.println("Function Code : "+ requestFunctionCode);
        
        short Address = (short)(((x[2] & 0xFF) << 8) | (x[3] & 0xFF));
        requestCommandAddress = Address;
        System.out.println("Input/Output Address : "+Address);
        
        short dataLength = (short)(((x[4] & 0xFF) << 8) | (x[5] & 0xFF));
        requestCommandDataLength = dataLength;
        System.out.println("Input/Output Length : "+ dataLength);
        
        int responseDataLength = 2 + 1 +  (dataLength * 2) + 2; 
        responseMsgDataLength = responseDataLength;
        
        if (requestFunctionCode == CommandCodes.WRITE_REGISTER)
        {
            responseDataLength = 8;//2 + 1 + (dataLength * 2) + 2;
            responseMsgDataLength = responseDataLength;
        }
         
        
        System.out.println("Response Message Data Length : "+ responseMsgDataLength);
        
        commandStartTime = System.currentTimeMillis();
        
        System.out.println("");
        if (MODBUS_TRANSMISION_FLAG == READY)
        {
            System.out.println("Command Start Time : "+commandStartTime);
            System.out.println("Sending command : " + Arrays.toString(x));
//            if(PortConnection.checkPortIsOpen())
//            {
                try 
                {      
//                    System.out.println("\tTimer Started.");
//                        singleCommandTimer = new Timer();
//                        singleCommandTimer.schedule(new CommandTimeOut(), 0, CTAppIntiate.TIME_OUT_INTERVAL);
                        
                    PortConnection.getSerialPortConnection().writeIntArray(x);
                    MODBUS_TRANSMISION_FLAG = COMPLETE;

                    System.out.println("Transmission Flag : "+CommandCodes.getTheStatus(MODBUS_TRANSMISION_FLAG) );

                    if (requestFunctionCode == READ_HOLDING_REGISTER || requestFunctionCode == WRITE_REGISTER)
                    {
                        
                    }   
                } catch (SerialPortException ex) 
                {
                    //Logger.getLogger(SendCommand.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("hjhgb");
                }
//            }
        }
    } 
    
}
