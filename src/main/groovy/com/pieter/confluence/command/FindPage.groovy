package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.GET

class FindPage extends AbstractCommand{
	public FindPage(ConfluenceConnection conn){
		super(conn)
	}

	public execute(String space,String title){
		this.requestParam["method"]=GET
		this.requestParam["contenttype"]=TEXT
		this.requestParam["path"]='rest/api/content'
		this.requestParam["query"]=[spaceKey:space,title:title]
		this.requestParam["expand"]="history,space,version,body.storage"
		return this.execute_request()
	}
}