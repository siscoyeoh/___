package yl.smb;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastModified {

	public static void main(String[] args) {
		File file = new File("F:\\yl\\ftpfolder\\lastest_radar_datas\\003.log");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(new Date(file.lastModified())));
	}

}
