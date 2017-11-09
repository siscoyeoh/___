package yl.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class CompareBytes {

	public static void main(String[] args) throws Exception {
		File file0 = new File("C:/Users/Administrator/Desktop/extract_msg/CRH201711090505.dat/CRH201711090505.dat");
		File file1 = new File("C:/Users/Administrator/Desktop/extract_msg/CRH201711090505.dat1/CRH201711090505.dat1");
		long length0 = file0.length();
		long length1 = file1.length();
		System.out.println("length0:" + length0);
		System.out.println("length1:" + length1);
		InputStream is0 = new FileInputStream(file0);
		InputStream is1 = new FileInputStream(file1);
		int byte0 = -1;
		int byte1 = -1;
		long position = 0;
		while ((byte0 = is0.read()) != -1) {
			byte1 = is1.read();
			if (byte0 != byte1) {
				is0.close();
				is1.close();
				throw new Exception("不同的position:" + position);
			}
			if (position % 10000 == 0) System.out.println("检查到:" + position);
			position++;
		}
		System.out.println("完全相同.");
		is0.close();
		is1.close();
	}

}
