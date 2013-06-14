package net.arunreddy.speechbrowser

import net.arunreddy.speechbrowser.groovy.AudioFile;
import net.arunreddy.speechbrowser.groovy.Corpus;

class CorpusController {

	Collection corpora
	Corpus corpus
	Collection corpusDirs
	Collection audioFiles
	int fileCount

	def syncAudioFileService

	def audioSegmenterService

	def index() {
		log.info(params)

		//		corpora = Corpus.all
		corpus = Corpus.get(params.id)
		log.info(corpus)
		audioFiles = AudioFile.list(fetch: [corpus : corpus.id])
		fileCount = corpus.audioFiles.size()


	}

	def synchronize(){

		def textToRender="Synchronizing..."
		try{
			textToRender+=syncAudioFileService.doSomething()
		}catch(e){
			render "Error occured.${e}"
		}
		render textToRender
	}

	def segment(){
		try{
			audioSegmenterService.segmentAudio();
		}catch(e){
			render "Error occured.${e}"
		}
	}
}
