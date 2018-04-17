package alunoServer;
import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.*;

public class Aluno implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAluno;
	private String nomeAluno;
	private ArrayList<Integer> turmas;
	
	public ArrayList<Integer> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<Integer> turmas) {
		this.turmas = turmas;
	}

	public Aluno(int idAluno, String nomeAluno) {
		super();
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
		turmas = new ArrayList<Integer>();
	}
	
	public Aluno() {
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

	public void adicionaTurma(String turmasArr[]) {
		for (String turma : turmasArr) {
			turmas.add(Integer.parseInt(turma));
		}
	}
	
	@Override
	public String toString() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(this);
	}
}
