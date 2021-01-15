package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto;

public class PlanoDeGastoNotFoundException extends RuntimeException{
    public PlanoDeGastoNotFoundException(String message) {
        super(message);
    }
}
