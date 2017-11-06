
package yl.smb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

public class Upload42smb {

	private static int buffer_size = 1024;
	private static boolean deleteOldFile = false;
	private static boolean loopit = false;
	private static boolean sleepit = false;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
	private static String info = 
//			"Please type in parameter:\n\nargs 0:\nu\tupload files\nd\tdelete files\np\tdisplay files"
//			+ "\n\n[args 1: buffer_size, default 256]\n\n[args 2: deleteOldFile, default false]";
	"Please type in parameter:\n\ndisplay: p yyyymmdd\n\ndelete: d yyyymmdd \n\nupload: u yyyymmdd [buffer_size]";
	
	public static void main(String[] args) {
//		args = new String[] { "u", "2048" };
//		args = new String[] { "p", "20171030" };
		do {
			doit(args);
			if (sleepit) {
				try {
					System.out.println("sleep......");
					Thread.sleep(15 * 60 * 1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (loopit);
//		test();
	}
	
	static void test() {
		String url = "smb://192.168.8.42/smbshared/20171031/";
		NtlmPasswordAuthentication authentication = 
				new NtlmPasswordAuthentication(".", "pc66", "pc66");
		try {
			SmbFile folder = new SmbFile(url, authentication);
			SmbFile[] list = folder.listFiles();
			for (int i = 0; i < list.length; i++) {
				SmbFile file = list[i];
				System.out.println(
						file.getPath()
//						file.getName() + ", " + 
//						file.lastModified() + ", " + 
//						format.format(new Date(file.lastModified()))
						);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void deleteFiles(String url, NtlmPasswordAuthentication authentication) {
		try {
			SmbFile folder = new SmbFile(url, authentication);
			SmbFile[] list = folder.listFiles();
			for (SmbFile file : list) {
				file.delete();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void display(String yyyymmdd) {
		String url = "smb://192.168.8.42/smbshared/" + yyyymmdd + "/";
		NtlmPasswordAuthentication authentication = 
				new NtlmPasswordAuthentication(".", "pc66", "pc66");
		try {
			SmbFile folder = new SmbFile(url, authentication);
			SmbFile[] list = folder.listFiles();
			for (int i = 0; i < list.length; i++) {
				SmbFile file = list[i];
				System.out.println(
						file.getName() + ", " + 
						file.lastModified() + ", " + 
						format.format(new Date(file.lastModified()))
						);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void doit(String[] args) {
		try {
			Date start = new Date();
			if (args != null && args.length >= 1) {
				String flag = args[0];
				if ("u".equals(flag.toLowerCase())) upload();
				else if ("d".equals(flag.toLowerCase())) deleteOnly();
				else if ("p".equals(flag.toLowerCase())) {
					if (args.length > 1) {
						String yyyymmdd = args[1];
						display(yyyymmdd);
					}
					else {
						System.out.println(info);
					}
				}
				else {
					System.out.println(info);
					return;
				}
				if (args.length >= 2) {
					try {
						buffer_size = Integer.parseInt(args[1].trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.out.println("set buffer_size default: 256");
					}
				}
				if (args.length >= 3) {
					try {
						String deleteOldFileStr = args[2].trim();
						if ("true".equals(deleteOldFileStr)) deleteOldFile = true;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						System.out.println("set buffer_size default: 256");
					}
				}
			}
			else {
				System.out.println(info);
			}
			Date end = new Date();
			System.out.println("seconds wasted:" + (end.getTime() - start.getTime()) / 1000L);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void deleteOnly() {
		String url = "smb://192.168.8.42/smbshared/";
		NtlmPasswordAuthentication authentication = 
				new NtlmPasswordAuthentication(".", "pc66", "pc66");
		// delete old files
		deleteFiles(url, authentication);
	}
	
	private static void upload() {
		loopit = true;
		sleepit = true;
		try {
			String url = "smb://192.168.8.42/smbshared/";
			NtlmPasswordAuthentication authentication = 
					new NtlmPasswordAuthentication(".", "pc66", "pc66");
			// delete old files
//			if(deleteOldFile) deleteFiles(url, authentication);
			// 
			File[] files = 
					new File("C:/radardatas").listFiles();
			System.out.println("file count:" + files.length);
			// backup one by one
			for (File file : files) {
				System.out.println("prepare to upload:" + file.getName());
				String fileName = file.getName();
				String yyyymmddhhmm = yyyyMMddHHmm.format(new Date());
				fileName = "TEST" + yyyymmddhhmm + "_" + file.getName();
				String subFolderPath = url + "/" + yyyymmddhhmm.substring(0, 8) + "/";
				SmbFile subFolder = new SmbFile(subFolderPath, authentication);
				if (!subFolder.exists()) subFolder.mkdirs();
				String remote = subFolder + fileName;
				transFile(new FileInputStream(file),
						new SmbFile(remote, authentication).getOutputStream());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/** 方法内将关闭输入、输出流 */
	private static void transFile(InputStream in, OutputStream out) {
        ReadableByteChannel src = Channels.newChannel(in);
        WritableByteChannel dest = Channels.newChannel(out);
        try {
			final ByteBuffer buffer = ByteBuffer.allocateDirect(buffer_size);
			while (src.read(buffer) != -1) {
			    // prepare the buffer to be drained
			    buffer.flip();
			    // write to the channel, may block
			    dest.write(buffer);
			    // If partial transfer, shift remainder down
			    // If buffer is empty, same as doing clear()
			    buffer.compact();
			}
			// EOF will leave buffer in fill state
			buffer.flip();
			// make sure the buffer is fully drained.
			while (buffer.hasRemaining()) {
			    dest.write(buffer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
        	try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
	}

}

