import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TowerOfHanoi {

	//���������б�ֱ��ʾ�������ӣ�count��ʾ����������numberOfDisks��ʾ��������
	private   ArrayList<Integer> A = new ArrayList<>();
	private   ArrayList<Integer> B = new ArrayList<>();
	private   ArrayList<Integer> C = new ArrayList<>();
	private   PrintWriter output;
	private   int count = 0;
	private   int numberOfDisks =0;
	
	public static void main(String[] args) throws FileNotFoundException{
		
		for(int i=1;i<=5;i++) {
			//���÷����������
			new TowerOfHanoi(i).moveDisks();
		}
	
	} 
	
	public TowerOfHanoi(int n) throws FileNotFoundException {
		
		this.numberOfDisks = n;
		
		//�ڵ�ǰĿ¼�½�TowerOfHanoi_n.txt���ڴ洢��������
		this.output = new PrintWriter(new File("txt/TowerOfHanoi_"+n+".txt"));
		
		//��ʼ������
		for(int i=n;i>0;i--) {
			this.A.add(i);
		}
		
		//�ѳ�ʼ״̬�����ļ�
		this.output.println("��������Ŀ��"+n);
		this.output.println("---------��ʼ״̬---------");
		this.output.print("A: ");
		for(Integer i:this.A) this.output.print(i+" ");
		this.output.println();
		this.output.print("B: ");
		for(Integer i:this.B) this.output.print(i+" ");
		this.output.println();
		this.output.print("C: ");
		for(Integer i:this.C) this.output.print(i+" ");
		this.output.println();		
	
	}


	public void moveDisks() {
		this.moveDisks(this.numberOfDisks,this.A,this.B,this.C);
		this.output.close();
		System.out.println("����ɵ���");
	}
	
	public void moveDisks(int n, ArrayList<Integer> fromTower, ArrayList<Integer> toTower, ArrayList<Integer> auxTower) {
		if(n == 1) {
			move(fromTower,toTower); //����Ҫ�ƶ�������ֻ��1��ʱ������ֱ�ӽ����ƶ���������Ҫ�ݹ�
			
			//ÿһ�ε����������ǰA��B��C����������ļ�������¼
			this.output.println("--------��"+(++this.count)+"�ε���--------");
			this.output.print("A: ");
			for(Integer i:this.A) this.output.print(i+" ");
			this.output.println();
			this.output.print("B: ");
			for(Integer i:this.B) this.output.print(i+" ");
			this.output.println();
			this.output.print("C: ");
			for(Integer i:this.C) this.output.print(i+" ");
			this.output.println();
			System.out.println("����ɵ�"+this.count+"�ε���");
			
		}
		else {
			//�����棨n-1�������ӵ���һ�����壬��fromTower�ƶ���auxTower
			moveDisks(n-1,fromTower,auxTower,toTower);
			//��fromTower��ʣ�µ����뾶�������ƶ���toTower����ֻ��һ��������Ҫ�ƶ��������
			moveDisks(1,fromTower,toTower,auxTower);
			//�Ѹո��ƶ���C���ģ�n-1����������Ϊ�����auxTower�ƶ���toTower
			moveDisks(n-1,auxTower,toTower,fromTower);
		}
		
	}

	public static void move(ArrayList<Integer> fromTower, ArrayList<Integer> toTower) {
		//fromTower�������Ԫ�أ�����������������ӣ���ֵ��ӵ�toTower��Ȼ������Ԫ�ش�fromTowerɾ����
		//ͨ��������ģ�����ӵ��ƶ�
		toTower.add(fromTower.get(fromTower.size()-1));
		fromTower.remove(fromTower.size()-1);
		
	}
	
	
}