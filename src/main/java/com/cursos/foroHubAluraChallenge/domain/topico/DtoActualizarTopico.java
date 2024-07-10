package com.cursos.foroHubAluraChallenge.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoActualizarTopico (

        String mensaje,
        String nombreCurso,
        String titulo,
        Estado estado,
        String isActivo

) {
}
