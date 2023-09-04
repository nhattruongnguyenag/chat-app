package com.chatapp.controller.socket;

import com.chatapp.dto.MessageDTO;
import com.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MessageSocketController {
    @Autowired
    private MessageService messageService;

    @MessageMapping({"messages/{senderId}/{receiverId}",
            "messages/{senderId}/{receiverId}/"})
    @SendTo({"topics/messages/{senderId}/{receiverId}",
            "topics/messages/{senderId}/{receiverId}/"})
    public List<MessageDTO> saveMessage(@DestinationVariable("senderId") Long senderId, @DestinationVariable("receiverId") Long receiverId, String content) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSenderId(senderId);
        messageDTO.setReceiverId(receiverId);
        messageDTO.setContent(content);
        messageService.save(messageDTO);
        return messageService.findBySenderOrReceiver(messageDTO.getSenderId(), messageDTO.getReceiverId());
    }

    @MessageMapping({"messages/{senderId}/{receiverId}/listen",
            "messages/{senderId}/{receiverId}/listen/"})
    @SendTo({"topics/messages/{senderId}/{receiverId}/listen",
            "topics/messages/{senderId}/{receiverId}/listen/"})
    public List<MessageDTO> getMessages(@DestinationVariable("senderId") Long senderId, @DestinationVariable("receiverId") Long receiverId) {
        return messageService.findBySenderOrReceiver(senderId, receiverId);
    }
}
