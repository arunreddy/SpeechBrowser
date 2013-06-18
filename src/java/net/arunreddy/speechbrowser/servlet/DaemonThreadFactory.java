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

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * Hands out threads from the wrapped threadfactory with setDeamon(true), so the
 * threads won't keep the JVM alive when it should otherwise exit.
 */
public class DaemonThreadFactory implements ThreadFactory {

    private final ThreadFactory factory;

    /**
     * Construct a ThreadFactory with setDeamon(true) using
     * Executors.defaultThreadFactory()
     */
    public DaemonThreadFactory() {
        this(Executors.defaultThreadFactory());
    }

    /**
     * Construct a ThreadFactory with setDeamon(true) wrapping the given factory
     * 
     * @param thread
     *            factory to wrap
     */
    public DaemonThreadFactory(ThreadFactory factory) {
        if (factory == null)
            throw new NullPointerException("factory cannot be null");
        this.factory = factory;
    }

    public Thread newThread(Runnable r) {
        final Thread t = factory.newThread(r);
        t.setDaemon(true);
        return t;
    }
}