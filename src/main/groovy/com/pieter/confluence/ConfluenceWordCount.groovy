package com.pieter.confluence

import com.pieter.confluence.ConfluenceConnection
import com.pieter.confluence.command.*

class ConfluenceWordCount{
	def conn=null
	public ConfluenceWordCount(ConfluenceConnection conn){
		this.conn=conn
	}

	public count(String pageId){
		// Get the page and count the words
		def confpage=(new GetPage(this.conn)).execute(pageId,"body.view")[1]
		this.printcount(confpage)
		this.printchildren(pageId)
	}

	def printchildren(pageId){
		def children=(new GetPageChild(this.conn)).execute(pageId,"")[1]
		for(child in children["results"]){
			this.count(child["id"])
		}
	}

	def printcount(pagecontent){
		def pageId=pagecontent["id"]
		def pageTitle=pagecontent["title"]
		def wordcount=pagecontent["body"]["view"]["value"].replaceAll("\\<.*?>"," ").split("\\s+").length
		println "${pageId},${pageTitle},${wordcount}"
	}
}