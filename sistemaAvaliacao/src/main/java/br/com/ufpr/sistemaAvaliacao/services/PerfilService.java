package br.com.ufpr.sistemaAvaliacao.services;

import br.com.ufpr.sistemaAvaliacao.repository.PerfilDAO;
import br.com.ufpr.sistemaAvaliacao.models.PerfilModel;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PerfilService {

    private final PerfilDAO perfilDAO;
    private final EntityManager entityManager;

    public PerfilService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.perfilDAO = new PerfilDAO(entityManager);
    }

    public PerfilModel cadastrar(PerfilModel perfil) {
        entityManager.getTransaction().begin();
        try {
            PerfilModel salvo = perfilDAO.save(perfil);
            entityManager.getTransaction().commit();
            return salvo;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Falha ao cadastrar o perfil: " + e.getMessage(), e);
        }
    }

    public List<PerfilModel> buscarTodos() {
        // Consultas de leitura (SELECT) geralmente não precisam de transação explícita
        // no JPA, dependendo da sua configuração, mas é bom ter o DAO.
        return perfilDAO.findAll();
    }

    public PerfilModel buscarPorId(Long id) {
        return perfilDAO.findById(id);
    }

    public void garantirPerfisIniciais() {
        String[] nomesIniciais = {"ALUNO", "PROFESSOR", "COORDENADOR", "ADMINISTRADOR"};
        entityManager.getTransaction().begin();
        try {
            for (String nome : nomesIniciais) {
                if (perfilDAO.buscarPorNome(nome) == null) {
                    PerfilModel p = new PerfilModel();
                    p.setNome(nome);
                    perfilDAO.save(p);
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Falha ao inicializar perfis: " + e.getMessage(), e);
        }
    }
}