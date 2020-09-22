//package com.project.demo.sockets;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class Server {
//
//    public void listenClients() throws IOException {
//        ServerSocket serverSocket = new ServerSocket(8080);
//
//        while(true){
//            Socket client = serverSocket.accept();
//
//            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
//            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
//            String request = reader.readLine();
//            String response = request.toUpperCase();
//            writer.write(response);
//            writer.flush();
//
//            writer.close();
//            reader.close();
//            client.close();
//
//        }
//    }
//}
