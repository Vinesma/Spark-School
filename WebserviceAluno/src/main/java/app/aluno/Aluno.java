package app.aluno;

import static spark.Spark.*;

public class Aluno {
	private int id;
	private String nome;
	private String cpf;
	private String email;
	
	public Aluno(int id, String nome, String cpf, String email) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNome() {
		return this.nome = nome;
	}
	
	public String getCPF() {
		return this.cpf = cpf;
	}
	
	public String getEmail() {
		return this.email = email;
	}
}
