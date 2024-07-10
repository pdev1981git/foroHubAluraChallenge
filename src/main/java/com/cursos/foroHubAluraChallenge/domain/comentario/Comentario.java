package com.cursos.foroHubAluraChallenge.domain.comentario;

import com.cursos.foroHubAluraChallenge.domain.topico.Topico;
import com.cursos.foroHubAluraChallenge.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "comentarios")
@Entity(name = "Comentario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String respuesta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    public void editarMensaje(DtoActualizarComentario actualizarComentario) {
        this.respuesta = actualizarComentario.mensajeComentario();
    }
}



















