package yl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FTPUtils {
	
	
	public static FTPClient GetFTPClient(String ftpHost, String port, String username,
			String password) {
		System.out.println(" ★ ftpHost:" + ftpHost);
		System.out.println(" ★ port:" + port);
		System.out.println(" ★ username:" + username);
		System.out.println(" ★ password:" + password);
		FTPClient ftpClient = null;
		int portI = -1;
		try {
			portI = Integer.parseInt(port);
		} catch (NumberFormatException e1) {
			portI = 21;
			System.out.println(ftpHost + ", 端口号配置错误:" + port + ", 已设置默认21.");
		}
		try {
			System.out.println("开始连接,ftpHost:" + ftpHost + ", port:" + port + ", username:" + username);
			// 连接
			ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(3 * 1000);
			ftpClient.connect(ftpHost, portI);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.out.println("ftpHost:" + ftpHost + ", port:" + port + ", username:" + username 
						+ ", FTP连接失败,请检查FTP服务器配置(服务应答码:" + reply + ").");
			}
			// 登录
			boolean loginSuccess = ftpClient.login(username, password);
			if (!loginSuccess) {
				ftpClient.disconnect();
				System.out.println("ftpHost:" + ftpHost + ", port:" + port + ", username:" + username 
						+ ", 错误的用户名或密码.");
			}
			ftpClient.setBufferSize(256);

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 需要再次设置utf-8编码,否则读取中文文件(夹)将出错
			ftpClient.setControlEncoding("utf8");
			System.out.println("ftpHost:" + ftpHost + ", port:" + port + ", username:" + username + ", 登陆成功.");
		} 
		catch (NumberFormatException e) {
			
		}
//		catch (SocketException e) {
//			e.printStackTrace();
////			throw new ArchiveException("FTP连接失败,请检查FTP连接.");
//		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("ftpHost:" + ftpHost + ", port:" + port + ", username:" + username 
					+ ", FTP连接失败,请检查FTP服务器配置.");
		}
		return ftpClient;
	}
	
	/**
	 * 上传一个文件到ftp server, 上传成功返回true, 失败返回false
	 * @param file
	 * @param ftpClient
	 * @param destination_path
	 * @return
	 * @Authour Yang Lin
	 * @Date: 2017年6月15日
	 * @Time: 下午6:19:23
	 */
	public static boolean UploadFile(File file, FTPClient ftpClient, String destination_path) {
		return false;
	}

}
