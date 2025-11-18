package br.com.ufpr.sistemaAvaliacao.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senhaHash;

    @Enumerated(EnumType.STRING)
    private RoleModel role;
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }

    public RoleModel getRole() { return role; }
    public void setRole(RoleModel role) { this.role = role; }
}