import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerNode implements Runnable{


    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;

    public ServerNode() {
        connections = new ArrayList<>();
        done = false;
    }


    @Override
    public void run(){
        try {
            server= new ServerSocket(9999);
            pool= Executors.newCachedThreadPool();
            while (!done) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch ( Exception e) {
            shutDown();
        }
    }

    public void broadcast(String message){

        for (ConnectionHandler ch: connections){
            if(ch !=null){
                ch.sendMessage(message);
            }
        }

    }

    public void shutDown(){

        try {

            done= true;
            if (!server.isClosed()){
                server.close();
            }

            for (ConnectionHandler ch: connections){
                ch.shutdown();
            }
        }catch (IOException E){

        }
        }
