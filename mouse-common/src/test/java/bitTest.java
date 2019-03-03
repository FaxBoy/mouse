
public class bitTest {

    public static void main(String[] args) {
        int [] arr=new int[] {2,2,1,4,1,5,4};

        int s= arr[0];

        System.out.println(arr[0]^arr[1]^arr[2]^arr[3]^arr[4]^arr[5]^arr[6]);

        for (int i = 1; i < arr.length; i++){
            s^=arr[i];
        }
        System.out.println(s);

        String d = "sdfasdf";

        System.out.println(d.charAt(2));

        System.out.println(Math.max(3,4));


    }

}
