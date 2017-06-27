$(function(){
    var Accordion = function(el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;

        // Variables privadas
        var links = this.el.find('.link');
        // Evento
        links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
    }
    Accordion.prototype.dropdown = function(e) {
        var $el = e.data.el;
        $this = $(this),
            $next = $this.next();

        $next.slideToggle();
        $this.parent().toggleClass('open');

        if (!e.data.multiple) {
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        };
    }
    var accordion = new Accordion($('#accordion'), false);

    var url = window.location.href;
    $("#accordion a").each(function () {
        var href = $(this).attr('href');
        if (url.indexOf(href) > 0) {
            $(this).parent().parent().parent().addClass('open');
            $(this).addClass('on');
            $(this).parent().parent().show();
        }
    });

});