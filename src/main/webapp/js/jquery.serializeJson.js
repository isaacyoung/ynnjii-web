/**
 * Created by admin on 2017/6/2.
 */
(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if (typeof this.value != 'undefined' && this.value != ''){
                if(serializeObj[this.name]){
                    if($.isArray(serializeObj[this.name])){
                        serializeObj[this.name].push(this.value);
                    }else{
                        serializeObj[this.name]=[serializeObj[this.name],this.value];
                    }
                }else{
                    serializeObj[this.name]=this.value;
                }
            }
        });
        return serializeObj;
    };
})(jQuery);