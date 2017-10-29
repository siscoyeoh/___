package checkclimate;

import java.text.ParseException;
import java.util.Date;

import yl.CM;

public class Test001 {

	public static void main(String[] args) throws ParseException {
		int min = 1;
		int max = 3;
		for (int i = 20; i <= 340; i += 30) {
			String sql = "SELECT * FROM \"ZULSUSER\".\"CON_WEATHER_ELEMENTS\"\n" +
					" WHERE BEIJINGTIME >= 1293811200000 AND BEIJINGTIME < 1483200000000"
					+ " AND BJMM = 2 AND AVE_WIND_DIRECTION IN ('" + i + "', '" + (i + 10) + "','" + (i + 20) + "');";
			sql = "SELECT AVE_WIND_DIRECTION,AVE_WIND_SPEED FROM \"ZULSUSER\".\"CON_WEATHER_ELEMENTS\"\n" +
					" WHERE BEIJINGTIME >= 1293811200000 AND BEIJINGTIME < 1483200000000 AND BJMM = 4"
					+ " AND AVE_WIND_DIRECTION IN ('" + (i + 0) + "', '" + (i + 10) + "','" + (i + 20) + "')\n" +
					" AND AVE_WIND_SPEED >= " + min + " AND AVE_WIND_SPEED <= " + max + ";";
			System.out.println(sql);
		}
	}

	public static void test01() throws ParseException {
		String yyyy_MM_dd_HH_mm_ss = "2011-01-01 00:00:00";
		System.out.println(CM.Str2Date2(yyyy_MM_dd_HH_mm_ss).getTime());
		System.out.println(new Date(CM.Str2Date2(yyyy_MM_dd_HH_mm_ss).getTime()));
		yyyy_MM_dd_HH_mm_ss = "2017-01-01 00:00:00";
		System.out.println(CM.Str2Date2(yyyy_MM_dd_HH_mm_ss).getTime());
		System.out.println(new Date(CM.Str2Date2(yyyy_MM_dd_HH_mm_ss).getTime()));
	}

}
