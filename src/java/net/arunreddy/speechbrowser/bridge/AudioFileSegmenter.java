/**
 * 
 */
package net.arunreddy.speechbrowser.bridge;

import java.io.File;
import java.util.List;
import java.net.URL;

import net.arunreddy.speech.SpeechToText;
import net.arunreddy.speechbrowser.AudioSegmenterService;
import net.arunreddy.speechbrowser.bridge.AudioFileSegmenter;
import net.arunreddy.speechbrowser.groovy.AudioFile;

/**
 * @author arun
 * 
 */
public class AudioFileSegmenter {

	private AudioSegmenterService audioSegmenterService;

	public void segmentAudio(
			net.arunreddy.speechbrowser.groovy.AudioFile gAudioFile) {

		String datasetRoot = System.getenv("speech_data_dir");
		File audioFile = new File(datasetRoot,gAudioFile.getPath() + File.separatorChar
				+ gAudioFile.getName());
		net.arunreddy.speech.AudioFile sAudioFile = new net.arunreddy.speech.AudioFile();
		 sAudioFile.setBitDepth(gAudioFile.getBitDepth());
		 sAudioFile.setChannels(gAudioFile.getChannels());
		 sAudioFile.setDuration(gAudioFile.getDuration()*1000);
		 sAudioFile.setFrames(gAudioFile.getFrames());
		 sAudioFile.setPath(audioFile.toURI());

		System.out.println("Successfully set all the required params.");

		net.arunreddy.speech.segmenter.AudioFileSegmenter fileSegmenter = new net.arunreddy.speech.segmenter.AudioFileSegmenter();

		try {
			List<net.arunreddy.speech.AudioSegment> segments = fileSegmenter
					.segmentAudioFile(sAudioFile);

			for (net.arunreddy.speech.AudioSegment audioSegment : segments) {
				if (audioSegment != null) {
					System.out.println(audioSegment.getName());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the audioSegmenterService
	 */
	public AudioSegmenterService getAudioSegmenterService() {
		return audioSegmenterService;
	}

	/**
	 * @param audioSegmenterService
	 *            the audioSegmenterService to set
	 */
	public void setAudioSegmenterService(
			AudioSegmenterService audioSegmenterService) {
		this.audioSegmenterService = audioSegmenterService;
	}

}
