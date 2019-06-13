package app.paginas;

import spark.Request;

public class Page {
	
	public String header() {
		return "<!DOCTYPE html>\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
				"    <link rel=\"stylesheet\" type=\"text/css\" href=\"http://localhost:9999/style.css\">\r\n" + 
				"    <title>Aluno</title>\r\n" + 
				"</head>";
	}
	
	public String headerEnd(){
		return "</html>";
	}
	
	public String bodyTag(){
		return "<body>";
	}
	
	public String bodyEndTag(){
		return "</body>";
	}
	
	public String alunoPage(Request req) {
		return 	header() + bodyTag() +
				"    <div class=\"bg\">\r\n" + 
				"        <div class=\"container\">\r\n" + 
				"            <h2><span class=\"bottom_border\">Cadastro de Aluno:</span></h2>\r\n" + 
				"            <form action=\"/aluno/add\" method=\"post\">\r\n" + 
				"                <h4>Nome:</h4>\r\n" + 
				"                <input type=\"text\" name=\"nome\">\r\n" + 
				"                <h4>CPF:</h4>\r\n" + 
				"                <input type=\"text\" name=\"cpf\">\r\n" + 
				"                <h4>Email:</h4>\r\n" + 
				"                <input type=\"text\" name=\"email\"><br>\r\n" + 
				"                <input type=\"submit\" value=\"Enviar\">\r\n" + 
				"            </form>\r\n" + 
				"        </div>\r\n" + 
				"        <div class=\"container\">\r\n" + 
				"            <a href=\"/aluno\"><h2>Verificar todos os alunos</h2></a>\r\n" + 
				"        </div>\r\n" + 
				"    </div>\r\n" + 
				bodyEndTag() + headerEnd();
	}
	
	public String alunoItem(String nome, String cpf, String email){
		return 	"	<div class=\"container\">\r\n" + 
				"        <h2><span class=\"bottom_border\">Aluno:</span></h2>\r\n" + 
				"        <div>\r\n" + 
				"            <h4>Nome: "+ nome +"</h4>\r\n" + 
				"            <h4>CPF: "+ cpf +"</h4>\r\n" + 
				"            <h4>Email: "+ email +"</h4>\r\n" + 
				"        </div>\r\n" + 
				"    </div>";
	}
	
	public String returnButton() {
		return "<br><a href=\"/\"><button autofocus type=\"button\" >Retornar</button></a>";
	}
}
