package com.pieter.confluence

import com.pieter.confluence.ConfluenceConnection
import com.pieter.confluence.command.*
import org.languagetool.*
import org.languagetool.rules.*
import org.languagetool.language.*

class ConfluenceLanguageCheck{
	def conn=null
	public ConfluenceLanguageCheck(ConfluenceConnection conn){
		this.conn=conn
	}

	public check(String pageId,child=false){
		// Get the page and count the words
		def confpage=(new GetPage(this.conn)).execute(pageId,"body.view")[1]
		this.print(confpage)
		if(child){
			this.printchildren(pageId)
		}
	}

	def printchildren(pageId){
		def children=(new GetPageChild(this.conn)).execute(pageId,"")[1]
		for(child in children["results"]){
			this.check(child["id"])
		}
	}

	def print(page){
		println(page["body"]["view"]["value"])
		println("")
		def content=page["body"]["view"]["value"].replaceAll("<code>.*?</code>","").replaceAll("\\<.*?>"," ").replaceAll("\\s+"," ")
		println(content)
		JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
		langTool.activateDefaultPatternRules();
		List<RuleMatch> matches = langTool.check(content);
 		if(!matches.isEmpty()){
 			println("Page " + page["title"])
			for (RuleMatch match : matches) {
  				println("Line " + match.getLine() + ", column " + match.getColumn() + ": " + match.getMessage().replaceAll("\\<.*?>","'"));
  				println("Suggested correction: " + match.getSuggestedReplacements());
  				println("")
			}
		}
	}
}