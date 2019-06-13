package app.matricula;

import static spark.Spark.*;

import java.util.List;

public class Matricula {
	private int id;
	private String nomeAluno;
	private String cpfAluno;
	private String emailAluno;
	private List<String> disciplinasMatriculadas;
	
	public Matricula(int id, String nomeAluno) {
		this.id = id;
		this.nomeAluno = nomeAluno;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNomeAluno() {
		return this.nomeAluno = nomeAluno;
	}
}
