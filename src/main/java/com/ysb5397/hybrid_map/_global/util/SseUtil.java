package com.ysb5397.hybrid_map._global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class SseUtil {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    // 구독 메서드
    public SseEmitter addEmitter(String userId) {
        log.info("연결확인");
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        this.emitters.put(userId, emitter);

        emitter.onCompletion(() -> removeEmitter(userId));
        emitter.onTimeout(() -> removeEmitter(userId));
        emitter.onError((e) -> removeEmitter(userId));

        sendToUser(userId, "connect","성공적으로 연결되었습니다! [userId=" + userId + "]");
        return emitter;
    }

    // 구독 취소 메서드
    public void removeEmitter(String userId) {
        if (emitters.containsKey(userId)) {
            this.emitters.remove(userId);
            log.info("emitter 제거됨: {}", userId);
        }
    }

    // PK, eventName, data로 알림을 보내는 메서드
    public void sendToUser(String userId, String eventName, String data) {
        SseEmitter emitter = emitters.get(userId);

        log.info("유저 ID : {} / eventName: {} / data: {}", userId, eventName, data);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name(eventName).data(data));
            } catch (IOException e) {
                log.error("SSE 전송 중 오류 발생! userId: {}, error: {}", userId, e.getMessage());
                removeEmitter(userId);
            }
        }
    }

    // eventName과 data로 방송하는 메서드
    public void broadcast(String eventName, String data) {
        emitters.keySet().forEach((userId) -> {
            sendToUser(userId, eventName, data);
        });
    }
}
