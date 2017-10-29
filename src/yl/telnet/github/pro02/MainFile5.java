package yl.telnet.github.pro02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.io.CopyStreamException;
import org.apache.commons.net.io.Util;
import org.apache.commons.net.telnet.TelnetClient;

public class MainFile5 {
	
	public static void main(String[]a) {
		
		TelnetClient telnet;
		telnet = new TelnetClient();
		try {
//			telnet.connect("rainmaker.wunderground.com", 3000);
			telnet.connect("192.168.8.115", 23);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		InputStream remoteInput = telnet.getInputStream();
		OutputStream remoteOutPut = telnet.getOutputStream();
		InputStream localInput = System.in;
		OutputStream localOutput = System.out;
		IOUtil5.readWrite(remoteInput, remoteOutPut, localInput, localOutput);
		
		try {
			telnet.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.exit(0);
		
	}

}
class IOUtil5 {
	public static final void readWrite(
			final InputStream remoteInput,
			final OutputStream remoteOutPut,
			final InputStream localInput,
			final OutputStream localOutput
			) {
		
		Thread reader;
		Thread writer;
		
		reader = new Thread() {
			@Override
			public void run() {
				int ch;
				try {
					while (!interrupted() && (ch = localInput.read()) != -1) {
						remoteOutPut.write(ch);
						remoteOutPut.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		reader.setName("telnet_reader");
		
		writer = new Thread() {
			@Override
			public void run() {
				try {
					Util.copyStream(remoteInput, localOutput);
				} catch (CopyStreamException e) {
					e.printStackTrace();
				}
			}
		};
		
		reader.setName("telnet_writer");
		
		writer.setPriority(Thread.currentThread().getPriority() + 1);
		writer.start();
		reader.setDaemon(true);
		reader.start();
		
		try {
			writer.join();
			reader.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}