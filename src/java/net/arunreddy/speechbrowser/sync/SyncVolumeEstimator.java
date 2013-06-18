/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package net.arunreddy.speechbrowser.sync;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;

import javax.activation.MimetypesFileTypeMap;

import net.arunreddy.speech.SpeechToText;
import net.arunreddy.speech.audio.type.WavFile;
import net.arunreddy.speechbrowser.groovy.AudioFile;
import net.arunreddy.speechbrowser.groovy.Corpus;
import net.arunreddy.speechbrowser.groovy.sync.SyncAudioFileService;

/**
 * 
 * @version $Id$
 */
public class SyncVolumeEstimator
{

    private SyncAudioFileService syncAudioFileService;

    public static final String DATASET_PATH = System.getenv("speech_data_dir");

    private int counter;

    private static final FilenameFilter ACCEPT_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            if (name.startsWith(".")) {
                return false;
            } else {
                return true;
            }
        }
    };

    
    public int estimateVolume(){
    
        File dataSetPath = new File(DATASET_PATH);
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

        for (File corpus : dataSetPath.listFiles(ACCEPT_FILTER)) {

            if (corpus == null || corpus.getName().contains("segment")) {
                continue;
            }
            // Check if corpus exists.
            Corpus corpus_db = (Corpus) syncAudioFileService.getCorpus(corpus
                    .getName());

            // If corpus doesnt exist in the db, add an entry and validate.
            if (corpus_db == null) {
                walk(corpus, corpus.getName());
            }

            

        }
        return counter;
        
    }
    
    
    public void walk(File dir, String path) {

        for (File file : dir.listFiles(ACCEPT_FILTER)) {

            if (file == null || file.getName().contains("segment")) {
                continue;
            }

            // Is directory - recurse
            if (file.isDirectory()) {
                walk(file, path + File.separatorChar + file.getName());
            } else {
                if (validAudioFile(file.getName())) {
                    // Check if file exists.
                    AudioFile audioFile = (AudioFile) syncAudioFileService
                            .getAudioFile(file.getName(), path);

                    // If exists ignore it.
                    if (audioFile != null) {

                        // else Create a new object and save it to database.
                    } else {
                        counter++;
                    }
                }
            }

        }

        // Is directory.
    }

    /**
     * @return the syncAudioFileService
     */
    public SyncAudioFileService getSyncAudioFileService() {
        return syncAudioFileService;
    }

    /**
     * @param syncAudioFileService
     *            the syncAudioFileService to set
     */
    public void setSyncAudioFileService(
            SyncAudioFileService syncAudioFileService) {
        this.syncAudioFileService = syncAudioFileService;
    }

    public boolean validAudioFile(String fileName) {
        return fileName
                .matches("^([a-zA-Z0-9_-]+)\\.[.avi|.AVI|.WMV|.wmv|.wav|.WAV|.mpg|.MPG|.mid|.MID|.asf|.ASF|.mpeg|.MPEG|.mp3]*$");
    }

}
