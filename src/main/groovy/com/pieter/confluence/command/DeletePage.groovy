package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.DELETE
import groovy.json.*

class DeletePage extends AbstractCommand{
	public DeletePage(ConfluenceConnection conn){
		super(conn)
	}

	public delete(String pageId){
		this.requestParam["method"]=DELETE
		this.requestParam["contenttype"]=TEXT
		this.requestParam["path"]='rest/api/content/'+pageId
		return this.execute_request()
	}
}