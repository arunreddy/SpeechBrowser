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
import java.io.IOException;
import java.net.MalformedURLException;

import javax.activation.MimetypesFileTypeMap;

import net.arunreddy.speech.audio.wav.WavFile;
import net.arunreddy.speechbrowser.AudioFile;
import net.arunreddy.speechbrowser.Corpus;

/**
 * @version $Id$
 */
public class SyncAudioFiles
{

    private SyncAudioFileService syncAudioFileService;

    public static final String DATASET_PATH = System.getenv("speech_data_dir");

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

    public void synchronizeAudioFiles()
    {
    	
    	System.out.println("Syncing files.."+DATASET_PATH);
        // Audio dataset path.
        File dataSetPath = new File(DATASET_PATH);
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

        System.out.println("Syncing files.."+dataSetPath);
        for (File corpus : dataSetPath.listFiles(ACCEPT_FILTER)) {

            // Check if corpus exists.
            Corpus corpus_db = (Corpus) syncAudioFileService.getCorpus(corpus.getName());

            // If corpus doesnt exist in the db, add an entry and validate.
            if (corpus_db == null) {
                corpus_db = new Corpus();
                corpus_db.setName(corpus.getName());
                corpus_db.setPath(corpus.getName());
                corpus_db.setDescription("Add description " + corpus.getName());

                System.out.println("Adding new corpus..");
                syncAudioFileService.updateOrSave(corpus_db);
                corpus_db = (Corpus) syncAudioFileService.getCorpus(corpus.getName());
            }

            walkAndUpdate(corpus, corpus.getName(), corpus_db);

        }
        // Walk through all the files.

        // Add to the database.

        // System.out.println(corpus);
        // if (corpus.isDirectory()) {
        //
        // for (File file : corpus.listFiles(ACCEPT_FILTER)) {
        //
        //
        //
        // if (file.isDirectory()) {
        // //Enter the directory and add files to the db.
        // for (File sfile : file.listFiles(ACCEPT_FILTER)) {
        // if(validAudioFile(sfile.getName())){
        // AudioFile af=new AudioFile();
        //
        // }
        //
        // }
        // }
        //
        // // If file is not directory add it to the db.
        //
        //
        //
        // }
        // }

    }

    public void walkAndUpdate(File dir, String path, Corpus corpus_db)
    {

        for (File file : dir.listFiles(ACCEPT_FILTER)) {

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
                        audioFile.setPath(path);

                        try {
                        	
                        	//Set audio properties.
                            // Open the wav file specified as the first argument
                            WavFile wavFile = WavFile.openWavFile(file);

                            long noOfFrames = wavFile.getNumFrames();
                            long bitRate = wavFile.getSampleRate();

                            long numFrames = wavFile.getNumFrames();
                            int numChannels = wavFile.getNumChannels();
                            int validBits = wavFile.getValidBits();
                            long sampleRate = wavFile.getSampleRate();
                            
                            int duration = (int)(((double)numFrames/sampleRate)*1000);
                        	
                        	audioFile.setChannels(numChannels);
                        	audioFile.setSampleRate((int)sampleRate);
                        	audioFile.setFrames(noOfFrames);
                        	audioFile.setDuration(duration);
                        	
                        	
                            audioFile.setMimetype(file.toURI().toURL().openConnection().getContentType());
                        } catch (MalformedURLException e) {
                           
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        audioFile.setCorpus(corpus_db);
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

    public String doSomething()
    {
        synchronizeAudioFiles();
        return "Am printing lots of shit here... and you know what it works.!!" + syncAudioFileService.fetchText();
    }

    public boolean validAudioFile(String fileName)
    {
        return fileName
            .matches("^([a-zA-Z0-9_-]+)\\.[.avi|.AVI|.WMV|.wmv|.wav|.WAV|.mpg|.MPG|.mid|.MID|.asf|.ASF|.mpeg|.MPEG|.mp3]*$");
    }

}
