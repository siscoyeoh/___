package yl.thread;

import java.util.Date;

public class MyThreadB implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5 * 1000L);
				System.out.println("-----–›√ﬂ5√Î:" + new Date());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
