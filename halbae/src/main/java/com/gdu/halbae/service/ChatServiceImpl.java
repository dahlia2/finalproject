package com.gdu.halbae.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.halbae.domain.ConversationDTO;
import com.gdu.halbae.domain.MessageDTO;
import com.gdu.halbae.mapper.ChatMapper;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {
	
    private final ChatMapper chatMapper;

    @Autowired
    public ChatServiceImpl(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }

	// 채팅방 생성
    @Override
    public void createChat(ConversationDTO conversationDTO) {
        chatMapper.createConversation(conversationDTO);
    }
    
    // 채팅방 입장
    @Override
    public void enterChatRoom(int conId) {
        chatMapper.enterChatRoom(conId);
    }
    
    // 채팅방 퇴장
    @Override
    public void exitChatRoom(int conId) {
        chatMapper.exitChatRoom(conId);
    }

    // 채팅 내용 가져오기
    @Override
    public List<MessageDTO> getChatMessagesByConversation(int conId) {
        return chatMapper.getChatMessagesByConversation(conId);
    }
    
    // 메시지 전송
    @Override
    public void sendMessage(int conId, int userNo, String message) {
        LocalDateTime sendTime = LocalDateTime.now();
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setConId(conId);
        messageDTO.setUserNo(userNo);
        messageDTO.setMessage(message);
        messageDTO.setSendTime(sendTime);
        chatMapper.insertMessage(messageDTO);
    }
    
    // 메시지 실시간 업데이트
    @Override
    public List<MessageDTO> getNewChatMessages(int conId, int lastMsgId) {
        return chatMapper.selectNewChatMessages(conId, lastMsgId);
    }
    
    // 채팅 내역 조회
    @Override
    public List<MessageDTO> getChatHistory(int conId) {
        return chatMapper.selectChatHistory(conId);
    }
    
    // 채팅방 삭제
    @Override
    public boolean removeChatRoom(int conId) {
        int rowsAffected = chatMapper.updateChatRoomState(conId, 0);
        return rowsAffected > 0;
    }
    
}