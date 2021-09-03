package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria;

public class CategoriaException extends RuntimeException{

    public CategoriaException(String msg) {
        super(msg);
    }

    public CategoriaException(String message, Throwable cause){
        super(message, cause);
    }

}
