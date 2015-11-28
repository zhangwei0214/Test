package classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class Test {

	/**
	 * 参考
	 * http://www.2cto.com/kf/201312/261175.html
	 * @param args
	 */
	public static void main(String[] args) {
		//SystemClassLoaderTest();
		//CustomizedClassLoaderTest();
		printClassLoaderHierachy();
		//String work_folder = System.getProperty("user.dir");
		//System.out.println("work_folder: " + work_folder);
	}
	
	/**
	 * They are same class : true
	 * 同一个classloader(同一个classloader & 类全名相同)
	 * 
	 */
	public static void SystemClassLoaderTest(){
		try{
			//Class.forName 使用当前class的classloader加载类
		Class  c1 = Class.forName("classloader.Test");
		Class  c2 = Class.forName("classloader.Test");
		System.out.println("They are same class : " + (c1 == c2));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * They are same class : false
	 */
	public static void CustomizedClassLoaderTest(){
		// TODO Auto-generated method stub
		//System.out.println(System.getProperty("user.dir"));	//当前的工作目录
		try{
		//使用工作目录能够让这个demo移植
		String url = "file:/" + System.getProperty("user.dir") +"/newClass/";
		URLClassLoader ucl1 = new URLClassLoader(new URL[]{new URL(url)});
		URLClassLoader ucl2 = new URLClassLoader(new URL[]{new URL(url)});
		Class  c1 = ucl1.loadClass("me.ben.entity.Person");
		Class  c2 = ucl2.loadClass("me.ben.entity.Person");
		System.out.println("They are same class : " + (c1 == c2));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * output
	 * 
		java.net.URLClassLoader@145f0e3
		sun.misc.Launcher$AppClassLoader@cdb06e
		sun.misc.Launcher$ExtClassLoader@1fa1bb6
	 */
	public static void printClassLoaderHierachy(){
		try{
			String url = "file:/" + System.getProperty("user.dir") +"/newClass/";
			URLClassLoader ucl1 = new URLClassLoader(new URL[]{new URL(url)});
			Class  c1 = ucl1.loadClass("me.ben.entity.Person");
			ClassLoader loader = c1.getClassLoader();
			while(loader != null){
				System.out.println(loader);
				loader=loader.getParent();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
