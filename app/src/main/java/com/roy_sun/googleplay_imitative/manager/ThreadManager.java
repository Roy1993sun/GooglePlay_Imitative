package com.roy_sun.googleplay_imitative.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 管理线程池; 一个app中有多个thread pool. 每个thread做自己的业务.
 * Created by Roy_Sun on 2016/2/10 0010.
 */
public class ThreadManager {

    private static ThreadPoolProxy mNormalPool = new ThreadPoolProxy(1, 3, 5 * 1000);

    public static ThreadPoolProxy getNormalPool() {
        return mNormalPool;
    }

    public static class ThreadPoolProxy {
        private final int                mCorePoolSize;
        private final int                mMaximumPoolSize;
        private final long               mKeepAliveTime;
        private       ThreadPoolExecutor mPool;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.mCorePoolSize = corePoolSize;
            this.mMaximumPoolSize = maximumPoolSize;
            this.mKeepAliveTime = keepAliveTime;
        }

        public void execute(Runnable task) {
            initPool();

            mPool.execute(task);

        }

        public Future<?> submit(Runnable task) {
            initPool();
            return mPool.submit(task);
        }

        public void remove(Runnable task) {
            if (mPool != null && !mPool.isShutdown()) {
                mPool.getQueue()
                     .remove(task);
            }
        }

        private void initPool() {
            if (mPool == null || mPool.isShutdown()) {
                //                int corePoolSize = 1; //核心线程池大小
                //                int maximumPoolSize = 3; // 最大线程池大小,最多可以执行3个线程
                //                long keepAliveTime = 5 * 1000; // 等待新的任务的时间,如果未等到新的任务则销毁
                TimeUnit unit = TimeUnit.MILLISECONDS; // 单位
                BlockingQueue<Runnable> workQueue = null; // 任务队列
                workQueue = new LinkedBlockingDeque();
                ThreadFactory threadFactory = Executors.defaultThreadFactory();
                RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();// 异常捕获


                mPool = new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, mKeepAliveTime,
                                               unit, workQueue, threadFactory, handler);
            }
        }
    }
}
