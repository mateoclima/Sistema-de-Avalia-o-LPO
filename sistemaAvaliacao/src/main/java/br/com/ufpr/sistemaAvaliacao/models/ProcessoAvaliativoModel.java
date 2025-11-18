package br.com.ufpr.sistemaAvaliacao.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "processo_avaliativo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoAvaliativoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "processoAvaliativo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormularioModel> formularios;
}