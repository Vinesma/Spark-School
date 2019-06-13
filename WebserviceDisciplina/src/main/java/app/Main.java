package app;

import spark.*;

import spark.Service.StaticFiles;
import app.disciplina.*;
import app.manager.DisciplinaManager;

import static spark.Spark.*;

import java.util.List;

import app.paginas.*;

public class Main {
	
	private static DisciplinaManager disciplinaManager = new DisciplinaManager();
	private static Page newHtml = new Page();
	
    public static void main(String[] args) {
    	
    	exception(Exception.class, (e, req, res) -> e.printStackTrace());
    	staticFiles.location("/public");
    	port(9998);
    	
    	get("/", (req, res) -> newHtml.disciplinaPage(req)); //pagina inicial
    	
    	get("/disciplina", (req, res) -> { //retorna todos as disciplinas
            List resultado = disciplinaManager.encontraTodos();
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
