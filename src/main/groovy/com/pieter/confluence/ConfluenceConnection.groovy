package com.pieter.confluence

import groovyx.net.http.HTTPBuilder
import org.apache.http.HttpRequest
import org.apache.http.HttpRequestInterceptor
import org.apache.http.protocol.HttpContext

class ConfluenceConnection {
	def connection
	def createConnection(String url,String user,String password,Map proxy=null){
		connection=new HTTPBuilder(url)
			if(proxy){
				confluence.setProxy("localhost",8888,"http")
			}
		connection.client.addRequestInterceptor(new HttpRequestInterceptor(){
			void process(HttpRequest httprequest,HttpContext httpcontext){
				httprequest.addHeader("Authorization","Basic "+(user+":"+password).bytes.encodeBase64()).toString()
			}
		})
	}
}
