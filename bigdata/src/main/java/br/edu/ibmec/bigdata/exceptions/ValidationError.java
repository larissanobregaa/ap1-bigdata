package br.edu.ibmec.bigdata.exceptions;

import java.util.List;

public class ValidationError {
    private String message;
    private List<String> errors;

    public ValidationError(String message, List<String> errors){
        this.message = message;
        this.errors = errors;
    }

    public String getMessage(){
        return message;
    }

    public List<String> getErrors(){
        return errors;
    }

    public void setErrors(List<String> errors){
        this.errors = errors;
    }
}
