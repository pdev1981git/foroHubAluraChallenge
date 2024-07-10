package com.cursos.foroHubAluraChallenge.domain.comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoActualizarComentario(

        @NotNull(message = "El id del mensaje no puede ser nulo")
        Long idMensaje,
        @NotBlank(message = "El contenido del comentario no puede estar vacío")
        String mensajeComentario

) {
}