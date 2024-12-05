package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.usuario;

public class UsuarioException extends RuntimeException{

    public UsuarioException(String msg) {
        super(msg);
    }

    public UsuarioException(String message, Throwable cause){
        super(message, cause);
    }

}
