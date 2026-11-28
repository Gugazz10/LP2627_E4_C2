
package pt.upt.projetolp.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoInventario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String categoria;

    private String descricao;

    @Column(nullable = false)
    private String estado = "DISPONIVEL";
    

    @JsonIgnore
    @ManyToMany(mappedBy = "equipamentos")
    private List<Reserva> reservas = new ArrayList<>();



    public Equipamento() {
    }


    public Equipamento(String codigoInventario,
                       String nome,
                       String categoria,
                       String descricao) {

        if (codigoInventario == null ||
            codigoInventario.isBlank() ||
            nome == null || nome.isBlank() ||
            categoria == null || categoria.isBlank()) {

            throw new IllegalArgumentException(
                "Codigo, nome e categoria sao obrigatorios.");
        }

        this.codigoInventario = codigoInventario;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.estado = "DISPONIVEL";
    }


    public Long getId() {
        return id;
    }
    

    public List<Reserva> getReservas() {
    	return reservas;
    }

    public String getCodigoInventario() {
        return codigoInventario;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEstado() {
        return estado;
    }


    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                "Nome invalido.");
        }
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException(
                "Categoria invalida.");
        }
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEstado(String estado) {

        if (!"DISPONIVEL".equals(estado) &&
            !"EM_USO".equals(estado) &&
            !"INATIVO".equals(estado)) {

            throw new IllegalArgumentException(
                "Estado invalido.");
        }

        this.estado = estado;
    }

    @Override
    public String toString() {

        return "Equipamento [id=" + id
                + ", codigo=" + codigoInventario
                + ", nome=" + nome
                + ", categoria=" + categoria
                + ", estado=" + estado + "]";
    }
}
