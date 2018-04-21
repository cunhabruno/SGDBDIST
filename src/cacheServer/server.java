package cacheServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class server implements Runnable{
	public static void main(String[] args) {
		Thread t = new Thread(new server());
		t.start();
	}
	@Override
	public void run() {
		try {
			ServerSocket server = new ServerSocket(2000);
			while (true) {
				Socket clientSocket = server.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
				Scanner in = new Scanner(new InputStreamReader(clientSocket.getInputStream()));
				System.out.println("Conexao requisitada! ");
				
				String requestStr = in.nextLine();
				System.out.println("Cliente requisitou: " + requestStr);
				
				CacheManagement cacheMgmt = new CacheManagement();
				out.println(cacheMgmt.handleRequest(requestStr));
				out.flush();
				
				in.close();
				out.close();
				clientSocket.close();
			}

		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
