package app.manager;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import app.matricula.Matricula;
import app.matricula.MatriculaException;

public class MatriculaManagerImpl implements MatriculaManager {
    private HashMap<String, Matricula> matriculaMap;
    private static final AtomicInteger count = new AtomicInteger(0);

    public MatriculaManagerImpl() {
        matriculaMap = new HashMap<>();
    }

    @Override
    public void addMatricula(String nome, String cpf, String email) {
    	String idAtual = Integer.toString(count.incrementAndGet());
    	    	    	
    	Matricula matricula = new Matricula(idAtual, nome, cpf, email);
        matriculaMap.put(String.valueOf(idAtual), matricula);
    }

    @Override
    public Collection<Matricula> getMatriculas() {
        return matriculaMap.values();
    }

    @Override
    public Matricula getMatricula(String id) {
        return matriculaMap.get(id);
    }

    @Override
    public Matricula editaMatricula(Matricula edicao) throws MatriculaException {
        try {
            if (edicao.getId() == null)
                throw new MatriculaException("ID nao pode ser vazio");

            Matricula toEdit = matriculaMap.get(edicao.getId());

            if (toEdit == null)
                throw new MatriculaException("matricula nao encontrado");

            if (edicao.getEmailAluno() != null) {
                toEdit.setEmailAluno(edicao.getEmailAluno());
            }
            if (edicao.getNome() != null) {
                toEdit.setNome(edicao.getNome());
            }
            if (edicao.getCPF() != null) {
                toEdit.setCPF(edicao.getCPF());
            }
            if (edicao.getId() != null) {
                toEdit.setId(edicao.getId());
            }

            return toEdit;
        } catch (Exception ex) {
            throw new MatriculaException(ex.getMessage());
        }
    }

    @Override
    public void deletaMatricula(String id) {
        matriculaMap.remove(id);
    }

    @Override
    public boolean matriculaExiste(String id) {
        return matriculaMap.containsKey(id);
    }

}