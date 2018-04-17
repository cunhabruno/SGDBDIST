package databaseMgmt;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import turmaServer.Turma;

public class AlunoResponse {
	private int idAluno;
	private String nomeAluno;
	ArrayList<Turma> turmas;
	public AlunoResponse(int idAluno, String nomeAluno, ArrayList<Turma> turmas) {
		super();
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
		this.turmas = turmas;
	}
	
	@Override
	public String toString() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		return gson.toJson(this);
	}
	
}
