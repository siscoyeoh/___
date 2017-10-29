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

public class Upload66smb {

	private static int buffer_size = 256;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
	private static String info = 
			"Please type in parameter:\n\nargs 0:\nu\tupload files\nd\tdelete files\np\tdisplay files"
			+ "\n\n[args 1: buffer_size]";
	
	public static void main(String[] args) {
		Date start = new Date();
		if (args != null && args.length >= 1) {
			String flag = args[0];
			if ("u".equals(flag.toLowerCase())) deleteAndUpload();
			else if ("d".equals(flag.toLowerCase())) deleteOnly();
			else if ("p".equals(flag.toLowerCase())) display();
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
		}
		else {
			System.out.println(info);
		}
		Date end = new Date();
		System.out.println("seconds wasted:" + (end.getTime() - start.getTime()) / 1000L);
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
	
	private static void display() {
		String url = "smb://192.168.8.66/smbshared/";
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
	
	private static void deleteOnly() {
		String url = "smb://192.168.8.66/smbshared/";
		NtlmPasswordAuthentication authentication = 
				new NtlmPasswordAuthentication(".", "pc66", "pc66");
		// delete old files
		deleteFiles(url, authentication);
	}
	
	private static void deleteAndUpload() {

		try {
			String url = "smb://192.168.8.66/smbshared/";
			NtlmPasswordAuthentication authentication = 
					new NtlmPasswordAuthentication(".", "pc66", "pc66");
			// delete old files
			deleteFiles(url, authentication);
			// 
			File[] files = 
					new File("C:/radardatas").listFiles();
			System.out.println("file count:" + files.length);
			// backup one by one
			for (File file : files) {
				String fileName = file.getName();
				fileName = "TEST" + yyyyMMddHHmm.format(new Date()) + file.getName();
				String remote = url + fileName;
//				InputStream in = new FileInputStream(file);
//				OutputStream out = new SmbFile(remote, authentication).getOutputStream();
//				transFile(file, new SmbFile(remote, authentication));
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
	
//	private static void transFile(File localFile, SmbFile newSmbFile) throws IOException {
//
//        int length = 2097152;
//        @SuppressWarnings("resource")
//		FileInputStream in = new FileInputStream(localFile);
////		FileOutputStream out = (FileOutputStream) newSmbFile.getOutputStream();
//        OutputStream out = newSmbFile.getOutputStream();
//        FileChannel inC = in.getChannel();
////        FileChannel outC = out.getChannel();
//        WritableByteChannel outC = Channels.newChannel(out);
//        while (true) {
//            if(inC.position() == inC.size()) {
//                inC.close();
//                outC.close();
//                break;
//            }
//            if ((inC.size() - inC.position()) < 20971520) {
//            	length = (int) (inC.size() - inC.position());
//            }
//            else {
//            	length = 20971520;
//            }
//            inC.transferTo(inC.position(), length, outC);
//            inC.position(inC.position() + length);
//        }
//	}
	private static void transFile(InputStream in, OutputStream out) throws IOException {
        ReadableByteChannel src = Channels.newChannel(in);
        WritableByteChannel dest = Channels.newChannel(out);
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
	}

}
