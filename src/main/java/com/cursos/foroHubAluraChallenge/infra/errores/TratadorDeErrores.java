package com.cursos.foroHubAluraChallenge.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErrores {

    //Manejo error 404 Not Found (error porque al buscar el registro, el registro no existe)---------------------------
    //En el @ExceptionHandler le decimos a spring que dispare este método cuando se lance una excepción=
    //= como la que tenemos dentro de los paréntesis (EntityNotFoundException.class)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }//---------------------------------------------------------------------------------------------------------------

    //Envía en el Body el mensaje de error-----------------------------------------------------------------------------
    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionesIntegridad(Exception error){
        return ResponseEntity.badRequest().body(error.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionesDeNegocio(Exception error){
        return ResponseEntity.badRequest().body(error.getMessage());
    }//---------------------------------------------------------------------------------------------------------------

    //Manejo error 400 Bad Request (y le sumo los detalles del error en el body, para contarle al cliente que mandó mal)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException errorAtrapado){
        List<DatosErrorValidacion> errores = errorAtrapado.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    //Esto es un Record DTO que solamente va a ser usado acá. Está creado para enviar=
    //= algúnos detalles sobre el error 400 (por eso está creado acá y no en el model)
    private record DatosErrorValidacion(
            String campo,
            String error
    ){
        public DatosErrorValidacion(FieldError error) {
            this(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }
    }//----------------------------------------------------------------------------------------------------------------

}
