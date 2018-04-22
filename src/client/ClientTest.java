package client;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import gerenciamento.ServidorGerenciamento;

public class ClientTest implements Runnable {

	private String request;
	public static void main(String[] args) {
		Thread t1 = new Thread(new ClientTest("/alunos"));
		t1.start();
		Thread t2 = new Thread(new ClientTest("/turmas"));
		t2.start();
		Thread t3 = new Thread(new ClientTest("/turma/5"));
		t3.start();
	}
	
	ClientTest(String request) {
		this.request = request;
	}

	@Override
	public void run() {
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", 1234);

			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			Scanner in = new Scanner(clientSocket.getInputStream());

			out.println(this.request);
			out.flush();
			while(in.hasNext()) {
				System.out.println(in.nextLine());	
			}
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException  e) {
			e.printStackTrace();
		}
	}

}
