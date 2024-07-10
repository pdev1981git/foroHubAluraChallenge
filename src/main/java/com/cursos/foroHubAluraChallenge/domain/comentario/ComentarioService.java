package com.cursos.foroHubAluraChallenge.domain.comentario;

import com.cursos.foroHubAluraChallenge.domain.topico.DtoTopicoRespuesta;
import com.cursos.foroHubAluraChallenge.domain.topico.Topico;
import com.cursos.foroHubAluraChallenge.domain.topico.TopicoRepository;
import com.cursos.foroHubAluraChallenge.domain.usuario.Usuario;
import com.cursos.foroHubAluraChallenge.domain.usuario.UsuarioRepository;
import com.cursos.foroHubAluraChallenge.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;


    public DtoComentarioRespuesta crearComentario(DtoRegistrarComentario dtoRegistrarComentario) {

        if(!usuarioRepository.findById(dtoRegistrarComentario.idAutorComentario()).isPresent()){
            throw new ValidacionDeIntegridad("El id del autor del comentario no fue encontrado.");
        }

        if(!topicoRepository.findById(dtoRegistrarComentario.idTopico()).isPresent()){
            throw new ValidacionDeIntegridad("El id del tópico no se encuentra en la base de datos");
        }

        Usuario usuarioDelComentario = usuarioRepository.findById(dtoRegistrarComentario.idAutorComentario()).get();
        Topico topicoParaComentar = topicoRepository.findById(dtoRegistrarComentario.idTopico()).get();
        Comentario comentarioParaGuardar = new Comentario(null,dtoRegistrarComentario.mensajeComentario(),usuarioDelComentario,topicoParaComentar);
        comentarioRepository.save(comentarioParaGuardar);

        return new DtoComentarioRespuesta(comentarioParaGuardar);
    }

    public DtoComentarioRespuesta actualizarComentario(DtoActualizarComentario actualizarComentario) {

        if(!usuarioRepository.findById(actualizarComentario.idMensaje()).isPresent()){
            throw new ValidacionDeIntegridad("El id del comentario no fue encontrado.");
        }

        Comentario comentarioParaActualizar = comentarioRepository.getReferenceById(actualizarComentario.idMensaje());

        comentarioParaActualizar.editarMensaje(actualizarComentario);

        return new DtoComentarioRespuesta(comentarioParaActualizar);
    }
}
