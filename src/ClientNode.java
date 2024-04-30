import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientNode implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;

    @Override
    public void run() {
        try {
            // Connect to the server
            client = new Socket("127.0.0.1", 9999);
            // Initialize input and output streams
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // Start a new thread to handle user input
            Thread inputThread = new Thread(new InputHandler());
            inputThread.start();

            String inMessage;
            // Listen for messages from the server
            while ((inMessage = in.readLine()) != null) {
                System.out.println(inMessage); // Print messages received from the server
            }
        } catch (IOException e) {
            shutdown(); // Shutdown the client in case of an exception
        }
    }

    // Method to shutdown the client
    public void shutdown() {
        done = true;
        try {
            // Close input, output streams, and the client socket
            if (in != null) in.close();
            if (out != null) out.close();
            if (client != null && !client.isClosed()) client.close();
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException
        }
    }

    // Inner class to handle user input
    class InputHandler implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String message = inReader.readLine(); // Read user input
                    if (message.equals("/quit")) 
                    {
                        out.println(message);
                        inReader.close();
                        shutdown(); // Shutdown the client if the user inputs '/quit'
                       
                    } else {
                        out.println(message); // Send the user input to the server
                    }
                }
                inReader.close(); // Close the input reader
            } catch (IOException e) {
                shutdown(); // Shutdown the client in case of an exception
            }
        }
    }

    // Main method to start the client
    public static void main(String[] args) {
        ClientNode clientNode = new ClientNode();
        Thread clientThread = new Thread(clientNode);
        clientThread.start(); // Start the client thread
    }
}
