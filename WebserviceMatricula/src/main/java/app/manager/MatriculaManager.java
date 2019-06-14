package app.manager;

import static spark.Spark.*;

import app.matricula.*;
import app.paginas.*;

import spark.Response;
import spark.Request;
import spark.Route;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MatriculaManager {
	public static Map<String, Matricula> matriculas = new HashMap<String, Matricula>();
    private static final AtomicInteger count = new AtomicInteger(0);
    private static Page newHtml = new Page();
    
    public Matricula buscaId(String id) {
    	return (Matricula) matriculas.get(id);
    }
    
    public Matricula adicionar(String nome, String cpf, String cod) {
    	int idAtual = count.incrementAndGet();
    	
    	Matricula disciplina = new Matricula(idAtual, nome, cpf, cod);
    	matriculas.put(String.valueOf(idAtual),disciplina);
    	return disciplina;
    }
    
    public void deletar(String id) {
		matriculas.remove(id);	
	}   
    
    public List encontraTodos() {
    	ArrayList<String> values = new ArrayList<String>();
    	
    	if (!matriculas.isEmpty()) {
    		values.add(newHtml.header());
    		values.add(newHtml.bodyTag());
			for (String chave : matriculas.keySet()) {
				values.add("<div class=\"container\">\r\n<h4>");
				values.add(matriculas.get(chave).getNomeAluno());
				values.add("</h4></div>");
			}
			values.add(newHtml.bodyEndTag());
			values.add(newHtml.headerEnd());
		}
		return values;
    }
    
    public MatriculaManager() {
    }
}
