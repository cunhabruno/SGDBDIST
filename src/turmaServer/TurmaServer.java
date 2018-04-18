package turmaServer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

import databaseMgmt.Request;

public class TurmaServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(2223);
			
			Request request = new Request();
			while (true) {
				Socket clientSocketGerenciamento = server.accept();
				PrintWriter out = new PrintWriter(clientSocketGerenciamento.getOutputStream());
				Scanner in = new Scanner(new InputStreamReader(clientSocketGerenciamento.getInputStream()));
				System.out.println("Conexao requisitada! ");
				
				String requestStr = in.nextLine();
				System.out.println("Gerenciador requisitou: " + requestStr);
				
				out.println(request.handleRequest(requestStr));
				out.flush();
				
				in.close();
				out.close();
				clientSocketGerenciamento.close();
			}

		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
