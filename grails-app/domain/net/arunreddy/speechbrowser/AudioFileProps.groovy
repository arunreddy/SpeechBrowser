package net.arunreddy.speechbrowser

class AudioFileProps {

	int channels
	int sampleRate
	int bitDepth
	long frames
	int duration
	Date lastUpdated
	
	static belongsTo = [audioFile: AudioFile]
	
    static constraints = {
		
    }
}
