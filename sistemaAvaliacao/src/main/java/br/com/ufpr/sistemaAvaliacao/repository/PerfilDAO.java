package br.com.ufpr.sistemaAvaliacao.repository;

import br.com.ufpr.sistemaAvaliacao.models.PerfilModel;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PerfilDAO extends GenericDAO<PerfilModel, Long> {

    public PerfilDAO(EntityManager entityManager) {
        super(entityManager, PerfilModel.class);
    }

    public PerfilModel buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Perfil p WHERE p.nome = :nome";

        try {
            return super.getEntityManager().createQuery(jpql, PerfilModel.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}