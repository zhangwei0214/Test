package classloader;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Stack;

public class FileSystemLoader {

	/**
	 * http://blog.csdn.net/mousebaby808/article/details/31788325
	 * 加载一个directory下面所有的class文件 
	 * 注意，加载的过程中class.forname 会执行static代码块
	 */
	public static void  loadClassesFromDirectory() {
		// 设置class文件所在根路径  
		// 例如/usr/java/classes下有一个test.App类，则/usr/java/classes即这个类的根路径，而.class文件的实际位置是/usr/java/classes/test/App.class  
		File clazzPath = new File("E:/studyWorkSpace/Test/bin/");  
		  
		// 记录加载.class文件的数量  
		int clazzCount = 0;
		  
		try{
		if (clazzPath.exists() && clazzPath.isDirectory()) {  
		    // 获取路径长度  
		    int clazzPathLen = clazzPath.getAbsolutePath().length() + 1;  
		  
		    Stack<File> stack = new Stack<>();  
		    stack.push(clazzPath);  
		  
		    // 遍历类路径  
		    while (stack.isEmpty() == false) {  
		        File path = stack.pop();  
		        File[] classFiles = path.listFiles(new FileFilter() {  
		            public boolean accept(File pathname) {  
		                return pathname.isDirectory() || pathname.getName().endsWith(".class");  
		            }  
		        });
		        
		        for (File subFile : classFiles) {  
		            if (subFile.isDirectory()) {  
		                stack.push(subFile);  
		            } else {  
		                if (clazzCount++ == 0) {  
		                    Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);  
		                    boolean accessible = method.isAccessible();  
		                    try {  
		                        if (accessible == false) {  
		                            method.setAccessible(true);  
		                        }  
		                        // 设置类加载器  
		                        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();  
		                        // 将当前类路径加入到类加载器中  
		                        method.invoke(classLoader, clazzPath.toURI().toURL());  
		                    } finally {  
		                        method.setAccessible(accessible);  
		                    }  
		                }  
		                // 文件名称  
		                String className = subFile.getAbsolutePath();  
		                className = className.substring(clazzPathLen, className.length() - 6);  
		                className = className.replace(File.separatorChar, '.');  
		                // 加载Class类  
		                try {
							Class.forName(className);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
		                System.out.println("读取应用程序类文件[class="+ className + "]");  
		            }  
		        }  
		    }  
		}  
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	/**
	 * 加载单个文件
	 * http://vivisidea.iteye.com/blog/662620
	 */
	public static void loadSingleClass(){
		try{
			//如果当前classpath下面能够加载到的类  使用URLClassLoader加载是没有用的, 现在加载的classloader是Appclassloader
			//class loader of clazzA : sun.misc.Launcher$AppClassLoader@cdb06e
			//class loader of a : sun.misc.Launcher$AppClassLoader@cdb06e
			//URLClassLoader loader=new URLClassLoader(new URL[]{new URL("file:E:/studyWorkSpace/Test/bin/")});
			//Class clazzA = loader.loadClass("classloader.A");
			
			//注意 动态加载类class文件的时候会递归加载该class里面依赖的所有的class, 以保证加载的class能够正常工作
			//如果依赖的类无法同步进行加载，那么会报NoClassDef异常
			URLClassLoader loader=new URLClassLoader(new URL[]{new URL("file:E:\\studyWorkSpace\\JDBCTest\\target\\classes\\")});
			Class clazzA = loader.loadClass("me.ben.entity.Person");
			//E:\studyWorkSpace\JDBCTest\target\classes
			//me\ben\entity\Person
			
			
			Object a = clazzA.newInstance();
			System.out.println("class loader of clazzA : " + clazzA.getClassLoader());
			System.out.println("class loader of a : " + a.getClass().getClassLoader());
			
			//这里可以用reflection调用方法 ?  可以 ， 注意参数列表 (这里是String.class)
			//Method method = clazzA.getDeclaredMethod("setId", String.class);
			//method.invoke(a, "1");//这里可以成功
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 动态加载jar文件
	 * http://blog.csdn.net/mousebaby808/article/details/31788325
	 */
	public static void loadJarsFromDirectory(){
		try{
			// 系统类库路径  
			File libPath = new File("E:\\studyWorkSpace\\MyBatis_study\\WebContent\\WEB-INF\\lib\\");  
			  
			// 获取所有的.jar和.zip文件  
			File[] jarFiles = libPath.listFiles(new FilenameFilter() {  
			    public boolean accept(File dir, String name) {  
			        return name.endsWith(".jar") || name.endsWith(".zip");  
			    }  
			});  
			  
			if (jarFiles != null) {  
			    // 从URLClassLoader类中获取类所在文件夹的方法  
			    // 对于jar文件，可以理解为一个存放class文件的文件夹  
			    Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);  
			    boolean accessible = method.isAccessible();     // 获取方法的访问权限  
			    try {  
			        if (accessible == false) {  
			            method.setAccessible(true);     // 设置方法的访问权限  
			        }  
			        // 获取系统类加载器  
			        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();  
			        for (File file : jarFiles) {
			            URL url = file.toURI().toURL();  
			            try {  
			                method.invoke(classLoader, url);    
			                System.out.println("读取jar文件[name={}]" +  file.getName());  
			            } catch (Exception e) {  
			            	System.out.println("读取jar文件[name={}]失败" +  file.getName());  
			            }  
			        }
			        //在mysql-connector-java-5.1.31.jar 中的类 -> mysql驱动
			        //Class clazzMySQLDriver = classLoader.loadClass("com.mysql.jdbc.Driver");
			        URLClassLoader.class.forName("com.mysql.jdbc.Driver");
			    } finally {  
			        method.setAccessible(accessible);  
			    }  
			}  
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 动态加载class文件:http://blog.csdn.net/mousebaby808/article/details/31788325
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//loadClassesFromDirectory();
		//loadSingleClass();
		loadJarsFromDirectory();
	}
}
