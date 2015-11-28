package serialization;

import java.io.Serializable;

public class AObject implements Serializable{

	private String a = "java";
	private String b = "bluedavy";
	private String c = "chapter4";
	private String d = "hello";
	private int i = 100;
	private int j = 10;
	private long m = 100L;
	private boolean isA=true;
	private boolean isB=false;
	private boolean isC=false;
	
	private BObject object = new BObject();
	private BObject bobject = new BObject();
	private BObject cobject = new BObject();
	private BObject dobject = new BObject();
}
