package com.chatapp.controller.socket;

import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.dto.response.MessageResponseDTO;
import com.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageSocketController {
    @Autowired
    private MessageService messageService;

    @MessageMapping({"/messages/{senderId}/{receiverId}", "/messages/{receiverId}/{senderId}"})
    @SendTo({"/topic/messages/{senderId}/{receiverId}", "/topic/messages/{receiverId}/{senderId}"})
    public List<MessageResponseDTO> saveMessage(@DestinationVariable("senderId") Long senderId, @DestinationVariable("receiverId") Long receiverId, String content) {
        MessageRequestDTO messageDTO = new MessageRequestDTO();
        messageDTO.setSenderId(senderId);
        messageDTO.setReceiverId(receiverId);
        messageDTO.setContent(content);
        messageDTO.setType("plain/text");
        messageService.save(messageDTO);
        return messageService.findBySenderOrReceiver(messageDTO.getSenderId(), messageDTO.getReceiverId());
    }

    @MessageMapping({"/messages/{senderId}/{receiverId}/listen", "/messages/{receiverId}/{senderId}/listen"})
    @SendTo({"/topic/messages/{senderId}/{receiverId}", "/topic/messages/{receiverId}/{senderId}"})
    public List<MessageResponseDTO> getMessages(@DestinationVariable("senderId") Long senderId, @DestinationVariable("receiverId") Long receiverId) {
        return messageService.findBySenderOrReceiver(senderId, receiverId);
    }
}
