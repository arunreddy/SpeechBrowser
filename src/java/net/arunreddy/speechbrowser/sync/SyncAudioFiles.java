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

import javax.activation.MimetypesFileTypeMap;

import net.arunreddy.speechbrowser.Corpus;

/**
 * @version $Id$
 */
public class SyncAudioFiles
{

    private SyncAudioFileService syncAudioFileService;

    public static final String DATASET_PATH = System.getenv("speech_data_dir") == null
        ? "/Users/venus/arun/drive/speech-corpus/" : System.getenv("speech_data_dir");

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
        // Audio dataset path.
        File dataSetPath = new File(DATASET_PATH);
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

        System.out.println("Synching files..");
        for (File corpus : dataSetPath.listFiles(ACCEPT_FILTER)) {

            // Check if corpus exists.
            Corpus corpus_db = (Corpus) syncAudioFileService.getCorpus(corpus.getName());

            System.out.println(">>"+corpus_db.getName());

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
