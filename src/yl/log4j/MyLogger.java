package yl.log4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

public class MyLogger {
	
	private static File log = new File("log.log");
	
	public static void info(Object obj) {
		record("[INFO]", obj);
	}
	
	public static void error(Object obj) {
		record("[ERROR]", obj);
	}
	
	private static void record(String prefix, Object obj) {
		String yyyymmddhhmiss = "";
		String msg = prefix + " " + yyyymmddhhmiss + " : " + obj.toString();
		append("" + msg + "\n");
	}
	
	private static void append(String msg) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(log, true)));
			writer.write(msg);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		System.out.println(msg);
	}
	
	public static void clear() {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(log, false)));
			writer.write("");
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[]args) {
		File log = new File("log.log");
		System.out.println(log.exists());
		info("sssss");
//		clear();
	}

}
