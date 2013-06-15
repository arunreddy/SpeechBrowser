package net.arunreddy.speechbrowser.groovy

import net.arunreddy.speechbrowser.bridge.AudioFileSegmenter;
import net.arunreddy.speechbrowser.groovy.AudioFile;
import net.arunreddy.speechbrowser.groovy.Corpus;

import org.springframework.transaction.annotation.Transactional

class AudioSegmenterService {
	
	@Transactional
	def segmentAudio(id){
		log.info("Segmenting audio..")
		def audioSegmenterObj=new AudioFileSegmenter();
		audioSegmenterObj.setAudioSegmenterService(this);
		AudioFile af=AudioFile.get(id)		
		Collection audioSegments = audioSegmenterObj.segmentAudio(af)
		return audioSegments
	}





	@Transactional(readOnly = true)
	def getCorpus(name){

		log.info("Trying to find corpus by name: "+name)
		return Corpus.findByName(name)
	}

	@Transactional
	def updateOrSave(object){
		try{
			if (!object.save()) {
				object.errors.each { println it }
			}
		}catch(e){
			log.error("Error in saving object "+object+"  : ${e}");
		}
	}


	@Transactional
	def getAudioFile(name,path) {
		return AudioFile.findByNameAndPath(name,path)
	}
}
