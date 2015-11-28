package reflection;

public class WorkerPOJO {
	private String name;
	private int age;

	/**
	 * 用Annotation修饰
	 * 
	 * @return 姓名
	 */
	@WorkerPOJOAnnotation(name = "landon", age = 22)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}