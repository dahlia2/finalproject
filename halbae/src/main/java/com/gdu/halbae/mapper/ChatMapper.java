package com.gdu.halbae.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gdu.halbae.domain.ConversationDTO;
import com.gdu.halbae.domain.MessageDTO;

@Mapper
public interface ChatMapper {
	// 채팅방 생성
    void createConversation(ConversationDTO conversationDTO);
    // 채팅방 입장
    void enterChatRoom(int conId);
    // 채팅방 퇴장
    void exitChatRoom(int conId);
    // 채팅 내용 가져오기
    List<MessageDTO> getChatMessagesByConversation(int conId);
    // 메시지 전송
    void insertMessage(MessageDTO message);
    // 메시지 실시간 업데이트
    List<MessageDTO> selectNewChatMessages(int conId, int lastMsgId);
    // 채팅 내역 조회
    List<MessageDTO> selectChatHistory(int conId);
    // 채팅방 삭제
    int updateChatRoomState(@Param("conId") int conId, @Param("state") int state);
}