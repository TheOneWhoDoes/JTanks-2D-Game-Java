package pack.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * this class manages connecting
 * to a server to play multi-player
 */
public class Client {

    private String ip;
    private static Socket server;

    public Client(String ip) {
        this.ip = ip;
    }

    /**
     * tries to connect to the sever
     * @return
     */
    public boolean connect() {

        try {
            server = new Socket(ip, Server.PORT_NUMBER);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * gets the inputStream of the server
     * @return
     */
    public static InputStream getInputStream() {
        try {
            return server.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * gets the outputStream of the server
     * @return
     */
    public static OutputStream getOutputStream() {
        try {
            return server.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
