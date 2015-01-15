package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.GET
import groovy.json.*

class GetPageLabel extends AbstractCommand{
	public GetPageLabel(ConfluenceConnection conn){
		super(conn)
	}

	public execute(String pageId){
		this.requestParam["method"]=GET
		this.requestParam["contenttype"]=TEXT
		this.requestParam["path"]='rest/api/content/'+pageId+'/label'

		return this.execute_request()
	}
}