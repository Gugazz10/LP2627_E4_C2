package pt.upt.projetolp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pt.upt.projetolp.entity.Equipamento;
import pt.upt.projetolp.service.EquipamentoService;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    // Listar equipamentos
    @GetMapping
    public List<Equipamento> listar() {
        return equipamentoService.listarEquipamentos();
    }

    // Procurar equipamento por ID
    @GetMapping("/{id}")
    public Equipamento procurar(@PathVariable Long id) {
        return equipamentoService.procurarEquipamento(id);
    }

    // Criar equipamento
    @PostMapping
    public ResponseEntity<Equipamento> criar(@RequestBody Equipamento dados) {

        Equipamento criado = equipamentoService.criarEquipamento(dados);

        URI location = URI.create("/api/equipamentos/" + criado.getId());

        return ResponseEntity.created(location).body(criado);
    }

    // Atualizar equipamento
    @PutMapping("/{id}")
    public Equipamento atualizar(
            @PathVariable Long id,
            @RequestBody Equipamento dados) {

        return equipamentoService.atualizarEquipamento(id, dados);
    }

    // Inativar equipamento
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long id) {
        equipamentoService.inativarEquipamento(id);
    }
}
