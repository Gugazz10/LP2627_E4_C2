package pt.upt.projetolp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilizador")
public class Utilizador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String tipo;

	@Column(nullable = false)
	private String estado = "ATIVO";

	@OneToMany(mappedBy = "utilizador")
	private List<Reserva> reservas = new ArrayList<>();

	
	public Utilizador() {
	}


	public Utilizador(String nome, String email, String tipo) {

		if (nome == null || nome.isBlank() || email == null || email.isBlank() || tipo == null || tipo.isBlank()) {

			throw new IllegalArgumentException("Nome, email e tipo sao obrigatorios!");
		}

		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.estado = "ATIVO";
	}



	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTipo() {
		return tipo;
	}

	public String getEstado() {
		return estado;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}



	public void setNome(String nome) {

		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome invalido!");
		}

		this.nome = nome;
	}

	public void setEmail(String email) {

		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("Email invalido!");
		}

		this.email = email;
	}

	public void setTipo(String tipo) {

		if (tipo == null || tipo.isBlank()) {
			throw new IllegalArgumentException("Tipo invalido!");
		}

		this.tipo = tipo;
	}

	public void setEstado(String estado) {

		if (!"ATIVO".equals(estado) && !"INATIVO".equals(estado)) {

			throw new IllegalArgumentException("Estado invalido!");
		}

		this.estado = estado;
	}

	@Override
	public String toString() {

		return "Utilizador [id=" + id + ", nome=" + nome + ", email=" + email + ", tipo=" + tipo + ", estado=" + estado
				+ "]";
	}
}
