/********************************************** 
Workshop # 6
Course: JAC433
Last Name:Yang
First Name:Shuqi
ID:132162207
Section:NBB 
This assignment represents my own work in accordance with Seneca Academic Policy. 
Signature 
Date:2022-03-20
**********************************************/ 

public class BMIStatus {
	private static int counts = 0;
	private int id;
	private String BMI, wStatus;
	boolean highlighted = false;
	public BMIStatus(String BMI, String wStatus, boolean highlighted) {
		this.BMI = BMI;
		this.wStatus = wStatus;
		this.highlighted = highlighted;
		this.id = ++counts;
	} 
	public String getBMI(){
		return this.BMI;
	}
	public int getID() {
		return this.id;
	}
	public String getWStatus(){
		return this.wStatus; 
	}
	public boolean getHighlighted() 
	{
		return this.highlighted;
	}
}
