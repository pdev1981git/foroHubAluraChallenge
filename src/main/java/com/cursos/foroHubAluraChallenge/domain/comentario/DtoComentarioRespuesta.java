package com.cursos.foroHubAluraChallenge.domain.comentario;

public record DtoComentarioRespuesta(

        String tituloTopico,
        Long idTopico,
        String mensajePosteado,
        Long idMensaje

) {
    public DtoComentarioRespuesta(Comentario comentarioParaGuardar) {
        this(
                comentarioParaGuardar.getTopico().getTitulo(),
                comentarioParaGuardar.getTopico().getId(),
                comentarioParaGuardar.getRespuesta(),
                comentarioParaGuardar.getId()
        );
    }

}
