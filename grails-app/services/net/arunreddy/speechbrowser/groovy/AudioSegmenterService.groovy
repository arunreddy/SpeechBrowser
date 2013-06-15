package net.arunreddy.speechbrowser

import net.arunreddy.speechbrowser.bridge.AudioFileSegmenter;
import net.arunreddy.speechbrowser.groovy.AudioFile;
import net.arunreddy.speechbrowser.groovy.Corpus;

import org.springframework.transaction.annotation.Transactional

class AudioSegmenterService {

	@Transactional(readOnly = true)
	def segmentAudio(){
		log.info("Segmenting audio..")
		def audioSegmenterObj=new AudioFileSegmenter();
		audioSegmenterObj.setAudioSegmenterService(this);
		AudioFile af=AudioFile.findById(4252)
		log.info(af)
		audioSegmenterObj.segmentAudio(af)
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
