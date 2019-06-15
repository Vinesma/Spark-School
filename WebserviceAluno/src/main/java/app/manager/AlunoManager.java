package app.manager;
import java.util.Collection;

import app.aluno.*;

public interface AlunoManager {
    public void addAluno(String nome, String cpf, String email);

    public Collection<Aluno> getAlunos();

    public Aluno getAluno(String id);

    public Aluno editaAluno(Aluno aluno) throws AlunoException;

    public void deletaAluno(String id);

    public boolean alunoExiste(String id);
}