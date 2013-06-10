package net.arunreddy.speechbrowser

class Corpus {

	String path
	String name
	Date dateCreated
	Date lastUpdated
	Collection corpusDirs
	
	static hasMany = [corpusDirs: CorpusDir]
	
    static constraints = {
		path(blank: false)
		name(blank: false)
    }
	
	static mapping = {
		autoTimestamp true
	}
}
