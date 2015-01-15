package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.GET

class FindSpacePage extends AbstractCommand{
	public FindSpacePage(ConfluenceConnection conn){
		super(conn)
	}

	public execute(String space,Integer start=0,Integer limit=100){
		this.requestParam["method"]=GET
		this.requestParam["contenttype"]=TEXT
		this.requestParam["path"]='rest/api/space/'+space+'/content/page'
		this.requestParam["query"]=["start":start,"limit":limit,"expand":"ancestors"]
		return this.execute_request()
	}
}