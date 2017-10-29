package yl.smb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Date;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.dom4j.DocumentException;

public class SMBTest {

	public static void main(String[]args) throws IOException, DocumentException {
//		LoadDataMGConfig.Load();
//		System.out.println(DataMGConfigContainer.getBackupLastestFile().toString());
		displayFilesName();
		uploadFiles();
	}
	
	public static void displayFilesName() throws MalformedURLException, SmbException {
		 String url = "smb://192.168.8.115/SMB/";
//		 url = "smb://192.168.8.44/smbsharedfolder/";
//		 url = "smb://127.0.0.1/smbsharedfolder/";
//		 url = "smb://localhost/smbsharedfolder/";
        NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(".", "smbuser", "smbuser");
        SmbFile folder = new SmbFile(url, authentication);
//        if (smbFile.exists()) {
//        	System.out.println("hello world!");
//        }
        SmbFile[] files = folder.listFiles();
        System.out.println(files.length);
        for (int i = 0; i < files.length; i++) {
        	System.out.println(files[i].getName() + ", " + new Date(files[i].lastModified()));
        }
	}
	
	public static void uploadFiles() throws IOException {
		String url = "smb://192.168.8.115/SMB/";
//		 url = "smb://192.168.8.44/smbsharedfolder/";
//		 url = "smb://127.0.0.1/smbsharedfolder/";
//		 url = "smb://localhost/smbsharedfolder/";
		NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(".", "smbuser", "smbuser");
		SmbFile folder = new SmbFile(url, authentication);
//        if (smbFile.exists()) {
//        	System.out.println("hello world!");
//        }
		File[] files = new File("C:\\file_serv").listFiles();
		System.out.println(files.length);
		for (int i = 0; i < files.length; i++) {
			File localFile = files[i];
			System.out.println(localFile.getName());
			if (localFile.getName().contains("Local")) continue;
			SmbFile remoteFile = new SmbFile(url + localFile.getName(), authentication);
			transFile(localFile, remoteFile);
//			try {
//				Thread.sleep(30 * 1000L);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	private static void transFile(File localFile, SmbFile newSmbFile) throws IOException {

        int length = 2097152;
        @SuppressWarnings("resource")
		FileInputStream in = new FileInputStream(localFile);
//		FileOutputStream out = (FileOutputStream) newSmbFile.getOutputStream();
        OutputStream out = newSmbFile.getOutputStream();
        FileChannel inC = in.getChannel();
//        FileChannel outC = out.getChannel();
        WritableByteChannel outC = Channels.newChannel(out);
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
	}

}
