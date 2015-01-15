package com.pieter.confluence.command

import com.pieter.confluence.ConfluenceConnection
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.POST
import groovy.json.*

class AddPageLabel extends AbstractCommand{
	public AddPageLabel(ConfluenceConnection conn){
		super(conn)
	}

	public execute(String pageId,ArrayList<String> labels){
		this.requestParam["method"]=POST
		this.requestParam["contenttype"]=TEXT
		this.requestParam["path"]='rest/api/content/'+pageId+'/label'

		def labelarray=[]
		for (label in labels){
			labelarray.add(["prefix":"global","name":label])
		}
		def builder=new groovy.json.JsonBuilder(labelarray);
		
		this.requestParam["postdata"]=builder.toString()
		this.requestParam["postcontenttype"]=JSON
		return this.execute_request()
	}
}