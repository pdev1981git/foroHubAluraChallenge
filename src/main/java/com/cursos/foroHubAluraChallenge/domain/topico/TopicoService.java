package com.cursos.foroHubAluraChallenge.domain.topico;

import com.cursos.foroHubAluraChallenge.domain.usuario.Usuario;
import com.cursos.foroHubAluraChallenge.domain.usuario.UsuarioRepository;
import com.cursos.foroHubAluraChallenge.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DtoTopicoRespuesta crearTopico(DtoRegistrarTopico dtoRegistrarTopico){

        if(!usuarioRepository.findById(dtoRegistrarTopico.idUsuario()).isPresent()){
            throw new ValidacionDeIntegridad("El id de este usuario no fue encontrado.");
        }

        if(topicoRepository.existsByTituloOrMensaje(dtoRegistrarTopico.titulo(), dtoRegistrarTopico.mensaje())){
            throw new ValidacionDeIntegridad("Este tópico o el mensaje del tópico ya existe.");
        }

        Usuario usuario = usuarioRepository.findById(dtoRegistrarTopico.idUsuario()).get();

        Topico topico = new Topico(dtoRegistrarTopico, usuario);

        topicoRepository.save(topico);

        return new DtoTopicoRespuesta(topico);
    }


    public Page<Topico> listadoTopicosActivos(Pageable paginacion) {
        return topicoRepository.findByisActivoTrue(paginacion);
    }

    public DtoTopicoRespuesta buscarTopico(Long id) {

        if(!topicoRepository.findById(id).isPresent()){
            throw new ValidacionDeIntegridad("El id del tópico no se encuentra en la base de datos");
        }

        Topico topico = topicoRepository.getReferenceById(id);

        return new DtoTopicoRespuesta(topico);
    }

    public DtoTopicoRespuesta actualizarTopico(Long id, DtoActualizarTopico dtoActualizarTopico) {

        if(!topicoRepository.findById(id).isPresent()){
            throw new ValidacionDeIntegridad("Revise el id del tópico que desea actualizar, debido a que el que se ingresó, no se encuentra en la base de datos");
        }
        Topico topicoParaActualizar = topicoRepository.getReferenceById(id);
        topicoParaActualizar.actualizarTopico(dtoActualizarTopico);

        return new DtoTopicoRespuesta(topicoParaActualizar);

    }

    public void borrarTopico(Long id) {
        if(!topicoRepository.findById(id).isPresent()){
            throw new ValidacionDeIntegridad("Revise el id del tópico que desea borrar, debido a que el que se ingresó, no se encuentra en la base de datos");
        }
        topicoRepository.deleteById(id);
    }
}
