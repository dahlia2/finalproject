package com.gdu.halbae.service;

import com.gdu.halbae.domain.ConversationDTO;
import com.gdu.halbae.domain.MessageDTO;
import com.gdu.halbae.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;

    @Autowired
    public ChatServiceImpl(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }

    @Override
    public void createChatRoom(ConversationDTO conversationDTO) {
        chatMapper.createChatRoom(conversationDTO);
    }

    @Override
    public List<ConversationDTO> getConversationList() {
        return chatMapper.getConversationList();
    }

    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        chatMapper.sendMessage(messageDTO);
        return messageDTO;
    }
}