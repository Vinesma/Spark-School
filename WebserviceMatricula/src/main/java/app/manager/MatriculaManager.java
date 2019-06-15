package app.manager;
import java.util.Collection;

import app.matricula.*;

public interface MatriculaManager {
    public void addMatricula(String nome, String cpf, String email);

    public Collection<Matricula> getMatriculas();

    public Matricula getMatricula(String id);

    public Matricula editaMatricula(Matricula Matricula) throws MatriculaException;

    public void deletaMatricula(String id);

    public boolean matriculaExiste(String id);
}