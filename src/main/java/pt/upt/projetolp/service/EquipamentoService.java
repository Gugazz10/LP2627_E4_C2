package pt.upt.projetolp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import pt.upt.projetolp.entity.Equipamento;
import pt.upt.projetolp.repository.EquipamentoRepository;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    // Listar todos os equipamentos
    public List<Equipamento> listarEquipamentos() {
        return equipamentoRepository.findByEstadoNot("INATIVO");
    }
    
    // Procurar equipamento pelo ID
    public Equipamento procurarEquipamento(Long id) {
        return equipamentoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Equipamento nao encontrado"));
    }

    // Criar equipamento
    public Equipamento criarEquipamento(Equipamento dados) {

        Equipamento equipamento = new Equipamento(
                dados.getCodigoInventario(),
                dados.getNome(),
                dados.getCategoria(),
                dados.getDescricao()
        );

        return equipamentoRepository.save(equipamento);
    }
    

    // Atualizar equipamento
    public Equipamento atualizarEquipamento(Long id, Equipamento dados) {

        Equipamento equipamento = procurarEquipamento(id);

        if (dados.getNome() != null && !dados.getNome().isBlank()) {
            equipamento.setNome(dados.getNome());
        }

        if (dados.getCategoria() != null && !dados.getCategoria().isBlank()) {
            equipamento.setCategoria(dados.getCategoria());
        }

        if (dados.getDescricao() != null) {
            equipamento.setDescricao(dados.getDescricao());
        }

        return equipamentoRepository.save(equipamento);
    }
    

    // Inativar equipamento
    public void inativarEquipamento(Long id) {

        Equipamento equipamento = procurarEquipamento(id);

        if ("INATIVO".equals(equipamento.getEstado())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O equipamento ja esta inativo"
            );
        }

        equipamento.setEstado("INATIVO");

        equipamentoRepository.save(equipamento);
    }


}
