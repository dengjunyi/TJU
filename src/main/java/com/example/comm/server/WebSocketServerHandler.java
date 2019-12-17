package com.example.comm.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import javax.xml.ws.Service;

public class WebSocketServerHandler extends ChannelHandlerAdapter {
	
	public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	/**
	 * 当客户端连接到服务器端之后，需要有一个连接激活的方法，此方法可以直接向客户端发送消息
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("进行网页的channelRead");
		// 在WebSocket之中所有的数据都是通过WebSocketFrame来进行包装的
		TextWebSocketFrame textFrame = (TextWebSocketFrame) msg ; // 获取发送的文本数据
		String echoValue = textFrame.content().toString(CharsetUtil.UTF_8);
		System.out.println("网页收到的信息:"+echoValue);
		//返回给网页的信息
		//channelGroup.writeAndFlush(new TextWebSocketFrame(echoValue));
		//把网页发送的条形码传入硬件
		new WebSocketServerHandler2().channelRead(ctx,echoValue);
	} 

	/**
	 * 新客户端第一次连接会调用此次方法
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("5555");
		Channel channel = ctx.channel();
		// 先写入到客户端，最后再将自己添加到ChannelGroup中
		channelGroup.add(channel);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
	
}
