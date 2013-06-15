package net.arunreddy.speechbrowser.groovy

class AudioSegment {

	String path
	String name
	long frames
	double duration
	Date dateCreated
	Date lastUpdated
	AudioFile audioFile
	int segmentOrder

	static belongsTo = [audioFile: AudioFile]

	static constraints = {
		path(blank: false)
		name(blank: false)
	}

	static mapping = { autoTimestamp true }
}
