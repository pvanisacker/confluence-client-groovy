package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.POST
import groovy.json.*

class CreatePage extends AbstractCommand{
	public CreatePage(ConfluenceConnection conn){
		super(conn)
	}

	public execute(String pageTitle,String pageSpace,String pageContent,String parent=null){
		this.requestParam["method"]=POST
		this.requestParam["contenttype"]=TEXT
		this.requestParam["path"]='rest/api/content/'

		def page=[type:"page",title:pageTitle,space:["key":pageSpace],body:[storage:[value:pageContent,representation:"storage"]]]
		if(parent){
			page["ancestors"]=[[type:"page",id:parent]]
		}
		def builder=new groovy.json.JsonBuilder(page);
		
		this.requestParam["postdata"]=builder.toString()
		this.requestParam["postcontenttype"]=JSON
		return this.execute_request()
	}
}