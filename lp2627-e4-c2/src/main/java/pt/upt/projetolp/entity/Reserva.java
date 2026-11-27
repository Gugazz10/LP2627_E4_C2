package pt.upt.projetolp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime dataInicio;

	@Column(nullable = false)
	private LocalDateTime dataFim;

	@Column(nullable = false)
	private String estado = "ATIVA";

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "utilizador_id", nullable = false)
	private Utilizador utilizador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "espaco_id")
	private Espaco espaco;

	@ManyToMany
	@JoinTable(name = "reserva_equipamento", joinColumns = @JoinColumn(name = "reserva_id"), inverseJoinColumns = @JoinColumn(name = "equipamento_id"))
	private List<Equipamento> equipamentos = new ArrayList<>();

	
	public Reserva() {
	}


	public Reserva(LocalDateTime dataInicio, LocalDateTime dataFim, Utilizador utilizador, Espaco espaco) {

		if (dataInicio == null || dataFim == null || !dataInicio.isBefore(dataFim)) {
			throw new IllegalArgumentException("Datas da reserva invalidas.");
		}

		if (utilizador == null) {
			throw new IllegalArgumentException("Utilizador obrigatorio.");
		}

		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.utilizador = utilizador;
		this.espaco = espaco;
		this.estado = "ATIVA";
	}


	public Long getId() {
		return id;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public String getEstado() {
		return estado;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public Espaco getEspaco() {
		return espaco;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}



	public void setDatas(LocalDateTime inicio, LocalDateTime fim) {

		if (inicio == null || fim == null || !inicio.isBefore(fim)) {
			throw new IllegalArgumentException("Datas da reserva invalidas.");
		}

		this.dataInicio = inicio;
		this.dataFim = fim;
	}

	public void setEstado(String estado) {

		if (!"ATIVA".equals(estado) && !"CANCELADA".equals(estado)) {
			throw new IllegalArgumentException("Estado da reserva invalido.");
		}

		this.estado = estado;
	}

	public void setUtilizador(Utilizador utilizador) {

		if (utilizador == null) {
			throw new IllegalArgumentException("Utilizador obrigatorio.");
		}

		this.utilizador = utilizador;
	}

	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}



	public void addEquipamento(Equipamento equipamento) {

		if (equipamento == null) {
			throw new IllegalArgumentException("Equipamento invalido.");
		}

		if (!equipamentos.contains(equipamento)) {
			equipamentos.add(equipamento);
		}

		if (!equipamento.getReservas().contains(this)) {
			equipamento.getReservas().add(this);
		}
	}



	public void setEquipamentos(List<Equipamento> novos) {

		if (novos == null) {
			throw new IllegalArgumentException("Lista de equipamentos invalida.");
		}

		for (Equipamento equipamento : new ArrayList<>(equipamentos)) {

			equipamento.getReservas().remove(this);
		}

		equipamentos.clear();

		for (Equipamento equipamento : novos) {
			addEquipamento(equipamento);
		}
	}

	@Override
	public String toString() {

		return "Reserva [id=" + id + ", inicio=" + dataInicio + ", fim=" + dataFim + ", estado=" + estado + "]";
	}
}
