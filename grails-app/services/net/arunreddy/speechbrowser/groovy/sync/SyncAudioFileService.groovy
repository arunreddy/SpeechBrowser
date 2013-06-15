package net.arunreddy.speechbrowser.groovy.sync

import net.arunreddy.speechbrowser.groovy.AudioFile
import net.arunreddy.speechbrowser.groovy.Corpus
import net.arunreddy.speechbrowser.sync.SyncAudioFiles;

import org.springframework.transaction.annotation.Transactional

class SyncAudioFileService {

	def serviceMethod() {
	}

	def doSomething(){
		def syncAudioFilesObj = new SyncAudioFiles()
		syncAudioFilesObj.setSyncAudioFileService(this)

		log.info(syncAudioFilesObj.doSomething())
		def strText = syncAudioFilesObj.doSomething()

		return "Am trying to dosomething here.."+strText
	}

	def fetchText(){
		return "This is text from service."
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
