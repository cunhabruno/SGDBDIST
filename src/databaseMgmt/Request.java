package databaseMgmt;

import java.io.IOException;

import turmaServer.Turma;

public class Request {
	DatabaseMgmt data = new DatabaseMgmt();
	public String handleRequest(String requestStr) throws IOException {
		if(requestStr.equals("/alunos")) {
			return data.getAllAlunos();
		}else if(requestStr.startsWith("/incluiTurma")) {
			int idTurma = Integer.parseInt(requestStr.split("/")[2]);
			String nometurma = requestStr.split("/")[3];
			int response = data.adicionaTurma(new Turma(idTurma, nometurma));
			return new Response(response).toString();
		}
		return "";
	}
}
