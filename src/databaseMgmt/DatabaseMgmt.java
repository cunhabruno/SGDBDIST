package databaseMgmt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.*;

import alunoServer.Aluno;
import alunoServer.AlunosTabela;
import turmaServer.Turma;
import turmaServer.TurmasTabela;

public class DatabaseMgmt {
	private GsonBuilder builder = new GsonBuilder();
	private Gson gson = builder.setPrettyPrinting().create();
	private File studentFile = new File("student.data");
	private File classFile = new File("class.data");
	TurmasTabela turmas = new TurmasTabela();
	AlunosTabela alunos = new AlunosTabela();

	public int adicionaAluno(Aluno novoAluno) throws IOException {

		if (studentFile.exists() && classFile.exists()) {
			String alunosTabela = this.getFileContent(studentFile);
			String turmasTabela = this.getFileContent(classFile);

			TurmasTabela turmasTabelaObj = new TurmasTabela();
			turmasTabelaObj = gson.fromJson(turmasTabela, TurmasTabela.class);
			System.out.println("Tetsing");
			if (!turmasTabelaObj.checkTurmasExistem(novoAluno.getTurmas())) {
				System.out.println("Alguma turma na lista nao existe!");
				return 2;
			}

			AlunosTabela tabelaAux = new AlunosTabela();
			tabelaAux = gson.fromJson(alunosTabela, AlunosTabela.class);
			if (!tabelaAux.checkAlunoExiste(novoAluno.getIdAluno())) {
				FileWriter writer = new FileWriter(studentFile);
				tabelaAux.adicionaAluno(novoAluno);
				writer.write(gson.toJson(tabelaAux));
				writer.close();
				return 0;
			} else {
				System.out.println("Aluno " + novoAluno.getIdAluno() + " ja existe!");
				return 1;
			}

		} else {
			if (classFile.exists()) {
				FileWriter writer = new FileWriter(studentFile);
				this.alunos.adicionaAluno(novoAluno);
				writer.write(gson.toJson(this.alunos));
				writer.close();
				return 0;
			} else {
				System.out.println("Arquivo Turma nao existe!");
				return 2;
			}
		}
	}

	public int adicionaTurma(Turma novaTurma) throws IOException {

		if (classFile.exists()) {
			String turmasTabela = this.getFileContent(classFile);

			TurmasTabela tabelaAux = new TurmasTabela();
			tabelaAux = gson.fromJson(turmasTabela, TurmasTabela.class);
			if (!tabelaAux.checkTurmaExiste(novaTurma.getIdTurma())) {
				FileWriter writer = new FileWriter(classFile);
				tabelaAux.adicionaTurma(novaTurma);
				writer.write(gson.toJson(tabelaAux));
				writer.close();
				return 0;
			} else {
				System.out.println("Turma " + novaTurma.getIdTurma() + " Ja existe");
				return 1;
			}

		} else {
			FileWriter writer = new FileWriter(classFile);
			this.turmas.adicionaTurma(novaTurma);
			writer.write(gson.toJson(this.turmas));
			writer.close();
			return 0;
		}
	}

	public void excluiTurma(int turmaARemover) throws IOException {

		if (classFile.exists()) {
			String turmasTabela = this.getFileContent(classFile);

			TurmasTabela tabelaAux = new TurmasTabela();
			tabelaAux = gson.fromJson(turmasTabela, TurmasTabela.class);
			if (tabelaAux.checkTurmaExiste(turmaARemover)) {
				FileWriter writer = new FileWriter(classFile);
				tabelaAux.excluiTurma(turmaARemover);
				writer.write(gson.toJson(tabelaAux));
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
			String alunosTabela = this.getFileContent(classFile);

			AlunosTabela tabelaAux = new AlunosTabela();
			tabelaAux = gson.fromJson(alunosTabela, AlunosTabela.class);
			if (tabelaAux.checkAlunoExiste(alunoARemover)) {
				FileWriter writer = new FileWriter(studentFile);
				tabelaAux.excluiAluno(alunoARemover);
				writer.write(gson.toJson(tabelaAux));
				writer.close();
			} else {
				System.out.println("Aluno " + alunoARemover + " não existe");
			}

		} else {
			System.out.println("Tabela de Turmas nao encontrada!");
		}
	}

	public String buscaTurma(int idTurma) {

		if (classFile.exists()) {
			String turmasTabela = this.getFileContent(classFile);

			TurmasTabela tabelaAuxTurmas = new TurmasTabela();
			tabelaAuxTurmas = gson.fromJson(turmasTabela, TurmasTabela.class);
			if (tabelaAuxTurmas.checkTurmaExiste(idTurma)) {
				if (studentFile.exists()) {
					String alunosTabela = this.getFileContent(studentFile);

					AlunosTabela tabelaAuxAlunos = new AlunosTabela();
					tabelaAuxAlunos = gson.fromJson(alunosTabela, AlunosTabela.class);

					ArrayList<Aluno> alunosNaTurma = tabelaAuxAlunos.getAlunosInTurma(idTurma);
					Turma turmaEncontrada = tabelaAuxTurmas.getTurmaObj(idTurma);

					TurmaResponse turmaResponse = new TurmaResponse(turmaEncontrada.getIdTurma(),
							turmaEncontrada.getNomeTurma(), alunosNaTurma);

					return turmaResponse.toString();

				} else {
					return tabelaAuxTurmas.getTurma(idTurma);
				}
			} else {
				System.out.println("Turma " + idTurma + " nao existe");
				return new Response(4).toString();
			}

		} else {
			System.out.println("Tabela de Turmas nao encontrada!");
			return "";
		}
	}

	public String buscaAluno(int idAluno) {

		if (studentFile.exists()) {
			String alunosTabela = this.getFileContent(studentFile);

			AlunosTabela tabelaAuxAlunos = new AlunosTabela();
			tabelaAuxAlunos = gson.fromJson(alunosTabela, AlunosTabela.class);
			if (tabelaAuxAlunos.checkAlunoExiste(idAluno)) {
				if (classFile.exists()) {
					String turmasTabela = this.getFileContent(classFile);

					TurmasTabela tabelaAuxTurmas = new TurmasTabela();
					tabelaAuxTurmas = gson.fromJson(turmasTabela, TurmasTabela.class);

					Aluno alunoEncontrado = tabelaAuxAlunos.getAlunoObj(idAluno);
					AlunoResponse alunoResponse = new AlunoResponse(alunoEncontrado.getIdAluno(),
							alunoEncontrado.getNomeAluno(), tabelaAuxTurmas.getTurmasInAluno(alunoEncontrado));
					
					return alunoResponse.toString();
				} else {
					return new Response(3).toString();
				}
			} else {
				System.out.println("Aluno " + idAluno + " não existe");
				return new Response(4).toString();
			}

		} else {
			System.out.println("Tabela de Alunos nao encontrada!");
			return new Response(3).toString();
		}
	}

	public String getAllAlunos() {

		if (studentFile.exists()) {
			String alunosTabela = this.getFileContent(studentFile);

			AlunosTabela tabelaAux = new AlunosTabela();
			tabelaAux = gson.fromJson(alunosTabela, AlunosTabela.class);
			return gson.toJson(tabelaAux);
		} else {
			System.out.println("Tabela de Alunos nao encontrada!");
			return null;
		}
	}

	public String getFileContent(File aFile) {
		StringBuilder contents = new StringBuilder();

		try {
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return contents.toString();
	}

}
