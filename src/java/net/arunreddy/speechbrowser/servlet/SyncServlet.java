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
package net.arunreddy.speechbrowser.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arunreddy.speechbrowser.sync.SyncAudioFiles;
import net.arunreddy.speechbrowser.sync.SyncAudioFilesTask;
import net.arunreddy.speechbrowser.sync.SyncStatus;

/**
 * @version $Id$
 */
public class SyncServlet extends HttpServlet
{
    static final long serialVersionUID = 1L;

    private static final int BUFSIZE = 4096;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        try {
            ExecutorService executor = (ExecutorService) getServletContext().getAttribute("MY_EXECUTOR");

            SyncStatus syncStatus = new SyncStatus();

            SyncAudioFiles saf = new SyncAudioFiles();

            PrintWriter writer = response.getWriter();

            writer.write(saf.toString());

            // SyncAudioFilesTask task = new SyncAudioFilesTask(syncStatus);
            //
            // FutureTask<Integer> ftask = (FutureTask<Integer>) executor.submit(task);
            //
            // getServletContext().setAttribute("ftask", ftask);
            // getServletContext().setAttribute("syncstatus", syncStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
