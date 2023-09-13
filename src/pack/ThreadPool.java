package pack;

import pack.network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class holds a global thread-pool for executing our threads.
 */
public class ThreadPool {

    private static ExecutorService executor;
    private static Future serverFuture;
    private static Server server;

    /**
     * Initializes a new CachedThreadPool.
     * @see java.util.concurrent.Executors#newCachedThreadPool()
     */
    public static void init() {
        executor = Executors.newCachedThreadPool();
    }

    /**
     * {@link java.util.concurrent.ExecutorService#execute(java.lang.Runnable)}
     */
    public static void execute(Runnable r) {
        if (executor == null)
            init();
        if (r instanceof Server) {
            server = (Server)r;
            serverFuture = executor.submit(r);
        } else
            executor.execute(r);


    }

    /**
     * for shut down server
     * and cancel it
     */
    public static void shutDownServer() {
        if (server != null && serverFuture != null) {
            serverFuture.cancel(true);

            ServerSocket serverSocket = server.getServerSocket();
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * {@link java.util.concurrent.ExecutorService#shutdown()}
     */
    public static void shutdown() {
        executor.shutdown();
    }

    /**
     * {@link java.util.concurrent.ExecutorService#shutdownNow()}
     */
    public static void shutdownNow() {
        executor.shutdownNow();
    }
}
