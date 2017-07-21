/*
wx.error(function(res){
	//alert(res);
});

function share(shareData)
{	
	wx.ready(function(){
		/!*分享到朋友圈*!/
		wx.onMenuShareTimeline({
			title: shareData.title, // 分享标题
			desc: shareData.desc,		  //分享描述	
			link: shareData.link, // 分享链接
			imgUrl: shareData.imgUrl, // 分享图标
			
			success: function () 
			{
				$(tipdom).remove();
			},
			
			cancel: function () 
			{ 
				// 用户取消分享后执行的回调函数
				//alert("取消分享!");
			}
		});
		
		/!*分享给朋友*!/
		wx.onMenuShareAppMessage({
		    title: shareData.title, // 分享标题
		    desc: shareData.desc, // 分享描述
		    link: shareData.link, // 分享链接
		    imgUrl: shareData.imgUrl, // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () {
		    	$(tipdom).remove();
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		/!*分享到QQ*!/
		wx.onMenuShareQQ({
		    title: shareData.title, // 分享标题
		    desc: shareData.desc, // 分享描述
		    link: shareData.link, // 分享链接
		    imgUrl: shareData.imgUrl, // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		       // 用户取消分享后执行的回调函数
		    }
		});
		
		/!*分享到腾讯微博*!/
		wx.onMenuShareWeibo({
		    title: shareData.title, // 分享标题
		    desc: shareData.desc, // 分享描述
		    link: shareData.link, // 分享链接
		    imgUrl: shareData.imgUrl, // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
	});
}*/
