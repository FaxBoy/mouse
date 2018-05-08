package cn.uicp.mouse.util.Doc;

import java.util.HashMap;
import java.util.Map;

public class testDoc {
	public static void main(String[] args) {
        DocUtil docUtil=new DocUtil();
        Map<String, Object> dataMap=new HashMap<String, Object>();
        dataMap.put("year", "2017");
        dataMap.put("compname", "上海市堤防（泵闸）设施管理处");
        dataMap.put("projectname", "蕴东等14座泵闸运行养护");
        dataMap.put("id", "12");
        dataMap.put("fundname", "水利基金");
        dataMap.put("money", "4343");
        dataMap.put("fg", "重要");
        dataMap.put("type", "业务工作");
        dataMap.put("workcontent", "蕴东水闸\n" + 
        		"\n" + 
        		"、桃浦河泵闸、西泗塘泵闸、郝桥港泵闸、东茭泾泵闸、河口水闸、木渎港泵闸、彭越浦泵闸、淀东水闸、张家塘泵闸、龙华港泵闸、\n" + 
        		"淀东泵站、杨树浦泵闸、大治河西闸管理范围内的工程设施与设备的管理运行、维修养护（不包括专项维修）以及治安、环境保洁等.");
        dataMap.put("demonstration", "细目中的明细是蕴东等14座泵闸正常安全运行所必须的，是根据2017年市属蕴东等14座泵闸运行养护项目招投标中的中标价，期限三年。");
        dataMap.put("profit", "蕴东等14座泵闸运行养护费是确保防汛安全、引清调水和改善上海水环境的重要保证，也是确保水上交通安全畅通的有力保障。");
//        dataMap.put("orderCount", "第1次");
//        dataMap.put("userImg1", docUtil.getImageStr("D:\\Img\\userImg1.png"));
//        dataMap.put("userImg2", docUtil.getImageStr("D:\\Img\\userImg2.png"));
//        dataMap.put("firstExamTime", "12:41:17-12:44:38");
//        dataMap.put("firstExamScores", "0分，不及格");
//        dataMap.put("firstDeductItem", "12:44:15 20102 1号倒车入库，车身出线 扣100分");
        dataMap.put("img", docUtil.getImageStr("rrrr"));
//        dataMap.put("firstPic2", docUtil.getImageStr("D:\\Img\\firstPic2.png"));
//        dataMap.put("firstPic3", docUtil.getImageStr("D:\\Img\\firstPic3.png"));
//        dataMap.put("secondExamTime", "12:46:50-13:05:37");
//        dataMap.put("secondExamScores", "90分，通过");
//        dataMap.put("secondDeductItem", "");
//        dataMap.put("secondPic1", docUtil.getImageStr("D:\\Img\\secondPic1.png"));
//        dataMap.put("secondPic2", docUtil.getImageStr("D:\\Img\\secondPic2.png"));
//        dataMap.put("secondPic3", docUtil.getImageStr("D:\\Img\\secondPic3.png"));
        docUtil.createDoc(dataMap, "shdike_apply", "apply.doc");
    }
}
