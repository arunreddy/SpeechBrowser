package net.arunreddy.speechbrowser.groovy.sync

import net.arunreddy.speechbrowser.groovy.AudioFile
import net.arunreddy.speechbrowser.groovy.Corpus
import net.arunreddy.speechbrowser.sync.SyncAudioFiles;

import org.springframework.transaction.annotation.Transactional

class SyncAudioFileService {

    static transactional = true

    def serviceMethod() {
    }


    @Transactional
    def syncFiles(servletContext){
        def syncAudioFilesObj = new SyncAudioFiles()
        syncAudioFilesObj.setSyncAudioFileService(this)

        def strText = syncAudioFilesObj.synchronizeAudioFiles(servletContext)

        return "Done"
    }

    @Transactional(readOnly = true)
    def getCorpus(name){

        log.info("Trying to find corpus by name: "+name)
        return Corpus.findByName(name)
    }


    @Transactional
    def updateOrSave(object){
        AudioFile.withTransaction {
            try{
                if (!object.save()) {
                    object.errors.each { println it }
                }
            }catch(e){
                log.error("Error in saving object "+object+"  : ${e}");
            }
        }
    }


    @Transactional
    def getAudioFile(name,path) {
        return AudioFile.findByNameAndPath(name,path)
    }
}
