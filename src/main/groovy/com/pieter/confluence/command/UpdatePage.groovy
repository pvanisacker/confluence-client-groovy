package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.POST
import groovy.json.*

class UpdatePage extends AbstractCommand{
	public UpdatePage(ConfluenceConnection conn){
		super(conn)
	}

	public execute(String pageId,String pageTitle,String pageSpace,String pageContent,Integer version){
		this.requestParam["method"]=POST
		this.requestParam["contenttype"]=TEXT
		this.requestParam["path"]='rest/api/content/'+pageId

		def builder=new groovy.json.JsonBuilder(["id":pageId,"type":"page","title":pageTitle,"space":["key":pageSpace],"body":["storage":["value":pageContent,"representation":"storage"]],"version":["number":version]]);
		
		this.requestParam["postdata"]=builder.toString()
		this.requestParam["postcontenttype"]=JSON
		return this.execute_request()
	}
}