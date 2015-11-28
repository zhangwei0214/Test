package weakreference;

import java.lang.ref.WeakReference;

/**
 * @author wison
 * @see http://www.tuicool.com/articles/imyueq
 */
public class TestWeakReference {

  
  public static void main(String[] args) {
    
    Car car = new Car(22000,"silver");
    WeakReference<Car> weakCar = new WeakReference<Car>(car);
    
    int i=0;
    
    while(true){
    	//System.out.println("here is the strong reference 'car' "+car);
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      if(weakCar.get()!=null){
        i++;
        System.out.println("Object is alive for "+i+" loops - "+weakCar);
        if(i>=10){
        	System.gc();
        }
      }else{
        System.out.println("Object has been collected.");
        break;
      }
    }
  }

}
