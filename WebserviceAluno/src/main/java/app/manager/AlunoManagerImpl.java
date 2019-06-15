package app.manager;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import app.aluno.Aluno;
import app.aluno.AlunoException;

public class AlunoManagerImpl implements AlunoManager {
    private HashMap<String, Aluno> alunoMap;
    private static final AtomicInteger count = new AtomicInteger(0);

    public AlunoManagerImpl() {
        alunoMap = new HashMap<>();
    }

    @Override
    public void addAluno(String nome, String cpf, String email) {
    	String idAtual = Integer.toString(count.incrementAndGet());
    	    	    	
    	Aluno aluno = new Aluno(idAtual, nome, cpf, email);
        alunoMap.put(String.valueOf(idAtual), aluno);
    }

    @Override
    public Collection<Aluno> getAlunos() {
        return alunoMap.values();
    }

    @Override
    public Aluno getAluno(String id) {
        return alunoMap.get(id);
    }

    @Override
    public Aluno editaAluno(Aluno edicao) throws AlunoException {
        try {
            if (edicao.getId() == null)
                throw new AlunoException("ID nao pode ser vazio");

            Aluno toEdit = alunoMap.get(edicao.getId());

            if (toEdit == null)
                throw new AlunoException("Aluno nao encontrado");

            if (edicao.getEmail() != null) {
                toEdit.setEmail(edicao.getEmail());
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
            throw new AlunoException(ex.getMessage());
        }
    }

    @Override
    public void deletaAluno(String id) {
        alunoMap.remove(id);
    }

    @Override
    public boolean alunoExiste(String id) {
        return alunoMap.containsKey(id);
    }

}