package com.pieter.confluence

import com.pieter.confluence.ConfluenceConnection
import com.pieter.confluence.command.*

class ConfluenceDeletePageTree{
	def conn=null
	public ConfluenceDeletePageTree(ConfluenceConnection conn){
		this.conn=conn
	}

	public delete(String space,String title){
		println("Looking for "+title+ " in "+space)
		def findpage = new FindPage(this.conn)
		def page=findpage.execute(space,title)[1]["results"][0]
		if(page){
			println("Found page with id:"+page["id"])
			this.delete(page["id"])
		}
	}

	public delete(String pageId){
		println("Deleting page:"+pageId)
		// delete the children
		try{
			def pagechild=new GetPageChild(this.conn)
			def children=pagechild.execute(pageId)[1]["results"]
			for(child in children){
				try{
					this.delete(child["id"])
				}catch(Exception e){
					println("Could not delete child "+child["id"])
				}
			}
		}catch(Exception e){
			println("Did not find any children for page "+pageId)
		}

		// delete the actual page
		try{
			def deletepage=new DeletePage(this.conn)
			deletepage.delete(pageId)
		}catch(Exception e){
			println("Error deleting page "+pageId)
			println(e.getMessage())
		}
	}
}