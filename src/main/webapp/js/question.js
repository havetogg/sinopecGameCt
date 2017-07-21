/**
 * Created by Administrator on 2015/12/27.
 */

var choiceWhich;
function changeColor(){
    var value = new Array();
    var redInfo = $("input[name='choseTypes']");
    $(redInfo).each(function(i,n){
        if($(n).prop('checked')){
            value.push($(n).val());

        }

    });
    //choiceWhich为所选项
    choiceWhich = value.toString();
    //alert(choiceWhich)
}


function chose(dom){
    var isNot = $(dom).hasClass("yeZi");
    if(isNot){
        $(dom).removeClass("yeZi");
    }else{
        $(dom).addClass("yeZi");
    }

    //alert($(dom).hasClass("yeZi"));

}


$(function(){


            luoye();
    setTimeout(function(){
        $(".role3").animate({'left':190,'top':280},600);
    },1000);




    //简答题部分
    setTimeout(function(){
        $(".role4").animate({'left':190,'top':280},600);
    },1000);

    //提交答案js
    $(".next").click(function(){
        text = $(".neiRong").val();
        if(text.length<1) {
            alerw("请填写内容!");
        }else {
            loading("start");
            $.ajax({
                url:getRootPath()+"?",
                data:{"text": text},
                dataType:"json",
                type:"post",
                success:function(data){
                    loading("stop");



                }
            });
        }
    });


    //$(".giveWant").click(function(){
    //    location.href=getRootPath()+"/writeWant.jsp";
    //});
});