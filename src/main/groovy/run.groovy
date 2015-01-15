import com.pieter.confluence.*
import com.pieter.confluence.command.*

def connection=new ConfluenceConnection()
connection.createConnection("http://confluencehost/confluence/","","")

/*
def createpage=new CreatePage(connection)
createpage.execute("Hallo","ds","lala","21102692")
*/

//def command = new GetPageChild(connection)
//println command.execute("16451581","")[1]["results"][0]

//def wordcount=new ConfluenceWordCount(connection)
//wordcount.count("16451581")

//def langcheck = new ConfluenceLanguageCheck(connection)
//langcheck.check("18913114",false)

/*
def pages=[
	[title:"Page 1",parent:"22021227",children:[
		[title:"Page 2",children:[
			[title:"Page 3"],
			[title:"Page 4"],
			[title:"Page 5"],
		]],
		[title:"Page 6"]
	]]
]
def createpages=new ConfluenceCreatePageTree(connection)
createpages.create("ds",pages);
*/

/*
def deletepage=new DeletePage(connection)
def del=deletepage.delete("22348937")
println(del)
*/

/*
def findpage=new FindPage(connection)
def page=findpage.execute("ds","Page 1")
println(page)
*/

/*
def deletepagetree = new ConfluenceDeletePageTree(connection)
deletepagetree.delete("ds","Page 1")
*/

/*
def copypagetree = new ConfluenceCopyPageTree(connection)
copypagetree.copy("ds","Page 1","otherspacekey")
*/

/*
addlabelpagetree=new ConfluenceAddLabelPageTree(connection)
addlabelpagetree.add("ds","Page 1",["label1","label2"])
*/

/*
def createpage=new CreatePage(connection)
content="""
<ac:layout>
  <ac:layout-section ac:type="two_equal">
    <ac:layout-cell>
      <h1>Header 1</h1>
      <p>
        
      </p>
      <h1>Header 2</h1>
      <p>
        
      </p>
      <p> </p>
    </ac:layout-cell>
    <ac:layout-cell>
      <ac:structured-macro ac:name="panel">
        <ac:parameter ac:name="title">Contents of this page</ac:parameter>
        <ac:rich-text-body>
          <p>
            <ac:structured-macro ac:name="toc">
              <ac:parameter ac:name="maxLevel">2</ac:parameter>
            </ac:structured-macro>
          </p>
        </ac:rich-text-body>
      </ac:structured-macro>
    </ac:layout-cell>
  </ac:layout-section>
  <ac:layout-section ac:type="single">
    <ac:layout-cell>
      <h1>Title</h1>
    </ac:layout-cell>
  </ac:layout-section>
</ac:layout>

"""
createpage.execute("Page 1","ds",content,"22020753")
*/

/*
def rootpages = new ConfluenceFindRootPages(connection)
def deletepage=new DeletePage(connection)

def pages=rootpages.execute("ds")
for(item in pages){
	if(item["title"]!="Page 1" && item["title"]!="Page 2"){
		println(item["title"])
		def del=deletepage.delete(item["id"])
		println(del)
	}
}
*/
