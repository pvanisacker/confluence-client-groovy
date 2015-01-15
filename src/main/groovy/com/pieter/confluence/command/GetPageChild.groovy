package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.GET

class GetPageChild extends AbstractCommand{
	public GetPageChild(ConfluenceConnection conn){
		super(conn)
	}

	public execute(String pageId,String expand="history,space,version,body.storage",String type="page",Integer start=0,Integer limit=100){
		this.requestParam["method"]=GET
		this.requestParam["contenttype"]=TEXT
		this.requestParam["path"]='rest/api/content/'+pageId+'/child/'+type
		this.requestParam["query"]=["expand":expand,"start":start,"limit":limit]
		return this.execute_request()
	}
}