package io.davi.catalog.services;

import io.davi.catalog.entities.User;
import io.davi.catalog.repositories.UserRepository;
import io.davi.catalog.services.exceptions.ForbiddenException;
import io.davi.catalog.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    /*
     * Método para pegar o usuário atual (autenticado)
     *
     * Exemplo : Requisição pro backend com o Token JWT , o Spring Security
     * reconhece o usuario , como que vai pegar ele ?
     *
     *
     */
    @Transactional(readOnly = true)
    public User authenticated() {
        try {
            // Pega o nome do usuario que foi reconhecido pelo spring security ( no caso ,o email)
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(username);
            // Caso capture qualquer erro( n reconhecer usuario ou algo do tipo) vou enviar um erro de Autorizacao
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid user");
        }

    }



    // Controlando no findById se o usuário que está logado é ele mesmo.
    /*
     * Se o id for do propio usuario ou do Admin = OK
     *
     * Se nao for = Acesso negado - ForbiddenException (403)
     */
    public void validateSelfOrAdmin(Long userId) {
        User user = authenticated();
        if(user.getId() != userId && !user.hasRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Acess denied");
        }
    }

    // Conteúdo customizado para usuário Logado
    /*
     * Exemplo : Usuário logado quando acessar o /notifications
     * deve aparecer pra ele somente as notificações dele.
     *
     */





}