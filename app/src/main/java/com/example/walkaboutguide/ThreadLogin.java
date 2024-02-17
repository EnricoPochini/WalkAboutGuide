package com.example.walkaboutguide;

import java.io.*;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadLogin extends Thread{

    private int portaLogin = 149;
    private String ipServer = "192.168.1.10";

    private String input_password;

    private String nominativo;

    public ThreadLogin(String input_password){
        this.input_password = input_password;
        nominativo = "Errore";
    }

    public String getNominativo(){
        return nominativo;
    }

    @Override
    public void run(){
        login(input_password);
    }
    public String login(String input_password){

        nominativo = "Errore";

        try{

            Socket socket = new Socket(ipServer,portaLogin);

            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println(input_password);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            nominativo = in.readLine();

        } catch (IOException ignored) {

        }

        return nominativo;

    }

}
