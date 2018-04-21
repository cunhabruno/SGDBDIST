package turmaServer;
import java.util.ArrayList;
import java.util.Iterator;

import alunoServer.Aluno;

public class TurmasTabela {
	private ArrayList<Turma> turmas = new ArrayList<Turma>();
	
	public ArrayList<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<Turma> turmas) {
		this.turmas = turmas;
	}

	public void adicionaTurma (Turma turma) {
		turmas.add(turma);
	}
	
	public boolean checkTurmaExiste(int idTurma) {
		for(Turma turma : turmas) {
			if(turma.getIdTurma() == idTurma) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkTurmasExistem(ArrayList<Integer> turmasList) {
		boolean existe = false;
		for (Integer idTurma : turmasList) {
			existe = false;
			for(Turma turma : turmas) {
				if(turma.getIdTurma() == idTurma) {
					existe = true;
				}
			}
			if(!existe) {
				return false;
			}
		}

		return !turmas.isEmpty();
	}
	
	public boolean excluiTurma(int idTurma) {
		Iterator<Turma> ite = turmas.iterator();
		while(ite.hasNext()) {
			if(ite.next().getIdTurma() == idTurma) {
				ite.remove();
				return true;
			}
		}
		return false;
	}
	
	public String getTurma(int idTurma) {
		Iterator<Turma> ite = turmas.iterator();
		Turma turma;
		while(ite.hasNext()) {
			turma = ite.next();
			if(turma.getIdTurma() == idTurma) {
				return turma.toString();
			}
		}
		return "";
	}
	
	public Turma getTurmaObj(int idTurma) {
		Iterator<Turma> ite = turmas.iterator();
		Turma turma;
		while(ite.hasNext()) {
			turma = ite.next();
			if(turma.getIdTurma() == idTurma) {
				return turma;
			}
		}
		return null;
	}
	
	public ArrayList<Turma> getTurmasInAluno(Aluno aluno) {
		ArrayList<Turma> turmaArr = new ArrayList<Turma>();
		for (Integer turma : aluno.getTurmas()) {
			turmaArr.add(this.getTurmaObj(turma));
		}
		return turmaArr;
	}
}
