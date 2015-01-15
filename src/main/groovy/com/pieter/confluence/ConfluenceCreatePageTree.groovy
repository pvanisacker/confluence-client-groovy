package com.pieter.confluence

import com.pieter.confluence.ConfluenceConnection
import com.pieter.confluence.command.*

class ConfluenceCreatePageTree{
	def conn=null
	def space=null
	public ConfluenceCreatePageTree(ConfluenceConnection conn){
		this.conn=conn
	}

	public create(String space,List pages){
		this.space=space
		for(page in pages){
			createPage(page)
		}
	}

	def createPage(page){
		if(!page["body"]){
			page["body"]=""
		}

		def createpage=new CreatePage(this.conn)
		def createdpage=createpage.execute(page["title"],this.space,page["body"],page["parent"])

		// Iterate over child pages and create them
		if(page["children"]){
			for (child in page["children"]){
				child["parent"]=createdpage[1]["id"]
				this.createPage(child)
			}
		}
	}
}