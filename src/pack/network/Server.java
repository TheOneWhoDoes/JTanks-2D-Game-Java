package pack.network;

import pack.entities.manager.EntityManager;
import pack.states.GameState;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * this class manages connecting possible clients
 * in a separate thread, and is able to get in/out stream
 * of these clients
 */
public class Server implements Runnable {


    private static HashMap<Integer, Socket> clientsMap;
    public static final int PORT_NUMBER = 7654;
    private ServerSocket server;
    private EntityManager entityManager;
    private int joinedPlayers = 0;


    public Server(GameState state) {

        entityManager = state.getEntityManager();
        clientsMap = new HashMap();

    }

    /**
     * runs the server
     */
    @Override
    public void run() {
        try {
            server = new ServerSocket(PORT_NUMBER);
            System.out.println("Server started...");
            System.out.println();

            } catch (IOException e) {
                 e.printStackTrace();
            }


        while (true) {
            try {
                Socket client = server.accept();
                joinedPlayers++;
                int number = joinedPlayers;
                clientsMap.put(number, client);
                entityManager.addClientPlayer(number);

            } catch (IOException ex) {
                return;
            }
        }

    }

    /**
     * returns the ServerSocket object of the server
     * @return
     */
    public ServerSocket getServerSocket() {
        return server;
    }

    /**
     * returns the inputs stream of the wanted client
     * @param number
     * @return
     */
    public static InputStream getInputStream(int number) {
        try {
            return clientsMap.get(number).getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns the inputs stream of the wanted client
     * @param number
     * @return
     */
    public static OutputStream getOutputStream(int number) {
        try {
            return clientsMap.get(number).getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}




