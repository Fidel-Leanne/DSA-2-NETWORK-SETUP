Assignment Documentation: Modeling a Star Topology Network

1. Overview:

This document outlines the implementation of a star topology network using socket programming. 
The assignment required modeling a star topology network with a central node (server) and peripheral nodes (clients), where the central node brokers messages between the peripheral nodes.
Additionally, the implementation serves as a chatroom, enabling communication between connected clients.

2. Implementation:

2.1. ServerNode Class:

The ServerNode class represents the central node (server) in the star topology network.
It establishes connections with multiple peripheral nodes (clients) using sockets.
The central functionality of the ServerNode class is to broker messages sent by client nodes to all other connected clients.
It achieves this by broadcasting messages received from one client to all other connected clients.

2.1.1. ConnectionHandler Inner Class:

The ConnectionHandler inner class of the ServerNode class manages individual connections from peripheral nodes (clients) to the central node (server).
Each ConnectionHandler instance handles the communication with a specific client, including sending and receiving messages.
To handle multiple client connections concurrently, each ConnectionHandler instance is executed in a separate thread. 
This allows the server to handle multiple clients simultaneously without blocking.

2.2. ClientNode Class:

The ClientNode class represents the peripheral nodes (clients) in the star topology network.
Each ClientNode object establishes a socket connection with the central node (server) to communicate.
It has methods to send messages to the server and receive messages from the server. 
When sending a message, it communicates with the server, which in turn broadcasts the message to all other connected clients.

2.3. Chatroom Functionality:

In addition to the star topology network model, the implementation functions as a chatroom.
Clients connected to the central node (server) can send messages to each other, enabling real-time communication within the network.
The server facilitates this chatroom functionality by relaying messages between connected clients, allowing for group communication similar to a traditional chatroom environment.

3. Conceptual Alignment:

The provided implementation aligns with the principles of a star topology network as follows:

All communication passes through the central node (server), emulating the point-to-point connections in a star topology.
Peripheral nodes (clients) do not directly communicate with each other; instead, they relay messages through the central node (server).
The central node (server) acts as a hub, receiving messages from one client and broadcasting them to all other connected clients, mimicking the behavior of a central switch or hub in a star topology network.


4. Conclusion:

In conclusion, the implementation effectively models a star topology network using socket programming concepts while also functioning as a chatroom. The central node (server) and peripheral nodes (clients) interact to simulate the communication patterns observed in a star topology network, while the chatroom functionality enables real-time communication between connected clients.
