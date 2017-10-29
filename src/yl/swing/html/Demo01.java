package yl.swing.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Demo01 {

	public static void main(String[] args) throws IOException, BadLocationException {
		
	}

	public static void localHtmlDisplay03() throws FileNotFoundException,
			IOException, BadLocationException {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(900, 600);
		JTextPane reportPane = new JTextPane();
		
		File html = new File("C:\\Users\\Administrator\\Desktop\\__\\awrrpt_1_19_21.html");
		System.out.println(html.exists());
		BufferedReader br = new BufferedReader(new FileReader(html));
		Document doc = reportPane.getDocument();
		String line = null;
		while ((line = br.readLine()) != null) {
			doc.insertString(doc.getLength(), line, null);
		}
		
		JScrollPane scroll = new JScrollPane(reportPane);
		f.add(scroll);
		
		f.setVisible(true);
	}

	public static void localHtmlDisplay02() throws IOException {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(900, 600);
		JTextPane reportPane = new JTextPane();
		
		File html = new File("C:\\Users\\Administrator\\Desktop\\__\\awrrpt_1_19_21.html");
		System.out.println(html.exists());
//		BufferedReader br = new BufferedReader(new FileReader(html));
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			reportPane
//		}
//		FileWriter fw = new FileWriter(html, false);
		String url = "C:/Users/Administrator/Desktop/__/awrrpt_1_19_21.html";
		url = "file:///C:/Users/Administrator/Desktop/__/awrrpt_1_19_21.html";
		reportPane.setPage(url);
		
		JScrollPane scroll = new JScrollPane(reportPane);
		f.add(scroll);
		
		f.setVisible(true);
	}

	public static void localHtmlDisplay01() throws IOException {
		// http://blog.csdn.net/rcyl2003/article/details/1721189
//		String vNewReportFileName = "file:///c:/temp.html";
//		   JEditorPane reportPane = new JEditorPane();
//		   File f = new File(FileUtil.reportDir,vNewReportFileName);
//		  FileWriter fw = new FileWriter(f, false);
//		   fw.write("<html>");
//		  fw.write("<head>");
////		  ¡­¡­¡­¡­
//		  fw.write("</body></html>");
//		//ÇåÀí²Ù×÷
//		   fw.flush();
//		   fw.close();
//		f = null;
//		reportPane.setPage(vNewReportFileName);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(900, 600);
		JEditorPane reportPane = new JEditorPane();
		
		File html = new File("C:\\Users\\Administrator\\Desktop\\__\\awrrpt_1_19_21.html");
		System.out.println(html.exists());
//		BufferedReader br = new BufferedReader(new FileReader(html));
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			reportPane
//		}
//		FileWriter fw = new FileWriter(html, false);
		String url = "C:/Users/Administrator/Desktop/__/awrrpt_1_19_21.html";
		url = "file:///C:/Users/Administrator/Desktop/__/awrrpt_1_19_21.html";
		reportPane.setPage(url);
		
		JScrollPane scroll = new JScrollPane(reportPane);
		f.add(scroll);
		
		f.setVisible(true);
	}

}
