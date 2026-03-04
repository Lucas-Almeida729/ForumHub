package Challenge.ForumHub.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem,

        @NotBlank(message = "O autor é obrigatório")
        String autor,

        @NotBlank(message = "O curso é obrigatório")
        String curso
) {
}