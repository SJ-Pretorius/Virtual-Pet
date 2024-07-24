package za.co.sjpret.virtualpet.thread;

import java.io.IOException;
import java.net.ServerSocket;

public class InstanceManager extends Thread {

    public InstanceManager() {
        start();
    }

    @Override
    public void run() {
        try (ServerSocket ignored = new ServerSocket(9999)) {
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
