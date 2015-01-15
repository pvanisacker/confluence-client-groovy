package com.pieter.confluence

import com.pieter.confluence.ConfluenceConnection
import com.pieter.confluence.command.*

class ConfluenceAddLabelPageTree{
	def conn=null
	public ConfluenceAddLabelPageTree(ConfluenceConnection conn){
		this.conn=conn
	}

	public add(String space,String title,ArrayList<String> labels){
		println("Looking for "+title+ " in "+space)
		def findpage = new FindPage(this.conn)
		def page=findpage.execute(space,title)[1]["results"][0]
		if(page){
			println("Found page with id:"+page["id"])
			this.add(page["id"],labels)
		}
	}

	public add(String pageId,ArrayList<String> labels){
		// add the labels to child pages
		try{
			def pagechild=new GetPageChild(this.conn)
			def children=pagechild.execute(pageId)[1]["results"]
			for(child in children){
				try{
					this.add(child["id"],labels)
				}catch(Exception e){
					println("Could not update child "+child["id"])
				}
			}
		}catch(Exception e){
			println("Did not find any children for page "+pageId)
		}

		// add labels to the current page
		try{
			def addpagelabel=new AddPageLabel(this.conn)
			addpagelabel.execute(pageId,labels)
		}catch(Exception e){
			println("Error labelling page "+pageId)
			println(e.getMessage())
		}
	}
}