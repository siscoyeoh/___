package yl.ftp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FTPUtils {
	
	
	/**
	 * ����һ������, ��: <font color="red">����binary�ļ����͡�utf-8���롢���ر���ģʽ</font>
	 * @param ftpHost
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 * @Author Yang Lin
	 * @Date 2017��9��15��
	 * @Time ����12:09:29
	 */
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
		}
		try {
			// ����
			ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(3 * 1000);
			ftpClient.connect(ftpHost, portI);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			}
			// ��¼
			boolean loginSuccess = ftpClient.login(username, password);
			if (!loginSuccess) {
				ftpClient.disconnect();
			}
			
			// �ڲ���������С
			ftpClient.setBufferSize(256);
			// �����ļ�����: �ֽ��ļ�
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// ��Ҫ�ٴ�����utf-8����,�����ȡ�����ļ�(��)������
			ftpClient.setControlEncoding("utf8");
			// ���ñ��ر���: ÿ�δ����ļ�ǰ���߷�������һ���˿�,�Ա�ͻ���ͨ���ö˿ڴ�������
			ftpClient.enterLocalPassiveMode();
			
		}
//		catch (SocketException e) {
//			e.printStackTrace();
////			throw new ArchiveException("FTP����ʧ��,����FTP����.");
//		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ftpClient;
	}

}
