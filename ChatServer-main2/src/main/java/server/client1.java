/*package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class client1 {


    Socket socket;
    PrintWriter pw;
    Scanner scanner;

    public void connect(String address,int port) throws IOException{
        socket = new Socket(address,port);
        pw = new PrintWriter(socket.getOutputStream(),true);
        scanner = new Scanner(socket.getInputStream());
        System.out.println(scanner.nextLine());

        Scanner keyboard = new Scanner(System.in);
        boolean Running = true;

        while(Running){
            String message = keyboard.nextLine();

            if(message.equals("close")){
                Running = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        client1 c = new client1();
        c.connect("localhost",8088);
    }
}
*/