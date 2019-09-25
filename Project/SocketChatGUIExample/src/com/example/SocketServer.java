package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer {
    ServerSocket server;
    Socket sk;
    ArrayList<ServerThread> list = new ArrayList<ServerThread>();

    public SocketServer() {
        try {
            server = new ServerSocket(1234);
            System.out.println("\n Client 접속 대기중");
            while(true) {
                sk = server.accept();
                System.out.println(sk.getInetAddress() + " 접속");

                //접속된 클라이언트를 스레드로 만들어 ArrayList에 추가
                ServerThread st = new ServerThread(this);
                addThread(st);
                st.start();
            }
        } catch(IOException e) {
            System.out.println(e + "-> ServerSocket failed");
        }
    }

    public void addThread(ServerThread st) {
        list.add(st);
    }

    public void removeThread(ServerThread st){
        list.remove(st); //제거
    }

    public void broadCast(String message){
        for(ServerThread st : list){
            st.pw.println(message);
        }
    }

    public static void main(String[] args) {
        new SocketServer();
    }
}

class ServerThread extends Thread {
    SocketServer server;
    PrintWriter pw;
    String name;

    public ServerThread(SocketServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            // 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(server.sk.getInputStream()));

            // 쓰기
            pw = new PrintWriter(server.sk.getOutputStream(), true);
            name = br.readLine();
            server.broadCast("**["+name+"]님이 입장하였습니다**");

            String data;
            while((data = br.readLine()) != null ){
                if(data == "/list"){
                    pw.println("a");
                }
                server.broadCast("["+name+"] "+ data);
            }
        } catch (Exception e) {
            //현재 쓰레드를 ArrayList으로부터 제거한다.
            server.removeThread(this);
            server.broadCast("**["+name+"]님이 퇴장하였습니다**");
            System.out.println(server.sk.getInetAddress()+" - ["+name+"] 퇴장");
            System.out.println(e + "---->");
        }
    }
}
