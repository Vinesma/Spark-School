package app;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import app.aluno.Aluno;
import app.disciplina.Disciplina;
import app.manager.*;
import app.matricula.Matricula;
import app.paginas.Page;

public class Main {
	private static Page newHtml = new Page();
	private static final String REST_URI = "http://localhost:9999/aluno/all";
	private static Client cliente = ClientBuilder.newClient();
	
    public static void main(String[] args) {
        final MatriculaManager MatriculaManager = new MatriculaManagerImpl();        
        staticFiles.location("/public");
        port(9997);
        
        get("/", (req, res) -> newHtml.matriculaPage(req)); //pagina inicial

        post("/matricula/add", (request, response) -> { //add Matricula
        	String nome = request.queryParams("nome");
            String cpf = request.queryParams("cpf");
            String email = request.queryParams("email");
            response.type("application/json");

            Matricula Matricula = new Gson().fromJson(request.body(), Matricula.class);
            MatriculaManager.addMatricula(nome, cpf, email);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/matricula", (request, response) -> { //retorna todas as Matriculas
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(MatriculaManager.getMatriculas())));
        });

        get("/matricula/:id", (request, response) -> { //retorna uma Matricula por id
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(MatriculaManager.getMatricula(request.params(":id")))));
        });

        put("/matricula/:id", (request, response) -> { //edita uma Matricula
            response.type("application/json");

            Matricula toEdit = new Gson().fromJson(request.body(), Matricula.class);
            Matricula editaMatricula = MatriculaManager.editaMatricula(toEdit);

            if (editaMatricula != null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(editaMatricula)));
            } else {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Matricula nao encontrada")));
            }
        });

        delete("/matricula/:id", (request, response) -> { //exclui Matricula
            response.type("application/json");

            MatriculaManager.deletaMatricula(request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Matricula excluida"));
        });

        options("/matricula/:id", (request, response) -> { //Matricula existe?
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, (MatriculaManager.matriculaExiste(request.params(":id"))) ? "Matricula existe" : "Matricula nao existe"));
        });

    }
    
    public static Aluno getAlunoId(String id) {
        return cliente.target(REST_URI)
            	.path(String.valueOf(id))
            	.request(MediaType.APPLICATION_JSON)
            	.get(Aluno.class);
    }
    
    public static Disciplina getDisciplinaId(String id) {
        return cliente.target(REST_URI)
            	.path(String.valueOf(id))
            	.request(MediaType.APPLICATION_JSON)
            	.get(Disciplina.class);
    }
}