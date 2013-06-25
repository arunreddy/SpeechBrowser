package net.arunreddy.speechbrowser.groovy

import java.util.Date;

class AudioFile {

	String path
	String name
	String mimetype
	int channels
	int sampleRate
	int bitDepth
	long frames
	double duration
	Date dateCreated
	Date lastUpdated
	String utterance
	Corpus corpus

	Collection audioSegments

	static belongsTo = [corpus: Corpus]

	static hasMany = [audioSegments:AudioSegment]

	static constraints = {
		path(blank: false)
		name(blank: false)
		mimetype(blank:false)
		utterance nullable: true
	}

	static mapping = {
		autoTimestamp true
		utterance type: 'text'
	}
}
