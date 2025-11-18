package br.com.ufpr.sistemaAvaliacao.services;

import br.com.ufpr.sistemaAvaliacao.repository.FormularioDAO;
import br.com.ufpr.sistemaAvaliacao.repository.PerfilDAO;
import br.com.ufpr.sistemaAvaliacao.repository.ProcessoAvaliativoDAO;
import br.com.ufpr.sistemaAvaliacao.models.FormularioModel;
import br.com.ufpr.sistemaAvaliacao.models.PerfilModel;
import br.com.ufpr.sistemaAvaliacao.models.ProcessoAvaliativoModel;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class FormularioService {

    private final FormularioDAO formularioDAO;
    private final ProcessoAvaliativoDAO processoAvaliativoDAO;
    private final PerfilDAO perfilDAO;
    private final EntityManager entityManager;

    public FormularioService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.formularioDAO = new FormularioDAO(entityManager);
        this.processoAvaliativoDAO = new ProcessoAvaliativoDAO(entityManager);
        this.perfilDAO = new PerfilDAO(entityManager);
    }

    public FormularioModel criarFormularioCompleto(
            FormularioModel formulario,
            List<Long> perfisDestinoIds,
            Long processoAvaliativoId
    ) {

        entityManager.getTransaction().begin();
        FormularioModel salvo;

        try {
            ProcessoAvaliativoModel processo = processoAvaliativoDAO.findById(processoAvaliativoId);
            if (processo == null) {
                throw new RuntimeException("Processo Avaliativo com ID " + processoAvaliativoId + " não encontrado.");
            }
            formulario.setProcessoAvaliativo(processo);

            List<PerfilModel> perfis = perfisDestinoIds.stream()
                    .map(id -> perfilDAO.findById(id))
                    .filter(p -> p != null)
                    .collect(Collectors.toList());

            formulario.setPerfisDestino(perfis);

            salvo = formularioDAO.save(formulario);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Falha ao criar o formulário: " + e.getMessage(), e);
        }

        return salvo;
    }

    public List<FormularioModel> listarTodos() {
        return formularioDAO.findAll();
    }
}