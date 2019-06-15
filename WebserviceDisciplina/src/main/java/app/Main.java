package app;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;

import app.disciplina.Disciplina;
import app.manager.*;
import app.paginas.Page;

public class Main {
	private static Page newHtml = new Page();
	
    public static void main(String[] args) {
        final DisciplinaManager disciplinaManager = new DisciplinaManagerImpl();
        staticFiles.location("/public");
        port(9998);
        
        get("/", (req, res) -> newHtml.disciplinaPage(req)); //pagina inicial

        post("/disciplina/add", (request, response) -> { //add disciplina
        	String nome = request.queryParams("nome");
            response.type("application/json");

            Disciplina disciplina = new Gson().fromJson(request.body(), Disciplina.class);
            disciplinaManager.addDisciplina(nome);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/disciplina", (request, response) -> { //retorna todas os disciplinas
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(disciplinaManager.getDisciplinas())));
        });

        get("/disciplina/:id", (request, response) -> { //retorna uma disciplina por id
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(disciplinaManager.getDisciplina(request.params(":id")))));
        });

        put("/disciplina/:id", (request, response) -> { //edita uma disciplina
            response.type("application/json");

            Disciplina toEdit = new Gson().fromJson(request.body(), Disciplina.class);
            Disciplina editadisciplina = disciplinaManager.editaDisciplina(toEdit);

            if (editadisciplina != null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(editadisciplina)));
            } else {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("disciplina nao encontrada")));
            }
        });

        delete("/disciplina/:id", (request, response) -> { //exclui disciplina
            response.type("application/json");

            disciplinaManager.deletaDisciplina(request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "disciplina excluido"));
        });

        options("/disciplina/:id", (request, response) -> { //disciplina existe?
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, (disciplinaManager.disciplinaExiste(request.params(":id"))) ? "disciplina existe" : "disciplina nao existe"));
        });

    }

}