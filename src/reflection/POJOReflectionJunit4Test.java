package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 关于Java反射以及Annotation的一个TestCase
 * @author lvwenyong
 *
 */
public class POJOReflectionJunit4Test {
 private Class pojo;
 private Field []fieldList;
 private Method[] methodList;
 @Before
 public void setUp() throws Exception {
  //加载类WorkPOJO
  pojo = Class.forName("reflection.WorkerPOJO");
  //获取域的数组
  fieldList = pojo.getDeclaredFields();
  //获取方法的数组
  methodList = pojo.getDeclaredMethods();
 }
 
 //测试字段和方法的个数
 @Test
 public void testSize()
 {
  Assert.assertEquals(2, fieldList.length);
  Assert.assertEquals(4, methodList.length);
 }
 //测试字段是否带有annotations
 @Test
 public void isFieldAnnotation()
 {
  for(int i = 0;i < fieldList.length;i++)
  {
   Assert.assertEquals(false, fieldList[i].isAnnotationPresent(WorkerPOJOAnnotation.class));
  }
 }
 
 //测试方法是否带有annotations
 @Test
 public void isMethodAnnotation()
 {
  int num=0;
  for(int i = 0;i < methodList.length;i++)
  {
	  if(methodList[i].isAnnotationPresent(WorkerPOJOAnnotation.class))
		  num++;
  }
  Assert.assertEquals(1, num);	//只有一个方法加了@WorkerPOJOAnnotation
 }
 
 @After 
 public void tearDown() throws Exception {
 }
}

