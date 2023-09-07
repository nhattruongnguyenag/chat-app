package com.chatapp.controller.api;

import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.dto.response.MessageResponseDTO;
import com.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageAPI {
    @Autowired
    private MessageService messageService;

    @GetMapping("/messages/{senderId}/{receiverId}")
    List<MessageResponseDTO> findMessagesBySenderOrReceiver(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId) {
        return messageService.findBySenderOrReceiver(senderId, receiverId);
    }

    @PostMapping("/messages")
    MessageRequestDTO save(@RequestBody MessageRequestDTO messageDTO) {
        return messageService.save(messageDTO);
    }

    @DeleteMapping("/messages/{id}")
    ResponseEntity delete(@PathVariable("id") Long id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }
}
