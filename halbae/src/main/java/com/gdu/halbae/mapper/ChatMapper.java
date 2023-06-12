package com.gdu.halbae.mapper;

import com.gdu.halbae.domain.ConversationDTO;
import com.gdu.halbae.domain.MessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    void createChatRoom(ConversationDTO conversationDTO);
    List<ConversationDTO> getConversationList();
    void sendMessage(MessageDTO messageDTO);
}