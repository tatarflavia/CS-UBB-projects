package tcp;

import container.Message;
import exception.ServiceException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TcpClient {
    public Message sendAndReceive(Message request) {
        try (var socket = new Socket(Message.HOST, Message.PORT);
             var is = socket.getInputStream();
             var os = socket.getOutputStream()
        ) {
            request.writeTo(new ObjectOutputStream(os));
            Message response = new Message();
            response.readFrom(new ObjectInputStream(is));
            return response;
        } catch (IOException | ClassNotFoundException e) {
            throw new ServiceException("Error connecting to server.");
        }
    }
}
