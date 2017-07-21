    wx.error(function(res){
        //alert(res);
    });
    var wxid = $("#wxId").val();
    
    /**
     * 分享
     * @param shareData 分享数据
     */
    function share(shareData)
    {
        wx.ready(function(){
            /*分享到朋友圈*/
            wx.onMenuShareTimeline({
                title: shareData.desc, 					// 分享标题(因分享到朋友圈只能显示标题,这里把标题改成描述)
                desc: shareData.desc,		  			// 分享描述
                link: shareData.link, 					// 分享链接
                imgUrl: shareData.imgUrl, 				// 分享图标

                success: function ()
                {
                  //  WeixinJSBridge.call('closeWindow');
                    $(tipdom).remove();

                },

                cancel: function ()
                {
                    // 用户取消分享后执行的回调函数
                    //alert("取消分享!");
                }
            });

            /*分享给朋友*/
            wx.onMenuShareAppMessage({
                title: shareData.title, 				// 分享标题
                desc: shareData.desc, 					// 分享描述
                link: shareData.link,					// 分享链接
                imgUrl: shareData.imgUrl, 				// 分享图标
                type: '', 								// 分享类型,music、video或link，不填默认为link
                dataUrl: '', 							// 如果type是music或video，则要提供数据链接，默认为空
                success: function () {
               //     WeixinJSBridge.call('closeWindow');
                    $(tipdom).remove();
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });

            /*分享到QQ*/
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

            /*分享到腾讯微博*/
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
    }

    /**
     * 微信支付
     */
    function wechatPay()
    {
        var request = {"url":'web-execute.action?execute=getPayParams', "data":{}, "needValidate":false, "invokeMethod":getParamsSuccess};
        $.sendRequest(request);


        function getParamsSuccess(getData)
        {
            var returnData = getData.returnData;
            var params = returnData.split('_');
            params[3] = "prepay_id=" + params[3];

            var payParams = {
               "appId" : params[0],     	//公众号名称，由商户传入
               "timeStamp" : params[1],     //时间戳，自1970年以来的秒数
               "nonceStr" : params[2], 		//随机串
               "package" : params[3],       //预支付id
               "signType" : params[4],      //微信签名方式
               "paySign" : params[5] 	    //微信签名
            };

            console.log(payParams);
            WeixinJSBridge.invoke('getBrandWCPayRequest', payParams, function(res){
                //使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回 OK，但并不保证它绝对可靠。
                if(res.err_msg == "get_brand_wcpay_request:ok")
                {
                    alert("支付成功!");
                }
                else if(res.err_msg == "get_brand_wcpay_request:cancel")
                {
                    alert("取消支付!");
                }
                else
                {
                    alert("支付失败!");
                }
            });
        }
    }