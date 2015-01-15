package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import groovy.json.*

class AbstractCommand {
	def confluence=null
	def requestParam=[:]

	def AbstractCommand(ConfluenceConnection conn){
		this.confluence=conn
	}

	def execute_request(){
		confluence.connection.request(requestParam["method"],requestParam["contenttype"]){
			uri.path=requestParam["path"]
			if(requestParam["query"]){
				uri.query=requestParam["query"]
			}
			headers."Accept"="application/json, application/javascript, text/javascript"
			if(requestParam["postdata"]){
				if(requestParam["postcontenttype"]){
					requestContentType= requestParam["postcontenttype"]
				}
				body = requestParam["postdata"]
				println body
			}
			
			response.success = {resp, reader ->
				if(reader){
					def jsonresponse=new JsonSlurper().parseText(reader.text)
					return [resp, jsonresponse]
				}else{
					return [resp]
				}
			}
			response.failure = { resp, reader ->
				println reader.text
			}
		}
	}
}