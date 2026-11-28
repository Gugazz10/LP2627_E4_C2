
package pt.upt.projetolp.entity;

import java.time.LocalTime;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "espaco")
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    private String descricao;

    @Column(nullable = false)
    private int lotacaoMaxima;

    @Column(nullable = false)
    private LocalTime horaAbertura;

    @Column(nullable = false)
    private LocalTime horaFecho;

    @Column(nullable = false)
    private String estado = "DESOCUPADO";
    
    @OneToMany(mappedBy = "espaco")
    private List<Reserva> reservas = new ArrayList<>();

    public Espaco() {
    }


    public Espaco(String nome, String tipo,
                  String descricao, int lotacaoMaxima,
                  LocalTime horaAbertura,
                  LocalTime horaFecho) {

        if (nome == null || nome.isBlank() || tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException(
                "Nome e tipo sao obrigatorios.");
        }

        if (lotacaoMaxima <= 0) {
            throw new IllegalArgumentException(
                "A lotacao deve ser positiva.");
        }

        if (horaAbertura == null ||
            horaFecho == null ||
            !horaAbertura.isBefore(horaFecho)) {
            throw new IllegalArgumentException(
                "Horário de Abertura não pode ser maior que Horário de Fecho.");
        }

        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.lotacaoMaxima = lotacaoMaxima;
        this.horaAbertura = horaAbertura;
        this.horaFecho = horaFecho;
        this.estado = "DESOCUPADO";
    }

    

    public List<Reserva> getReservas() {
    	return reservas;
    }
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getLotacaoMaxima() {
        return lotacaoMaxima;
    }

    public LocalTime getHoraAbertura() {
        return horaAbertura;
    }

    public LocalTime getHoraFecho() {
        return horaFecho;
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

    public void setTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException(
                "Tipo invalido.");
        }
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLotacaoMaxima(int lotacaoMaxima) {
        if (lotacaoMaxima <= 0) {
            throw new IllegalArgumentException(
                "Lotacao invalida.");
        }
        this.lotacaoMaxima = lotacaoMaxima;
    }
    
    public void setHoraAbertura(LocalTime horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public void setHoraFecho(LocalTime horaFecho) {
        this.horaFecho = horaFecho;
    }

    public void setEstado(String estado) {
        if (!"DESOCUPADO".equals(estado) &&
            !"INATIVO".equals(estado)) {
            throw new IllegalArgumentException(
                "Estado invalido.");
        }
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Espaco [id=" + id
                + ", nome=" + nome
                + ", tipo=" + tipo
                + ", lotacao=" + lotacaoMaxima
                + ", estado=" + estado + "]";
    }
}
