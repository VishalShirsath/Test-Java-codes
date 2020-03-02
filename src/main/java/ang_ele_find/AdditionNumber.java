package ang_ele_find;

public class AdditionNumber {

	public static void main(String[] args) {
		  int sum = 0;

		   for(int i=1;i<=100;i++) {

		      sum = sum + i;

		   } 

		   System.out.println("The sum of first 100 numbers is : "+sum);

	
	
	
	 for(int j=1;j<=100;j++) {
		 
		 if(j%5==0){
			 
			 System.out.println("divisible by 5"+ "\t"+ j);
			 
		 }
		 
	 }

}
}