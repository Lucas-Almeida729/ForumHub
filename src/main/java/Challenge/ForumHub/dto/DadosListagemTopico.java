package Challenge.ForumHub.dto;

import Challenge.ForumHub.model.EstadoTopico;
import Challenge.ForumHub.model.Topico;
import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        EstadoTopico status,
        String autor,
        String curso
) {
    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getDataCriacao(), topico.getStatus(),
                topico.getAutor(), topico.getCurso());
    }
}