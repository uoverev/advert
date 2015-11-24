package com.advert.cms.task;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class EduThreadFactory implements ThreadFactory {
	// 计数器
	private final AtomicInteger count = new AtomicInteger();
	// 默认线程名称
	private static final String DEFAULT_THREAD_NAME = "edu_threads";

	private String threadName;

	public EduThreadFactory() {
		this(DEFAULT_THREAD_NAME);
	}

	public EduThreadFactory(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setName(threadName + count.getAndIncrement());
		t.setDaemon(true);
		return t;
	}
}
