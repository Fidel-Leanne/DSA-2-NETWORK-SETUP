Assignment Documentation: Modeling a Star Topology Network

1. Overview:

0utline of the implementation of a star topology network using socket programming. 
The assignment required modeling a star topology network with a central node (server) and peripheral nodes (clients), where the central node brokers messages between the peripheral nodes.
the implementation serves as a chatroom, enabling communication between connected clients.

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
A client can be removed by typing /quit 

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

5 why we would choose huffman encoding


Efficient Compression: 
It generates variable-length codes that minimize redundancy in the data, leading to optimal compression ratios.

Fast Processing: 
Huffman encoding and decoding are simple and efficient processes, making it suitable for real-time compression and decompression tasks .

Lossless Compression:
Huffman encoding preserves all original data during compression and decompression, ensuring data integrity in our chat rom.

5.1

The overall time complexity of the Huffman coding algorithm can be summarized as:

Building the Huffman Tree: O(n log n)
Generating Huffman Codes: O(n)
Compressing a Message: O(m)
Decompressing a Message: O(m)
Printing Huffman Codes: O(n)

Where:
n is the number of unique characters in the input message.
m is the length of the input message.

The dominant factor in the overall time complexity is building the Huffman tree, which has a time complexity of O(n log n).
The other operations, such as generating Huffman codes, compressing and decompressing messages, and printing Huffman codes, have linear time complexities .
Therefore, the overall time complexity of the Huffman coding algorithm is O(n log n) for building the tree, and linear for other operations.


6.How to run the code 

First run the serverNode in one terminal.
Then open another terminal to run the ClientNode.
Then open another terminal and run the ClientNode
