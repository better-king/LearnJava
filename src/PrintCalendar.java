import java.util.Scanner;

public class PrintCalendar {
	
	//主函数入口
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		//获得用户输入的年月数据
		System.out.print("请输入年月(格式：年.月，如2019.9): ");
		String yearAndMonth = input.nextLine();
		//把客户输入的数据分离出年与月，并且转为整数类型
		int year,month;
		year = Integer.parseInt(yearAndMonth.substring(0,yearAndMonth.indexOf('.')));
		month = Integer.parseInt(yearAndMonth.substring(yearAndMonth.indexOf('.')+1));
		
		//打印日历的头部
		printMonthTitle(year,month);
		//打印日历的日期部分
		printMonthbody(year,month);
	}

	//打印日历的头部部分
	public static void printMonthTitle(int year, int month) {
		//这个方法的效果其实可以通过创建一个数组，然后取数组的值来实现，而不需要创建一个方法
		String nameOfMonth = getNameOfMonth(month);
		System.out.printf("\n%13s-%-13d\n",nameOfMonth,year);
		for(int i=0;i<27;i++) System.out.print('=');
		System.out.println("\nMon Tue Wed Thu Fri Sat Sun");
	}
	
	//获得某个月的英文名称
	//这个方法的效果其实可以通过创建一个数组，然后取数组的值来实现，而不需要创建一个方法
	public static String getNameOfMonth(int month) {
		// TODO Auto-generated method stub
		String nameOfMonth="January";
		switch(month) {
		case 1: nameOfMonth = "January";break;
		case 2: nameOfMonth = "February";break;
		case 3: nameOfMonth = "March";break;
		case 4: nameOfMonth = "April";break;
		case 5: nameOfMonth = "May";break;
		case 6: nameOfMonth = "June";break;
		case 7: nameOfMonth = "July";break;
		case 8: nameOfMonth = "August";break;
		case 9: nameOfMonth = "September";break;
		case 10: nameOfMonth = "October";break;
		case 11: nameOfMonth = "November";break;
		case 12: nameOfMonth = "Decmber";break;
		}
		
		return nameOfMonth;
	}

	//输出打印的月份日期部分
	public static void printMonthbody(int year, int month) {
		// TODO Auto-generated method stub
		int count=0;
		int firstDayOfMonth=getFirstDayOfMonth(year,month);
		int totalDayOfMonth=getTotalDayOfMonth(year,month);
		
		//打印1号之前的日历空格
		for(int i=1;i<firstDayOfMonth;i++) {
			System.out.print("    ");
			count++;
		}
		//开始从1号开始打印，一直到整个月结束
		for(int day=1;day<=totalDayOfMonth;day++) {
			if(count==7) {
				System.out.print("\n");
				count = 0; 
			}
			System.out.printf("%-4d",day);
			count++;
		}
	}

	//获得某个月的总天数
	public static int getTotalDayOfMonth(int year, int month) {
		//返回各个月的天数
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
			return 31;
		else if(month==4||month==6||month==9||month==11)
			return 30;
		else{
			if(isLeapYear(year)) return 29;
			else return 28;
		}
	}

	//算出某月第一天是星期几
	public static int getFirstDayOfMonth(int year, int month) {
		// 返回某个月的第一天是星期几,已知2019-1-1是周二
		int totalDayFrom2019_1_1=0,day=2;
		if(year>=2019) {
			//累加2019年到year之间的所有整年总天数
			if(year>2019) {
				for(int i=2019;i<year;i++) {
					if(isLeapYear(i)){
						totalDayFrom2019_1_1 += 366;
					}else {
						totalDayFrom2019_1_1 += 365;
					}
				}
			}
			//累加该月份到该年1月的总天数
			for(int j=1;j<month;j++) {
				totalDayFrom2019_1_1 += getTotalDayOfMonth(year,j);
			}
		}
		else {
			//累加2019年到year之间的所有整年总天数
			for(int i=year+1;i<2019;i++) {
				if(isLeapYear(i)){
					totalDayFrom2019_1_1 += 366;
				}else {
					totalDayFrom2019_1_1 += 365;
				}
			}
			//累加该月份到该年12月的总天数
			for(int j=12;j>=month;j--) {
				totalDayFrom2019_1_1 += getTotalDayOfMonth(year,j);
				}
		}
		
		//计算总天数%7得到该月第一天星期几，如果结果为0即为星期天
		if(year>=2019) {
			day = ((totalDayFrom2019_1_1%7)+day)%7;
		}else {
			day = (day-(totalDayFrom2019_1_1%7)+7)%7;
		}
		if(day==0) day=7;
		
		return day;
	}
	
	//计算某年是否为闰年
	public static boolean isLeapYear(int year) {
		//判断一个年份是否是闰年
		boolean leapYearOrNot=false;
		if ((year%4==0 && year%100!=0)||year%400==0) {
			leapYearOrNot=true;
		}
		else 
			leapYearOrNot=false;
		return leapYearOrNot;
	}

}
