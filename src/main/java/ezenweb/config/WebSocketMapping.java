package ezenweb.config;

import ezenweb.controller.ChatSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration // 스프링 컨테이너에 빈 등록
@EnableWebSocket //웹소켓 매핑
public class WebSocketMapping implements WebSocketConfigurer{
    //* 스프링 버전에 따라 라이브러리 이름이 다를 수 있음
    @Autowired private ChatSocket socket; // 채팅관련 서버소켓

    //1. 웹소켓 매핑 등록
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //- ws로 요청된 url 어디로 핸들러 할건지 설정
        registry.addHandler( socket,"/chat");
    }



}
