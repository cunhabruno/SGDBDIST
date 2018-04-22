package gerenciamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import alunoServer.Aluno;
import alunoServer.AlunosTabela;
import databaseMgmt.AlunoResponse;
import databaseMgmt.ConfigFile;
import databaseMgmt.TurmaResponse;
import turmaServer.Turma;
import turmaServer.TurmasTabela;

public class Request {
	private Socket dbSocket;
	private GsonBuilder builder = new GsonBuilder();
	private Gson gson = builder.setPrettyPrinting().create();
	ConfigFile configFile = new ConfigFile("src/gerenciamento/gerenciamento.config");
	
	public String handleRequest(String requestString) {

		if (requestString.equals("/alunos")) {
			String alunosTabela = this.makeRequestToDB(requestString);

			String turmasTabela = this.makeRequestToDB("/turmas");

			AlunosTabela tabelaAuxAlunos = new AlunosTabela();
			tabelaAuxAlunos = gson.fromJson(alunosTabela, AlunosTabela.class);

			TurmasTabela tabelaAuxTurmas = new TurmasTabela();
			tabelaAuxTurmas = gson.fromJson(turmasTabela, TurmasTabela.class);

			ArrayList<AlunoResponse> alunoResponse = new ArrayList<AlunoResponse>();

			for (Aluno alunoEncontrado : tabelaAuxAlunos.getAlunos()) {
				alunoResponse.add(new AlunoResponse(alunoEncontrado.getIdAluno(), alunoEncontrado.getNomeAluno(),
						tabelaAuxTurmas.getTurmasInAluno(alunoEncontrado)));
			}
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("alunos", gson.toJsonTree(alunoResponse));
			return gson.toJson(jsonObject);
		} else if (requestString.equals("/turmas")) {

			String turmasTabela = this.makeRequestToDB(requestString);

			String alunosTabela = this.makeRequestToDB("/alunos");

			TurmasTabela tabelaAuxTurmas = new TurmasTabela();
			tabelaAuxTurmas = gson.fromJson(turmasTabela, TurmasTabela.class);

			AlunosTabela tabelaAuxAlunos = new AlunosTabela();
			tabelaAuxAlunos = gson.fromJson(alunosTabela, AlunosTabela.class);

			System.out.println(tabelaAuxAlunos.getAlunos());

			ArrayList<TurmaResponse> turmaResponse = new ArrayList<TurmaResponse>();

			for (Turma turmaEncontrada : tabelaAuxTurmas.getTurmas()) {
				AlunosTabela alunosTabelaNoTurma = new AlunosTabela();
				alunosTabelaNoTurma.setAlunos(tabelaAuxAlunos.getAlunos());
				ArrayList<Aluno> alunosInTurma = new ArrayList<Aluno>();
				alunosInTurma.addAll(alunosTabelaNoTurma.getAlunosInTurma(turmaEncontrada.getIdTurma()));
				turmaResponse.add(
						new TurmaResponse(turmaEncontrada.getIdTurma(), turmaEncontrada.getNomeTurma(), alunosInTurma));
			}
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("turmas", gson.toJsonTree(turmaResponse));
			return gson.toJson(jsonObject);
		} else if (requestString.matches("\\/turma\\/[0-9]+$")) {
			String turmaEncontrada = this.makeRequestToDB(requestString);
			if (turmaEncontrada.contains("codRetorno")) {
				return turmaEncontrada;
			} else {
				String alunosTabela = this.makeRequestToDB("/alunos");

				TurmaResponse turmaEncontradaObj = new TurmaResponse();

				AlunosTabela alunosTabelaObj = new AlunosTabela();

				turmaEncontradaObj = gson.fromJson(turmaEncontrada, TurmaResponse.class);
				alunosTabelaObj = gson.fromJson(alunosTabela, AlunosTabela.class);

				ArrayList<Aluno> alunosNaTurma = alunosTabelaObj.getAlunosInTurma(turmaEncontradaObj.getIdTurma());

				turmaEncontradaObj.setAlunos(alunosNaTurma);

				return turmaEncontradaObj.toString();
			}
		} else if (requestString.matches("\\/aluno\\/[0-9]+$")) {
			String alunoEncontrado = this.makeRequestToDB(requestString);
			if (alunoEncontrado.contains("codRetorno")) {
				return alunoEncontrado;
			} else {
				String turmasTabela = this.makeRequestToDB("/turmas");

				Aluno alunoEncontradoObj = new Aluno();

				TurmasTabela turmasTabelaObj = new TurmasTabela();

				alunoEncontradoObj = gson.fromJson(alunoEncontrado, Aluno.class);
				turmasTabelaObj = gson.fromJson(turmasTabela, TurmasTabela.class);

				ArrayList<Turma> turmasNoAluno = turmasTabelaObj.getTurmasInAluno(alunoEncontradoObj);

				AlunoResponse alunoResponse = new AlunoResponse(alunoEncontradoObj.getIdAluno(),
						alunoEncontradoObj.getNomeAluno(), turmasNoAluno);

				return alunoResponse.toString();
			}

		} else {
			return this.makeRequestToDB(requestString);
		}
	}

	public void setDatabaseSocket(String requestString) {
		if (requestString.startsWith("/incluiAluno") || requestString.startsWith("/apagaAluno")
				|| requestString.startsWith("/aluno")) {
			try {
				dbSocket = new Socket(this.configFile.getStudentServerHost(), this.configFile.getStudentServerPort());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (requestString.startsWith("/incluiTurma") || requestString.startsWith("/apagaTurma")
				|| requestString.startsWith("/turma")) {
			try {
				dbSocket = new Socket(this.configFile.getClassServerHost(), this.configFile.getClassServerPort());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				dbSocket = new Socket(this.configFile.getClassServerHost(), this.configFile.getClassServerPort());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String makeRequestToDB(String requestString) {
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
}
