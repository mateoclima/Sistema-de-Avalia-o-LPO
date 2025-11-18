package br.com.ufpr.sistemaAvaliacao.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "questao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formulario_id", nullable = false)
    private FormularioModel formulario;

    @Column(columnDefinition = "TEXT")
    private String texto;

    private boolean obrigatoria;

    private int ordem;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_questao")
    private TipoQuestaoEnum tipoQuestao;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlternativaModel> alternativas;
}