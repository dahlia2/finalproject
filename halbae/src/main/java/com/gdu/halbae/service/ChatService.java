package com.gdu.halbae.service;

import java.util.List;

import com.gdu.halbae.domain.ConversationDTO;
import com.gdu.halbae.domain.MessageDTO;

public interface ChatService {
	// 채팅방 생성
    void createChat(ConversationDTO conversationDTO);
    // 채팅방 입장
    void enterChatRoom(int conId);
    // 채팅방 퇴장
    void exitChatRoom(int conId);
    // 채팅 내용 가져오기
    List<MessageDTO> getChatMessagesByConversation(int conId);
    // 메시지 전송
    void sendMessage(int conId, int userNo, String message);
    // 메시지 실시간 업데이트
    List<MessageDTO> getNewChatMessages(int conId, int lastMsgId);
    // 채팅 내역 조회
    List<MessageDTO> getChatHistory(int conId);
    // 채팅방 삭제
    boolean removeChatRoom(int conId);
}