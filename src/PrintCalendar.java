import java.util.Scanner;

public class PrintCalendar {
	
	//���������
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		//����û��������������
		System.out.print("����������(��ʽ����.�£���2019.9): ");
		String yearAndMonth = input.nextLine();
		//�ѿͻ���������ݷ���������£�����תΪ��������
		int year,month;
		year = Integer.parseInt(yearAndMonth.substring(0,yearAndMonth.indexOf('.')));
		month = Integer.parseInt(yearAndMonth.substring(yearAndMonth.indexOf('.')+1));
		
		//��ӡ������ͷ��
		printMonthTitle(year,month);
		//��ӡ���������ڲ���
		printMonthbody(year,month);
	}

	//��ӡ������ͷ������
	public static void printMonthTitle(int year, int month) {
		//���������Ч����ʵ����ͨ������һ�����飬Ȼ��ȡ�����ֵ��ʵ�֣�������Ҫ����һ������
		String nameOfMonth = getNameOfMonth(month);
		System.out.printf("\n%13s-%-13d\n",nameOfMonth,year);
		for(int i=0;i<27;i++) System.out.print('=');
		System.out.println("\nMon Tue Wed Thu Fri Sat Sun");
	}
	
	//���ĳ���µ�Ӣ������
	//���������Ч����ʵ����ͨ������һ�����飬Ȼ��ȡ�����ֵ��ʵ�֣�������Ҫ����һ������
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

	//�����ӡ���·����ڲ���
	public static void printMonthbody(int year, int month) {
		// TODO Auto-generated method stub
		int count=0;
		int firstDayOfMonth=getFirstDayOfMonth(year,month);
		int totalDayOfMonth=getTotalDayOfMonth(year,month);
		
		//��ӡ1��֮ǰ�������ո�
		for(int i=1;i<firstDayOfMonth;i++) {
			System.out.print("    ");
			count++;
		}
		//��ʼ��1�ſ�ʼ��ӡ��һֱ�������½���
		for(int day=1;day<=totalDayOfMonth;day++) {
			if(count==7) {
				System.out.print("\n");
				count = 0; 
			}
			System.out.printf("%-4d",day);
			count++;
		}
	}

	//���ĳ���µ�������
	public static int getTotalDayOfMonth(int year, int month) {
		//���ظ����µ�����
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
			return 31;
		else if(month==4||month==6||month==9||month==11)
			return 30;
		else{
			if(isLeapYear(year)) return 29;
			else return 28;
		}
	}

	//���ĳ�µ�һ�������ڼ�
	public static int getFirstDayOfMonth(int year, int month) {
		// ����ĳ���µĵ�һ�������ڼ�,��֪2019-1-1���ܶ�
		int totalDayFrom2019_1_1=0,day=2;
		if(year>=2019) {
			//�ۼ�2019�굽year֮�����������������
			if(year>2019) {
				for(int i=2019;i<year;i++) {
					if(isLeapYear(i)){
						totalDayFrom2019_1_1 += 366;
					}else {
						totalDayFrom2019_1_1 += 365;
					}
				}
			}
			//�ۼӸ��·ݵ�����1�µ�������
			for(int j=1;j<month;j++) {
				totalDayFrom2019_1_1 += getTotalDayOfMonth(year,j);
			}
		}
		else {
			//�ۼ�2019�굽year֮�����������������
			for(int i=year+1;i<2019;i++) {
				if(isLeapYear(i)){
					totalDayFrom2019_1_1 += 366;
				}else {
					totalDayFrom2019_1_1 += 365;
				}
			}
			//�ۼӸ��·ݵ�����12�µ�������
			for(int j=12;j>=month;j--) {
				totalDayFrom2019_1_1 += getTotalDayOfMonth(year,j);
				}
		}
		
		//����������%7�õ����µ�һ�����ڼ���������Ϊ0��Ϊ������
		if(year>=2019) {
			day = ((totalDayFrom2019_1_1%7)+day)%7;
		}else {
			day = (day-(totalDayFrom2019_1_1%7)+7)%7;
		}
		if(day==0) day=7;
		
		return day;
	}
	
	//����ĳ���Ƿ�Ϊ����
	public static boolean isLeapYear(int year) {
		//�ж�һ������Ƿ�������
		boolean leapYearOrNot=false;
		if ((year%4==0 && year%100!=0)||year%400==0) {
			leapYearOrNot=true;
		}
		else 
			leapYearOrNot=false;
		return leapYearOrNot;
	}

}
