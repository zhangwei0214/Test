package concurrent;

/**
 * number=42和ready=true的执行顺序可能会被jvm reorder, 结果不可预知
 * 在我的netease机器上面测试一直输出42
 * @see http://blog.csdn.net/cutesource/article/details/5780486
 * @author wei.zhangw
 *
 */
public class NoVisibility {  
    private static boolean ready;  
    private static int number;  
    private static class ReaderThread extends Thread {  
        public void run() {  
            while (!ready)  
                Thread.yield();  
            System.out.println(number);
        }  
    }  
    public static void main(String[] args) {  
        new ReaderThread().start();  
        number = 42;  
        ready = true;  
    }  
}

