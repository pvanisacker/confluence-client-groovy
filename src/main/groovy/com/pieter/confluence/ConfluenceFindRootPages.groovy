package com.pieter.confluence

import com.pieter.confluence.ConfluenceConnection
import com.pieter.confluence.command.*

class ConfluenceFindRootPages{
	def conn=null
	public ConfluenceFindRootPages(ConfluenceConnection conn){
		this.conn=conn
	}

	public execute(String space){
		// Get the pages for this space and print the ones that do not have an ancestor
		def findspacepage=new FindSpacePage(this.conn);
		def start=0;
		def limit=100;
		def haspages=true
		def pages=[]
		while(haspages){
			def result=findspacepage.execute(space,start,limit);
			for(item in result[1]["results"]){
				if(item["ancestors"].size()==0 ){
					pages << item
				}
			}
			if(result[1]["_links"]["next"]){
				start+=limit
			}else{
				haspages=false
			}
		}
		return pages
	}
}