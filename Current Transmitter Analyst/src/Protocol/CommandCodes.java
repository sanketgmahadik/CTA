/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocol;

import Screens.GraphicalScreen;


/**
 *
 * @author Sanket - Unicom
 */
public class CommandCodes 
{
    public static boolean READY = true;
    public static boolean COMPLETE = false;    
    public static boolean MODBUS_TRANSMISION_FLAG = COMPLETE;
    
    
    public static boolean WRITE_RESPONCE_FLAG = COMPLETE;
    
    public static int READ_HOLDING_REGISTER = 3;
    public static int READ_INPUT_REGISTER = 4;
    public static int WRITE_REGISTER = 16;
    
    public static int COMMAND_CODE;
    
    public static int DEVICE_ADDRESS = Integer.parseInt(GraphicalScreen.txtTransmiterAddress.getText());;
    
    public static String getTheStatus(boolean Value)
    {
        String complete = "COMPLETE", ready = "ready";
        String str_Status = complete;
        
        if(Value == true)
        {       str_Status = ready ;        }
        else
        {       str_Status = complete ;     }
        
        return str_Status;
    }
    
}
