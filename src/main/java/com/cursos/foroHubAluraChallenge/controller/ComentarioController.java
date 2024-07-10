package com.cursos.foroHubAluraChallenge.controller;


import com.cursos.foroHubAluraChallenge.domain.comentario.ComentarioService;
import com.cursos.foroHubAluraChallenge.domain.comentario.DtoActualizarComentario;
import com.cursos.foroHubAluraChallenge.domain.comentario.DtoComentarioRespuesta;
import com.cursos.foroHubAluraChallenge.domain.comentario.DtoRegistrarComentario;
import com.cursos.foroHubAluraChallenge.infra.errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comentario")
@SecurityRequirement(name = "bearer-key")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    //Crear comentario----------------------------------------------------------------------------------------
    @PostMapping
    @Transactional
    public ResponseEntity<DtoComentarioRespuesta> registrarComentario(@RequestBody @Valid DtoRegistrarComentario dtoRegistrarComentario) throws ValidacionDeIntegridad {
        return ResponseEntity.ok(comentarioService.crearComentario(dtoRegistrarComentario));
    }

    //Editar un comentario----------------------------------------------------------------------------------------
    @PutMapping
    @Transactional
    public ResponseEntity<DtoComentarioRespuesta> actualizarComentario(@RequestBody @Valid DtoActualizarComentario dtoActualizarComentario){
        return ResponseEntity.ok(comentarioService.actualizarComentario(dtoActualizarComentario));
    }

}
