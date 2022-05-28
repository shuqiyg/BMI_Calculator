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
public class Calculator {
	double weight, height; //weight in kg, height in cm
	double BMI;
	
	public double computeInCMKG(double weight, double height) {
		if(height <= 0) return 0;
		this.BMI = weight/(Math.pow(height, 2)) * 10000;
		return this.BMI;
	}
	public double computeInINLB(double weight, double height) {
		if(height <= 0) return 0;
	   return this.BMI = weight * 703 /(Math.pow(height,2));
	    ///return this.BMI;
	}
}
