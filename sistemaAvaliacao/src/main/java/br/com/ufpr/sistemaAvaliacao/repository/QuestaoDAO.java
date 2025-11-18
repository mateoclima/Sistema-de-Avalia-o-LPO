package br.com.ufpr.sistemaAvaliacao.repository;

import br.com.ufpr.sistemaAvaliacao.models.QuestaoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class QuestaoDAO extends GenericDAO<QuestaoModel, Long> {

    public QuestaoDAO(EntityManager entityManager) {
        super(entityManager, QuestaoModel.class);
    }

    public List<QuestaoModel> buscarPorFormularioOrdenado(Long formularioId) {
        String jpql = "SELECT q FROM Questao q " +
                "WHERE q.formulario.id = :formularioId " +
                "ORDER BY q.ordem ASC";

        TypedQuery<QuestaoModel> query = super.getEntityManager().createQuery(jpql, QuestaoModel.class);
        query.setParameter("formularioId", formularioId);

        return query.getResultList();
    }

    public List<QuestaoModel> buscarAbertasPorFormulario(Long formularioId) {
        String jpql = "SELECT q FROM Questao q " +
                "WHERE q.formulario.id = :formularioId AND q.tipoQuestao = 'ABERTA'";

        TypedQuery<QuestaoModel> query = super.getEntityManager().createQuery(jpql, QuestaoModel.class);
        query.setParameter("formularioId", formularioId);

        return query.getResultList();
    }
}