package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.GET

class GetPageDescendant extends AbstractCommand{
	public GetPageDescendant(ConfluenceConnection conn){
		super(conn)
	}

	public execute(String pageId,String expand="history,space,version,body.storage",String type="page"){
		this.requestParam["method"]=GET
		this.requestParam["contenttype"]=TEXT
		this.requestParam["path"]='rest/api/content/'+pageId+'/descendant/'+type
		this.requestParam["query"]=["expand":expand]
		return this.execute_request()
	}
}