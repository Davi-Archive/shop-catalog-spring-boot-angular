package io.davi.catalog.services;

import io.davi.catalog.dto.NotificationDTO;
import io.davi.catalog.entities.Notification;
import io.davi.catalog.entities.User;
import io.davi.catalog.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public Page<NotificationDTO> notificationForCurrentUser(boolean unreadOnly, Pageable pageable) {
        // Busquei o usuario autenticado
        User user = authService.authenticated();
        // Fazemos agora a busca somente das notificações do  usuario autenticado
        Page<Notification> page = repository.find(user, unreadOnly, pageable);
        return page.map(x -> new NotificationDTO(x));
    }
}
