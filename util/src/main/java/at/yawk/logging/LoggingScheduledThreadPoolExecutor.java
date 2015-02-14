/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package at.yawk.logging;

import java.util.concurrent.*;

/**
 * @author yawkat
 */
public abstract class LoggingScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    public LoggingScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    public LoggingScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public LoggingScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public LoggingScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory,
                                              RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    ////////

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t == null) {
            if (r instanceof Future<?> && ((Future) r).isDone()) {
                try {
                    ((Future) r).get();
                } catch (InterruptedException ignored) {
                } catch (CancellationException ignored) {
                } catch (ExecutionException e) {
                    t = e.getCause();
                }
            }
        }
        if (t != null) {
            handleException(Thread.currentThread(), r, t);
        }
    }

    private void handleException(Thread thread, Runnable task, Throwable exception) {
        try {
            onException(thread, task, exception);
        } catch (Throwable ignored) {} // swallow exceptions
    }

    protected abstract void onException(Thread thread, Runnable task, Throwable exception);
}
