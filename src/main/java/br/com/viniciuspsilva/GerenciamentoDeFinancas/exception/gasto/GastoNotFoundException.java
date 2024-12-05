package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto;

public class GastoNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1159241039409861914L;

    public GastoNotFoundException(String msg){
        super(msg);
    }
}
