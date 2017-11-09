package yl.ftp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FTPUtils {
	
	
	/**
	 * 创建一个连接, 并: <font color="red">设置binary文件类型、utf-8编码、本地被动模式</font>
	 * @param ftpHost
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 * @Author Yang Lin
	 * @Date 2017年9月15日
	 * @Time 下午12:09:29
	 */
	public static FTPClient GetFTPClient(String ftpHost, String port, String username,
			String password) {
		FTPClient ftpClient = null;
		int portI = -1;
		try {
			portI = Integer.parseInt(port);
		} catch (NumberFormatException e1) {
			portI = 21;
		}
		try {
			// 连接
			ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(3 * 1000);
			ftpClient.connect(ftpHost, portI);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			}
			// 登录
			boolean loginSuccess = ftpClient.login(username, password);
			if (!loginSuccess) {
				ftpClient.disconnect();
			}
			
			// 内部缓冲区大小
			ftpClient.setBufferSize(256);
			ftpClient.setDefaultTimeout(6000);
			// 设置文件类型: 字节文件
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 需要再次设置utf-8编码,否则读取中文文件(夹)将出错
			ftpClient.setControlEncoding("utf8");
			// 设置本地被动: 每次传送文件前告诉服务器打开一个端口,以便客户端通过该端口传输数据
			ftpClient.enterLocalPassiveMode();
			
		}
//		catch (SocketException e) {
//			e.printStackTrace();
////			throw new ArchiveException("FTP连接失败,请检查FTP连接.");
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
