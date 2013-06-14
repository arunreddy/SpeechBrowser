// Place your Spring DSL code here
import net.arunreddy.speechbrowser.bridge.AudioFileSegmenter;
import net.arunreddy.speechbrowser.sync.SyncAudioFileService;
import net.arunreddy.speechbrowser.sync.SyncAudioFiles


beans = {
	syncAudioFiles(SyncAudioFiles){ syncAudioFileService = ref("syncAudioFileService") }

	segmentAudioFiles(AudioFileSegmenter){ audioSegmenterService = ref("audioSegmenterService") }
}
