package com.cursos.foroHubAluraChallenge.controller;

import com.cursos.foroHubAluraChallenge.domain.topico.*;
import com.cursos.foroHubAluraChallenge.infra.errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    //Crear Tópico----------------------------------------------------------------------------------------
    @PostMapping
    @Transactional
    public ResponseEntity<DtoTopicoRespuesta> registrarTopico(@RequestBody @Valid DtoRegistrarTopico dtoRegistrarTopico) throws ValidacionDeIntegridad {
        return ResponseEntity.ok(topicoService.crearTopico(dtoRegistrarTopico));
    }

    //Listar tópicos----------------------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<Page<DtoTopicoRespuesta>> listadoDeTopicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listadoTopicosActivos(paginacion).map(DtoTopicoRespuesta::new));
    }

    //Buscar un tópico----------------------------------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<DtoTopicoRespuesta> retornaTopico(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.buscarTopico(id));
    }

    //Actualizar un tópico----------------------------------------------------------------------------------------
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoTopicoRespuesta> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DtoActualizarTopico dtoActualizarTopico){
        return ResponseEntity.ok(topicoService.actualizarTopico(id, dtoActualizarTopico));
    }

    //Borrar tópico de ddbb----------------------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoTopicoRespuesta> borrarTopico(@PathVariable Long id){
        topicoService.borrarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
