package MainProgram;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientSide {

    static Logger logger = Logger.getLogger(ClientSide.class.getName());

    public static void main(String[] args) {
        Apartment apartment = new Apartment();
        ObjectMapper objectMapper = new ObjectMapper();
        String readValue = "";
        apartment.chamberNumber = 3;
        apartment.area = 32.2f;
        apartment.doorAddress = "403";
        apartment.streetAddress = "Zelinski str.";
        OutputStream outputStream;
        try {
            readValue = objectMapper.writeValueAsString(apartment);
            Socket socket = new Socket("localhost", 1234);
            outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(readValue);
            dataOutputStream.flush();
            dataOutputStream.close();
            logger.info("Message \"" +readValue + "\" has been sent to the Server!");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
