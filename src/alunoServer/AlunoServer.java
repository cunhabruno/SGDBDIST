package alunoServer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

import databaseMgmt.ConfigFile;
import databaseMgmt.Request;
import gerenciamento.ServidorGerenciamento;

public class AlunoServer implements Runnable{

	public static void main(String[] args) {
		Thread t = new Thread(new AlunoServer());
		t.start();
	}

	@Override
	public void run() {
		ConfigFile configFile = new ConfigFile("src/alunoServer/alunos.config");
		try {
			ServerSocket server = new ServerSocket(configFile.getPort());
			
			Request request = new Request();
			while (true) {
				System.out.println("Aluno server running on port " + server.getLocalPort());
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
