package com.ohgiraffers.websockettest.config;

import org.springframework.context.annotation.Configuration;

/*
위에서 만든 handler를 이용하여 Websocket을 활성화하기 위한 Config 파일을 작성합니다.
@EnableWebSocket을 선언하여 Websocket을 활성화합니다. Websocket에 접속하기 위한 endpoint는 /ws/chat으로
설정하고 도메인이 다른 서버에서도 접속 가능하도록 CORS : setAllowedOrigins(“*”)를 설정을 추가해 줍니다.
이제 클라이언트가 ws://localhost:8080/ws/chat으로 커넥션을 연결하고 메시지 통신을 할 수 있는 기본적인 준비가
끝났습니다.
*/
@Configuration
public class WebSocketConfig {
//    @Bean(name = "customChatRoomRepository")
//    public ChatRoomRepository chatRoomRepository() {
//        return new ChatRoomRepository() {
//            @Override
//            public List<ChatRoom> findAll(Sort sort) {
//                return List.of();
//            }
//
//            @Override
//            public Page<ChatRoom> findAll(Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatRoom> S save(S entity) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatRoom> List<S> saveAll(Iterable<S> entities) {
//                return List.of();
//            }
//
//            @Override
//            public Optional<ChatRoom> findById(Long aLong) {
//                return Optional.empty();
//            }
//
//            @Override
//            public boolean existsById(Long aLong) {
//                return false;
//            }
//
//            @Override
//            public List<ChatRoom> findAll() {
//                return List.of();
//            }
//
//            @Override
//            public List<ChatRoom> findAllById(Iterable<Long> longs) {
//                return List.of();
//            }
//
//            @Override
//            public long count() {
//                return 0;
//            }
//
//            @Override
//            public void deleteById(Long aLong) {
//
//            }
//
//            @Override
//            public void delete(ChatRoom entity) {
//
//            }
//
//            @Override
//            public void deleteAllById(Iterable<? extends Long> longs) {
//
//            }
//
//            @Override
//            public void deleteAll(Iterable<? extends ChatRoom> entities) {
//
//            }
//
//            @Override
//            public void deleteAll() {
//
//            }
//
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public <S extends ChatRoom> S saveAndFlush(S entity) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatRoom> List<S> saveAllAndFlush(Iterable<S> entities) {
//                return List.of();
//            }
//
//            @Override
//            public void deleteAllInBatch(Iterable<ChatRoom> entities) {
//
//            }
//
//            @Override
//            public void deleteAllByIdInBatch(Iterable<Long> longs) {
//
//            }
//
//            @Override
//            public void deleteAllInBatch() {
//
//            }
//
//            @Override
//            public ChatRoom getOne(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public ChatRoom getById(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public ChatRoom getReferenceById(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatRoom> Optional<S> findOne(Example<S> example) {
//                return Optional.empty();
//            }
//
//            @Override
//            public <S extends ChatRoom> List<S> findAll(Example<S> example) {
//                return List.of();
//            }
//
//            @Override
//            public <S extends ChatRoom> List<S> findAll(Example<S> example, Sort sort) {
//                return List.of();
//            }
//
//            @Override
//            public <S extends ChatRoom> Page<S> findAll(Example<S> example, Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatRoom> long count(Example<S> example) {
//                return 0;
//            }
//
//            @Override
//            public <S extends ChatRoom> boolean exists(Example<S> example) {
//                return false;
//            }
//
//            @Override
//            public <S extends ChatRoom, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//                return null;
//            }
//
//            @Override
//            public List<ChatRoom> findAllRoom() {
//                return List.of();
//            }
//
//            @Override
//            public List<ChatMessage> findAllMessage() {
//                return List.of();
//            }
//
//            @Override
//            public ChatRoom findById(long id) {
//                return null;
//            }
//
//            @Override
//            public ChatMessage findMessageById(long id) {
//                return null;
//            }
//
//            @Override
//            public int createRoom(String name) {
//
//                return 0;
//            }
//
//            @Override
//            public int createMessage(String name) {
//                return 0;
//            }
//
//        };
//
//    }
//    @Bean(name = "customChatMessageRepository")
//    public ChatMessageRepository customChatMessageRepository() {
//        return new ChatMessageRepository() {
//
//            @Override
//            public List<ChatMessage> findAll(Sort sort) {
//                return List.of();
//            }
//
//            @Override
//            public Page<ChatMessage> findAll(Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatMessage> S save(S entity) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatMessage> List<S> saveAll(Iterable<S> entities) {
//                return List.of();
//            }
//
//            @Override
//            public Optional<ChatMessage> findById(Long aLong) {
//                return Optional.empty();
//            }
//
//            @Override
//            public boolean existsById(Long aLong) {
//                return false;
//            }
//
//            @Override
//            public List<ChatMessage> findAll() {
//                return List.of();
//            }
//
//            @Override
//            public List<ChatMessage> findAllById(Iterable<Long> longs) {
//                return List.of();
//            }
//
//            @Override
//            public long count() {
//                return 0;
//            }
//
//            @Override
//            public void deleteById(Long aLong) {
//
//            }
//
//            @Override
//            public void delete(ChatMessage entity) {
//
//            }
//
//            @Override
//            public void deleteAllById(Iterable<? extends Long> longs) {
//
//            }
//
//            @Override
//            public void deleteAll(Iterable<? extends ChatMessage> entities) {
//
//            }
//
//            @Override
//            public void deleteAll() {
//
//            }
//
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public <S extends ChatMessage> S saveAndFlush(S entity) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatMessage> List<S> saveAllAndFlush(Iterable<S> entities) {
//                return List.of();
//            }
//
//            @Override
//            public void deleteAllInBatch(Iterable<ChatMessage> entities) {
//
//            }
//
//            @Override
//            public void deleteAllByIdInBatch(Iterable<Long> longs) {
//
//            }
//
//            @Override
//            public void deleteAllInBatch() {
//
//            }
//
//            @Override
//            public ChatMessage getOne(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public ChatMessage getById(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public ChatMessage getReferenceById(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatMessage> Optional<S> findOne(Example<S> example) {
//                return Optional.empty();
//            }
//
//            @Override
//            public <S extends ChatMessage> List<S> findAll(Example<S> example) {
//                return List.of();
//            }
//
//            @Override
//            public <S extends ChatMessage> List<S> findAll(Example<S> example, Sort sort) {
//                return List.of();
//            }
//
//            @Override
//            public <S extends ChatMessage> Page<S> findAll(Example<S> example, Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends ChatMessage> long count(Example<S> example) {
//                return 0;
//            }
//
//            @Override
//            public <S extends ChatMessage> boolean exists(Example<S> example) {
//                return false;
//            }
//
//            @Override
//            public <S extends ChatMessage, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//                return null;
//            }
//
//            @Override
//            public List<ChatMessage> findAllMessage() {
//                return List.of();
//            }
//
//            @Override
//            public ChatMessage findMessageById(long id) {
//                return null;
//            }
//
//            @Override
//            public int createMessage(String name) {
//
//                return 0;
//            }
//        };
//    }
}
