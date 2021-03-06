/**
 * 
 */
package net.arunreddy.speechbrowser.sync;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.activation.MimetypesFileTypeMap;

import net.arunreddy.speech.SpeechToText;
import net.arunreddy.speech.audio.type.WavFile;
import net.arunreddy.speechbrowser.groovy.AudioFile;
import net.arunreddy.speechbrowser.groovy.Corpus;
import net.arunreddy.speechbrowser.groovy.sync.SyncAudioFileService;

/**
 * @author arun
 */
public class SyncAudioFilesTask implements Callable<Integer>
{

    private SyncAudioFileService syncAudioFileService;

    public static final String DATASET_PATH = System.getenv("speech_data_dir");

    private SyncStatus syncStatus;

    private SpeechToText stt;

    private URL configURL;

    private Corpus corpus;

    private File corpusFile;

    private static final FilenameFilter ACCEPT_FILTER = new FilenameFilter()
    {

        @Override
        public boolean accept(File dir, String name)
        {
            if (name.startsWith(".")) {
                return false;
            } else {
                return true;
            }
        }
    };

    public SyncAudioFilesTask(SyncStatus syncStatus, Corpus corpus, File corpusFile)
    {
        this(syncStatus, null, corpus, corpusFile);

    }

    public SyncAudioFilesTask(SyncStatus syncStatus, URL configURL, Corpus corpus, File corpusFile)
    {
        this.syncStatus = syncStatus;
        System.out.println(syncStatus.getSyncAudioFileService());
        this.syncAudioFileService = syncStatus.getSyncAudioFileService();
        this.configURL = configURL;
        this.corpus = corpus;
        this.corpusFile = corpusFile;

        if (configURL != null) {
            stt = new SpeechToText(configURL);
        }
    }

    /**
     * @return the syncStatus
     */
    public SyncStatus getSyncStatus()
    {
        return syncStatus;
    }

    public void walkAndUpdate(File dir, String path, Corpus corpus_db)
    {

        for (File file : dir.listFiles(ACCEPT_FILTER)) {

            if (file == null || file.getName().contains("segment")) {
                continue;
            }

            // Is directory - recurse
            if (file.isDirectory()) {
                walkAndUpdate(file, path + File.separatorChar + file.getName(), corpus_db);
            } else {
                if (validAudioFile(file.getName())) {
                    // Check if file exists.
                    AudioFile audioFile = (AudioFile) syncAudioFileService.getAudioFile(file.getName(), path);

                    // If exists ignore it.
                    if (audioFile != null) {

                        // else Create a new object and save it to database.
                    } else {
                        audioFile = new AudioFile();
                        audioFile.setName(file.getName());

                        try {

                            path = file.toURI().toURL().getPath();

                            audioFile.setPath(path.replaceAll(DATASET_PATH + "/", ""));

                            // Set audio properties.
                            // Open the wav file specified as the first argument
                            WavFile wavFile = WavFile.openWavFile(file);

                            long noOfFrames = wavFile.getNumFrames();
                            long bitRate = wavFile.getSampleRate();

                            long numFrames = wavFile.getNumFrames();
                            int numChannels = wavFile.getNumChannels();
                            int validBits = wavFile.getValidBits();
                            long sampleRate = wavFile.getSampleRate();

                            double duration = (((double) numFrames / sampleRate));

                            audioFile.setChannels(numChannels);
                            audioFile.setSampleRate((int) sampleRate);
                            audioFile.setFrames(noOfFrames);
                            audioFile.setDuration(duration);
                            audioFile.setBitDepth(validBits);

                            if (stt != null) {
                                String transcription = stt.speechToText(file.toURI().toURL());
                                audioFile.setUtterance(transcription);
                            }

                            audioFile.setMimetype(file.toURI().toURL().openConnection().getContentType());
                        } catch (MalformedURLException e) {

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        audioFile.setCorpus(corpus_db);
                        syncStatus.increment();
                        syncAudioFileService.updateOrSave(audioFile);
                    }
                }
            }

        }

        // Is directory.
    }

    /**
     * @return the syncAudioFileService
     */
    public SyncAudioFileService getSyncAudioFileService()
    {
        return syncAudioFileService;
    }

    /**
     * @param syncAudioFileService the syncAudioFileService to set
     */
    public void setSyncAudioFileService(SyncAudioFileService syncAudioFileService)
    {
        this.syncAudioFileService = syncAudioFileService;
    }

    public boolean validAudioFile(String fileName)
    {
        return fileName
            .matches("^([a-zA-Z0-9_-]+)\\.[.avi|.AVI|.WMV|.wmv|.wav|.WAV|.mpg|.MPG|.mid|.MID|.asf|.ASF|.mpeg|.MPEG|.mp3]*$");
    }

    @Override
    public Integer call() throws Exception
    {

        System.out.println("<<<CALLED>>>");
        walkAndUpdate(corpusFile, corpusFile.getName(), corpus);

        return 0;
    }

}
