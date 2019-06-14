package app.matricula;

import static spark.Spark.*;

import java.util.List;

public class Matricula {
	private int id;
	private String nomeAluno;
	private String cpfAluno;
	//private String emailAluno;
	private List<String> disciplinasMatriculadas;
	
	public Matricula(int id, String nomeAluno, String cpfAluno, String cod) {
		this.id = id;
		this.nomeAluno = nomeAluno;
		this.cpfAluno = cpfAluno;
		//this.emailAluno = emailAluno;		
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNomeAluno() {
		return this.nomeAluno = nomeAluno;
	}
}
