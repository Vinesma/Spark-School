package app;

import spark.*;

import spark.Service.StaticFiles;
import app.manager.MatriculaManager;
import app.matricula.*;

import static spark.Spark.*;

import java.util.List;

import app.paginas.*;

public class Main {
	
	private static MatriculaManager matriculaManager = new MatriculaManager();
	private static Page newHtml = new Page();
	
    public static void main(String[] args) {
    	
    	exception(Exception.class, (e, req, res) -> e.printStackTrace());
    	staticFiles.location("/public");
    	port(9997);
    	
    	get("/", (req, res) -> newHtml.matriculaPage(req)); //pagina inicial
    	
    	get("/matricula", (req, res) -> { //retorna todos as matriculas
            List resultado = matriculaManager.encontraTodos();
            if (resultado.isEmpty()) {
                return "Nenhuma matricula encontrada!" + newHtml.returnButton();
            } else {
            	String temp = "";
            	for (int i = 0; i < resultado.size(); i++) {
            		temp += resultado.get(i).toString() + "<br>";
				}
                return temp + newHtml.returnButton();
            }
        });
    	
    	post("/matricula/add", (req, res) -> { //adiciona uma matricula 	
            String name = req.queryParams("nome");
            String cpf = req.queryParams("cpf");
            String cod = req.queryParams("cod");
    		
            Matricula matricula = matriculaManager.adicionar(name, cpf, cod);
            res.status(201); // 201 Created
            return "matricula " + matricula.getNomeAluno() + " adicionada! Id = " + matricula.getId() + newHtml.returnButton();
        });
    	
    	delete("/matricula/:id", (req, res) -> { //apaga uma matricula
            String id = req.params(":id");
            Matricula matricula = matriculaManager.buscaId(id);
            if (matricula != null) {
                matriculaManager.deletar(id);
                return "matricula com id " + id + " foi deletada!" + newHtml.returnButton();
            } else {
                res.status(404);
                return "matricula nao encontrada!" + newHtml.returnButton();
            }
        });
    }
}
