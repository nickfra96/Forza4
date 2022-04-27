package homework;

import javax.swing.JButton;

public class Bottoni extends JButton{
	int c;
	int r;
	JButton Bottone;
	
	public Bottoni(String s){
		Bottone= new JButton(s);
		
	}
	
	
	
	public void setC(int c){
		this.c=c;
		
	}
	
	public int getC(){
		return c;
		
	}
	
	public void setR(int r){
		this.r= r;
		
	}
	
	public int getR(){
		return r;
	}


}
