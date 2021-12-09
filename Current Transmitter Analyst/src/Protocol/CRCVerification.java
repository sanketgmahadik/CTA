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
public class CRCVerification {
    
    public static int received_MSB;
    public static int received_LSB;
    
    public static int calculated_MSB;
    public static int calculated_LSB;
    
    public static boolean verifyCRC(int[] byteArray)
    {
        boolean CRCFlag = true;
        
        int arraYLength = byteArray.length;
        received_LSB = byteArray[arraYLength-2];
        received_MSB = byteArray[arraYLength-1];
        
        System.out.println("Received LSB : "+received_LSB);
        System.out.println("Received MSB : "+received_MSB);
        
        int[] tempByte = Arrays.copyOfRange(byteArray, 0, arraYLength-2);
        
        System.out.println(Arrays.toString(tempByte));
        
        int modbusCRC16[] = new int[2];
        
        modbusCRC16 = CRC16.getCRC(tempByte, arraYLength-2);
        
        calculated_LSB = (byte)((modbusCRC16[0]<<8)>>8);
        
        calculated_MSB = (byte)((modbusCRC16[1]<<8)>>8);
        
        System.out.println("Calculated LSB : "+calculated_LSB);
        System.out.println("Calculated MSB : "+calculated_MSB);
        
        if (received_LSB == calculated_LSB && received_MSB == calculated_MSB)
        {
            System.out.println("CRC Matched.");
            CRCFlag = true;
        }
        else    
        {
            CRCFlag = false;
        }
        
        
        return CRCFlag;
    }
    
}
