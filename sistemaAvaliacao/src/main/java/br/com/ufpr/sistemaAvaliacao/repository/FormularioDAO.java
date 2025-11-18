package br.com.ufpr.sistemaAvaliacao.repository;

import br.com.ufpr.sistemaAvaliacao.models.FormularioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class FormularioDAO extends GenericDAO<FormularioModel, Long> {

    public FormularioDAO(EntityManager entityManager) {
        super(entityManager, FormularioModel.class);
    }

    public List<FormularioModel> buscarFormulariosParaPerfil(Long processoId, Long perfilId) {
        String jpql = "SELECT f FROM Formulario f " +
                "JOIN f.perfisDestino p " +
                "WHERE f.processoAvaliativo.id = :processoId AND p.id = :perfilId";

        TypedQuery<FormularioModel> query = super.getEntityManager().createQuery(jpql, FormularioModel.class);
        query.setParameter("processoId", processoId);
        query.setParameter("perfilId", perfilId);

        return query.getResultList();
    }

    public List<FormularioModel> buscarAnonimos() {
        String jpql = "SELECT f FROM Formulario f WHERE f.isAnonimo = true";
        TypedQuery<FormularioModel> query = super.getEntityManager().createQuery(jpql, FormularioModel.class);
        return query.getResultList();
    }
}