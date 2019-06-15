package app.matricula;

import java.util.List;

public class Matricula {

    private String id;
    private String nomeAluno;
    private String cpfAluno;
    private String emailAluno;
    private List disciplinas;

    public Matricula(String id, String nome, String cpf, String emailAluno) {
        super();
        this.id = id;
        this.nomeAluno = nome;
        this.cpfAluno = cpf;
        this.emailAluno = emailAluno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nomeAluno;
    }

    public void setNome(String nome) {
        this.nomeAluno = nome;
    }

    public String getCPF() {
        return cpfAluno;
    }

    public void setCPF(String cpf) {
        this.cpfAluno = cpf;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    @Override
    public String toString() {
        return new StringBuffer().append(getEmailAluno()).toString();
    }
}