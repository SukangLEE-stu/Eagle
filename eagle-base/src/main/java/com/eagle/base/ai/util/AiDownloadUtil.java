package com.eagle.base.ai.util;

import java.util.concurrent.ThreadPoolExecutor;

public class AiDownloadUtil {
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            3,
            60,
            java.util.concurrent.TimeUnit.SECONDS,
            new java.util.concurrent.LinkedBlockingQueue<>()
    );

    public static void run(Runnable runnable) {
        executor.execute(runnable);
    }
}
