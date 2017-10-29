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
 * ���ǽ���Ʒ
 * 
 * @Author Yang Lin
 * @Date 2017��9��18��
 * @Time ����1:15:17
 */
@SuppressWarnings(value = "unchecked")
public class LocalToFtps extends Thread {

	/**
	 * ���������ļ��������� ��No;;State;;FTPUserName;;FTPUserPassword;;IPAdress;;FilePath;;
	 * DestinationPath�� ��1;;1;;1;;1;;1.1.1.1;;1;1��
	 * */
	public static int BasicsColumn = 24;

	/**
	 * ���������ļ�����Ԫ�ظ���
	 * */
	public static int BasicsElements = 7;

	/**
	 * �����Ԫ�ؼ�ļ������
	 * */
	public static String BaseInterval = ";;";

	/**
	 * ���������ļ�·��
	 * */
	// public static String ConfigFilePath = "D:\\L2FTPS_TESTa\\xnet\\config\\fip.conf";
	// public static String ConfigFilePath = "/home/mhdbs/xnet/config/fip.conf";
	public static String ConfigFilePath = "fip.conf";

	/**
	 * ��������ʱ��,1000���� X 60�� X 1����;
	 * */
	public static int sleepTime = 1000 * 10;

	/**
	 * �����Ҫɾ�����ļ�����
	 * */
	public static ArrayList list = new ArrayList();

	public static void main(String[] args) {
		ArrayList line = readTxtFile(ConfigFilePath);
		// lineOne �� �����ļ�������
		String[] lineOne = new String[line.size()];// ÿһ�е�������Ϣ������
		for (int i = 0; i < line.size(); i++) {
			lineOne[i] = (String) line.get(i);
		}

		// ��һά�����ɶ�ά����
		String[][] lineOF = getStringS(lineOne, line.size());

		// for (int i = 0; i < lineOF.length; i++) {
		// for (int j = 0; j < lineOF[i].length; j++) {
		// System.out.println("lineOF[" + i + "][" + j + "]="
		// + lineOF[i][j]);
		// }
		// }

		// ����ά�������

		// Ϊÿ����ά��������µ��߳�
		for (int i = 0; i < lineOF.length; i++) {
			// �����߳�
			new LocalToFtps(lineOF).start();
		}

	}

	/**
	 * ��ȡTXT�ļ�
	 * */
	public static ArrayList readTxtFile(String filePath) {
		ArrayList lineOne = new ArrayList();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// ���ǵ������ʽ
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
				System.err.println("�Ҳ���ָ�����ļ�");
			}
		} catch (Exception e) {
			System.err.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}
		return lineOne;
	}

	/**
	 * ��һά�����ɶ�ά����
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
	 * ��ö�ά�����е�һά����
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
				// ���Դ�ļ���list
				list.clear();
				createFile(line);

				long startTime = System.currentTimeMillis();
				String StartTime = stampToDate(startTime);

				readsend(line);

				long endTime = System.currentTimeMillis();
				String EndTime = stampToDate(endTime);
				System.err.println("����ʼʱ�䣺" + StartTime);
				System.err.println("�������ʱ�䣺" + EndTime);
				System.err.println("��������ʱ�䣺 " + (endTime - startTime) + "ms");

				// ɾ��Դ�ļ�
				for (int j = 0; j < list.size(); j++) {
					String a = String.valueOf(list.get(j));
					System.out.println("a =" + a);
					deleteFile(a);
				}
				// DeleteSourceFile(line);
				i++;
				System.err.println(Thread.currentThread().getName() + "���д���Ϊ"
						+ i);

				Thread.currentThread().sleep(sleepTime);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��ʱ���ת��Ϊʱ��
	 */
	public static String stampToDate(long lt) {
		String res;
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd-HH-mm");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy��MM��dd��HHʱmm��ss��SS����");
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * ��Դ�ļ������ӦIP�ļ����ڲ��ϴ�����ӦIP��ַ��FTP������
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
			FilePath = lineOne[5] + "/" + lineOne[4];// ��Դ�ļ���ַ����Ϊ��Ӧ��IP��ַ�ļ����ڵ��ļ���ַ
			DestinationPath = lineOne[6];
		}
		if (No != null && State != null && IPAdress != null && FilePath != null
				&& DestinationPath != null) {
			if (State.equals("1")) {
				System.out.println(No + "״̬Ϊ�����á�!");

				Vector<File> vecFile = new Vector<File>();
				recursion(FilePath, vecFile);

				for (File file : vecFile) {
					String filename = file.getName();
					String filePath = file.getPath();
					long lastTime = file.lastModified();
					System.out.println("�ļ���Ϊ��" + filename);
					System.out.println("�ļ�λ��Ϊ��" + filePath);

					// ���ļ��е��޸�ʱ���ʱ���ת��Ϊ����ʱ��
					String last = stampToDate(lastTime);
					System.out.println(filename + "�ļ�����޸�����Ϊ��" + last);
					String time = Judgement_time(last);
					// �������޸�ʱ��Ϊ��ǰϵͳʱ��֮���ʱ�䣬���ա����졿���������ϴ��ļ�
					if (time.contains("����")) {
						// ����ģ�Ҫ�ϴ�
						InputStream input = new FileInputStream(filePath);
						uploadFile(IPAdress, UserName, UserPassword,
								DestinationPath, filename, input);
						// �ϴ���Ϻ󣬽���ӦIP��ַΪ�����ļ���ɾ����
						System.err.println("�ϴ���Ϻ�Ҫɾ�����ļ���Ϊ��" + filePath);
						deleteFile(filePath);
					} else {
						if (time.contains("����")) {
							// ����ģ�����
							System.out.println(filename + "�ļ�Ϊ����ģ�������");
							continue;
						} else {
							// ����ʱ�䣬ɾ��
							System.err.println(filename + "�ļ�Ϊ����֮ǰ�ģ�׼��ɾ����");
							deleteFile(filePath);
						}
					}
				}
			} else {
				System.err.println("���ñ��Ϊ��" + No + "��״̬Ϊ�������á�!");
			}
		} else {
			System.err
					.println("���飺����š�����״̬������FTP�û���������FTP�û����롿����IP��ַ������Դ�ļ���ַ�������ϴ��ļ�Ŀ�ĵ�ַ���Ƿ��п�ȱ�����м���Ϊ��"
							+ BaseInterval + "��");
		}
		return null;

	}

	/**
	 * Description: ��FTP�������ϴ��ļ�
	 * 
	 * @param host
	 *            FTP������hostname
	 * @param port
	 *            FTP�������˿�
	 * @param username
	 *            FTP��¼�˺�
	 * @param password
	 *            FTP��¼����
	 * @param basePath
	 *            FTP����������Ŀ¼
	 * @param filePath
	 *            FTP�������ļ����·������������ڴ�ţ�/2015/01/01���ļ���·��ΪbasePath+filePath
	 * @param filename
	 *            �ϴ���FTP�������ϵ��ļ���
	 * @param input
	 *            ������
	 * @return �ɹ�����true�����򷵻�false
	 */
	public static boolean uploadFile(String host, String username,
			String password, String basePath, String filename, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host);// ����FTP������
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(host)�ķ�ʽֱ������FTP������
			ftp.login(username, password);// ��¼
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			System.out.println("�ϴ���FTP��������Ŀ¼ =" + basePath);
			// �л����ϴ�Ŀ¼
			if (!ftp.changeWorkingDirectory(basePath)) {
			}
			ftp.makeDirectory(basePath);// ����Ŀ¼���ǲ��ɹ�
			ftp.changeWorkingDirectory(basePath);// �л�Ŀ¼���ǲ��ɹ�
			// �����ϴ��ļ�������Ϊ����������
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// �ϴ��ļ�
			ftp.storeFile(filename, input);
			input.close();
			ftp.logout();
			System.out.println(host + "�˳���½��������");
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
	 * ɾ�������ļ�
	 * 
	 * @param sPath��ɾ���ļ����ļ���
	 *            (����ַ)
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
			System.out.println(sPath + "ɾ���ɹ���");
		} else {
			System.out.println(sPath + "ɾ��ʧ�ܣ�");
		}
		return flag;
	}

	/**
	 * �ж�ʱ���Ƿ���ϣ����жϳ������켰֮������ڡ��������졿����ǰ�켰֮ǰ�����ڡ�������в���
	 * */
	public static String Judgement_time(String last) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy��MM��dd��HHʱmm��ss��SS����");
		Date date = formatter.parse(last);

		String todySDF = "���� HH:mm";
		String yesterDaySDF = "���� HH:mm";
		String otherSDF = "M��d�� HH:mm";
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
	 * ���ļ�·���µ������ļ������ļ��б���
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
	 * ����IP��ַ��������IP��ַΪ�����ļ��� �����ļ����Ƶ��ļ�����
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
	 * ����Դ�ļ�
	 * */
	public static void Backups(String[] One) throws Exception {
		String filePath = One[5];
		String f = filePath + "/" + "Backup";
		File f1 = new File(f);
		f1.mkdir();
		turnFile(filePath, f);

	}

	/**
	 * �����ļ��к󣬶�ȡԴ�ļ�����Դ�ļ����У����ƣ���IP��ַ�ļ�����
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
			if (time.contains("����")) {
				// ����ģ�Ҫ�ϴ�
				System.out.println(filename + "�ļ�Ϊ����ģ�׼�����Ƶ�" + f + "�ļ��к��ϴ���");
				FileInputStream fis = new FileInputStream(filePath1);
				FileOutputStream fos = new FileOutputStream(new File(f,
						filename));
				copy(fis, fos);
				fis.close();
				fos.close();
				System.out.println("���ƽ����ˣ�");
			} else {
				if (time.contains("����")) {
					// ����ģ�����
					continue;
				} else {
					// ����ʱ�䣬ɾ��
					System.err.println(filePath1 + "�ļ�Ϊ����֮ǰ�ģ�׼��ɾ����");
					deleteFile(filePath1);
				}
			}
		}
	}

	/**
	 * �������������ݸ��Ƶ��������
	 * */
	public static void copy(InputStream in, OutputStream out) throws Exception {
		byte[] buf = new byte[1024];
		int len = 0;
		/* ��ȡ�ļ����ݲ�д���ļ��ֽ����� */
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
	}
}
