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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

import net.arunreddy.speechbrowser.groovy.sync.SyncAudioFileService;

import org.apache.catalina.core.ApplicationContextFacade;

/**
 * @version $Id$
 */
public class SyncAudioFiles
{

    private SyncAudioFileService syncAudioFileService;

    public void synchronizeAudioFiles(ApplicationContextFacade servletContext)
    {

        System.out.println(servletContext);
        System.out.println(syncAudioFileService);

        ExecutorService executor = (ExecutorService) servletContext.getAttribute("MY_EXECUTOR");

        SyncStatus syncStatus = new SyncStatus();

        
        SyncVolumeEstimator estimator=new SyncVolumeEstimator();
        estimator.setSyncAudioFileService(syncAudioFileService);
        int totalFiles =estimator.estimateVolume();
        syncStatus.setTotalFiles(totalFiles);

        
        
        SyncAudioFilesTask task = new SyncAudioFilesTask(syncStatus);
        task.setSyncAudioFileService(syncAudioFileService);

        FutureTask<Integer> ftask = (FutureTask<Integer>) executor.submit(task);

        servletContext.setAttribute("ftask", ftask);
        servletContext.setAttribute("syncstatus", syncStatus);
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
