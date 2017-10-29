package yl.java;

public class EnumTest {

	public static void main(String[] args) {
		System.out.println(CautionLevel.Normal);
		System.out.println(CautionLevel.Warn.ordinal());
		System.out.println(CautionLevel.Warn.compareTo(CautionLevel.Danger));
		
		System.out.println("-------------");
		System.out.println(CautionLevel.Danger.equals(CautionLevel.Danger));
		System.out.println(CautionLevel.Danger == CautionLevel.Danger);

		System.out.println("-------------");
		System.out.println(CautionLevel.Danger.equals(CautionLevel.Normal));
		System.out.println(CautionLevel.Danger == CautionLevel.Normal);

		System.out.println("-------------");
		System.out.println(CautionLevel.Normal.ordinal());
		System.out.println(CautionLevel.Warn.ordinal());
		System.out.println(CautionLevel.Danger.ordinal());
	}
	
	enum CautionLevel {
		Normal, Warn, Danger
	}

}
