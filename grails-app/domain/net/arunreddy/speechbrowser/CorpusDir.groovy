package net.arunreddy.speechbrowser

import java.util.Date;

class CorpusDir {

	String path
	String name
	Date dateCreated
	Date lastUpdated
    Collection audioFiles
	
	static hasMany = [audioFiles: AudioFile]
	static belongsTo = [corpus: Corpus]

	static constraints = {
		path(blank: false)
		name(blank: false)

	}

	static mapping = { autoTimestamp true }
}
