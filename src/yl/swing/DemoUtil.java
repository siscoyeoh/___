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

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("JFreeChart官方示例");
       
        
        for (Class clas :classes) {  
            System.out.println(clas.getName());  
            top.add(new DefaultMutableTreeNode(clas.getName()));
        }  
        
 
        final JTree tree = new JTree(top);
//        tree.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(tree);
        
        JFrame f = new JFrame("JFreeChart官方示例");
        f.add(scrollPane);
        f.setSize(300, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 添加选择事件
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
                    System.out.println("你选择了：" + classNameSelected);
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
     * 取得某个接口下所有实现这个接口的类 
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
                // 获取当前的包名  
                String packageName = c.getPackage().getName();  
                // 获取当前包下以及子包下所以的类  
                List<Class<?>> allClass = getClasses(packageName);  
                System.out.println("获取当前包下以及子包下所以的类:" + allClass);
                if(allClass != null) {  
                    returnClassList = new ArrayList<Class>();  
                    for(Class classes : allClass) {  
                        // 判断是否是同一个接口  
//                        if(c.isAssignableFrom(classes)) {  
                            // 本身不加入进去  
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
     * 从包package中获取所有的Class 
     * @param pack 
     * @return 
     */  
    public static List<Class<?>> getClasses(String packageName){  
          
        //第一个class类的集合  
        List<Class<?>> classes = new ArrayList<Class<?>>();  
        //是否循环迭代  
        boolean recursive = true;  
        //获取包的名字 并进行替换  
        String packageDirName = packageName.replace('.', '/');  
        //定义一个枚举的集合 并进行循环来处理这个目录下的things  
        Enumeration<URL> dirs;  
        try {  
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);  
            //循环迭代下去  
            while (dirs.hasMoreElements()){  
                //获取下一个元素  
                URL url = dirs.nextElement();  
                //得到协议的名称  
                String protocol = url.getProtocol();  
                //如果是以文件的形式保存在服务器上  
                if ("file".equals(protocol)) {  
                    //获取包的物理路径  
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");  
                    //以文件的方式扫描整个包下的文件 并添加到集合中  
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);  
                } else if ("jar".equals(protocol)){  
                    //如果是jar包文件   
                    //定义一个JarFile  
                    JarFile jar;  
                    try {  
                        //获取jar  
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();  
                        //从此jar包 得到一个枚举类  
                        Enumeration<JarEntry> entries = jar.entries();  
                        //同样的进行循环迭代  
                        while (entries.hasMoreElements()) {  
                            //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件  
                            JarEntry entry = entries.nextElement();  
                            String name = entry.getName();  
                            //如果是以/开头的  
                            if (name.charAt(0) == '/') {  
                                //获取后面的字符串  
                                name = name.substring(1);  
                            }  
                            //如果前半部分和定义的包名相同  
                            if (name.startsWith(packageDirName)) {  
                                int idx = name.lastIndexOf('/');  
                                //如果以"/"结尾 是一个包  
                                if (idx != -1) {  
                                    //获取包名 把"/"替换成"."  
                                    packageName = name.substring(0, idx).replace('/', '.');  
                                }  
                                //如果可以迭代下去 并且是一个包  
                                if ((idx != -1) || recursive){  
                                    //如果是一个.class文件 而且不是目录  
                                    if (name.endsWith(".class") && !entry.isDirectory()) {  
                                        //去掉后面的".class" 获取真正的类名  
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);  
                                        try {  
                                            //添加到classes  
                                        	System.out.println("查找类:" + packageName + '.' + className);
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
     * 以文件的形式来获取包下的所有Class 
     * @param packageName 
     * @param packagePath 
     * @param recursive 
     * @param classes 
     */  
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes){  
        //获取此包的目录 建立一个File  
        File dir = new File(packagePath);  
        //如果不存在或者 也不是目录就直接返回  
        if (!dir.exists() || !dir.isDirectory()) {  
            return;  
        }  
        //如果存在 就获取包下的所有文件 包括目录  
        File[] dirfiles = dir.listFiles(new FileFilter() {  
        //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)  
              public boolean accept(File file) {  
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));  
              }  
            });  
        //循环所有文件  
        for (File file : dirfiles) {  
            //如果是目录 则继续扫描  
            if (file.isDirectory()) {  
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),  
                                      file.getAbsolutePath(),  
                                      recursive,  
                                      classes);  
            }  
            else {  
                //如果是java类文件 去掉后面的.class 只留下类名  
                String className = file.getName().substring(0, file.getName().length() - 6);  
                try {  
                    //添加到集合中去  
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
