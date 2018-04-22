package cacheServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import databaseMgmt.ConfigFile;


public class CacheServer implements Runnable{
	public static void main(String[] args) {
		Thread t = new Thread(new CacheServer());
		t.start();
	}
	@Override
	public void run() {
		ConfigFile configFile = new ConfigFile("src/cacheServer/cache.config");
		try {
			ServerSocket server = new ServerSocket(configFile.getPort());
			System.out.println("Cache server running on port " + server.getLocalPort());
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
