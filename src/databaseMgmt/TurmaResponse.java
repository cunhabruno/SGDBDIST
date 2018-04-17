package databaseMgmt;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import alunoServer.Aluno;

public class TurmaResponse {
	private int idTurma;
	private String nomeTurma;
	private ArrayList<Aluno> alunos;
	
	public TurmaResponse(int idTurma, String nomeTurma, ArrayList<Aluno> alunos) {
		super();
		this.idTurma = idTurma;
		this.nomeTurma = nomeTurma;
		this.alunos = alunos;
	}
	public int getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	public String getNomeTurma() {
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public void adicionaAluno(Aluno aluno) {
		Aluno alunoAux = new Aluno();
		alunoAux.setIdAluno(aluno.getIdAluno());
		alunoAux.setNomeAluno(aluno.getNomeAluno());
		alunos.add(alunoAux);
	}
	
	@Override
	public String toString() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
