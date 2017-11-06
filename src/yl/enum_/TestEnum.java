package yl.enum_;

public class TestEnum {

	public static void main(String[] args) {
		TabName[] types = TabName.values();
		for (int i = 0; i < types.length; i++) {
			System.out.println(types[i]);
		}
	}

}
