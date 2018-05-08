import java.io.File;
import java.util.ArrayList;

public class t {
	private static ArrayList<String> listname = new ArrayList<String>();  
    public static void main(String[] args)throws Exception{  
    		String path="/tools/data3/";
        readAllFile(path);  
        System.out.println(listname.size());
        for (int i = 0; i < listname.size(); i++) {
			System.out.println(path+listname.get(i));
			File file = new File(path+listname.get(i));  
			file.renameTo(new File(path+listname.get(i)+"sss")); 
		}
    }  
    public static void readAllFile(String filepath) {  
        File file= new File(filepath);  
        if(!file.isDirectory()){  
            listname.add(file.getName());  
        }else if(file.isDirectory()){  
            System.out.println("文件");  
            String[] filelist=file.list();  
            for(int i = 0;i<filelist.length;i++){  
                File readfile = new File(filepath);  
                if (!readfile.isDirectory()) {  
                    listname.add(readfile.getName());  
                } else if (readfile.isDirectory()) {  
                    readAllFile(filepath + "//" + filelist[i]);//递归  
                }  
            }  
        }  
        for(int i = 0;i<listname.size();i++){  
            System.out.println(listname.get(i));  
        }  
    }  
}
