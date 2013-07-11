import net.arunreddy.speechbrowser.bridge.AudioFileSegmenter;
import net.arunreddy.speechbrowser.groovy.sync.SyncAudioFileService;
import net.arunreddy.speechbrowser.sync.SyncAudioFiles;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import net.arunreddy.speechbrowser.asr.ASREndPoint;



beans = {
    syncAudioFiles(SyncAudioFiles){ syncAudioFileService = ref("syncAudioFileService") }

    segmentAudioFiles(AudioFileSegmenter){ audioSegmenterService = ref("audioSegmenterService") }

    credentialMatcher(Sha256CredentialsMatcher) { storedCredentialsHexEncoded = true }
}
