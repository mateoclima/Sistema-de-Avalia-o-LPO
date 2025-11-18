package br.com.ufpr.sistemaAvaliacao.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alternativa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlternativaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questao_id", nullable = false)
    private QuestaoModel questao;

    @Column(columnDefinition = "TEXT")
    private String texto;
}