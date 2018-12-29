<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SOON</title>
<style type="text/css">
body{padding:0;margin:0;background:#ddd url(${PATH}/project/solr/img/bc.png) repeat;}
img{border:none;}
a{text-decoration:none;color:#444;}
a:hover{color:#999;}
#title{width:600px;margin:20px auto;text-align:center;}

#wrap{width:auto;height:auto;margin:0 auto;position:relative;}
#wrap .box{width:280px;height:auto;padding:10px;border:none;float:left;}
#wrap .box .info{/* width:280px; */height:auto;border-radius:8px;box-shadow:0 0 11px #666;background:#fff;}
#wrap .box .info .pic{width:260px;height:auto;margin:0 auto;padding-top:10px;}
#wrap .box .info .pic i{margin: 5px;}
#wrap .box .info .pic img{width:260px;border-radius:3px;}
#wrap .box .info .title{width:260px;height:40px;margin:0 auto;line-height:40px;text-align:center;color:#666;font-size:18px;font-weight:bold;overflow:hidden;}

.gover_search{ position:relative; z-index:99; height:63px; padding:15px 0 0 20px; border:1px solid #b8cfe6; border-bottom:0; background:#ccc;}
.gover_search_form{height:36px;}
.gover_search .search_t{ float:left; width:112px; line-height:26px; color:#666666;}
.gover_search .input_search_key{ float:left; width:462px; height:18px; padding:3px; margin-right:5px; border:1px solid #cccccc; line-height:18px; background:#FFFFFF;}
.gover_search .search_btn{ float:left; width:68px; height:26px; overflow:hidden; border:1px solid #cccccc; text-align:center; color:#ff3300; letter-spacing:5px; background:#ddd; cursor:pointer; font-weight:bold;}
.gover_search .search_suggest{ position:absolute; z-index:999; left:132px; top:41px; width:468px; border:1px solid #999999; display:none; }
.gover_search .search_suggest li{height:24px; overflow:hidden; padding-left:3px; line-height:24px; background:#FFFFFF; cursor:default;}
.gover_search .search_suggest li.hover{background:#DDDDDD;}    

</style>

<script type="text/javascript" src="${PATH}/project/solr/js/index.js"></script>

</head>
<body>
<center>
	  <div class="gover_search">
    	<div class="gover_search_form clearfix">
            <span class="search_t">关键词匹配搜索</span>
            <input type="text" class="input_search_key" id="gover_search_key" placeholder="请输入关键词直接搜索" />
            <button type="button" class="search_btn">搜索</button>
            <div class="search_suggest" id="gov_search_suggest">
                <ul>
                </ul>
            </div>
            <i class="iconfont icon-shijian"></i><span class="qtime">0.001</span>&nbsp;
            <i class="iconfont icon-fl-shuju"></i><span class="numFound">0000</span>&nbsp;
            <i class="iconfont icon-icon"></i><span>${fwnumber}次</span>
        </div>
    </div>
</center>

	<div id="wrap">
	
		<!-- <div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-gongzuofanwei"></i>从事货物及技术进出口业务，实业投资，煤炭、机械设备、化工产品及原料（除危险化学品、监控化学品、烟花爆竹、民用爆炸物品、易制毒化学品）、石油制品、燃料油（除危险品）、润滑油、矿产品、金属材料及制品、五金工具、建筑材料、金银饰品、化妆品、化肥、饲料、食用农产品、橡塑制品、电子产品、计算机及配件、日用百货...<br>
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div>
		
		<div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div>
		
		<div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div>
		
		<div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div>
	
		<div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div>
		
		<div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div>
		
		<div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div>
		
		<div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div>
		
		<div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div>
		
		<div class="box">
			<div class="info">
				<div class="pic">
					<i class="iconfont icon-ren"></i>张熟悉<br>
					<i class="iconfont icon-lianxifangshi"></i>021-62771939<br>
					<i class="iconfont icon-dizhi"></i>中国（上海）自由贸易试验区德堡路38号1幢三层301-12室<br>
					<i class="iconfont icon-minganguanjianzi"></i>化妆品
				</div>
				<div class="title"><a href="#"><i class="iconfont icon-commpany"/></i>上海渊臣国际贸易有限公司</a></div>
			</div>
		</div> -->
	
	</div>

<script type="text/javascript">

//实现搜索输入框的输入提示js类
mousection oSearchSuggest(searchFuc){
	var input = $('#gover_search_key');
	var suggestWrap = $('#gov_search_suggest');
	var key = "";
	var init = mousection(){
		input.bind('keyup',sendKeyWord);
		input.bind('blur',mousection(){setTimeout(hideSuggest,100);})
	}
	var hideSuggest = mousection(){
		suggestWrap.hide();
	}
	
	//发送请求，根据关键字到后台查询
	var sendKeyWord = mousection(event){
		
		//键盘选择下拉项
		if(suggestWrap.css('display')=='block'&&event.keyCode == 38||event.keyCode == 40){
			var current = suggestWrap.find('li.hover');
			if(event.keyCode == 38){
				if(current.length>0){
					var prevLi = current.removeClass('hover').prev();
					if(prevLi.length>0){
						prevLi.addClass('hover');
						input.val(prevLi.html());
					}
				}else{
					var last = suggestWrap.find('li:last');
					last.addClass('hover');
					input.val(last.html());
				}
				
			}else if(event.keyCode == 40){
				if(current.length>0){
					var nextLi = current.removeClass('hover').next();
					if(nextLi.length>0){
						nextLi.addClass('hover');
						input.val(nextLi.html());
					}
				}else{
					var first = suggestWrap.find('li:first');
					first.addClass('hover');
					input.val(first.html());
				}
			}
			
		//输入字符
		}else{ 
			var valText = $.trim(input.val());
			if(valText ==''||valText==key){
				return;
			}
			searchFuc(valText);
			key = valText;
		}			
		
	}
	//请求返回后，执行数据展示
	this.dataDisplay = mousection(data){
		if(data.length<=0){
            suggestWrap.hide();
			return;
		}
		
		//往搜索框下拉建议显示栏中添加条目并显示
		var li;
		var tmpFrag = document.createDocumentFragment();
		suggestWrap.find('ul').html('');
		for(var i=0; i<data.length; i++){
			li = document.createElement('LI');
			li.innerHTML = data[i];
			tmpFrag.appendChild(li);
		}
		suggestWrap.find('ul').append(tmpFrag);
		suggestWrap.show();
		
		//为下拉选项绑定鼠标事件
		suggestWrap.find('li').hover(mousection(){
				suggestWrap.find('li').removeClass('hover');
				$(this).addClass('hover');
		
			},mousection(){
				$(this).removeClass('hover');
		}).bind('click',mousection(){
			input.val(this.innerHTML);
			suggestWrap.hide();
		});
	}
	init();
};

//实例化输入提示的JS,参数为进行查询操作时要调用的函数名
var searchSuggest =  new oSearchSuggest(sendKeyWordToBack);

//这是一个模似函数，实现向后台发送ajax查询请求，并返回一个查询结果数据，传递给前台的JS,再由前台JS来展示数据。本函数由程序员进行修改实现查询的请求
//参数为一个字符串，是搜索输入框中当前的内容
mousection sendKeyWordToBack(keyword){
	   var obj = {
			    "keyWord" : keyword
			 };
			 $.ajax({
					   type: "POST",
					   url : "${PATH}/solr/suggest",
					   async:false,
					   data: obj,
					   dataType: "json",
					   success: mousection(data){
						  var key = data.data;
						  var aData = [];
						  $.each(key, mousection(i) {
							  if(key[i].code!=undefined){
							  	aData.push(key[i].code);
							  }
						 })  
						 //将返回的数据传递给实现搜索输入框的输入提示js类
						 searchSuggest.dataDisplay(aData);
					   }
		 });	 
}
var startpage=0;
$(".search_btn").on('click', mousection(){
	$("#wrap").html("");
	var keyword=$("#gover_search_key").val();
	startpage=0;
	var obj = {
		    "keyWord" : keyword,
		    "start" : startpage,
		 };
	 $.ajax({
		   type: "POST",
		   url : PATH+"/solr/query",
		   async:false,
		   data: obj,
		   dataType: "json",
		   success: mousection(data){
			  var key = data.data.data;
			  var aData = [];
			  dataDisplay(key);
			  PBL('wrap','box');
			  $(".qtime").text(data.data.qtime);
			  $(".numFound").text(data.data.numFound);
		   }
	 });	 
});

</script>

</body>
</html>