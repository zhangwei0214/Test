package performance.cpu;

import java.util.ArrayList;

public class Demo {

	/**
	 * @param args
	 * @throws Exception 
	 * @see java应用.pdf 5.1.1 注意使用jstack [pid]  dump stack信息，  或者使用可视化工具jvisualvm
	 * 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Demo demo = new Demo();
		demo.runTest();
		
	}
	
	private void runTest() throws Exception{
		int count = Runtime.getRuntime().availableProcessors();
		for(int i = 0; i < count; i++){
			new Thread(new ConsumeCPUTask()).start();
		}
		for(int i = 0; i < 200; i++){
			new Thread(new NotConsumeCPUTask()).start();
		}
	}
	
	class ConsumeCPUTask implements Runnable{
		public void run(){
			String str = "alsudhfpiheufpbscubuowehusbciuabcupihbwoupehfpaubsciubsadfasdfasdfasdfasdfasdfasdfsdfasdfasdfsadfsdf";
			float i = 0.002f;
			float j = 232.13243f;
			while(true){
				j = i*j;
				str.indexOf("#");
				ArrayList<String> list = new ArrayList<String>();
				for(int k=0; k < 10000; k++){
					list.add(str+String.valueOf(k));
				}
				list.contains("iii");
				try{
					Thread.sleep(10);
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	class NotConsumeCPUTask implements Runnable
	{
		public void run(){
			while(true){
				try{
					Thread.sleep(10000000);
				}
				catch(InterruptedException e ){
					e.printStackTrace();
				}
			}
		}
	}
}
