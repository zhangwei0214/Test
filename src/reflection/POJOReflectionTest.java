package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;

/**
 * 运用Java的反射机制，输出POJO类的字段和方法（新增了Annotation的修饰）
 * @author lvwenyong
 *
 */
public class POJOReflectionTest {
 public static void main(String[] args) throws InstantiationException, IllegalAccessException
 {
  try 
  {
   //加载WorkPOJO,注意这里一定要写全类名，包括包名，因为包名是类名的一部分
   Class pojo = Class.forName("reflection.WorkerPOJO");
   //获取域的数组
   Field []fieldList = pojo.getDeclaredFields();
   //获取方法的数组
   Method []methodList = pojo.getDeclaredMethods();
   
   System.out.println("WorkerPOJO类的所有字段：");
   System.out.println("修饰符" + "    " + "类型" + "                   " + "字段名");
   
   for(int i = 0;i < fieldList.length;i++)
   {
    Field field = fieldList[i];
    //用下面的形式获取具体的修饰符
    System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getType() + " " + field.getName());
   }
   
   System.out.println();
   System.out.println("WorkerPOJO类的所有方法(不包括annotation修饰的方法)：");
   
   for(int j = 0;j < methodList.length;j++)
   {
    Method method = methodList[j];
    //判断方法是否被Annotation修饰
    boolean methodAnnotation = method.isAnnotationPresent(WorkerPOJOAnnotation.class);
    
    //如果被annotation修饰，则过滤掉该方法，即不输出
    if(methodAnnotation)
    {
     continue;
    }
    //获取方法参数列表
    Class parameters[] = method.getParameterTypes();
    
    System.out.print(Modifier.toString(method.getModifiers()) + " " + method.getReturnType() + " " + method.getName() + " (");
    
    for(int k = 0;k < parameters.length;k++)
    {
     System.out.print(parameters[k].toString());
    }
    
    System.out.println(")");
    
   }
  }
  catch(ClassNotFoundException exception1)
  {
   exception1.printStackTrace();
  }
  
 }
}



