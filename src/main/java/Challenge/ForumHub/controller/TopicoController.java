package Challenge.ForumHub.controller;

import Challenge.ForumHub.dto.DadosCadastroTopico;
import Challenge.ForumHub.model.Topico;
import Challenge.ForumHub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok("Tópico criado com sucesso!");
    }
}