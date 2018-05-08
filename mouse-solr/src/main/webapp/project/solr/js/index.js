
window.onload = function(){
	//运行瀑布流主函数
	//PBL('wrap','box');	
	//模拟数据
	var data = [{'src':'1.jpg','title':'This is a title1.'},{'src':'2.jpg','title':'This is a title2.'},{'src':'3.jpg','title':'This is a title3.'},{'src':'4.jpg','title':'This is a title4.'},{'src':'5.jpg','title':'This is a title5.'},{'src':'6.jpg','title':'This is a title6.'},{'src':'7.jpg','title':'This is a title7.'},{'src':'8.jpg','title':'This is a title8.'},{'src':'9.jpg','title':'This is a title9.'},{'src':'10.jpg','title':'This is a title10.'}];
	
	//设置滚动加载
	window.onscroll = function(){
		//校验数据请求
		if(getCheck()){
			var keyword="海";
			var obj = {
				    "keyWord" : keyword,
				    "start" : ++startpage
				 };
			 $.ajax({
				   type: "POST",
				   url : PATH+"/solr/query",
				   async:false,
				   data: obj,
				   dataType: "json",
				   success: function(data){
					  var key = data.data.data;
					  var aData = [];
					  dataDisplay(key);
					 PBL('wrap','box');
				   }
			 });	 
		}
	}
}
/*
* 瀑布流主函数
*/
function PBL(wrap,box){
	//	1.获得外层以及每一个box
	var wrap = document.getElementById(wrap);
	var boxs  = getClass(wrap,box);
	//	2.获得屏幕可显示的列数
	var boxW = boxs[0].offsetWidth;
	var colsNum = Math.floor(document.documentElement.clientWidth/boxW);
	wrap.style.width = boxW*colsNum+'px';//为外层赋值宽度
	//	3.循环出所有的box并按照瀑布流排列
	var everyH = [];//定义一个数组存储每一列的高度
	for (var i = 0; i < boxs.length; i++) {
		if(i<colsNum){
			everyH[i] = boxs[i].offsetHeight;
		}else{
			var minH = Math.min.apply(null,everyH);//获得最小的列的高度
			var minIndex = getIndex(minH,everyH); //获得最小列的索引
			getStyle(boxs[i],minH,boxs[minIndex].offsetLeft,i);
			everyH[minIndex] += boxs[i].offsetHeight;//更新最小列的高度
		}
	}
}
/**
* 获取类元素
*/
function getClass(wrap,className){
	var obj = wrap.getElementsByTagName('*');
	var arr = [];
	for(var i=0;i<obj.length;i++){
		if(obj[i].className == className){
			arr.push(obj[i]);
		}
	}
	return arr;
}
/**
* 获取最小列的索引
*/
function getIndex(minH,everyH){
	for(index in everyH){
		if (everyH[index] == minH ) return index;
	}
}
/**
* 数据请求检验
*/
function getCheck(){
	var documentH = document.documentElement.clientHeight;
	var scrollH = document.documentElement.scrollTop || document.body.scrollTop;
	return documentH+scrollH>=getLastH() ?true:false;
}
/*
* 获得最后一个box所在列的高度
*/
function getLastH(){
	var wrap = document.getElementById('wrap');
	var boxs = getClass(wrap,'box');
	return boxs[boxs.length-1].offsetTop+boxs[boxs.length-1].offsetHeight;
}
/*
* 设置加载样式
*/
var getStartNum = 0;//设置请求加载的条数的位置
function getStyle(box,top,left,index){
    if (getStartNum>=index) return;
    $(box).css({
    	'position':'absolute',
        'top':top,
        "left":left,
        "opacity":"0"
    });
    $(box).stop().animate({
        "opacity":"1"
    },999);
    getStartNum = index;//更新请求数据的条数位置
}


function dataDisplay(key){
	var wrap = document.getElementById('wrap');
	$.each(key, function(i) {
		  if(key[i]!=undefined){
			//创建box
			var box = document.createElement('div');
			box.className = 'box';
			wrap.appendChild(box);
			//创建info
			var info = document.createElement('div');
			info.className = 'info';
			box.appendChild(info);
			//创建pic
			var pic = document.createElement('div');
			pic.className = 'pic';
			info.appendChild(pic);
			//创建img
			//var img = document.createElement('img');
			//img.src = PATH+'/project/solr/img/'+data[i].src;
			//img.style.height = 'auto';
			var html="";
			html+='<i class="iconfont icon-ren"></i>'+key[i].name+'<br>';
			if(key[i].tel!=""){
				html+='<i class="iconfont icon-lianxifangshi"></i>'+key[i].tel+'<br>';
			}
			html+='<i class="iconfont icon-minganguanjianzi"></i>'+key[i].keyword+'<br>';
			html+='<i class="iconfont icon-dizhi"></i>'+key[i].address+'<br>';
			if(key[i].management!=""){
				html+='<i class="iconfont icon-lianxifangshi"></i>'+key[i].management+'<br>';
			}
			pic.innerHTML = html;
			pic.style.height = 'auto';
			//$(".pic").html(html);
			//创建title
			var title = document.createElement('div');
			title.className = 'title';
			info.appendChild(title);
			//创建a标记
			var a = document.createElement('a');
			a.innerHTML = key[i].companyname;
			title.appendChild(a);
		  }
	 })
}