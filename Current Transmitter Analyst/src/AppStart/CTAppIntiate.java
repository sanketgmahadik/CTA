/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppStart;

import Screens.GraphicalScreen;
import Screens.GraphCT;
import Communication.OpenPortConnection;
import Communication.PortConnection;
import Communication.SerialPortReader;
import Protocol.CommandCodes;
import static Protocol.CommandCodes.READY;
import Protocol.ReadCommand;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author UNICOM-SOFTWARE
 */
public class CTAppIntiate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        GraphicalScreen.createGraphicalScreen();
    }
    
    public static int TIME_OUT_INTERVAL = 500;
    public static int NEXT_COMMAND_INTERVAL = 200;
    
    
    public static int NO_OF_ADDRESS = 3000;
    public static int ADDRESS_INCREMENTER = 100;
    
    public static void connectAndPlot()
    {
        if(GraphicalScreen.txtTransmiterAddress.getText().contentEquals(""))
        {
            String message = "Enter Slave Address Before Connect";
            JOptionPane.showMessageDialog(new JFrame(), message, "Notice", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            if(GraphicalScreen.portNames.length == 0)
            {
                String message = "No Port Found";
                JOptionPane.showMessageDialog(new JFrame(), message, "Notice", JOptionPane.ERROR_MESSAGE);
            }
            else
            {    
                OpenPortConnection portCon = new OpenPortConnection();

                System.out.println("");
                System.out.println("Connection Done : Sending Read Command For Address");
                CommandCodes.MODBUS_TRANSMISION_FLAG = READY;

                int i = 0;
                while (i < NO_OF_ADDRESS)
                {
                    System.out.println("");
                    System.out.println("------------------------------------------------------");
                    System.out.println("COMMAND NO : "+ i);
                        int trans_address = Integer.parseInt(GraphicalScreen.txtTransmiterAddress.getText());
                        ReadCommand.readAddressFrame(trans_address, CommandCodes.READ_INPUT_REGISTER, i, ADDRESS_INCREMENTER);
                        i = i + ADDRESS_INCREMENTER;

                        PortConnection.wait(NEXT_COMMAND_INTERVAL);
                }

                System.out.println("");
                System.out.println("------------------------------------------------------");
                
                for (int j = 0; j < NO_OF_ADDRESS; j++) {
                    System.out.println("Value at Register-"+j+" : "+SerialPortReader.CT_VALUES_ARRAY[j]);
                }

                for (int j = 0; j < NO_OF_ADDRESS; j++) {
                    GraphCT.series1.add(j, SerialPortReader.CT_VALUES_ARRAY[j]);
                }
            }
        }
    }
    
}
