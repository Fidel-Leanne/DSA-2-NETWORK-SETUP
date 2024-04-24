import java.util.ArrayList;
import java.util.List;

public class Star {
    private ServerNode serverNode;
    private List<ClientNode> clientNodes;

    public Star() {
        clientNodes = new ArrayList<>();
        serverNode = new ServerNode();
    }

    // Method to insert a client node into the network
    public void insertNode(String id) {
        ClientNode clientNode = new ClientNode(id);
        clientNode.setServerNode(serverNode);
        clientNodes.add(clientNode);
        serverNode.addClient(clientNode);
        System.out.println("Node " + id + " inserted into the network.");
    }

    // Method to delete a client node from the network
    public void deleteNode(String id) {
        ClientNode nodeToRemove = null;
        for (ClientNode clientNode : clientNodes) {
            if (clientNode.getId().equals(id)) {
                nodeToRemove = clientNode;
                break;
            }
        }
        if (nodeToRemove != null) {
            clientNodes.remove(nodeToRemove);
            System.out.println("Node " + id + " deleted from the network.");
        } else {
            System.out.println("Node " + id + " not found in the network.");
        }
    }

    // Method to get a specific client node by ID
    public ClientNode getClientNode(String id) {
        for (ClientNode clientNode : clientNodes) {
            if (clientNode.getId().equals(id)) {
                return clientNode;
            }
        }
        return null;
    }
}