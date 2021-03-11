package server;

import java.net.Socket;

public class User {


    private String name;
    private Boolean online;
    private Socket socket;

    public User(String name, Boolean online, Socket socket) {
        this.name = name;
        this.online = online;
        this.socket = socket;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", online=" + online +
                ", socket='" + socket + '\'' +
                '}';
    }
}
