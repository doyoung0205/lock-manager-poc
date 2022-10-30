### POC

- [x] 상품 수정 페이지에 다른 사용자도 수정하고 있을 때 인지 할 수 있도록 !!
- [ ] 같은 상품에 대한 수정 페이지 에서만 인지 할 수 있도록 !!
- [ ] 상품 뿐만 아니라 다른 도메인에서도 쉽게 사용할 수 있도록 추상화
- [ ] socket session redis 로 원격으로 관리하기!

----

## 알게 된 점

### 순수 websocket 의 한계

1. 모든 클라이언트의 브라우저에서 WebSocket을 지원한다는 보장이 없다.
2. 또한, Server/Client 중간에 위치한 Proxy가 Upgrade헤더를 해석하지 못해 서버에 전달하지 못할 수 있다. 마지막으로
3. Server/Client 중간에 위치한 Proxy가 유휴 상태에서 도중에 Connection 종료시킬 수도 있다.


아래와 같이 순수 web socket 을 사용할 경우 순수 모바일 크롬 브라우저와 IE에서는 WebSocket이 동작하지 않았다.

```javascript
const websocket = new WebSocket("ws://localhost:8080/ws/lock");
```

### 순수 websocket 의 한계를 극복하기 위해 Spring 을 사용한다면 SockJS 
