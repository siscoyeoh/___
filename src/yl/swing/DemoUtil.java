package yl.swing;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import demo.BarChart3DDemo3;

public class DemoUtil {
	
	public static void main(String[]a) throws ClassNotFoundException {
		String arg0 = null;
//		BarChart3DDemo1.main(a);
//		BarChart3DDemo2.main(a);
//		BarChart3DDemo3.main(a);
//		System.out.println(isDemoFile("111ddDemo1"));
		String className = "yl.swing.chart_bar.StackedBarChartDemo1";
		className = "demo.BarChart3DDemo3";
		List<Class> classes = DemoUtil.getAllClassByInterface(Class.forName(className));  

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("JFreeChart�ٷ�ʾ��");
       
        
        for (Class clas :classes) {  
            System.out.println(clas.getName());  
            top.add(new DefaultMutableTreeNode(clas.getName()));
        }  
        
 
        final JTree tree = new JTree(top);
//        tree.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(tree);
        
        JFrame f = new JFrame("JFreeChart�ٷ�ʾ��");
        f.add(scrollPane);
        f.setSize(300, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ���ѡ���¼�
        tree.addTreeSelectionListener(new TreeSelectionListener() {
 
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();
 
                if (node == null)
                    return;
 
                Object object = node.getUserObject();
                if (node.isLeaf()) {
                    String classNameSelected = (String) object;
                    System.out.println("��ѡ���ˣ�" + classNameSelected);
//                    try {
//						Class.forName(classNameSelected).newInstance();
//					} catch (InstantiationException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (IllegalAccessException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (ClassNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
                    
//                    try {
//						Method method = Class.forName(classNameSelected).getMethod("main", String[].class);
//						method.invoke(null, new Object[] { new String[]{} });
//					} catch (SecurityException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (NoSuchMethodException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (ClassNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (IllegalArgumentException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (IllegalAccessException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (InvocationTargetException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
                    
//                    RunDemo demo = new RunDemo(classNameSelected);
//                    Thread t = new Thread(demo);
//                    t.start();
                    
                    try {
//                    	Object demo = Class.forName(classNameSelected).newInstance();
//						Method method = demo.getMethod("pack", new Class[] {});
//						method.invoke(null, new Object[] { new String[]{} });
//                    	Method pack = Class.forName(classNameSelected).getMethod("pack");
//                    	pack.invoke(demo);
//                    	Method setVisible = Class.forName(classNameSelected).getMethod("setVisible", new Class[] { boolean.class });
//                    	setVisible.invoke(demo, new Object[] { true });
                    	Constructor<?> constructor = Class.forName(classNameSelected).getConstructor(String.class);
                    	Object demo = constructor.newInstance("");
                    	Method pack = Class.forName(classNameSelected).getMethod("pack");
                    	pack.invoke(demo);
                    	Method setCloseOperation = Class.forName(classNameSelected).getMethod("setDefaultCloseOperation", new Class[] { int.class });
                    	setCloseOperation.invoke(demo, new Object[] { JFrame.DO_NOTHING_ON_CLOSE });
                    	Method setVisible = Class.forName(classNameSelected).getMethod("setVisible", new Class[] { boolean.class });
                    	setVisible.invoke(demo, new Object[] { true });
					} catch (SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
 
            }
        });
	}
	
	/** 
     * ȡ��ĳ���ӿ�������ʵ������ӿڵ��� 
     * */  
    public static List<Class> getAllClassByInterface(Class c) {  
            List<Class>  returnClassList = null;  
              
            // if(c.isInterface()) {  
            System.out.println("c:\t" + c);
            System.out.println("isAnnotation:\t" + c.isAnnotation());
            System.out.println("isAnonymousClass:\t" + c.isAnonymousClass());
            System.out.println("isArray:\t" + c.isArray());
            System.out.println("isEnum:\t" + c.isEnum());
            System.out.println("isInterface:\t" + c.isInterface());
            System.out.println("isLocalClass:\t" + c.isLocalClass());
            System.out.println("isMemberClass:\t" + c.isMemberClass());
            System.out.println("isPrimitive:\t" + c.isPrimitive());
            System.out.println("isSynthetic:\t" + c.isSynthetic());
//            if(c.isMemberClass()) {  
                // ��ȡ��ǰ�İ���  
                String packageName = c.getPackage().getName();  
                // ��ȡ��ǰ�����Լ��Ӱ������Ե���  
                List<Class<?>> allClass = getClasses(packageName);  
                System.out.println("��ȡ��ǰ�����Լ��Ӱ������Ե���:" + allClass);
                if(allClass != null) {  
                    returnClassList = new ArrayList<Class>();  
                    for(Class classes : allClass) {  
                        // �ж��Ƿ���ͬһ���ӿ�  
//                        if(c.isAssignableFrom(classes)) {  
                            // ���������ȥ  
//                            if(!c.equals(classes)) {  
                                returnClassList.add(classes);          
//                            }  
//                        }  
                    }  
                }  
//            }  
              
            return returnClassList;  
        } 
    
    /** 
     * �Ӱ�package�л�ȡ���е�Class 
     * @param pack 
     * @return 
     */  
    public static List<Class<?>> getClasses(String packageName){  
          
        //��һ��class��ļ���  
        List<Class<?>> classes = new ArrayList<Class<?>>();  
        //�Ƿ�ѭ������  
        boolean recursive = true;  
        //��ȡ�������� �������滻  
        String packageDirName = packageName.replace('.', '/');  
        //����һ��ö�ٵļ��� ������ѭ�����������Ŀ¼�µ�things  
        Enumeration<URL> dirs;  
        try {  
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);  
            //ѭ��������ȥ  
            while (dirs.hasMoreElements()){  
                //��ȡ��һ��Ԫ��  
                URL url = dirs.nextElement();  
                //�õ�Э�������  
                String protocol = url.getProtocol();  
                //��������ļ�����ʽ�����ڷ�������  
                if ("file".equals(protocol)) {  
                    //��ȡ��������·��  
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");  
                    //���ļ��ķ�ʽɨ���������µ��ļ� ����ӵ�������  
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);  
                } else if ("jar".equals(protocol)){  
                    //�����jar���ļ�   
                    //����һ��JarFile  
                    JarFile jar;  
                    try {  
                        //��ȡjar  
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();  
                        //�Ӵ�jar�� �õ�һ��ö����  
                        Enumeration<JarEntry> entries = jar.entries();  
                        //ͬ���Ľ���ѭ������  
                        while (entries.hasMoreElements()) {  
                            //��ȡjar���һ��ʵ�� ������Ŀ¼ ��һЩjar����������ļ� ��META-INF���ļ�  
                            JarEntry entry = entries.nextElement();  
                            String name = entry.getName();  
                            //�������/��ͷ��  
                            if (name.charAt(0) == '/') {  
                                //��ȡ������ַ���  
                                name = name.substring(1);  
                            }  
                            //���ǰ�벿�ֺͶ���İ�����ͬ  
                            if (name.startsWith(packageDirName)) {  
                                int idx = name.lastIndexOf('/');  
                                //�����"/"��β ��һ����  
                                if (idx != -1) {  
                                    //��ȡ���� ��"/"�滻��"."  
                                    packageName = name.substring(0, idx).replace('/', '.');  
                                }  
                                //������Ե�����ȥ ������һ����  
                                if ((idx != -1) || recursive){  
                                    //�����һ��.class�ļ� ���Ҳ���Ŀ¼  
                                    if (name.endsWith(".class") && !entry.isDirectory()) {  
                                        //ȥ�������".class" ��ȡ����������  
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);  
                                        try {  
                                            //��ӵ�classes  
                                        	System.out.println("������:" + packageName + '.' + className);
                                            if (isDemoFile(className)) {
                                            	classes.add(Class.forName(packageName + '.' + className));
                                            }
                                        } catch (ClassNotFoundException e) {  
                                            e.printStackTrace();  
                                        }  
                                      }  
                                }  
                            }  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }   
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
         
        return classes;  
    }  
    
    private static boolean isDemoFile(String className) {
    	String regex = ".*Demo([0-9]{1,2})";
    	Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(className);
		return matcher.matches();
	}

	/** 
     * ���ļ�����ʽ����ȡ���µ�����Class 
     * @param packageName 
     * @param packagePath 
     * @param recursive 
     * @param classes 
     */  
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes){  
        //��ȡ�˰���Ŀ¼ ����һ��File  
        File dir = new File(packagePath);  
        //��������ڻ��� Ҳ����Ŀ¼��ֱ�ӷ���  
        if (!dir.exists() || !dir.isDirectory()) {  
            return;  
        }  
        //������� �ͻ�ȡ���µ������ļ� ����Ŀ¼  
        File[] dirfiles = dir.listFiles(new FileFilter() {  
        //�Զ�����˹��� �������ѭ��(������Ŀ¼) ��������.class��β���ļ�(����õ�java���ļ�)  
              public boolean accept(File file) {  
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));  
              }  
            });  
        //ѭ�������ļ�  
        for (File file : dirfiles) {  
            //�����Ŀ¼ �����ɨ��  
            if (file.isDirectory()) {  
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),  
                                      file.getAbsolutePath(),  
                                      recursive,  
                                      classes);  
            }  
            else {  
                //�����java���ļ� ȥ�������.class ֻ��������  
                String className = file.getName().substring(0, file.getName().length() - 6);  
                try {  
                    //��ӵ�������ȥ  
                    classes.add(Class.forName(packageName + '.' + className));  
                } catch (ClassNotFoundException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }

}  

class RunDemo extends Thread {
	private String classNameSelected;
	public RunDemo(String classNameSelected) {
		this.classNameSelected = classNameSelected;
	}
	public void run() {
		try {
			Method method = Class.forName(classNameSelected).getMethod("main", String[].class);
			method.invoke(null, new Object[] { new String[]{} });
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	};
}
