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
	public AlunoResponse() {
		super();
	}
	
	public int getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public ArrayList<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(ArrayList<Turma> turmas) {
		this.turmas = turmas;
	}
	@Override
	public String toString() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		return gson.toJson(this);
	}
	
}
