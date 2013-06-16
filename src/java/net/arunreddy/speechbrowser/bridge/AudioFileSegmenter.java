/**
 * 
 */
package net.arunreddy.speechbrowser.bridge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.arunreddy.speechbrowser.groovy.AudioSegment;
import net.arunreddy.speechbrowser.groovy.AudioSegmenterService;

/**
 * @author arun
 * 
 */
public class AudioFileSegmenter {

	private AudioSegmenterService audioSegmenterService;

	public List<AudioSegment> segmentAudio(
			net.arunreddy.speechbrowser.groovy.AudioFile gAudioFile) {

		List<AudioSegment> audioSegments = new ArrayList<AudioSegment>();
		String datasetRoot = System.getenv("speech_data_dir");
		File audioFile = new File(datasetRoot, gAudioFile.getPath());
		net.arunreddy.speech.AudioFile sAudioFile = new net.arunreddy.speech.AudioFile();
		sAudioFile.setBitDepth(gAudioFile.getBitDepth());
		sAudioFile.setChannels(gAudioFile.getChannels());
		sAudioFile.setDuration(gAudioFile.getDuration() * 1000);
		sAudioFile.setFrames(gAudioFile.getFrames());
		sAudioFile.setPath(audioFile.toURI());
		sAudioFile.setSampleRate(gAudioFile.getSampleRate());
		sAudioFile.setName(gAudioFile.getName());
		sAudioFile.setMimetype(gAudioFile.getMimetype());

		System.out.println("Successfully set all the required params.");

		net.arunreddy.speech.segmenter.AudioFileSegmenter fileSegmenter = new net.arunreddy.speech.segmenter.AudioFileSegmenter();

		try {
			List<net.arunreddy.speech.AudioSegment> segments = fileSegmenter
					.segmentAudioFile(sAudioFile);

			for (int i = 0; i < segments.size(); i++) {
				net.arunreddy.speech.AudioSegment audioSegment=segments.get(i);
				if (audioSegment != null) {
					AudioSegment segment=new AudioSegment();
					segment.setDuration(audioSegment.getDuration());
					segment.setFrames(audioSegment.getFrames());
					segment.setName(audioSegment.getName());
					String path=audioSegment.getPath().toURL().getPath();
					segment.setPath(path.replaceAll(datasetRoot+"/", ""));
					segment.setAudioFile(gAudioFile);
					segment.setSegmentOrder(i);
					this.audioSegmenterService.updateOrSave(segment);
					audioSegments.add(segment);
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return audioSegments;
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
