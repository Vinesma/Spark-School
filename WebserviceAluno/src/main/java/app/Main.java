package app;

import spark.*;

import spark.Service.StaticFiles;
import app.manager.AlunoManager;

import static spark.Spark.*;

import java.util.List;

import app.aluno.*;
import app.paginas.*;

public class Main {
	
	private static AlunoManager alunoManager = new AlunoManager();
	private static Page newHtml = new Page();
	
    public static void main(String[] args) {
    	
    	exception(Exception.class, (e, req, res) -> e.printStackTrace());
    	staticFiles.location("/public");
    	port(9999);
    	
    	get("/", (req, res) -> newHtml.alunoPage(req)); //pagina inicial
    	
    	get("/aluno", (req, res) -> { //retorna todos os alunos
            List resultado = alunoManager.encontraTodos();
            if (resultado.isEmpty()) {
                return "Nenhum aluno encontrado!" + newHtml.returnButton();
            } else {
            	String temp = "";
            	for (int i = 0; i < resultado.size(); i++) {
            		temp += resultado.get(i).toString() + "<br>";
				}
                return temp + newHtml.returnButton();
            }
        });
    	
    	post("/aluno/add", (req, res) -> { //adiciona um aluno     	
            String name = req.queryParams("nome");
            String cpf = req.queryParams("cpf");
            String email = req.queryParams("email");
    		
            Aluno aluno = alunoManager.adicionar(name, cpf, email);
            res.status(201); // 201 Created
            return "Aluno " + aluno.getNome() + " adicionado! Id = " + aluno.getId() + newHtml.returnButton();
        });
    	
    	delete("/aluno/:id", (req, res) -> { //apaga um aluno
            String id = req.params(":id");
            Aluno aluno = alunoManager.buscaId(id);
            if (aluno != null) {
                alunoManager.deletar(id);
                return "Aluno com id " + id + " foi deletado!" + newHtml.returnButton();
            } else {
                res.status(404);
                return "Aluno nao encontrado!" + newHtml.returnButton();
            }
        });
    }
}