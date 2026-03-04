package Challenge.ForumHub.controller;

import Challenge.ForumHub.dto.*;
import Challenge.ForumHub.model.Topico;
import Challenge.ForumHub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {
        var jaExiste = repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem());

        if (jaExiste) {
            return ResponseEntity.badRequest().body("Erro: Já existe um tópico com este título e mensagem.");
        }

        var topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(dados.autor());
        topico.setCurso(dados.curso());

        repository.save(topico);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}, direction = Sort.Direction.ASC) Pageable paginacao) {

        var pagina = repository.findAll(paginacao).map(DadosListagemTopico::new);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Optional<Topico> optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            return ResponseEntity.ok(new DadosDetalhamentoTopico(optionalTopico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        Optional<Topico> optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            var topico = optionalTopico.get();
            topico.setTitulo(dados.titulo());
            topico.setMensagem(dados.mensagem());
            topico.setAutor(dados.autor());
            topico.setCurso(dados.curso());

            return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        Optional<Topico> optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}