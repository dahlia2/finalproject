package com.gdu.halbae.service;

import com.gdu.halbae.domain.ConversationDTO;
import com.gdu.halbae.domain.MessageDTO;

import java.util.List;

public interface ChatService {
    void createChatRoom(ConversationDTO conversationDTO);
    List<ConversationDTO> getConversationList();
    MessageDTO sendMessage(MessageDTO messageDTO);
}