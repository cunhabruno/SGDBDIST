import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

public class DatabaseMgmt {
	private GsonBuilder builder = new GsonBuilder();
	private Gson gson = builder.setPrettyPrinting().create();
	private File studentFile = new File("student.data");
	private File classFile = new File("class.data");
	
	public void adicionaAluno(AlunosTabela alunos, Aluno novoAluno) throws IOException {

		if (studentFile.exists()) {
			String alunosTabela = "";
			FileReader fr = new FileReader(studentFile);
			char[] a = new char[(int) studentFile.length()];
			fr.read(a); // reads the content to the array
			for (char c : a) {
				alunosTabela += c;	
			}
			
			AlunosTabela tabelaAux = new AlunosTabela();
			tabelaAux = gson.fromJson(alunosTabela, AlunosTabela.class);
			if(!tabelaAux.checkAlunoExiste(novoAluno.getIdAluno())) {
				FileWriter writer = new FileWriter(studentFile);
				tabelaAux.adicionaAluno(novoAluno);
				writer.write(gson.toJson(tabelaAux));
				fr.close();
				writer.close();
			} else {
				System.out.println("Aluno " + novoAluno.getIdAluno() + " ja existe!");
			}

		} else {
			FileWriter writer = new FileWriter(studentFile);
			alunos.adicionaAluno(novoAluno);
			writer.write(gson.toJson(alunos));
			writer.close();	
		}
	}
	
	public void adicionaTurma(TurmasTabela turmas, Turma novaTurma) throws IOException {

		if (classFile.exists()) {
			String turmasTabela = "";
			FileReader fr = new FileReader(classFile);
			char[] a = new char[(int) classFile.length()];
			fr.read(a); // reads the content to the array
			for (char c : a) {
				turmasTabela += c;	
			}
			
			TurmasTabela tabelaAux = new TurmasTabela();
			tabelaAux = gson.fromJson(turmasTabela, TurmasTabela.class);
			if(!tabelaAux.checkTurmaExiste(novaTurma.getIdTurma())) {
				FileWriter writer = new FileWriter(classFile);
				tabelaAux.adicionaTurma(novaTurma);
				writer.write(gson.toJson(tabelaAux));
				fr.close();
				writer.close();
			} else {
				System.out.println("Turma " + novaTurma.getIdTurma() + " Já existe");
			}
			
		} else {
			FileWriter writer = new FileWriter(classFile);
			turmas.adicionaTurma(novaTurma);
			writer.write(gson.toJson(turmas));
			writer.close();	
		}
	}
	
	public void excluiTurma(int turmaARemover) throws IOException {

		if (classFile.exists()) {
			String turmasTabela = "";
			FileReader fr = new FileReader(classFile);
			char[] a = new char[(int) classFile.length()];
			fr.read(a); // reads the content to the array
			for (char c : a) {
				turmasTabela += c;	
			}
			
			TurmasTabela tabelaAux = new TurmasTabela();
			tabelaAux = gson.fromJson(turmasTabela, TurmasTabela.class);
			if(tabelaAux.checkTurmaExiste(turmaARemover)) {
				FileWriter writer = new FileWriter(classFile);
				tabelaAux.excluiTurma(turmaARemover);
				writer.write(gson.toJson(tabelaAux));
				fr.close();
				writer.close();
			} else {
				System.out.println("Turma " + turmaARemover + " não existe");
			}
			
		} else {
			System.out.println("Tabela de Turmas nao encontrada!");
		}
	}
	
	public void excluiAlunos(int alunoARemover) throws IOException {

		if (studentFile.exists()) {
			String alunosTabela = "";
			FileReader fr = new FileReader(studentFile);
			char[] a = new char[(int) studentFile.length()];
			fr.read(a); // reads the content to the array
			for (char c : a) {
				alunosTabela += c;	
			}
			
			AlunosTabela tabelaAux = new AlunosTabela();
			tabelaAux = gson.fromJson(alunosTabela, AlunosTabela.class);
			if(tabelaAux.checkAlunoExiste(alunoARemover)) {
				FileWriter writer = new FileWriter(studentFile);
				tabelaAux.excluiAluno(alunoARemover);
				writer.write(gson.toJson(tabelaAux));
				fr.close();
				writer.close();
			} else {
				System.out.println("Aluno " + alunoARemover + " não existe");
			}
			
		} else {
			System.out.println("Tabela de Turmas nao encontrada!");
		}
	}
	
	public String getAllAlunos() throws IOException {

		if (studentFile.exists()) {
			String alunosTabela = "";
			FileReader fr = new FileReader(studentFile);
			char[] a = new char[(int) studentFile.length()];
			fr.read(a); // reads the content to the array
			for (char c : a) {
				alunosTabela += c;	
			}
			
			AlunosTabela tabelaAux = new AlunosTabela();
			tabelaAux = gson.fromJson(alunosTabela, AlunosTabela.class);
			fr.close();
			return gson.toJson(tabelaAux);
		} else {
			System.out.println("Tabela de Alunos nao encontrada!");
			return null;
		}
	}
	
}
