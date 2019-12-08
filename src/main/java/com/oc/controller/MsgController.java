package com.oc.controller;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class MsgController {

	@OnOpen
	public void echoOpen() {
		
	}
	
	@OnMessage
	public void echoMsg(Session session, String msg) {
		
	}
	
	@OnClose
	public void echoClose() {
		
	}
	
	@OnError
	public void echoError(Throwable t) {
		
	}
	
}
