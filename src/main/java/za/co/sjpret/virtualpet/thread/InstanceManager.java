package za.co.sjpret.virtualpet.thread;

import java.io.IOException;
import java.net.ServerSocket;

public class InstanceManager extends Thread {
    private static final int PORT = 9999;

    public InstanceManager() {
        start();
    }

    @Override
    public void run() {
        try (ServerSocket ignored = new ServerSocket(PORT)) {
            System.out.println("Application is running.");
            synchronized (this) {
                wait();
            }
        } catch (IOException e) {
            System.err.println("Another instance is running.");
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println("ServerSocket Thread interrupted.");
        }
    }
}
