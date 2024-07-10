package com.cursos.foroHubAluraChallenge.domain.comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegistrarComentario(

        @NotNull(message = "El id del tópico no puede ser nulo")
        Long idTopico,
        @NotNull(message = "El id del usuario no puede ser nulo")
        Long idAutorComentario,
        @NotBlank(message = "El contenido del comentario no puede estar vacío")
        String mensajeComentario

) {
}
