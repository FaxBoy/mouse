<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索</title>
<meta name="description" content=" 内容介绍不超过100个中文">
<meta name="keywords" content=" 内容相关关键词3-5个">
<style>
body, ul, li {margin: 0;padding: 0;}
body{ font-size:12px; font-family:sumsun,arial;color:#666666;background:#FFFFFF;}
.gover_search{ position:relative; z-index:99; height:63px; padding:15px 0 0 20px; border:1px solid #b8cfe6; border-bottom:0; background:#ccc;}
.gover_search_form{height:36px;}
.gover_search .search_t{ float:left; width:112px; line-height:26px; color:#666666;}
.gover_search .input_search_key{ float:left; width:462px; height:18px; padding:3px; margin-right:5px; border:1px solid #cccccc; line-height:18px; background:#FFFFFF;}
.gover_search .search_btn{ float:left; width:68px; height:26px; overflow:hidden; border:1px solid #cccccc; text-align:center; color:#ff3300; letter-spacing:5px; background:#ddd; cursor:pointer; font-weight:bold;}
.gover_search .search_suggest{ position:absolute; z-index:999; left:132px; top:41px; width:468px; border:1px solid #999999; display:none; }
.gover_search .search_suggest li{height:24px; overflow:hidden; padding-left:3px; line-height:24px; background:#FFFFFF; cursor:default;}
.gover_search .search_suggest li.hover{background:#DDDDDD;}    
</style>

<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
</head>

<body>
  <div class="gover_search">
    	<div class="gover_search_form clearfix">
            <span class="search_t">关键词匹配搜索</span>
            <input type="text" class="input_search_key" id="gover_search_key" placeholder="请输入关键词直接搜索" />
            <button type="submit" class="search_btn">搜索</button>
            <div class="search_suggest" id="gov_search_suggest">
                <ul>
                </ul>
            </div>
        </div>
    </div>

<script type="text/javascript">

//实现搜索输入框的输入提示js类
function oSearchSuggest(searchFuc){
	var input = $('#gover_search_key');
	var suggestWrap = $('#gov_search_suggest');
	var key = "";
	var init = function(){
		input.bind('keyup',sendKeyWord);
		input.bind('blur',function(){setTimeout(hideSuggest,100);})
	}
	var hideSuggest = function(){
		suggestWrap.hide();
	}
	
	//发送请求，根据关键字到后台查询
	var sendKeyWord = function(event){
		
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
	this.dataDisplay = function(data){
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
		suggestWrap.find('li').hover(function(){
				suggestWrap.find('li').removeClass('hover');
				$(this).addClass('hover');
		
			},function(){
				$(this).removeClass('hover');
		}).bind('click',function(){
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
function sendKeyWordToBack(keyword){
	   var obj = {
			    "keyWord" : keyword
			 };
			 $.ajax({
					   type: "POST",
					   url : "${PATH}/solr/suggest",
					   async:false,
					   data: obj,
					   dataType: "json",
					   success: function(data){
						  var key = data.data;
						  console.info(key.length);
						  var aData = [];
						  $.each(key, function(i) {
							  if(key[i].code!=undefined){
							  	aData.push(key[i].code);
							  }
						 })  
						 console.info(aData)
						 //将返回的数据传递给实现搜索输入框的输入提示js类
						 searchSuggest.dataDisplay(aData);
					   }
		 });	 
}

</script>
</body>
</html>
