package com.gdu.halbae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/room")
    public String chatRoom(Model model, @RequestParam("conId") int conId) {
        model.addAttribute("conId", conId);
        return "chat";
    }

    @GetMapping("/list")
    public String chatList(Model model) {
        // 채팅방 목록을 가져와서 모델에 추가
        model.addAttribute("conversationList", chatService.getConversationList());
        return "chatList";
    }

    @PostMapping("/create")
    public String createChatRoom(ConversationDTO conversationDTO) {
        chatService.createChatRoom(conversationDTO);
        return "redirect:/chat/list";
    }

    @PostMapping("/sendMessage")
    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat/{conId}")
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        return chatService.sendMessage(messageDTO);
    }
}