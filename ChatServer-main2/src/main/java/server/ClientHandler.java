package server;




import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ClientHandler extends Thread {


    private final Socket clientSocket;
    String name;

    static Socket socket;
    static boolean once = false;
    static boolean firstOne = true;
    static ArrayList<User> users = new ArrayList<User>();
    List<ClientHandler> clientList = ChatServer.getAr();
    static ArrayList<String> onlineUsers= new ArrayList<String>();
    private OutputStream outputStream;
    private InputStream inputStream;


    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;

        usersAdd();
        //onlineUsers();


    }


    private void usersAdd() throws IOException {
        if (once == false) {

            User u1 = new User("Nicholas", false, socket);
            User u2 = new User("Ole", false, socket);
            User u3 = new User("Tim", false, socket);
            User u4 = new User("Daniel", false, socket);
            users.add(u1);
            users.add(u2);
            users.add(u3);
            users.add(u4);
            once = true;


        }

    }


    @Override
    public void run() {

        try {
            ClientHandler(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void ClientHandler(Socket clientSocket) throws IOException, InterruptedException {

        this.outputStream = clientSocket.getOutputStream();
        this.inputStream = clientSocket.getInputStream();
        ///send output to client
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        /// get input from client
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


        ///test server msg
        outputStream.write("Welcome,type username or send close to exit\n".getBytes());


        String line = "";


        while ((line = reader.readLine()) != null) {


            inputChecker(line, clientSocket);
            onlineUsers(line);



            if ("close".equals(line)) {
                printWriter.println("connection closed");
                clientSocket.close();

                //THREAD CLOSE ?
                break;
            }


        }
        /// send name of all client currently online

    }


    public void inputChecker(String line, Socket socket) throws IOException { // void istedet for string


        String inputSplit = line.split("#")[0];
        String output = "";

        if (inputSplit.equals("CONNECT")) {
            inputSplit = line.split("#")[1];

            for (User u : users) {
                if (inputSplit.equals(u.getName()) && !u.getOnline()) {

                    output = inputSplit + " is online";
                    for (ClientHandler client : clientList) {
                        onlineUsers.add(u.getName());
                        send(output);
                        break;
                    }
                    u.setOnline(true);
                    u.setSocket(socket);
                    break;

                } else if (inputSplit.equals(u.getName()) && u.getOnline() == true) {
                    output = "You are logged in from another client... Closing connection...";
                    send(output);

                    break;

                } else {
                    output = "Invalid Username... Closing connection.\n";
                    //socket.close();


                }
            }

        }
        if (inputSplit.equals("SEND")) {                    //[0]SEND#[1]Peter,Hans#[2]Hello Peter and Hans   //
            inputSplit = line.split("#")[2];
            String message = inputSplit;
            line = line.split("#")[1];
            //String[] arrayofstring = line.split(",");
            StringBuilder tempMessage = new StringBuilder();
            tempMessage.append("MESSAGE#");

            for (User u : users) {
                if (line.equals(u.getName())) {
                    sendMessage(message, u.getName());
//                        if (line.split(",")[i].equals(u.getName()) && u.getOnline() == false) {
//                        output = "This user is not online.";
//                        send(output);
//                        break;
//                    } else if (line.split(",")[i].equals(u.getName()) && u.getOnline() == true && firstOne) {          //message#Ole#Hej tim
//                        tempMessage.append(u.getName());
//                        firstOne = false;
//                    } else if (line.split(",")[i].equals(u.getName()) && u.getOnline() == true && !firstOne) {          //message#Ole#Hej tim
//                        tempMessage.append(",");
//                        tempMessage.append(u.getName());


//                    }
//                    tempMessage.append("#" + message);
//                    output = tempMessage.toString();

                    //sendMessage(output, String!!!!!)   //line.split(",")[i]

                }



                }
                //for (int i = 0; i < line.split(",").length-1; i++))

            }
        }



    private void send(String msg) throws IOException {
        outputStream.write(msg.getBytes());
    }

    /*public void userMessage(String line) throws IOException {


        String inputSplit;
        inputSplit = line.split("#")[0];
        String output = " ";

        for(ClientHandler client: clientList){
            if (inputSplit.equals("SEND") ) {
                inputSplit = line.split("#")[1];
                String outMsg = " " + line;
                client.send(outMsg);
            }
        }








    }*/
    private void onlineUsers(String line) throws IOException {
        String inputSplit;
        inputSplit = line.split("#")[0];
        String output = "e";

        if (inputSplit.equals("ONLINE")) {

            for (ClientHandler client : clientList) {
                for (int i = 0; i < onlineUsers.size(); i++) {
                    send(onlineUsers +"is online");

                }
             break;
            }
        }

    }

    void sendMessage(String message, String userName) throws IOException {
        for (User u : users)
            if(userName.equals(u.getName())){
                outputStream = u.getSocket().getOutputStream();
                outputStream.write(message.getBytes());
                outputStream.close();
            }



    }


}




//  metode (String message, sender, recipient)
// for (u : users)
//    for (clienthandler client : clientlist)
//        if client.getsocket == u.getsocket
//            send
//
//  metode("HEJ TABER OLE", Nico , OLE)



//  REGEX   HEJ#MED#DIG#OLE,Tim,NICO#GÅ
//  split("#")[0] = HEJ
//  split("#")[1] = MED
//  split("#")[2] = OLE,TIM,NICO.split(",") = [0]OLE [1]TIM [2]NICO
















