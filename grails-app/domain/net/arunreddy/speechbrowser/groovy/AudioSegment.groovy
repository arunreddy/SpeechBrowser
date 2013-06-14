package net.arunreddy.speechbrowser.groovy

import java.util.Date;

class AudioSegment {


	String path
	String name
	int channels
	int sampleRate
	int bitDepth
	long frames
	int duration
	Date dateCreated
	Date lastUpdated
	AudioFile audioFile
	int order

	static belongsTo = [audioFile: AudioFile]

	static constraints = {
		path(blank: false)
		name(blank: false)
		order(blank:false)
	}

	static mapping = { autoTimestamp true }
}
