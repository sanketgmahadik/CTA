/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import AppStart.CTAppIntiate;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import Protocol.CommandCodes;
import static Protocol.CommandCodes.COMPLETE;
import static Protocol.CommandCodes.MODBUS_TRANSMISION_FLAG;
import static Protocol.CommandCodes.READY;
import Protocol.CommandTimeOut;
import Protocol.SendCommand;

/**
 *
 * @author Sanket - Unicom
 */
public class SerialPortReader implements SerialPortEventListener 
{
 
    public SerialPortReader(SerialPort s) { }
          
    @Override
    public void serialEvent(SerialPortEvent event) 
    {
        int[] temp = null;
        
        if(event.isRXCHAR())        //If data is available
        {               
            if(MODBUS_TRANSMISION_FLAG == COMPLETE)     // check for response which is asked. 
            {
                if(event.getEventValue() == SendCommand.responseMsgDataLength)  //Check bytes count in the input buffer
                {                                                                                                               
                    try 
                    {
                        //Read data available.
                        temp = PortConnection.getSerialPortConnection().readIntArray();
                        
                        System.out.println("Response Received.");
                        long endTime = System.currentTimeMillis();
                        System.out.println("Command End Time : "+endTime);
                        long totalTime = endTime - SendCommand.commandStartTime;
                        System.out.println("");
                        System.out.println("Total Time : "+ totalTime);                       
                        //System.out.println("\tTimer Ends Here.");                            

                        int receivedMsgDataLength = temp[2];
                        if (SendCommand.requestFunctionCode == CommandCodes.READ_INPUT_REGISTER )
                        {                                 
                            System.out.println("");                            
                            System.out.println("Response Packet Length : "+ receivedMsgDataLength);
                            System.out.println("Read Command Response :"+ Arrays.toString(temp));                            
                            commonResponse(temp, receivedMsgDataLength);
                        }    
                        else
                        {                           
                            System.out.println("");
                            System.out.println("Response Packet Length : "+ receivedMsgDataLength);
                            System.out.println("Write Command Response : "+Arrays.toString(temp));
                            //CommandCodes.WRITE_RESPONCE_FLAG = READY;
                        }
                        
                        //CommandTimeOut.timeOutCount = 0;
                        MODBUS_TRANSMISION_FLAG = READY;

                        temp = null;

                    } 
                    catch (SerialPortException ex) 
                    {
                        Logger.getLogger(OpenPortConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else
            {
                //System.out.println("Busy In Data Transfer OR DATA Coming Randomly");
            }
        }
    }
    
    public static short[] buffer_100ValuesArray;
    public static short[] CT_VALUES_ARRAY = new short[CTAppIntiate.NO_OF_ADDRESS];
    public static void commonResponse(int[] byteArray, int responseDataLength)
    {
        int incrementValue = SendCommand.requestCommandDataLength;
        System.out.println("**** RESPONSE With requested Data Length : "+responseDataLength);
        buffer_100ValuesArray = new short[incrementValue];
        for (int i = 3 ; i < 3+responseDataLength; i++)//for (int i = 3 ; i < SendCommand.responseMsgDataLength-2; i++)
        {
            System.out.print("|"+byteArray[i]+"|");
        }
        System.out.println("");

        int j = 3, k=0;
        while(j < responseDataLength+2)
        {
            buffer_100ValuesArray[k] = (short)(((byteArray[j] & 0xFF) << 8) | (byteArray[j+1] & 0xFF));
            //System.out.println("Value at Register-"+k+" : "+buffer_100ValuesArray[k]);
            k=k+1;
            j=j+2;
        }
        
        System.arraycopy(buffer_100ValuesArray, 0, CT_VALUES_ARRAY, SendCommand.requestCommandAddress, CTAppIntiate.ADDRESS_INCREMENTER);
        
    }
   
}
