package com.pieter.confluence

import com.pieter.confluence.ConfluenceConnection
import com.pieter.confluence.command.*

class ConfluenceCopyPageTree{
	def conn=null
	public ConfluenceCopyPageTree(ConfluenceConnection conn){
		this.conn=conn
	}

	public copy(String space,String title,String toSpace){
		println("Looking for "+title+ " in "+space)
		def findpage = new FindPage(this.conn)
		def page=findpage.execute(space,title)[1]["results"][0]
		if(page){
			println("Found page with id:"+page["id"])
			this.copyPage(page["id"],toSpace)
		}
	}

	public copyPage(String pageId,String toSpace,String parent=null){
		def page=(new GetPage(this.conn)).execute(pageId,"body.storage")[1]
		println("Copying page:"+page["id"])
		def createdpage
		// copy the actual page
		try{
			def createpage=new CreatePage(this.conn)
			createdpage=createpage.execute(page["title"],toSpace,page["body"]["storage"]["value"],parent)
		}catch(Exception e){
			println("Error creating page "+page["title"])
			println(e.getMessage())
		}

		// copy the children
		try{
			def pagechild=new GetPageChild(this.conn)
			def children=pagechild.execute(page["id"])[1]["results"]
			for(child in children){
				try{
					this.copyPage(child["id"],toSpace,createdpage[1]["id"])
				}catch(Exception e){
					println("Could not copy child "+child["id"])
				}
			}
		}catch(Exception e){
			println("Did not find any child for page "+page["id"])
		}

		
	}
}