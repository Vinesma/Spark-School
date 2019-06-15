package app.manager;
import java.util.Collection;

import app.disciplina.*;

public interface DisciplinaManager {
    public void addDisciplina(String nome);

    public Collection<Disciplina> getDisciplinas();

    public Disciplina getDisciplina(String id);

    public Disciplina editaDisciplina(Disciplina disciplina) throws DisciplinaException;

    public void deletaDisciplina(String id);

    public boolean disciplinaExiste(String id);
}