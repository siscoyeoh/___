package yl.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class Filechan {

	public static void main(String[] args) throws FileNotFoundException {
		File oldFile = new File("F:\\yl\\ftpfolder\\server_logs\\sfsafd\\20170928.txt");
		File newFile = new File("F:\\yl\\ftpfolder\\server_logs\\20170928.txt");
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(newFile);
		test(in, out);
	}
	
	public static void test(FileInputStream in, FileOutputStream out) {
		try {
	        int length = 2097152;
			
	        FileChannel inC = in.getChannel();
	        FileChannel outC = out.getChannel();
	        while (true) {
	            if(inC.position() == inC.size()) {
	                inC.close();
	                outC.close();
	                break;
	            }
	            if ((inC.size() - inC.position()) < 20971520) {
	            	length = (int) (inC.size() - inC.position());
	            }
	            else {
	            	length = 20971520;
	            }
	            inC.transferTo(inC.position(), length, outC);
	            inC.position(inC.position() + length);
	        }
			
//			newFile.setLastModified(lastModified);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
