/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocol;

import java.util.Arrays;

/**
 *
 * @author Sanket - Unicom
 */
public class ReadCommand {
    
    public static int frameLength = 8;
    public static int crcBitLength = 2;

    public static void readAddressFrame(int slaveAdd,int functionNr,int startAdd, int dataLength)
    {
        
        int[] readAddressFrame = new int[frameLength];
        int modbusCRC16[] = new int[crcBitLength];
        
        readAddressFrame[0] = slaveAdd;
        readAddressFrame[1] = functionNr;
        readAddressFrame[2] = (startAdd >> 8);
        readAddressFrame[3] = ((startAdd << 8)>>8);
        readAddressFrame[4] = (dataLength >> 8);
        readAddressFrame[5] = ((dataLength << 8)>>8);
        
        modbusCRC16 = CRC16.getCRC(readAddressFrame, 6);
        readAddressFrame[6] = ((modbusCRC16[0]<<8)>>8);
        readAddressFrame[7] = ((modbusCRC16[1]<<8)>>8);
        

        System.out.println("------------------------------------------------------");
        System.out.println("Sending New Frame : "+Arrays.toString(readAddressFrame));

        SendCommand.sendCommand(readAddressFrame);
        
    }
    
}
