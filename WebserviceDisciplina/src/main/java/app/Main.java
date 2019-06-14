package app;

import spark.*;

import spark.Service.StaticFiles;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.disciplina.*;
import app.manager.DisciplinaManager;

import static spark.Spark.*;

import java.util.List;

import app.paginas.*;

public class Main {
	
	private static DisciplinaManager disciplinaManager = new DisciplinaManager();
	private static ObjectMapper om = new ObjectMapper();
	private static Page newHtml = new Page();
	
    public static void main(String[] args) {
    	
    	exception(Exception.class, (e, req, res) -> e.printStackTrace());
    	staticFiles.location("/public");
    	port(9998);
    	
    	get("/", (req, res) -> newHtml.disciplinaPage(req)); //pagina inicial
    	
    	get("/disciplina/all", (req, res) -> { //retorna todas as disciplinas em JSON
    		List resultado = disciplinaManager.encontraTodos();
    		if(resultado.isEmpty()) {
    			return "Nenhuma disciplina encontrada!" + newHtml.returnButton();
    		}else{
    			return om.writeValueAsString(disciplinaManager.encontraTodos());
    		}
        });
    	
    	get("/disciplina", (req, res) -> { //retorna todas as disciplinas em HTML
            List resultado = disciplinaManager.encontraTodosHTML();
            if (resultado.isEmpty()) {
                return "Nenhuma disciplina encontrada!" + newHtml.returnButton();
            } else {
            	String temp = "";
            	for (int i = 0; i < resultado.size(); i++) {
            		temp += resultado.get(i).toString() + "<br>";
				}
                return temp + newHtml.returnButton();
            }
        });
    	
    	get("/disciplina/:id", (req, res) -> { //encontra uma disciplina por ID
    		Disciplina disciplina = disciplinaManager.encontraId(req.params(":id"));
            if (disciplina != null) {
                return om.writeValueAsString(disciplina);
            } else {
                res.status(404);
                return "Disciplina nao encontrada" + newHtml.returnButton();
            }
    	});
    	
    	post("/disciplina/add", (req, res) -> { //adiciona uma disciplina 	
            String name = req.queryParams("nome");
    		
            Disciplina disciplina = disciplinaManager.adicionar(name);
            res.status(201); // 201 Created
            return "Disciplina " + disciplina.getNome() + " adicionada! Id = " + disciplina.getId() + newHtml.returnButton();
        });
    	
    	delete("/disciplina/:id", (req, res) -> { //apaga uma disciplina
            String id = req.params(":id");
            Disciplina disciplina = disciplinaManager.buscaId(id);
            if (disciplina != null) {
                disciplinaManager.deletar(id);
                return "Disciplina com id " + id + " foi deletada!" + newHtml.returnButton();
            } else {
                res.status(404);
                return "Disciplina nao encontrada!" + newHtml.returnButton();
            }
        });
    }
}
