package net.arunreddy.speechbrowser

import net.arunreddy.speechbrowser.groovy.AudioFile;
import net.arunreddy.speechbrowser.groovy.Corpus;

class CorpusController {

    Collection corpora
    Corpus corpus
    Collection corpusDirs
    Collection audioFiles
    int fileCount

    Collection audioSegments

    def syncAudioFileService

    def audioSegmenterService

    def index() {
        log.info(params)

        //If Corpus table is empty return.
        if(Corpus.count==0){
            return
        }

        //Get corpus with given Corpus_id
        corpus = Corpus.get(params.id)

        def pid=params.id
        def max=params.max
        def offset = params.offset

        //Fetch list of audio files associated with given corpus.
        //		audioFiles = AudioFile.list(fetch: [corpus : corpus.id])

        if(!(params.containsKey("max")&&params.containsKey("offset"))){
            max=10;
            offset=0;
        }


        audioFiles = AudioFile.findAll("from AudioFile where " +
                "CORPUS_ID  = "+pid+" order by name",
                [max: max.toInteger(), offset: offset.toInteger()])


        fileCount = corpus.audioFiles.size()


    }
    
    def settings(){
        
        
    }

    def synchronize(){

        log.info("sync in progress.")
        try{
            log.info(servletContext)
            syncAudioFileService.syncFiles(servletContext)
        }catch(e){
            render "Error occured.${e}"
        }

        log.info("sync complete..")
    }

    def segment(){
        try{
            audioSegmenterService.segmentAudio();
        }catch(e){
            render "Error occured.${e}"
        }
    }

    def fetchsegments(){
        log.info(params.id)

        //Check if segments for the given id exists.
        //If not create the segments.
        if(AudioFile.get(params.long('id')).audioSegments.empty){
            audioSegments = audioSegmenterService.segmentAudio(params.long('id'));
        }else{
            //Fetch the segments.
            audioSegments = AudioFile.get(params.long('id')).audioSegments
        }

        log.info("Successfully fetched..");


        render(template: "segment", collection:audioSegments, var:"audioSegment")
    }
}
