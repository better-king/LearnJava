import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TowerOfHanoi {

	//三个数组列表分别表示三根柱子，count表示调换次数，numberOfDisks表示盘子总数
	private   ArrayList<Integer> A = new ArrayList<>();
	private   ArrayList<Integer> B = new ArrayList<>();
	private   ArrayList<Integer> C = new ArrayList<>();
	private   PrintWriter output;
	private   int count = 0;
	private   int numberOfDisks =0;
	
	public static void main(String[] args) throws FileNotFoundException{
		
		for(int i=1;i<=5;i++) {
			//调用方法解决问题
			new TowerOfHanoi(i).moveDisks();
		}
	
	} 
	
	public TowerOfHanoi(int n) throws FileNotFoundException {
		
		this.numberOfDisks = n;
		
		//在当前目录新建TowerOfHanoi_n.txt用于存储调换过程
		this.output = new PrintWriter(new File("txt/TowerOfHanoi_"+n+".txt"));
		
		//初始化柱子
		for(int i=n;i>0;i--) {
			this.A.add(i);
		}
		
		//把初始状态存入文件
		this.output.println("总盘子数目："+n);
		this.output.println("---------初始状态---------");
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
		System.out.println("已完成调换");
	}
	
	public void moveDisks(int n, ArrayList<Integer> fromTower, ArrayList<Integer> toTower, ArrayList<Integer> auxTower) {
		if(n == 1) {
			move(fromTower,toTower); //当需要移动的盘子只有1个时，可以直接进行移动，而不需要递归
			
			//每一次调换均输出当前A、B、C柱的情况到文件中做记录
			this.output.println("--------第"+(++this.count)+"次调换--------");
			this.output.print("A: ");
			for(Integer i:this.A) this.output.print(i+" ");
			this.output.println();
			this.output.print("B: ");
			for(Integer i:this.B) this.output.print(i+" ");
			this.output.println();
			this.output.print("C: ");
			for(Integer i:this.C) this.output.print(i+" ");
			this.output.println();
			System.out.println("已完成第"+this.count+"次调换");
			
		}
		else {
			//把上面（n-1）个盘子当做一个整体，从fromTower移动到auxTower
			moveDisks(n-1,fromTower,auxTower,toTower);
			//把fromTower仅剩下的最大半径的盘子移动到toTower（即只有一个盘子需要移动的情况）
			moveDisks(1,fromTower,toTower,auxTower);
			//把刚刚移动到C柱的（n-1）个盘子作为整体从auxTower移动到toTower
			moveDisks(n-1,auxTower,toTower,fromTower);
		}
		
	}

	public static void move(ArrayList<Integer> fromTower, ArrayList<Integer> toTower) {
		//fromTower最大索引元素（即柱子最上面的盘子）的值添加到toTower，然后把这个元素从fromTower删除，
		//通过这两步模拟盘子的移动
		toTower.add(fromTower.get(fromTower.size()-1));
		fromTower.remove(fromTower.size()-1);
		
	}
	
	
}