import java.util.ArrayList;

public class d {

	public static void main(String[] args) {
		int [] arrx=new int[] {2,1,4,6,3,7,9,8};
		int [] arr = new int[] {1,2,3,4,5,6,7,8,9};
		ArrayList<Integer> arrlist = new ArrayList<>() ;
		int y=0;
		for (int i = 0; i < arr.length-1; i++) {
			arr[arrx[i]-1]=0;
			y +=arr[i];
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			//System.out.println(arr[i]);
			System.out.print(arr[i]+",");
		}
		System.out.println();
		System.out.println(y);
	}
	
}
