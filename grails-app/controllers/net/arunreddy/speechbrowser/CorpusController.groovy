package net.arunreddy.speechbrowser

class CorpusController {

	Collection corpora
	Corpus corpus
	Collection corpusDirs
	Collection audioFiles
	int fileCount

	def syncAudioFileService

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
}
