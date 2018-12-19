//微信授权
function wechat() {
	$.ajax({
		type: "get",
		url: "getUrl",
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		success: function(result) {
			var html = '';
			html += '<a href="' + result.data + '" class="alipayicon" >';
			html += '	<img src="images/wechat.png">';
			html += '</a>';
			$('.wechat').append(html);
		}
	});
}
function guid() {
	function S4() {
		return(((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	}
	return(S4() + "-" + S4() + "-" + S4() + "-" + S4());
}
$(function() {
	wechat();
	$('#img-code').attr('src',"code/get");
	$('#img-code').on('click', function() {
		$('#img-code').attr('src', "code/get"+ '?' + guid());
	});
})
