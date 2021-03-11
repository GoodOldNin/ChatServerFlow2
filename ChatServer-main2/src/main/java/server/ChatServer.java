package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChatServer {





    // Vector to store active clients
    static Vector<ClientHandler> ar = new Vector<>();
    /// Vector to store users in a string
    ArrayList<String> onlineUsers = new ArrayList<String>();
    // counter for clients
    static int i = 0;

    public static Vector<ClientHandler> getAr() {
        return ar;
    }

    @Override
    public String toString() {
        return "ChatServer{" +
                "onlineUsers=" + onlineUsers +
                '}';
    }

    public void process() throws Exception {

        String ip = "localhost";
        int port = 8088;
        String logFile = "log.txt";  //Do we need this

        //System.out.println(users);


        try {




            ///initialize serversocket
            ServerSocket serversocket = new ServerSocket(port);
            System.out.println("server started: "+port);

            /// use while loop to continuously create connection to client
            while (true) {

                ///create connection to client
                System.out.println("client connection in progress");
                Socket clientSocket = serversocket.accept(); ///blocking call
                System.out.println("new client connected");


                /// add incoming Clients
                System.out.println("Accepted connection from" + clientSocket);


                OutputStream outputStream = clientSocket.getOutputStream();

                String name = "";
                ClientHandler clientHandler = new ClientHandler(clientSocket);


                Thread t = new Thread(clientHandler);
                ar.add(clientHandler);
                System.out.println(onlineUsers);
                t.start();



                //SendMessage send = new SendMessage(ar);
                //send.run();

                i++;




            }


        } catch (IOException e) {
            e.printStackTrace();

        }


    }


    //Call server with arguments like this: 0.0.0.0 8088 logfile.log
    public static void main(String[] args) throws Exception {
        ChatServer chatServer = new ChatServer();
        chatServer.process();
    }




    }
