package gerenciamento;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorGerenciamento {

	public static void main(String[] args) {
		try {
			ServerSocket socketGerenciamento = new ServerSocket(2221);

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
