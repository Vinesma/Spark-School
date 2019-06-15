package app;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;

import app.aluno.Aluno;
import app.manager.*;
import app.paginas.Page;

public class Main {
	private static Page newHtml = new Page();
	
    public static void main(String[] args) {
        final AlunoManager alunoManager = new AlunoManagerImpl();
        staticFiles.location("/public");
        port(9999);
        
        get("/", (req, res) -> newHtml.alunoPage(req)); //pagina inicial

        post("/aluno/add", (request, response) -> { //add aluno
        	String nome = request.queryParams("nome");
            String cpf = request.queryParams("cpf");
            String email = request.queryParams("email");
            response.type("application/json");

            Aluno aluno = new Gson().fromJson(request.body(), Aluno.class);
            alunoManager.addAluno(nome, cpf, email);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/aluno", (request, response) -> { //retorna todos os alunos
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(alunoManager.getAlunos())));
        });

        get("/aluno/:id", (request, response) -> { //retorna um aluno por id
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(alunoManager.getAluno(request.params(":id")))));
        });

        put("/aluno/:id", (request, response) -> { //edita um aluno
            response.type("application/json");

            Aluno toEdit = new Gson().fromJson(request.body(), Aluno.class);
            Aluno editaAluno = alunoManager.editaAluno(toEdit);

            if (editaAluno != null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(editaAluno)));
            } else {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Aluno nao encontrado")));
            }
        });

        delete("/aluno/:id", (request, response) -> { //exclui aluno
            response.type("application/json");

            alunoManager.deletaAluno(request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Aluno excluido"));
        });

        options("/aluno/:id", (request, response) -> { //aluno existe?
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, (alunoManager.alunoExiste(request.params(":id"))) ? "Aluno existe" : "Aluno nao existe"));
        });

    }

}