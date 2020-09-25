package container;

import java.io.*;


public class Message {
    public static final int PORT = 1234;
    public static final String HOST = "localhost";

    private String header;
    private Serializable body;

    public Message() {
    }

    public Message(String header, Serializable body) {
        this.header = header;
        this.body = body;
    }

    public void setSuccess() {
        this.header="success";
    }

    public void setNoParameter() {
        this.body="";
    }

    public void setError() {
        this.header="error";
    }

    public boolean isSuccess() {
        return header.equals("success");
    }

    public boolean isError() {
        return header.equals("error");
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Serializable getBody() {
        return body;
    }

    public void setBody(Serializable body) {
        this.body = body;
    }

    //writeObject() method for serializing an Object
    public void writeTo(ObjectOutputStream os) throws IOException {
        os.writeObject(header);
        os.writeObject(body);
    }

    // readObject() method for deserializing an object
    public void readFrom(ObjectInputStream is) throws IOException, ClassNotFoundException {
        header= (String) is.readObject();
        body= (Serializable) is.readObject();
    }

    @Override
    public String toString() {
        return "Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
