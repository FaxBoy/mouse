import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class t {
    private static ArrayList<String> listname = new ArrayList<String>();

    //    public static void main(String[] args)throws Exception{
//    		String path="/tools/data3/";
//        readAllFile(path);
//        System.out.println(listname.size());
//        for (int i = 0; i < listname.size(); i++) {
//			System.out.println(path+listname.get(i));
//			File file = new File(path+listname.get(i));
//			file.renameTo(new File(path+listname.get(i)+"sss"));
//		}
//    }
    public static void readAllFile(String filepath) {
        File file = new File(filepath);
        if (!file.isDirectory()) {
            listname.add(file.getName());
        } else if (file.isDirectory()) {
            System.out.println("文件");
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath);
                if (!readfile.isDirectory()) {
                    listname.add(readfile.getName());
                } else if (readfile.isDirectory()) {
                    readAllFile(filepath + "//" + filelist[i]);//递归  
                }
            }
        }
        for (int i = 0; i < listname.size(); i++) {
            System.out.println(listname.get(i));
        }
    }

    public static int minimumTotal(List<List<Integer>> triangle) {

//        int min = 0;
//
//        for(int i=0;i<triangle.size();i++){
//            List<Integer> arr = triangle.get(i);
//            int minNum = arr.get(0);
//            for(int j = 1;j<arr.size();j++){
//                if(minNum<arr.get(j)){
//                    minNum = arr.get(j);
//                }
//            }
//            min += minNum ;
//        }
//        return min;


        int n = triangle.size();
        int m = triangle.get(n - 1).size();
        int[][] mins = new int[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == n - 1) {
                    mins[i][j] = triangle.get(i).get(j);
                } else {
                    mins[i][j] = triangle.get(i).get(j) + Math.min(mins[i + 1][j], mins[i + 1][j + 1]);
                }
            }
        }

        System.out.printf(mins.toString());

        return mins[0][0];


    }

    public static double findMaxAverage(int[] nums, int k) {
        int max = 0;
        for(int i = 0; i<k;i++){
            max += nums[i];
        }
        int reMax = max;
        for(int i = 1;i<nums.length-k+1;i++){
            int  max2 =  max - nums[i-   1] +nums[i+k-1];
            if(nums[i+k-1]- nums[i-1] >0 && reMax<max2)
                reMax = max2;
            max = max2;
        }

        return ((double)reMax)/k;
    }

    public static void main(String[] args) {
        //[[-1],[3,2],[-3,1,-1]]
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(-1);
//
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(3);
//        list2.add(2);
//
//        List<Integer> list3 = new ArrayList<>();
//        list3.add(-3);
//        list3.add(1);
//        list3.add(-1);
//
//        List<List<Integer>> l = new ArrayList<>();
//        l.add(list1);
//        l.add(list2);
//        l.add(list3);
//
//        minimumTotal(l);


        System.out.print(findMaxAverage(new int[]{1,2,3,4,5,6,1},3));


    }
}
