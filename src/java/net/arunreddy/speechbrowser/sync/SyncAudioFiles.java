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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

import javax.activation.MimetypesFileTypeMap;

import net.arunreddy.speechbrowser.groovy.Corpus;
import net.arunreddy.speechbrowser.groovy.sync.SyncAudioFileService;

import org.apache.catalina.core.ApplicationContextFacade;

/**
 * @version $Id$
 */
public class SyncAudioFiles
{
    public static final String DATASET_PATH = System.getenv("speech_data_dir");
    public static final String CONFIGS_PATH = System.getenv("configs_dir");

    
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

    private SyncAudioFileService syncAudioFileService;

    public void synchronizeAudioFiles(ApplicationContextFacade servletContext)
    {

        ExecutorService executor = (ExecutorService) servletContext.getAttribute("MY_EXECUTOR");

        List<SyncStatus> syncStatusList = null;
        
        if(servletContext.getAttribute("syncStatusList")!=null){
            syncStatusList = (List<SyncStatus>) servletContext.getAttribute("syncStatusList");
        }else{
            syncStatusList= new ArrayList<SyncStatus>();
        }
        
        
        
        // Audio dataset path.

        try {
            File dataSetPath = new File(DATASET_PATH);
            MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

            for (File corpus : dataSetPath.listFiles(ACCEPT_FILTER)) {

                if (corpus == null || corpus.getName().contains("segment")) {
                    continue;
                }
                // Check if corpus exists.
                Corpus corpus_db = (Corpus) syncAudioFileService.getCorpus(corpus.getName());

                // If corpus doesnt exist in the db, add an entry and validate.
                if (corpus_db == null) {
                    corpus_db = new Corpus();
                    corpus_db.setName(corpus.getName());
                    corpus_db.setPath(corpus.getName());
                    corpus_db.setDescription("Add description " + corpus.getName());

                    syncAudioFileService.updateOrSave(corpus_db);
                    corpus_db = (Corpus) syncAudioFileService.getCorpus(corpus.getName());
                }

                SyncStatus syncStatus = new SyncStatus();

                SyncVolumeEstimator estimator = new SyncVolumeEstimator();
                estimator.setSyncAudioFileService(syncAudioFileService);
                int totalFiles = estimator.estimateVolume();
                syncStatus.setTotalFiles(totalFiles);
                
                File configFile = new File(CONFIGS_PATH+File.separator+"sphinx4"+File.separator+corpus.getName()+File.separator+"config.xml");
                System.out.println(executor);

                SyncAudioFilesTask task = new SyncAudioFilesTask(syncStatus,configFile.toURI().toURL(),corpus_db,corpus);
                task.setSyncAudioFileService(syncAudioFileService);

                FutureTask<Integer> ftask = (FutureTask<Integer>) executor.submit(task);

                syncStatusList.add(syncStatus);
            }
            
            servletContext.setAttribute("syncStatusList",syncStatusList);
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

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

}
