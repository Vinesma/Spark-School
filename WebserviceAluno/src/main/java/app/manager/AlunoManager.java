package app.manager;

import static spark.Spark.*;
import app.paginas.*;
import app.aluno.*;

import spark.Response;
import spark.Request;
import spark.Route;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AlunoManager {
	public static Map<String, Aluno> alunos = new HashMap<String, Aluno>();
    private static final AtomicInteger count = new AtomicInteger(0);
    private static Page newHtml = new Page();
    
    public Aluno buscaId(String id) {
    	return (Aluno) alunos.get(id);
    }
    
    public Aluno adicionar(String nome, String cpf, String email) {
    	int idAtual = count.incrementAndGet();
    	
    	Aluno aluno = new Aluno(idAtual, nome, cpf, email);
    	alunos.put(String.valueOf(idAtual),aluno);
    	return aluno;
    }
    
    public void deletar(String id) {
		alunos.remove(id);	
	}
    
    public List encontraTodos() {
    	ArrayList<String> values = new ArrayList<String>();
    	
    	if (!alunos.isEmpty()) {
    		values.add(newHtml.header());
    		values.add(newHtml.bodyTag());
			for (String chave : alunos.keySet()) {
				String nome = alunos.get(chave).getNome();
				String cpf = alunos.get(chave).getCPF();
				String email = alunos.get(chave).getEmail();

				values.add(newHtml.alunoItem(nome, cpf, email));
			}
			values.add(newHtml.bodyEndTag());
			values.add(newHtml.headerEnd());
		}
		return values;
    }
    
    public AlunoManager() {
    }	
}
