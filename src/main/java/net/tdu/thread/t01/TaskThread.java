package net.tdu.thread.t01;

/**
 * 定义一个线程
 * 
 * @author tangdu
 * 
 * @time 2013-3-27 上午9:03:58
 */
public class TaskThread extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在执行.....");
	}

}
