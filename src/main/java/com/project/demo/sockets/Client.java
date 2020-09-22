//package com.project.demo.sockets;
//
//import java.io.*;
//import java.net.Socket;
//
//public class Client {
//
//    public void connectToServer() throws IOException {
//        Socket client = new Socket("127.0.0.1", 8080);
//
//        OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
//        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
//        String request = "cat\n";
//        writer.write(request);
//        writer.flush();
//
//        String response = reader.readLine();
//        System.out.println(response);
//
//        writer.close();
//        reader.close();
//        client.close();
//    }
//}
