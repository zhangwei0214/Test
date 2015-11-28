package io.reader_;

import java.io.*;

public class FileRead {
	/*
	 * @see http://www.cnblogs.com/pepcod/archive/2013/01/20/2913435.html
	 */
	public static void main(String[] args) {
		File fin, fout;
		BufferedReader bf = null;
		PrintWriter pw = null;
		try {
			/*
			//使用reader必须是读取文本文件，  如果操作jpg文件， copy出来的文件打不开(损坏)
			fin = new File("IO.jpg"); // 注意文件与程序都要在同一个文件夹下。zzc.txt为要被复制的文件。
			fout = new File("zzccopy.jpg"); // 如果没有会自动创建。
			*/
			fin = new File("zzc.txt"); // 注意文件与程序都要在同一个文件夹下。zzc.txt为要被复制的文件。
			fout = new File("zzccopy.txt"); // 如果没有会自动创建。
			bf = new BufferedReader(new FileReader(fin));
			pw = new PrintWriter(fout); // PrintWriter为打印流，也可以使用BufferedWriter.
			String line = bf.readLine();
			while (line != null) {
				pw.println(line);
				/* equals to ->
				pw.write(line);
				pw.write("\n");
				*/
				line = bf.readLine();
			}
			System.out.println("copy finished with reader & writer.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭 文件。
				if (bf != null) {
					bf.close();
					bf = null;
				}
				if (pw != null) {
					pw.close();
					pw = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}