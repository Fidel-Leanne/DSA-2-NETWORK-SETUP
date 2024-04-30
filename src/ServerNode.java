import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ServerNode class representing a chat server
public class ServerNode implements Runnable {

    // List to hold all active connections
    private ArrayList<ConnectionHandler> connections;

    // ServerSocket to accept incoming connections
    private ServerSocket server;

    // Flag to indicate whether the server is running or not
    private boolean done;

    // Thread pool to manage client connections
    private ExecutorService pool;

    // Constructor to initialize the ServerNode
    public ServerNode() {
        connections = new ArrayList<>();
        done = false;
    }

    // Method to start the server
    @Override
    public void run() {
        try {
            // Create a ServerSocket bound to port 9999
            server = new ServerSocket(9999);

            // Create a thread pool to handle client connections
            pool = Executors.newCachedThreadPool();

            // Listen for incoming connections
            while (!done) {
                Socket client = server.accept(); // Accept a new client connection
                ConnectionHandler handler = new ConnectionHandler(client); // Create a handler for the connection
                connections.add(handler); // Add the handler to the list of connections
                pool.execute(handler); // Execute the handler in a separate thread
            }
        } catch (Exception e) {
            shutDown(); // Shutdown the server in case of an exception
        }
    }

    // Method to broadcast a message to all connected clients
    public void broadcast(String message) {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendMessage(message);
            }
        }
    }

    // Method to shut down the server
    public void shutDown() {
        try {
            done = true; // Set the flag to indicate server shutdown
            pool.shutdown();
            if (!server.isClosed()) {
                server.close(); // Close the ServerSocket
            }

            // Shutdown all active connections
            for (ConnectionHandler ch : connections) {
                ch.shutdown();
            }
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }
    }

    // Inner class to handle individual client connections
    class ConnectionHandler implements Runnable {

        // Socket representing the client connection
        private Socket client;

        // Reader to read messages from the client
        private BufferedReader in;

        // Writer to send messages to the client
        private PrintWriter out;

        // Nickname of the client
        private String nickname;

        // Constructor to initialize the connection handler with a client socket
        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        // Method to handle communication with the client
        @Override
        public void run() {
            try {
                // Initialize input and output streams
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                // Request the client to enter a nickname
                out.println("Please enter a nickname");
                nickname = in.readLine();

                // Notify other clients about the new user
                System.out.println(nickname + " connected");
                broadcast(nickname + " joined the chat");

                String message;

                // Continue reading messages from the client until null is received
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("/nick")) {
                        // Handle nickname change command
                        String[] messageSplit = message.split(" ", 2);
                        if (messageSplit.length == 2) {
                            broadcast(nickname + " renamed themselves to " + messageSplit[1]);
                            System.out.println(nickname + " renamed themselves to " + messageSplit[1]);
                            nickname = messageSplit[1];
                            out.println("Successfully changed nickname to " + nickname);
                        } else {
                            out.println("No nickname provided");
                        }
                    } else if (message.startsWith("/quit")) {
                        // Handle quit command
                        broadcast(nickname + " left chat");
                        shutdown();
                    } else {
                        // Broadcast the message to all clients
                        broadcast(nickname + ": " + message);
                    }
                }
            } catch (IOException e) {
                // Handle IOException
                e.printStackTrace();
                shutdown();
            }
        }

        // Method to send a message to the client
        public void sendMessage(String message) {
            out.println(message);
        }

        // Method to clean up resources and close the connection
        public void shutdown() {
            try {
                in.close(); // Close the input stream
                out.close(); // Close the output stream

                if (!client.isClosed()) {
                    client.close(); // Close the client socket
                }
            } catch (IOException e) {
                // Handle IOException
                e.printStackTrace();
            }
        }
    }

    // Main method to start the server
    public static void main(String[] args) {
        ServerNode server = new ServerNode();
        server.run();
    }
}
