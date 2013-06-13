package net.arunreddy.speechbrowser

class CorpusController {

	Corpus corpus
	Collection corpusDirs
	Collection audioFiles
    
    def syncAudioFileService
	
    def index() { 
		log.info(params)
		corpus = Corpus.get(params.id)
		log.info(corpus)
		
		
		corpusDirs=corpus.corpusDirs
		log.info(corpusDirs)
		
		corpusDir = CorpusDir.get(params.id2)
		log.info(corpusDir)
		
		audioFiles = corpusDir.audioFiles
		log.info(audioFiles)
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
