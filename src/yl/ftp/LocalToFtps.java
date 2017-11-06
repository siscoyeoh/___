package yl.ftp;

import java.io.BufferedReader;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 王亚杰作品
 * 
 * @Author Yang Lin
 * @Date 2017年9月18日
 * @Time 下午1:15:17
 */
@SuppressWarnings(value = "unchecked")
public class LocalToFtps extends Thread {

	/**
	 * 定义配置文件基础列数 【No;;State;;FTPUserName;;FTPUserPassword;;IPAdress;;FilePath;;
	 * DestinationPath】 【1;;1;;1;;1;;1.1.1.1;;1;1】
	 * */
	public static int BasicsColumn = 24;

	/**
	 * 定义配置文件基础元素个数
	 * */
	public static int BasicsElements = 7;

	/**
	 * 定义各元素间的间隔符号
	 * */
	public static String BaseInterval = ";;";

	/**
	 * 定义配置文件路径
	 * */
	// public static String ConfigFilePath = "D:\\L2FTPS_TESTa\\xnet\\config\\fip.conf";
	// public static String ConfigFilePath = "/home/mhdbs/xnet/config/fip.conf";
	public static String ConfigFilePath = "fip.conf";

	/**
	 * 定义休眠时间,1000毫秒 X 60秒 X 1分钟;
	 * */
	public static int sleepTime = 1000 * 10;

	/**
	 * 传输后要删除的文件名称
	 * */
	public static ArrayList list = new ArrayList();

	public static void main(String[] args) {
		ArrayList line = readTxtFile(ConfigFilePath);
		// lineOne 即 配置文件的数组
		String[] lineOne = new String[line.size()];// 每一行的配置信息的数组
		for (int i = 0; i < line.size(); i++) {
			lineOne[i] = (String) line.get(i);
		}

		// 将一维数组拆成二维数组
		String[][] lineOF = getStringS(lineOne, line.size());

		// for (int i = 0; i < lineOF.length; i++) {
		// for (int j = 0; j < lineOF[i].length; j++) {
		// System.out.println("lineOF[" + i + "][" + j + "]="
		// + lineOF[i][j]);
		// }
		// }

		// 将二维数组存入

		// 为每个二维数组分配新的线程
		for (int i = 0; i < lineOF.length; i++) {
			// 分配线程
			new LocalToFtps(lineOF).start();
		}

	}

	/**
	 * 读取TXT文件
	 * */
	public static ArrayList readTxtFile(String filePath) {
		ArrayList lineOne = new ArrayList();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					if (lineTxt.length() > BasicsColumn
							&& !lineTxt.contains("No")) {
						lineOne.add(lineTxt);
					}
				}
				read.close();
			} else {
				System.err.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.err.println("读取文件内容出错");
			e.printStackTrace();
		}
		return lineOne;
	}

	/**
	 * 将一维数组拆成二维数组
	 * */
	public static String[][] getStringS(String[] lineOne_, int count) {
		String[][] line = new String[count][BasicsElements];
		for (int i = 0; i < lineOne_.length; i++) {
			// System.out.println("lineOne_  ="+lineOne_[i]);
			line[i] = lineOne_[i].split(BaseInterval);
		}
		return line;
	}

	private static int j = 0;

	/**
	 * 获得二维数组中的一维数组
	 * */
	public synchronized String[] getOne(String[][] b) {
		String[] c = b[j];
		j++;
		return c;
	}

	private String[][] shuzu;

	public LocalToFtps(String[][] shuzu) {
		this.shuzu = shuzu;
	}

	public void run() {

		String[] line = getOne(shuzu);
		for (int i = 0; i < line.length; i++) {
			System.out.println(Thread.currentThread().getName() + "|| ="
					+ line[i]);
		}
		try {
			int i = 0;
			while (true) {
				// 清空源文件的list
				list.clear();
				createFile(line);

				long startTime = System.currentTimeMillis();
				String StartTime = stampToDate(startTime);

				readsend(line);

				long endTime = System.currentTimeMillis();
				String EndTime = stampToDate(endTime);
				System.err.println("程序开始时间：" + StartTime);
				System.err.println("程序结束时间：" + EndTime);
				System.err.println("程序运行时间： " + (endTime - startTime) + "ms");

				// 删除源文件
				for (int j = 0; j < list.size(); j++) {
					String a = String.valueOf(list.get(j));
					System.out.println("a =" + a);
					deleteFile(a);
				}
				// DeleteSourceFile(line);
				i++;
				System.err.println(Thread.currentThread().getName() + "运行次数为"
						+ i);

				Thread.currentThread().sleep(sleepTime);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(long lt) {
		String res;
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd-HH-mm");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy年MM月dd日HH时mm分ss秒SS毫秒");
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * 将源文件放入对应IP文件夹内并上传到相应IP地址的FTP服务器
	 * */
	public static Runnable readsend(String[] lineOne)
			throws FileNotFoundException, ParseException {
		String No = null;
		String State = null;
		String UserName = null;
		String UserPassword = null;
		String IPAdress = null;
		String FilePath = null;
		String DestinationPath = null;

		if (lineOne != null) {
			No = lineOne[0];
			State = lineOne[1];
			UserName = lineOne[2];
			UserPassword = lineOne[3];
			IPAdress = lineOne[4];
			FilePath = lineOne[5] + "/" + lineOne[4];// 将源文件地址更改为对应的IP地址文件夹内的文件地址
			DestinationPath = lineOne[6];
		}
		if (No != null && State != null && IPAdress != null && FilePath != null
				&& DestinationPath != null) {
			if (State.equals("1")) {
				System.out.println(No + "状态为【启用】!");

				Vector<File> vecFile = new Vector<File>();
				recursion(FilePath, vecFile);

				for (File file : vecFile) {
					String filename = file.getName();
					String filePath = file.getPath();
					long lastTime = file.lastModified();
					System.out.println("文件名为：" + filename);
					System.out.println("文件位置为：" + filePath);

					// 将文件夹的修改时间的时间戳转换为北京时间
					String last = stampToDate(lastTime);
					System.out.println(filename + "文件最后修改日期为：" + last);
					String time = Judgement_time(last);
					// 如果最后修改时间为当前系统时间之后的时间，则按照【今天】来处理，即上传文件
					if (time.contains("今天")) {
						// 今天的，要上传
						InputStream input = new FileInputStream(filePath);
						uploadFile(IPAdress, UserName, UserPassword,
								DestinationPath, filename, input);
						// 上传完毕后，将相应IP地址为名的文件夹删除！
						System.err.println("上传完毕后将要删除的文件名为：" + filePath);
						deleteFile(filePath);
					} else {
						if (time.contains("昨天")) {
							// 昨天的，不管
							System.out.println(filename + "文件为昨天的，不处理！");
							continue;
						} else {
							// 其他时间，删除
							System.err.println(filename + "文件为昨天之前的，准备删除！");
							deleteFile(filePath);
						}
					}
				}
			} else {
				System.err.println("配置编号为：" + No + "的状态为【不启用】!");
			}
		} else {
			System.err
					.println("请检查：【序号】、【状态】、【FTP用户名】、【FTP用户密码】、【IP地址】、【源文件地址】、【上传文件目的地址】是否有空缺，且中间间隔为【"
							+ BaseInterval + "】");
		}
		return null;

	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param host
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param basePath
	 *            FTP服务器基础目录
	 * @param filePath
	 *            FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String host, String username,
			String password, String basePath, String filename, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			System.out.println("上传到FTP服务器的目录 =" + basePath);
			// 切换到上传目录
			if (!ftp.changeWorkingDirectory(basePath)) {
			}
			ftp.makeDirectory(basePath);// 创建目录总是不成功
			ftp.changeWorkingDirectory(basePath);// 切换目录总是不成功
			// 设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 上传文件
			ftp.storeFile(filename, input);
			input.close();
			ftp.logout();
			System.out.println(host + "退出登陆！！！！");
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath被删除文件的文件名
	 *            (带地址)
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
			System.out.println(sPath + "删除成功！");
		} else {
			System.out.println(sPath + "删除失败！");
		}
		return flag;
	}

	/**
	 * 判断时间是否符合，仅判断出【今天及之后的日期】、【昨天】、【前天及之前的日期】方便进行操作
	 * */
	public static String Judgement_time(String last) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日HH时mm分ss秒SS毫秒");
		Date date = formatter.parse(last);

		String todySDF = "今天 HH:mm";
		String yesterDaySDF = "昨天 HH:mm";
		String otherSDF = "M月d日 HH:mm";
		SimpleDateFormat sfd = null;
		String time = "";
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);
		Date now = new Date();
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(now);
		targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
		targetCalendar.set(Calendar.MINUTE, 0);
		if (dateCalendar.after(targetCalendar)) {
			sfd = new SimpleDateFormat(todySDF);
			time = sfd.format(date);
			return time;
		} else {
			targetCalendar.add(Calendar.DATE, -1);
			if (dateCalendar.after(targetCalendar)) {
				sfd = new SimpleDateFormat(yesterDaySDF);
				time = sfd.format(date);
				return time;
			}
		}
		sfd = new SimpleDateFormat(otherSDF);
		time = sfd.format(date);
		return time;
	}

	/**
	 * 将文件路径下的所有文件存入文件列表中
	 * */
	public static void recursion(String FilePath, Vector<File> vecFile) {
		File file = new File(FilePath);
		File[] subFile = file.listFiles();
		for (int i = 0; i < subFile.length; i++) {
			if (subFile[i].isDirectory()) {
			} else {
				vecFile.add(subFile[i]);
			}
		}
	}

	/**
	 * 根据IP地址，生成以IP地址为名的文件夹 并将文件复制到文件夹内
	 * 
	 * @throws Exception
	 * */
	public static void createFile(String[] One) throws Exception {
		String IPAdress = One[4];
		String filePath = One[5];
		String f = filePath + "/" + IPAdress;
		File f1 = new File(f);
		f1.mkdir();
		turnFile(filePath, f);
	}

	/**
	 * 备份源文件
	 * */
	public static void Backups(String[] One) throws Exception {
		String filePath = One[5];
		String f = filePath + "/" + "Backup";
		File f1 = new File(f);
		f1.mkdir();
		turnFile(filePath, f);

	}

	/**
	 * 生成文件夹后，读取源文件，把源文件剪切（复制）到IP地址文件夹内
	 * */
	public static void turnFile(String filePath, String f) throws Exception {
		Vector<File> vecFile = new Vector<File>();
		recursion(filePath, vecFile);
		for (File file : vecFile) {
			String filename = file.getName();
			String filePath1 = file.getPath();

			list.add(filePath1);

			long lastTime = file.lastModified();
			String last = stampToDate(lastTime);
			String time = Judgement_time(last);
			if (time.contains("今天")) {
				// 今天的，要上传
				System.out.println(filename + "文件为今天的，准备复制到" + f + "文件夹后上传！");
				FileInputStream fis = new FileInputStream(filePath1);
				FileOutputStream fos = new FileOutputStream(new File(f,
						filename));
				copy(fis, fos);
				fis.close();
				fos.close();
				System.out.println("复制结束了！");
			} else {
				if (time.contains("昨天")) {
					// 昨天的，不管
					continue;
				} else {
					// 其他时间，删除
					System.err.println(filePath1 + "文件为昨天之前的，准备删除！");
					deleteFile(filePath1);
				}
			}
		}
	}

	/**
	 * 将输入流的内容复制到输出流中
	 * */
	public static void copy(InputStream in, OutputStream out) throws Exception {
		byte[] buf = new byte[1024];
		int len = 0;
		/* 读取文件内容并写入文件字节流中 */
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
	}
}
