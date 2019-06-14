package app.manager;

import static spark.Spark.*;
import app.paginas.*;

import spark.Response;
import spark.Request;
import spark.Route;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import app.aluno.Aluno;
import app.disciplina.*;

public class DisciplinaManager {
	public static Map<String, Disciplina> disciplinas = new HashMap<String, Disciplina>();
    private static final AtomicInteger count = new AtomicInteger(0);
    private static Page newHtml = new Page();
    
    public Disciplina buscaId(String id) {
    	return (Disciplina) disciplinas.get(id);
    }
    
    public Disciplina adicionar(String nome) {
    	int idAtual = count.incrementAndGet();
    	
    	Disciplina disciplina = new Disciplina(idAtual, nome);
    	disciplinas.put(String.valueOf(idAtual),disciplina);
    	return disciplina;
    }
    
    public void deletar(String id) {
		disciplinas.remove(id);	
	}
    
    public List encontraTodos() {
    	return new ArrayList<>(disciplinas.values());
    }
    
    public List encontraTodosHTML() {
    	ArrayList<String> values = new ArrayList<String>();
    	
    	if (!disciplinas.isEmpty()) {
    		values.add(newHtml.header());
    		values.add(newHtml.bodyTag());
			for (String chave : disciplinas.keySet()) {
				values.add("<div class=\"container\">\r\n<h4>");
				values.add(disciplinas.get(chave).getNome());
				values.add("</h4></div>");
			}
			values.add(newHtml.bodyEndTag());
			values.add(newHtml.headerEnd());
		}
		return values;
    }
    
    public Disciplina encontraId(String id) {
		return disciplinas.get(id);
	}
    
    public DisciplinaManager() {
    }
}
