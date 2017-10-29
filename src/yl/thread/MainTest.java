package yl.thread;

import java.util.Map;

public class MainTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		MyThreadA ta = new MyThreadA();
		MyThreadB tb = new MyThreadB();
		Thread threadA = new Thread(ta);
		threadA.setName("AAA");
		threadA.start();
		Thread threadB = new Thread(tb);
		threadB.setName("BBB");
		threadB.start();
		// 结束一个线程
		try {
			Thread.sleep(30 * 1000L);
			Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
			for (Thread t : map.keySet()) {
				System.out.println(t.getName());
				if (t.getName().equals("AAA")) {
//					t.interrupt(); // java.lang.InterruptedException: sleep interrupted
					t.stop();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
