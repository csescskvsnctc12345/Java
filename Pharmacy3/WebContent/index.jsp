<%@page import="jqgrid.jQGridselect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" media="screen" href="Lib/jquery-ui-1.9.2.custom/css/redmond/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" />
<link type="text/css" media="screen" href="Lib/jquery.jqGrid-5.0.0/css/ui.jqgrid.css" rel="stylesheet" />
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="Lib/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="Lib/jquery.jqGrid-5.0.0/js/jquery.jqGrid.min.js" ></script>
<script type="text/javascript" src="Lib/jquery.jqGrid-5.0.0/js/i18n/grid.locale-ja.js" ></script>

<link rel="stylesheet" type="text/css" href="css/index.css">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<title>薬局画面</title>
<%
String store_id = (String)session.getAttribute("store_id");
String store_name = (String)session.getAttribute("store_name");

jQGridselect reservation = new jQGridselect();
String strjson = reservation.getJSONtable(store_id);

%>
</head>
<body>

<div id="wrapper">
	<div id="header">
		<h1><a href="login.jsp"><%=store_name %></a></h1>
		<h2>
			<font color="00ff00">予約一覧画面</font>
		</h2>
	</div>
	<div class="nav">
		<ul class="nl clearFix">
			<li id="select"><a href="index.jsp">予約一覧 <span class="en">Reservation</span></a></li>
			<li><a href="state_change.jsp">処方完了一覧 <span class="en">Completion</span></a></li>
			<li><a href="barcode_finish.jsp">予約完了 <span class="en">Barcode</span></a></li>
		</ul>
	</div>
	<div id="main">
		<div style="margin-left: 100px; margin-top: 20px;">
			<!-- jQGrid表示部分 -->
			<table id="list"></table>
			<div id="statusbar"></div>
		</div>
	</div>
</div>


<!-- ダイアログ部分 -->
<div id="dialog" title="詳細画面">

	<!-- 予約者の名前表示部分 -->
	<div style="text-align: center; margin-top: 50px;  margin-bottom: 50px;">
		<h2 id="text_customer_name"></h2>
	</div>

	<!-- 予約者の電話番号表示部分 -->
	<div style="text-align: right;">
		<p id="text_tel"></p>
	</div>

	<!-- 薬詳細を表示するテーブル -->
	<table id="table_id" class="table table-striped table-bordered">
		<tr>
			<th>薬名</th>
			<th>薬量</th>
			<th>備考</th>
		</tr>

	</table>
	<form method="get" action="./ProcessServlet" id="process_form">
		<input type="hidden" id="barcode_id" name="barcode_id" value=""/>
	</form>

</div>



<script type="text/javascript">

//一時保存用
jsonArysave = new Array();

//ダイアログ
$(function(){
	$("#dialog").dialog({
		//ダイアログの高さと幅
		height: 550,
		width: 850,
		modal: true, // モーダルダイアログ（ダイアログが開いている間は他の操作が出来ない）指定
		autoOpen: false,//最初は非表示
		//ダイアログの右上の閉じるUI無効
		closeOnEscape: false,
		resizable: false,
		open:function(event, ui){ $(".ui-dialog-titlebar-close").hide();},

		//右下に閉じるボタンを設置する
		buttons:[
			//完了のボタンが押された時
			{
			    text: '処方を完了する',
			    class:'btn-update',
			    click: function() {
			        //ボタンを押したときの処理
			    	$('#process_form').submit();
			    }
			},
			//ダイアログのキャンセルボタンが押された時
			{
			    text: '閉じる',
			    class:'btn-close',
			    click: function() {
			    	delRow();//初期化
			        $('#dialog').dialog('close');
			    }
			}
		]

	});
});

//取得したJSONArrayを一時保存する
function valueSet(jsonAry){
	jsonArysave = jsonAry;
}

//閉じた時表示されている行を削除(初期化)
function delRow(){
	console.log(jsonArysave.length);
	var tblObj = document.getElementById("table_id");
	//表示されている行数分消す
	for(var i = 0 ;i < jsonArysave.length ; i++){
		tblObj.deleteRow(-1);// 末尾行を削除
	}
}

//薬の詳細を表示する関数
function DatailsDisplay(jsonobject){
	jsonAry = null;
	jsonAry = new Array();

	//JSONを配列に入れる
	jsonAry = jsonobject;
	console.log(jsonAry);

	//薬の情報を入れていく
	for(var i = 0 ;i < jsonAry.length ; i++){
		var table = document.getElementById("table_id");
		//tr生成
		var newtr = table.insertRow( table.rows.length );

		//tdを３つ生成
		var newtd1 = newtr.insertCell( newtr.cells.length );
		var newtd2 = newtr.insertCell( newtr.cells.length );
		var newtd3 = newtr.insertCell( newtr.cells.length );
		newtd1.appendChild( document.createTextNode(jsonAry[i]["medicine_name"]) );
		newtd2.appendChild( document.createTextNode(jsonAry[i]["quantity"]) );
		newtd3.appendChild( document.createTextNode(jsonAry[i]["message"]) );
	}

	//値を一時保存する(初期化するため)
	valueSet(jsonAry);

	//ダイアログを表示する
	$( "#dialog" ).dialog( 'open' );
}

//非同期通信で処理
var jsonobject;
function Datailsajax(barcode_id) {
	$.ajax({
		type : "POST",
		data: {
			barcode_id : barcode_id//今行選択されているバーコードのid
		},
		url : "http://localhost:8080/hal_work3/DetailsServlet",
		dataType : "json",
		success : function(json) {//非同期で通信成功した時の処理
			console.log(json);

			//JSON型にキャスト
			jsonobject = JSON.parse(json);
			//詳細のダイアログに薬の情報を表示する関数呼び出し(引数JSON)
			DatailsDisplay(jsonobject);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("エラーが発生しました：" + textStatus + ":\n" + errorThrown);
		}
	});
};

//jQgrid表示部分
var jsonData;
<%if(!strjson.equals("")){%>
	jsonData = <%=strjson%>;
<%}%>
jQuery(document).ready(function(){
	var mydata = jsonData;

	jQuery("#list").jqGrid({
		data: mydata,
		datatype: "local",
		colNames:['ID', '患者名', '予約時間','予約された薬の処方','電話番号'],
		//列の設定
		colModel : [ {
			index : 'barcode_id',
			name : 'barcode_id',
			sorttype : 'int'
		}, {
			index : 'customer_name',
			name : 'customer_name',
			sorttype : 'str',
			align: 'center'
		}, {
			index : 'reservation_time',
			name : 'reservation_time',
			sorttyp : 'date',
			align: 'center'
		}, {
			index : 'process_flg',
			name : 'process_flg',
			sorttyp : 'int',
			align: 'center'
		}, {
			index : 'tel',
			name : 'tel',
			sorttyp : 'str',
			hidden : true //非表示
		}],
		multiselect: false,
		caption: '予約一覧',
		sortname : "reservation_time", //デフォルトでcodeをみてソートし表示する
		sortorder : "ASC",
		rowNum : 30, // 1ページの表示件数
		scroll : true, //スクロール出来るか
		height : 400,
		width : 800,
		pager: "#statusbar", //footerのページャー要素のid
		regional: 'ja', //日本語
		shrinkToFit : true, //画面サイズに依存せず固定の大きさを表示する設定
		viewrecords: true, // ページ、件数を右下ツールバー上に表示
		rowList : [10, 20, 30], //変更可能な1ページ当たりの行数
		gridview   : true,
		ondblClickRow: function(line) { //ダブルクリックした時処理理
			//行のデータを取得
			data = jQuery('#list').getRowData(line);
			//ダブルクリックされた行の名前をダイアログの名前を表示する場所に値を格納する
			$("#text_customer_name").text("氏名："+data.customer_name+"さん");

			//電話番号を表示
			$("#text_tel").text("電話番号："+data.tel);

			//アップデートする為にバーコードのIDをセットする
			$("#barcode_id").val(data.barcode_id);

			//ダブルクリックされた行の詳細を非同期通信で取得
			Datailsajax(data.barcode_id);
		}

	});

	jQuery("#list").jqGrid('navGrid','#statusbar',{del:false, add:false, edit:false, search:true});
	$("#list").filterToolbar({
	    defaultSearch:'cn'     //一致条件を入れる。
	});
});

//自動更新用
function ajaxjqgrid() {
	$.ajax({
		type : "POST",
		data: {
			store_id : <%=store_id%>//今ログインしている店のid
		},
		url : "http://localhost:8080/hal_work3/AjaxjQgrid",
		dataType : "json",
		success : function(json) {//非同期で通信成功した時の処理

			var ajaxjson;
			//JSON型にキャスト
			ajaxjson = JSON.parse(json);
			console.log(ajaxjson);


			//新しく表示する為の設定をする。
			jQuery("#list").jqGrid("clearGridData").setGridParam({
				data: ajaxjson,
				datatype: "local",
				colNames:['ID', '患者名', '予約時間','予約された薬の処方','電話番号'],
				//列の設定
				colModel : [ {
					index : 'barcode_id',
					name : 'barcode_id',
					sorttype : 'int'
				}, {
					index : 'customer_name',
					name : 'customer_name',
					sorttype : 'str',
					align: 'center'
				}, {
					index : 'reservation_time',
					name : 'reservation_time',
					sorttyp : 'date',
					align: 'center'
				}, {
					index : 'process_flg',
					name : 'process_flg',
					sorttyp : 'int',
					align: 'center'
				}, {
					index : 'tel',
					name : 'tel',
					sorttyp : 'str',
					hidden : true //非表示
				}],
				multiselect: false,
				caption: '予約一覧',
				sortname : "reservation_time", //デフォルトでcodeをみてソートし表示する
				sortorder : "ASC",
				rowNum : 30, // 1ページの表示件数
				scroll : true, //スクロール出来るか
				height : 400,
				width : 800,
				pager: "#statusbar", //footerのページャー要素のid
				regional: 'ja', //日本語
				shrinkToFit : true, //画面サイズに依存せず固定の大きさを表示する設定
				viewrecords: true, // ページ、件数を右下ツールバー上に表示
				rowList : [10, 20, 30], //変更可能な1ページ当たりの行数
				gridview   : true,
				ondblClickRow: function(line) { //ダブルクリックした時処理理
					//行のデータを取得
					data = jQuery('#list').getRowData(line);
					//ダブルクリックされた行の名前をダイアログの名前を表示する場所に値を格納する
					$("#text_customer_name").text("氏名："+data.customer_name+"さん");

					//電話番号を表示
					$("#text_tel").text("電話番号："+data.tel);

					//アップデートする為にバーコードのIDをセットする
					$("#barcode_id").val(data.barcode_id);

					//ダブルクリックされた行の詳細を非同期通信で取得
					Datailsajax(data.barcode_id);
				}

			}).trigger("reloadGrid");//再描画の為にリロードする


		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("エラーが発生しました：" + textStatus + ":\n" + errorThrown);
		}
	});
};

setInterval('ajaxjqgrid()',10000);//10秒毎に非同期通信する
</script>
</body>
</html>