package com.cursos.foroHubAluraChallenge.domain.topico;

import com.cursos.foroHubAluraChallenge.domain.comentario.Comentario;
import com.cursos.foroHubAluraChallenge.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String nombreCurso;
    private boolean isActivo;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Topico(DtoRegistrarTopico dtoRegistrarTopico, Usuario usuario) {
        this.titulo = dtoRegistrarTopico.titulo();
        this.mensaje = dtoRegistrarTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = Estado.SIN_RESOLVER;
        this.nombreCurso = dtoRegistrarTopico.nombreCurso();
        this.isActivo = true;
        this.usuario = usuario;
    }

    public void cambiarEstado() {
        this.estado = Estado.RESUELTO;
    }

    public void borradoLogico() {
        this.isActivo = false;
    }

    public void actualizarTopico(DtoActualizarTopico dtoActualizarTopico) {
        if (dtoActualizarTopico.mensaje() != null){
            this.mensaje = dtoActualizarTopico.mensaje();
        }
        if (dtoActualizarTopico.nombreCurso() != null){
            this.nombreCurso = dtoActualizarTopico.nombreCurso();
        }
        if (dtoActualizarTopico.titulo() != null){
            this.titulo = dtoActualizarTopico.titulo();
        }
        if (dtoActualizarTopico.estado() != null){
            this.estado = dtoActualizarTopico.estado();
        }
        if (dtoActualizarTopico.isActivo() != null){
            this.isActivo = Boolean.parseBoolean(dtoActualizarTopico.isActivo());
        }

    }
}











