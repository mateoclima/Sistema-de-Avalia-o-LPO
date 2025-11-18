package br.com.ufpr.sistemaAvaliacao.repository;

import br.com.ufpr.sistemaAvaliacao.models.ProcessoAvaliativoModel;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProcessoAvaliativoDAO extends GenericDAO<ProcessoAvaliativoModel, Long> {

    public ProcessoAvaliativoDAO(EntityManager entityManager) {
        super(entityManager, ProcessoAvaliativoModel.class);
    }

    public List<ProcessoAvaliativoModel> buscarProcessosAtivos() {
        String jpql = "SELECT p FROM ProcessoAvaliativo p WHERE p.ativo = true";
        return super.getEntityManager().createQuery(jpql, ProcessoAvaliativoModel.class).getResultList();
    }
}