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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @version $Id$
 */
public class ExecutorContextListener implements ServletContextListener
{
    private ExecutorService executor;

    public void contextInitialized(ServletContextEvent arg0)
    {
        ServletContext context = arg0.getServletContext();
        int nr_executors = 1;
        ThreadFactory daemonFactory = new DaemonThreadFactory();
        try {
            nr_executors = Integer.parseInt(context.getInitParameter("nr-executors"));
        } catch (NumberFormatException ignore) {
        }

        if (nr_executors <= 1) {
            executor = Executors.newSingleThreadExecutor(daemonFactory);
        } else {
            executor = Executors.newFixedThreadPool(nr_executors, daemonFactory);
        }
        context.setAttribute("MY_EXECUTOR", executor);
    }

    public void contextDestroyed(ServletContextEvent arg0)
    {
        ServletContext context = arg0.getServletContext();
        executor.shutdownNow(); // or process/wait until all pending jobs are done
    }
    
    
   

}
