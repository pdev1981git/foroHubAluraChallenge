package com.cursos.foroHubAluraChallenge.domain.topico;

import com.cursos.foroHubAluraChallenge.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DtoTopicoRespuesta(

        long id,
        String titulo,
        String mensaje,
        String nombreCurso,
        LocalDateTime fechaCreacion,
        Estado estado,
        Long autorId

) {
    public DtoTopicoRespuesta(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getNombreCurso(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                topico.getUsuario().getId()
        );
    }

}
