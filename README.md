# confluence-client-groovy
A Groovy client for the Confluence REST API that helps you working around some of the lacking features in Confluence.
Like deleting a page tree, add labels to a bunch of pages, batch creation of pages, ...



# Usage

First make a connection with Confluence

    def connection=new ConfluenceConnection()
    connection.createConnection("http://confluencehost/confluence/","username","password")

Then you can use that connection to execute a simple command:

    def wordcount=new ConfluenceWordCount(connection)
    wordcount.count("16451581")

Or you use one of the more advanced commands:

    def deletepagetree = new ConfluenceDeletePageTree(connection)
    deletepagetree.delete("ds","Page 1")

# License 

This code is provided as is, it is not supported and surely has bugs.
Here's the license: http://creativecommons.org/licenses/by-nc-sa/4.0/
