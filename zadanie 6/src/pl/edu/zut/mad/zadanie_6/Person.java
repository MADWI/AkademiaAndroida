package pl.edu.zut.mad.zadanie_6;

public class Person {

	private String name;
	private int age;

	public Person(String name, int age) {
		setName(name);
		setAge(age);
	}

	public Person() {
		this("", 0);
	}

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
