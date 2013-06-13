package net.arunreddy.speechbrowser

class Corpus {

	String path
	String name
    String description
	Date dateCreated
	Date lastUpdated
	Collection corpusDirs
	
	static hasMany = [audioFiles: AudioFile]
	
    static constraints = {
		path(blank: false)
		name(blank: false)
    }
	
	static mapping = {
		autoTimestamp true
	}
}
