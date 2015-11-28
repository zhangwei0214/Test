package inner;

//外部类
class Out {
  private int age = 12;
   
  //内部类
  class In {
      public void print() {
          System.out.println(age);
      }
  }
}

/**
 * @see http://www.cnblogs.com/nerxious/archive/2013/01/24/2875649.html
 * 内部类的优势就是可以访问外部类对象的成员
 * @author Administrator
 *
 */
public class Demo {
  public static void main(String[] args) {
      Out.In in = new Out().new In();
      in.print();
      //或者采用下种方式访问
      /*
      Out out = new Out();
      Out.In in = out.new In();
      in.print();
      */
  }
}
