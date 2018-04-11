//package com.cn.util.Doc;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.OutputStream;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.zip.ZipOutputStream;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import cn.com.wavenet.frame.core.base.SystemProperty;
//import cn.com.wavenet.frame.core.dao.DataManager;
//import cn.com.wavenet.frame.core.dao.DataObject;
//import cn.com.wavenet.hydro.action.FrameAjaxStandardAction;
//import cn.com.wavenet.hydro.action.sys.SecurityForm;
//import cn.com.wavenet.hydro.login.WaveUserInfo;
//import cn.com.wavenet.hydro.util.doc.DocUtil;
//import cn.com.wavenet.hydro.util.doc.ZipUtils;
//import freemarker.template.TemplateException;
///**
// * 
//* @ClassName: testZip 
//* @Description: TODO(方法可以使用，只是代码从项目中复杂过来，暂时注释) 
//* @author shil
//* @date 2017年10月24日 下午1:39:21 
//*
// */
//public class testZip extends FrameAjaxStandardAction {
//
//	@Override
//	protected String doExecute(DataManager dm, HttpServletRequest request, SecurityForm form,
//			HttpServletResponse response) throws Exception {
//		WaveUserInfo userInfo = form.getWaveUserInfo();
//		String userId = userInfo.getUserId();
//
//		StringBuffer sb = new StringBuffer();
//		sb.append(
//				"select pack_info.f_get_comp_namebydept(i.st_subdeptid) compname,i.*,f_get_param(st_nodeid) ST_NODENAMES,st_content probtype from t_hy_reportmain_i i join  t_hy_reportmain_is b  on  i.nm_sid=b.nm_pid ");
//		sb.append(" and nm_state in (102211,102212,102213)");
//		sb.append(" and nm_subtype in (101912) ");
//
//		sb.append(" and b.st_name='PROB_TYPE' and st_content='专项维修'");
//		sb.append(" and i.nm_sid in(select nm_pid from T_HY_REPORTMAIN_IS where st_name='ST_PUMPID' and st_content in "
//				+ "(select st_content from t_hy_org_is where st_orgsid "
//				+ "in(select deptid from v_pj_ssodeptuser where userid ='" + userId + "') and st_name ='下属泵闸'))");
//
//		Collection<DataObject> col = dm.find(sb.toString(), null);
//
//		
//		String zipName = System.currentTimeMillis()+".zip";
//		response.setContentType("APPLICATION/OCTET-STREAM");
//		response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
//		ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
//		try {
//			Iterator it = col.iterator();
//			while (it.hasNext()) {
//				DataObject dbo = (DataObject) it.next();
//				ZipUtils.doCompress(getFile(dbo,dm), out, "");
//				response.flushBuffer();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			out.close();
//		}
//
//		return null;
//	}
//
//	private File getFile(DataObject dbo,DataManager dm) throws SQLException {
//		DocUtil docUtil = new DocUtil();
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("year", dbo.getString("DT_SUB").substring(0, 4));
//		dataMap.put("compname", dbo.getString("COMPNAME"));
//		dataMap.put("projectname", dbo.getString("probtype"));
//		dataMap.put("id", dbo.getString("nm_sid"));
//		dataMap.put("fundname", "水利基金");
//		dataMap.put("money", "100000");
//		dataMap.put("fg", "重要");
//		dataMap.put("type", dbo.getString("probtype"));
//		dataMap.put("workcontent", dbo.getString("st_desc"));
//		dataMap.put("demonstration", dbo.getString("st_desc"));
//		dataMap.put("profit", dbo.getString("st_desc"));
//		try {
//			
//		
//		String sql = "SELECT\n" + 
//				"    st_prefix_name,\n" + 
//				"    st_suffix_name,\n" + 
//				"    st_url,\n" + 
//				"    dt_create,\n" + 
//				"    nm_aid,\n" + 
//				"    st_sid,\n" + 
//				"    st_type,\n" + 
//				"    st_full_url,\n" + 
//				"    nm_time_flag,\n" + 
//				"    st_category_url,\n" + 
//				"    st_base_code\n" + 
//				"FROM\n" + 
//				"    t_hy_attachment_i where st_sid='"+dbo.getString("nm_processid")+"'";
//		DataObject d = dm.findByPrimaryKey(sql, null);
//		dataMap.put("img", docUtil.getImageStr(SystemProperty.getProperty(d.getString("st_base_code"))+d.getString("st_full_url")));
//		//dataMap.put("img", 3333);
//		} catch (Exception e) {
//			System.out.println("没图片");
//			dataMap.put("img", "");
//		}
//		try {
//			return docUtil.createDocFile(dataMap, "shdike_apply", dbo.getString("nm_sid")+"_apply.doc");
//		} catch (TemplateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//
//	}
//
//}
