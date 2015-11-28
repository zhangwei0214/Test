package enumtest;

/*
 * 重写enum的toString()方法
 * @see http://www.cnblogs.com/happyPawpaw/archive/2013/04/09/3009553.html
 * 
 */
public class TestOverride {
	 public enum Color {
	        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
	        // 成员变量
	        private String name;
	        private int index;

	        // 构造方法
	        private Color(String name, int index) {
	            this.name = name;
	            this.index = index;
	        }

	        // 覆盖方法
	        @Override
	        public String toString() {
	            return this.index + "_" + this.name;
	        }
	    }

	    public static void main(String[] args) {
	        System.out.println(Color.RED.toString());
	    }

}
