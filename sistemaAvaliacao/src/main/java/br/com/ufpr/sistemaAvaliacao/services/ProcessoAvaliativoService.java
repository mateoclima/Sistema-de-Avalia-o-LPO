package br.com.ufpr.sistemaAvaliacao.services;

import br.com.ufpr.sistemaAvaliacao.repository.ProcessoAvaliativoDAO;
import br.com.ufpr.sistemaAvaliacao.models.ProcessoAvaliativoModel;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessoAvaliativoService {

    private final ProcessoAvaliativoDAO processoAvaliativoDAO;
    private final EntityManager entityManager;

    public ProcessoAvaliativoService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.processoAvaliativoDAO = new ProcessoAvaliativoDAO(entityManager);
    }

    public ProcessoAvaliativoModel cadastrar(ProcessoAvaliativoModel processo) {
        entityManager.getTransaction().begin();
        try {
            ProcessoAvaliativoModel salvo = processoAvaliativoDAO.save(processo);
            entityManager.getTransaction().commit();
            return salvo;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Falha ao cadastrar processo avaliativo: " + e.getMessage(), e);
        }
    }


    public List<ProcessoAvaliativoModel> buscarTodosAtivos() {
        List<ProcessoAvaliativoModel> todos = processoAvaliativoDAO.findAll();

        return todos.stream()
                .filter(ProcessoAvaliativoModel::getAtivo)
                .collect(Collectors.toList());
    }

    public ProcessoAvaliativoModel buscarPorId(Long id) {
        return processoAvaliativoDAO.findById(id);
    }
}