package MainProgram;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;


public class ServerSide {

    static Logger logger = Logger.getLogger(ServerSide.class.getName());

    public static void main(String[] args) {
        Apartment apartment;
        String buffer = "";
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            ObjectMapper objectMapper = new ObjectMapper();
            Socket clientSocket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            buffer = dataInputStream.readUTF();
            //System.out.println(buffer);
            apartment = objectMapper.readValue(buffer, Apartment.class);
            logger.info("The following message has been successfully serialized into an object! \n"
                    + buffer + "\nWith the following fields:\n"
                    + apartment.streetAddress + " \n"
                    + apartment.doorAddress + " \n"
                    + apartment.area + " \n"
                    + apartment.chamberNumber);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
