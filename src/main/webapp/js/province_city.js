/**
 * Created by Administrator on 2017/6/20.
 */
$(function() {

    var $province = $("#province");
    var $city = $("#city");
    var $area = $("#area");
    var preProvince = "<option value=''>选择省（市）</option>";
    var preCity = "<option value=''>选择市（区）</option>";
    var preArea = "<option value=''>选择区（县）</option>";

    //初始化
    $province.html(preProvince);
    $city.html(preCity);
    $area.html(preArea);

    //func_suc_getXmlProvice进行 省的 解析
    $.ajax({
        type : "GET",
        url : "/admin/area/tree?code=0",
        success : func_suc_getXmlProvice
    });

    //省 下拉选择发生变化触发的事件
    $province.change(function() {
        //province.val()  : 返回是每个省对应的下标,序号从0开始
        if ($province.val() != "") {
            $city.html(preCity);
            //根据下拉得到的省对于的下标序号,动态从从province_city_select_Info.xml获取数据,成功之后采用
            //func_suc_getXmlProvice进行省对应的市的解析
            var value = $(this).val();
            $.ajax({
                type : "GET",
                url : "/admin/area/tree?code=" + value,
                success : function(data){
                    $.each(data,function(k,p){
                        if(p.parentCode == value){
                            var option = "<option value='"+p.code+"'>"+p.name+"</option>";
                            $city.append(option);
                        }
                    })
                }
            });
        }
    });
    //市 下拉选择发生变化触发的事件
    $city.change(function() {
        $area.html(preArea);
        var value = $(this).val();
        $.ajax({
            type : "GET",
            url : "/admin/area/tree?code=" + value,
            //根据下拉得到的省、市对于的下标序号,动态从从province_city_select_Info.xml获取数据,成功之后采用
            //func_suc_getXmlArea进行省对应的市对于的区的解析
            success : function(data){
                $.each(data,function(k,p){
                    if(p.parentCode == value){
                        var option = "<option value='"+p.code+"'>"+p.name+"</option>";
                        $area.append(option);
                    }
                })
            }
        });
    });
    //解析获取xml格式文件中的prov标签,得到所有的省,并逐个进行遍历 放进下拉框中
    function func_suc_getXmlProvice(data) {
        //jquery的查找功能
        $.each(data,function(k,p){
            if(p.parentCode == "0"){
                var option = "<option value='"+p.code+"'>"+p.name+"</option>";
                 $province.append(option);
            }
        });
    }
});