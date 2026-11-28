package pt.upt.projetolp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.upt.projetolp.entity.Equipamento;

public interface EquipamentoRepository
        extends JpaRepository<Equipamento, Long> {

    List<Equipamento> findByEstadoNot(String estado);
}
