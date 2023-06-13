package com.gdu.halbae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.halbae.domain.ConversationDTO;
import com.gdu.halbae.domain.MessageDTO;
import com.gdu.halbae.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

	// 채팅방 생성
    @PostMapping("/create")
    public String createChatRoom(int classNo) {
        // classNo를 기반으로 채팅방 생성
        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setClassNo(classNo);
        chatService.createChat(conversationDTO);
        return "redirect:/chat/create";
    }
    
    // 채팅방 입장
    @GetMapping("/enter")
    public void enterChatRoom(@RequestParam("conId") int conId) {
        chatService.enterChatRoom(conId);
    }

    // 채팅방 퇴장
    @GetMapping("/exit")
    public void exitChatRoom(@RequestParam("conId") int conId) {
        chatService.exitChatRoom(conId);
    }

    // 채팅 내용 가져오기
    @GetMapping("/messages")
    public List<MessageDTO> getChatMessages(@RequestParam("conId") int conId) {
        return chatService.getChatMessagesByConversation(conId);
    }
    
    // 메시지 전송
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestParam("conId") int conId,
                                         @RequestParam("userNo") int userNo,
                                         @RequestParam("message") String message) {
        chatService.sendMessage(conId, userNo, message);
        return ResponseEntity.ok().build();
    }
    
    // 메시지 실시간 업데이트(Long Polling 방식)
    @GetMapping("/messages/{conId}")
    public List<MessageDTO> getChatMessages(@PathVariable("conId") int conId,
                                            @RequestParam("lastMsgId") int lastMsgId) {
        return chatService.getNewChatMessages(conId, lastMsgId);
    }
    
    // 채팅 내역 조회
    @GetMapping("/history/{conId}")
    public List<MessageDTO> getChatHistory(@PathVariable("conId") int conId) {
        return chatService.getChatHistory(conId);
    }
    
    // 채팅방 삭제
    @DeleteMapping("/delete/{conId}")
    public ResponseEntity<String> removeChatRoom(@PathVariable("conId") int conId) {
        boolean deleted = chatService.removeChatRoom(conId);
        if (deleted) {
            return ResponseEntity.ok("대화방이 삭제되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("대화방 삭제를 실패하였습니다. 다시 시도해주세요.");
        }
    }
    
}