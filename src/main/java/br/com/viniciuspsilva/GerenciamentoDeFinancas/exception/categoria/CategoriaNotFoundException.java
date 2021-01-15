package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria;

public class CategoriaNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1149241039409861914L;

    public CategoriaNotFoundException(String msg){
        super(msg);
    }
}
