package io.davi.catalog.services.exceptions;


// Usada para retornar o erro 403  :  ''Usuario logado porem nao tem autorizaçao pra acessar um determinado recurso"
public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException(String msg) {
        super(msg);
    }

}