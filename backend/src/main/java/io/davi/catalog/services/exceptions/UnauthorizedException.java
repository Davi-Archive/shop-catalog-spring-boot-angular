package io.davi.catalog.services.exceptions;

//Usada para retornar o erro 401  :  ''Token inválido''
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

}