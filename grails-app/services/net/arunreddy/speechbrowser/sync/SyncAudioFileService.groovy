package net.arunreddy.speechbrowser.sync

import net.arunreddy.speechbrowser.Corpus

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
    
    
}
