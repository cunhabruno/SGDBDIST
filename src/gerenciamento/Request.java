package gerenciamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Request {
	private Socket dbSocket;

	public String handleRequest(String requestString) {
		
		this.setDatabaseSocket(requestString);
		
		try {
			PrintWriter outAlunos = new PrintWriter(dbSocket.getOutputStream(), true);
			Scanner inAlunos = new Scanner(dbSocket.getInputStream());
			outAlunos.println(requestString);
			outAlunos.flush();

			String inAlunosStr = "";
			while (inAlunos.hasNext()) {
				inAlunosStr += inAlunos.nextLine() + '\n';
			}

			outAlunos.close();
			inAlunos.close();
			dbSocket.close();

			return inAlunosStr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public void setDatabaseSocket (String requestString) {
		if (requestString.startsWith("/incluiAluno") || requestString.startsWith("/apagaAluno")
				|| requestString.startsWith("/aluno")) {
			try {
				dbSocket = new Socket("localhost", 2222);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (requestString.startsWith("/incluiTurma") || requestString.startsWith("/apagaTurma")
				|| requestString.startsWith("/turma")) {
			try {
				dbSocket = new Socket("localhost", 2223);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			dbSocket = null;
		}
	}
}
