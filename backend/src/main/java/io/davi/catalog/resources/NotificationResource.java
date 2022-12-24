package io.davi.catalog.resources;

import io.davi.catalog.dto.NotificationDTO;
import io.davi.catalog.entities.Notification;
import io.davi.catalog.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationResource {

    @Autowired
    private NotificationService service;

    @GetMapping
    public ResponseEntity<Page<NotificationDTO>> notificationsForCurrentUser(
            @RequestParam(name = "unreadOnly") Boolean unreadOnly,
            Pageable pageable){
        Page<NotificationDTO> page = service.notificationForCurrentUser(unreadOnly,pageable);
        return ResponseEntity.ok().body(page);
    }
}
