package yl.thread;

import java.util.Date;

public class MyThreadA implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3 * 1000L);
				System.out.println("����3��:" + new Date());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
