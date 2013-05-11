package net.tdu.thread.t01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * 线程池
 * 
 * @author tangdu
 * 
 * @time 2013-3-27 上午9:03:46
 */
public class ThreadPool {

	@Test
	public void execute() {
		// 创建固定大小的线程池
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		TaskThread thread1 = new TaskThread();
		TaskThread thread2 = new TaskThread();
		TaskThread thread3 = new TaskThread();
		TaskThread thread4 = new TaskThread();

		/**
		 * 向线程池提交任务 execute方法 没有返回值，无法得到线程执行情况 submit方法 返回一个Future,通过它的get()
		 * 方法能得到返回值，但是get方法会阻塞直到任务完成。 而使用get(long timeout, TimeUnit
		 * unit)方法则会阻塞一段时间后立即返回，这时有可能任务没有执行完。
		 */
		executorService.execute(thread1);
		executorService.execute(thread2);
		executorService.execute(thread3);
		executorService.execute(thread4);

		executorService.shutdown();
	}

	@Test
	public void future() {
		// 创建固定大小的线程池
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		TaskThread thread1 = new TaskThread();
		TaskThread thread2 = new TaskThread();
		TaskThread thread3 = new TaskThread();
		TaskThread thread4 = new TaskThread();

		/**
		 * 向线程池提交任务 execute方法 没有返回值，无法得到线程执行情况 submit方法 返回一个Future,通过它的get()
		 * 方法能得到返回值，但是get方法会阻塞直到任务完成。 而使用get(long timeout, TimeUnit
		 * unit)方法则会阻塞一段时间后立即返回，这时有可能任务没有执行完。
		 */
		Future<Object> future1=(Future<Object>) executorService.submit(thread1);
		Future<Object> future2=(Future<Object>) executorService.submit(thread2);
		Future<Object> future3=(Future<Object>) executorService.submit(thread3);
		Future<Object> future4=(Future<Object>) executorService.submit(thread4);
		
		try {
			 Object s = future3.get();
			 System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
			executorService.shutdown();
		}
		executorService.shutdown();
	}
}
