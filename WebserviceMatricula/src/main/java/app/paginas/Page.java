package app.paginas;

import spark.Request;

public class Page {
	
	public String header() {
		return "<!DOCTYPE html>\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
				"    <link rel=\"stylesheet\" type=\"text/css\" href=\"http://localhost:9997/style.css\">\r\n" + 
				"    <title>Matriculas</title>\r\n" + 
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
	
	public String matriculaPage(Request req) {
		return 	header() + bodyTag() +
				"<div class=\"bg\">\r\n" + 
				"        <div class=\"container\">\r\n" + 
				"            <h2>Criação de Matriculas:</h2>\r\n" + 
				"            <form action=\"/matricula/add\" method=\"post\">\r\n" + 
				"                <h4>Nome do aluno:</h4>\r\n" + 
				"                <input type=\"text\" name=\"nome\">\r\n" + 
				"                <h4>CPF do aluno:</h4>\r\n" + 
				"                <input type=\"text\" name=\"cpf\">\r\n" + 
				"                <h4>Código das disciplinas:</h4>\r\n" + 
				"                <input type=\"text\" name=\"cod\"><br>\r\n" + 
				"                <input type=\"submit\" value=\"Enviar\">\r\n" + 
				"            </form>\r\n" + 
				"        </div>\r\n" + 
				"        <div class=\"container\">\r\n" + 
				"            <a href=\"/matricula\"><h2>Verificar todas as matriculas</h2></a>\r\n" + 
				"        </div>        \r\n" + 
				"    </div>" + 
				bodyEndTag() + headerEnd();
	}
	
	public String returnButton() {
		return "<br><a href=\"/\"><button autofocus type=\"button\" >Retornar</button></a>";
	}
}
