package com.cursos.foroHubAluraChallenge.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegistrarTopico(

        @NotNull(message = "El usuario no puede ser nulo")
        Long idUsuario,
        @NotBlank(message = "El contenido del tópico no puede estar vacío")
        String mensaje,
        @NotBlank(message = "El nombre del curso debe ser el correcto")
        String nombreCurso,
        @NotBlank(message = "El título no puede estar vacío")
        String titulo

) {
}
