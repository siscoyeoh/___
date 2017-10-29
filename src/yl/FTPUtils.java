package yl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FTPUtils {
	
	
	public static FTPClient GetFTPClient(String ftpHost, String port, String username,
			String password) {
		System.out.println(" �� ftpHost:" + ftpHost);
		System.out.println(" �� port:" + port);
		System.out.println(" �� username:" + username);
		System.out.println(" �� password:" + password);
		FTPClient ftpClient = null;
		int portI = -1;
		try {
			portI = Integer.parseInt(port);
		} catch (NumberFormatException e1) {
			portI = 21;
			System.out.println(ftpHost + ", �˿ں����ô���:" + port + ", ������Ĭ��21.");
		}
		try {
			System.out.println("��ʼ����,ftpHost:" + ftpHost + ", port:" + port + ", username:" + username);
			// ����
			ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(3 * 1000);
			ftpClient.connect(ftpHost, portI);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.out.println("ftpHost:" + ftpHost + ", port:" + port + ", username:" + username 
						+ ", FTP����ʧ��,����FTP����������(����Ӧ����:" + reply + ").");
			}
			// ��¼
			boolean loginSuccess = ftpClient.login(username, password);
			if (!loginSuccess) {
				ftpClient.disconnect();
				System.out.println("ftpHost:" + ftpHost + ", port:" + port + ", username:" + username 
						+ ", ������û���������.");
			}
			ftpClient.setBufferSize(256);

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// ��Ҫ�ٴ�����utf-8����,�����ȡ�����ļ�(��)������
			ftpClient.setControlEncoding("utf8");
			System.out.println("ftpHost:" + ftpHost + ", port:" + port + ", username:" + username + ", ��½�ɹ�.");
		} 
		catch (NumberFormatException e) {
			
		}
//		catch (SocketException e) {
//			e.printStackTrace();
////			throw new ArchiveException("FTP����ʧ��,����FTP����.");
//		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("ftpHost:" + ftpHost + ", port:" + port + ", username:" + username 
					+ ", FTP����ʧ��,����FTP����������.");
		}
		return ftpClient;
	}
	
	/**
	 * �ϴ�һ���ļ���ftp server, �ϴ��ɹ�����true, ʧ�ܷ���false
	 * @param file
	 * @param ftpClient
	 * @param destination_path
	 * @return
	 * @Authour Yang Lin
	 * @Date: 2017��6��15��
	 * @Time: ����6:19:23
	 */
	public static boolean UploadFile(File file, FTPClient ftpClient, String destination_path) {
		return false;
	}

}
