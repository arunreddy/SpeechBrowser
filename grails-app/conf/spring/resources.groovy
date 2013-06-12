// Place your Spring DSL code here
import net.arunreddy.speechbrowser.sync.SyncAudioFileService;
import net.arunreddy.speechbrowser.sync.SyncAudioFiles


beans = {
    synchAudioFiles(SyncAudioFiles){
        syncAudioFileService = ref("syncAudioFileService")
    }
}
