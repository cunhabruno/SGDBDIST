package gerenciamento;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import cacheServer.CacheServer;
import databaseMgmt.ConfigFile;

public class ServidorGerenciamento implements Runnable{

	public static void main(String[] args) {
		Thread t = new Thread(new ServidorGerenciamento());
		t.start();
	}

	@Override
	public void run() {
		ConfigFile configFile = new ConfigFile("src/gerenciamento/gerenciamento.config");
		try {
			ServerSocket socketGerenciamento = new ServerSocket(configFile.getPort());
			System.out.println("Gerenciamento server running on port " + socketGerenciamento.getLocalPort());
			while (true) {
				Socket clientSocket = socketGerenciamento.accept();
				
				Request requet = new Request();
				
				PrintWriter outCliente = new PrintWriter (clientSocket.getOutputStream());
				Scanner inCliente = new Scanner(new InputStreamReader(clientSocket.getInputStream()));
				
				String inClient = inCliente.nextLine();
				System.out.println("Cliente requisitou: " + inClient);
				
				String inDataBaseStr = requet.handleRequest(inClient);
				outCliente.println(inDataBaseStr);
				outCliente.flush();
				
				inCliente.close();
				outCliente.close();
				clientSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
