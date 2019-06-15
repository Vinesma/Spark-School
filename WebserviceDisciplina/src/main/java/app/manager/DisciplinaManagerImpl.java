package app.manager;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import app.disciplina.Disciplina;
import app.disciplina.DisciplinaException;

public class DisciplinaManagerImpl implements DisciplinaManager {
    private HashMap<String, Disciplina> disciplinaMap;
    private static final AtomicInteger count = new AtomicInteger(0);

    public DisciplinaManagerImpl() {
        disciplinaMap = new HashMap<>();
    }

    @Override
    public void addDisciplina(String nome) {
    	String idAtual = Integer.toString(count.incrementAndGet());
    	    	    	
    	Disciplina disciplina = new Disciplina(idAtual, nome);
        disciplinaMap.put(String.valueOf(idAtual), disciplina);
    }

    @Override
    public Collection<Disciplina> getDisciplinas() {
        return disciplinaMap.values();
    }

    @Override
    public Disciplina getDisciplina(String id) {
        return disciplinaMap.get(id);
    }

    @Override
    public Disciplina editaDisciplina(Disciplina edicao) throws DisciplinaException {
        try {
            if (edicao.getId() == null)
                throw new DisciplinaException("ID nao pode ser vazio");

            Disciplina toEdit = disciplinaMap.get(edicao.getId());

            if (toEdit == null)
                throw new DisciplinaException("Disciplina nao encontrada");

            if (edicao.getNome() != null) {
                toEdit.setNome(edicao.getNome());
            }
            if (edicao.getId() != null) {
                toEdit.setId(edicao.getId());
            }
            return toEdit;
        } catch (Exception ex) {
            throw new DisciplinaException(ex.getMessage());
        }
    }

    @Override
    public void deletaDisciplina(String id) {
        disciplinaMap.remove(id);
    }

    @Override
    public boolean disciplinaExiste(String id) {
        return disciplinaMap.containsKey(id);
    }

}