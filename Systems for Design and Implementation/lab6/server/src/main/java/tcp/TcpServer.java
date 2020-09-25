package tcp;

import container.Message;
import exception.ServiceException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

public class TcpServer {
    private ExecutorService executorService;
    private Map<String, UnaryOperator<Message>> methodHandlers;

    public TcpServer(ExecutorService executorService) {
        this.methodHandlers = new HashMap<>();
        this.executorService = executorService;
    }

    public void addHandler(String methodName, UnaryOperator<Message> handler) {
        methodHandlers.put(methodName, handler);
    }

    public void startServer() {
        try (var serverSocket = new ServerSocket(Message.PORT)) {
            System.out.println("server started");
            while (true) {
                Socket client = serverSocket.accept();
                executorService.submit(new ClientHandler(client));
            }
        } catch (IOException e) {
            throw new ServiceException("error connecting clients");
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket client) {
            this.socket = client;
        }

        @Override
        public void run() {
            try (var is = new ObjectInputStream(socket.getInputStream());
                 var os = new ObjectOutputStream(socket.getOutputStream())) {
                Message request = new Message();
                request.readFrom(is);
                Message response = methodHandlers.get(request.getHeader())
                        .apply(request);
                response.writeTo(os);
            } catch (IOException | ClassNotFoundException e) {
                throw new ServiceException("Error processing client.");
            }
        }
    }
}
