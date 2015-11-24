package com.advert.cms.task;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;

import org.apache.log4j.Logger;

public final class EduThreadManager implements ThreadManager, LifeCycle {

	private static final Logger logger = Logger.getLogger(EduThreadManager.class);

	/**
	 * 默认线程数
	 */
	private final static int DEFAULT_THREAD_COUNT = Runtime.getRuntime().availableProcessors();

	private final Semaphore semaphore = new Semaphore(0);

	/**
	 * 线程队列
	 */
	private final ConcurrentLinkedQueue<Runnable> tasks = new ConcurrentLinkedQueue<Runnable>();

	/**
	 * 是否关闭
	 */
	private boolean closing = false;

	private int threadCount;

	private ThreadFactory threadFactory;

	/**
	 * 线程池工作队列
	 */
	final private ArrayList<Thread> threads = new ArrayList<Thread>();

	public EduThreadManager() {
		this(DEFAULT_THREAD_COUNT);
	}

	public EduThreadManager(int threadCount) {
		this(threadCount, new EduThreadFactory());
	}

	public EduThreadManager(int threadCount, ThreadFactory threadFactory) {
		this.threadCount = threadCount;
		this.threadFactory = threadFactory;
	}

	@Override
	public void init() {
		if (logger.isDebugEnabled()) {
			logger.debug("开始初始化线程池工作队列...........................");
		}
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						semaphore.acquire();
						if (closing)
							return;
						Runnable task = tasks.poll();
						Thread.sleep(10L);
						if (task != null)
							task.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		int c = 0;
		while (c < threadCount) {
			c += 1;
			Thread t = threadFactory.newThread(runnable);
			threads.add(t);
			t.start();
			if (logger.isDebugEnabled()) {
				logger.debug("初试化工作线程====" + t.getName());
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("结束初始化线程池工作队列...........................");
		}
	}

	@Override
	public void process(Runnable r) {
		tasks.add(r);
		semaphore.release();
	}

	@Override
	public void close() {
		if (logger.isDebugEnabled()) {
			logger.debug("开始关闭线程池工作队列...........................");
		}
		closing = true;
		int c = 0;
		while (c < threadCount) {
			c += 1;
			semaphore.release();
		}
		c = 0;
		Thread ct = Thread.currentThread();
		while (c < threadCount) {
			Thread t = threads.get(c);
			if (ct != t) {
				try {
					t.join();
				} catch (InterruptedException e) {
				}
			}
			c += 1;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("结束关闭线程池工作队列...........................");
		}
	}
}
