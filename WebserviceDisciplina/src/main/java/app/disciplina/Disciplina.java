package app.disciplina;

import static spark.Spark.*;

public class Disciplina {
	private int id;
	private String nome;
	
	public Disciplina(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNome() {
		return this.nome = nome;
	}
}
