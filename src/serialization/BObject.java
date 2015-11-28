package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BObject implements Serializable{
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @see java应用.pdf -> 4.3 序列化与反序列化
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		for(int i = 0;i<10;i++){
			AObject object = new AObject();
			long beginTime = System.currentTimeMillis();
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
			objectOutput.writeObject(object);
			objectOutput.close();
			byteOutput.close();
			
			byte[] bytes = byteOutput.toByteArray();
			System.out.println("Java 序列化耗时:"+ (System.currentTimeMillis()-beginTime));
			System.out.println("Java序列化之后的大小: " + bytes.length);
			
			beginTime = System.currentTimeMillis();
			ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInput = new ObjectInputStream(byteIn);
			objectInput.readObject();
			objectInput.close();
			byteIn.close();
			System.out.println("Java 反序列化耗时:"+ (System.currentTimeMillis()-beginTime));

			
		}
	}

}
