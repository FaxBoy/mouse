package xstream;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import xstream.POJO.Bill;
import xstream.POJO.BillHead;
import xstream.POJO.Custsaleclass;
import xstream.POJO.RendezvousMessage;

import javax.swing.text.Document;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: Tutorial
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/6/3 10:07
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/6/3        shilun           v1.0.0               创建
 */
public class Tutorial {

    public static void main(String[] args) throws Exception {

        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
//        xstream.alias("xml", RendezvousMessage.class);
        xstream.processAnnotations(RendezvousMessage.class);
        RendezvousMessage rendezvousMessage = new RendezvousMessage();
        rendezvousMessage.setAccount("DEVNC633TDH");
//        rendezvousMessage.setBillType("");
        rendezvousMessage.setBillType(0);
        rendezvousMessage.setGroupCode("TDH");
        rendezvousMessage.setIsexchange(true);
        rendezvousMessage.setReplace(true);
        rendezvousMessage.setSender("tdh01");
        Bill bill = new Bill();
        bill.setId("123456789");
        BillHead billHead = new BillHead();
        billHead.setCode("210930");
        billHead.setPkGroup("TDH");
        billHead.setPkOrg("TDH");
        billHead.setName("测试客户");
        billHead.setPkCustclass("总部（客商申请专用）");
        billHead.setIssupplier("N");
        billHead.setCustprop("0");
        billHead.setIsfreecust("N");
        billHead.setPkCountry("CN");
        billHead.setPkTimezone("P0800");
        billHead.setPkFormat("ZH-CN");
        billHead.setFrozenflag("N");
        billHead.setEnabletate("已启用");
        bill.setBillHead(billHead);

//        Custsaleclass custsaleclass = new Custsaleclass();
//        custsaleclass.setPkGroup("33");
//        custsaleclass.setPkOrg("22");
//
//        bill.setCustsaleclass(custsaleclass);

        rendezvousMessage.setBill(bill);

        System.out.println(RendezvousMessage.headInfo+xstream.toXML(rendezvousMessage));

//        FileOutputStream fs = new FileOutputStream("/logs/tdh/"+System.currentTimeMillis()+".xml");
//        fs.write(RendezvousMessage.headInfo.getBytes());
//        xstream.toXML(rendezvousMessage, fs);

        String url = "http://120.132.30.152:80/service/XChangeServlet?account=DEVNC633TDH&groupcode=TDH";
        String result = HttpClient.doPost(url,RendezvousMessage.headInfo+xstream.toXML(rendezvousMessage));
        System.out.println("返回值：");
        System.out.println(result);
        JSONObject json = xml2Json(result);
        System.out.println(json.toJSONString());
        JSONObject sendresult = JSONObject.parseObject(json.get("ufinterface").toString());
        System.out.println(sendresult.toJSONString());
        JSONArray jsonArray = JSONArray.parseArray(sendresult.get("sendresult").toString());
        System.out.println(jsonArray.toJSONString());
        JSONObject resultjson = JSONObject.parseObject(jsonArray.get(0).toString());
        System.out.println(resultjson.toJSONString());
        System.out.println(resultjson.get("resultcode"));
        System.out.println(resultjson.get("resultdescription"));

//        URL realURL = new URL(url);
//        HttpURLConnection connection = (HttpURLConnection)realURL.openConnection();
//        connection.setDoOutput(true);
//        connection.setRequestProperty("Content-type", "text/xml");
//        connection.setRequestMethod("POST");
//        // 将Document对象写入连接的输出流中
//        File file = new File("C:/samples/psndoc.xml");
//
//        BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
//        BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
//        int length;
//        byte[] buffer = new byte[1000];
//        while ((length = input.read(buffer, 0, 1000)) != -1) { out.write(buffer, 0, length);
//        }
//        input.close();
//        out.close();
//        // 从连接的输入流中取得回执信息
//        InputStream inputStream = connection.getInputStream();
//
//
//
//        Document resDoc = XMLUtil.getDocumentBuilder().parse(inputStream);


    }

    public static JSONObject xml2Json(String xmlStr) throws JDOMException, IOException {
//        if (StringUtils.isEmpty(xmlStr)) {
//            return null;
//        }
        xmlStr = xmlStr.replaceAll("\\\n", "");
        byte[] xml = xmlStr.getBytes("UTF-8");
        JSONObject json = new JSONObject();
        InputStream is = new ByteArrayInputStream(xml);
        SAXBuilder sb = new SAXBuilder();
        org.jdom2.Document doc = sb.build(is);
        Element root = doc.getRootElement();
        json.put(root.getName(), iterateElement(root));

        return json;
    }

    private static JSONObject iterateElement(Element element) {
        List<Element> node = element.getChildren();
        JSONObject obj = new JSONObject();
        List list = null;
        for (Element child : node) {
            list = new LinkedList();
            String text = child.getTextTrim();
            if (StringUtils.isBlank(text)) {
                if (child.getChildren().size() == 0) {
                    continue;
                }
                if (obj.containsKey(child.getName())) {
                    list = (List) obj.get(child.getName());
                }
                list.add(iterateElement(child)); //遍历child的子节点
                obj.put(child.getName(), list);
            } else {
                if (obj.containsKey(child.getName())) {
                    Object value = obj.get(child.getName());
                    try {
                        list = (List) value;
                    } catch (ClassCastException e) {
                        list.add(value);
                    }
                }
                if (child.getChildren().size() == 0) { //child无子节点时直接设置text
                    obj.put(child.getName(), text);
                } else {
                    list.add(text);
                    obj.put(child.getName(), list);
                }
            }
        }
        return obj;
    }

}
