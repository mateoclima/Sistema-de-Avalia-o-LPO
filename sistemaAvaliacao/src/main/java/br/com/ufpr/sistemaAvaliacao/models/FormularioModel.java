package br.com.ufpr.sistemaAvaliacao.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "formulario")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FormularioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "is_anonimo")
    private boolean anonimo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_avaliativo_id")
    private ProcessoAvaliativoModel processoAvaliativo;

    @ManyToMany
    @JoinTable(
            name = "formulario_perfil",
            joinColumns = @JoinColumn(name = "formulario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<PerfilModel> perfisDestino;

    @OneToMany(mappedBy = "formulario", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordem ASC")
    private List<QuestaoModel> questoes;
}