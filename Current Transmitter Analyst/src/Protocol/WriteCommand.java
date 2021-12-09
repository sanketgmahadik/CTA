/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocol;



/**
 *
 * @author Sanket - Unicom
 */
public class WriteCommand {
    
    public static int frameLength = 7;
    public static int crcBitLength = 2;
    
    public static void writeAddressFrame(int slaveAdd,int functionNr,int startAdd, int dataLength, int[] value)
    {
        System.out.println("***********************************************************************");
        System.out.println("Writing Address "+ startAdd);
        int modbusWriteFrame[] = new int[frameLength + (dataLength * 2) + crcBitLength];
        int modbusCRC16[] = new int[crcBitLength];
        
        modbusWriteFrame[0] = (byte)slaveAdd;
        modbusWriteFrame[1] = (byte)functionNr;
        modbusWriteFrame[2] = (byte)(startAdd >> 8);
        modbusWriteFrame[3] = (byte)((startAdd << 8)>>8);
        modbusWriteFrame[4] = (byte)(dataLength >> 8);
        modbusWriteFrame[5] = (byte)((dataLength << 8)>>8);
        modbusWriteFrame[6] = (byte)(dataLength * 2) ;
        
        int countValueIndex = 7;
        for (int i=0 ; i < value.length ; i++)
        {
            modbusWriteFrame[countValueIndex] = (byte)(value[i] >> 8);  
            countValueIndex++;
            modbusWriteFrame[countValueIndex] = (byte)((value[i] << 8)>>8);
            countValueIndex++;
        }

        modbusCRC16 = CRC16.getCRC(modbusWriteFrame, countValueIndex);
        modbusWriteFrame[countValueIndex] = (byte)((modbusCRC16[0]<<8)>>8);
        countValueIndex++;
        modbusWriteFrame[countValueIndex] = (byte)((modbusCRC16[1]<<8)>>8);

        //Screen.requesttext.setText(Arrays.toString(modbusWriteFrame));
        SendCommand.sendCommand(modbusWriteFrame);

    }
    
    public static void main(String[] args) 
    {
        //SingleRead();
        //SingleWrite();
    }
    
//    public static void SingleRead()
//    {
//        System.out.println("");
//        //OpenPortConnection connection = new OpenPortConnection();
//        SendCommand.MODBUS_TRANSMISION_FLAG = true;
//        //int address = Integer.parseInt(Gra.addresstext.getText());
//        //int trans_address = Integer.parseInt(Screen.txtTransmiterAddress.getText());
//        //ReadCommand.readAddressFrame(trans_address, CommandCodes.READ, address, 1);
//    }
    
    
}
