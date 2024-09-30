package br.edu.ibmec.bigdata.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorInterceptor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Coleta os erros
        List<ValidationMessageError> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(this::mapToValidationMessageError)
                .collect(Collectors.toList());

        // Cria o objeto ValidationError diretamente com a lista de ValidationMessageError
        ValidationError validationError = new ValidationError("Erro de validação", 
                errors.stream()
                .map(ValidationMessageError::getMessage)  // aqui chamamos diretamente getMessage()
                .collect(Collectors.toList()));  // coletamos as mensagens de erro em uma lista de Strings

        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    // Mapeia FieldError para ValidationMessageError
    private ValidationMessageError mapToValidationMessageError(FieldError fieldError) {
        return new ValidationMessageError(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
