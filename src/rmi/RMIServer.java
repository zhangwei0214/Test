package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * @author wei.zhangw
 *
 */
public class RMIServer {

	public static void main(String[] args) {
		try {
			PersonService personService = new PersonServiceImpl();
			// 注册通讯端口
			Registry registry = LocateRegistry.createRegistry(6600);
			
			//bind
			registry.bind("PersonService", personService);
			
			// 注册通讯路径
			//Naming.rebind("rmi://127.0.0.1:6600/PersonService", personService);
			System.out.println("Service Start!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}