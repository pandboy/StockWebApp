/* common js */

function menu_drop(menuId, display)
{
	$id(menuId).style.display=display;
}

function $id(id)
{
	return document.getElementById(id);
}

function isScroll()
{
		return $(window).height() == $(document).height()?false:true;
}

 function scroll_position(id)
 {
		var o = $('#'+id) ;
		var offsetY = $(window).height() - o.height() + $(document).scrollTop();
		o.stop(true, false).animate({top:offsetY},{duration:700,queue:false});
		if($(document).scrollTop()==0)
		{
		    $('#go_top').hide();
		    $('#go_bottom').show();
		}
		else if($(document).scrollTop()==$('body').height() - $(window).height())
		{
		    $('#go_top').show();
		    $('#go_bottom').hide();
		}
		else
		{
		    o.find('a').show();
		}
		if(!isScroll())
		{
			$('#go_top_bottom').hide();
		}
		else
		{
			 $('#go_top_bottom').show();
		}
}
function scroll_to_pos(pos)
{
		var scrollY = 0 ;
		if(pos=='top')
		{
		    scrollY = 0;
		}
		else if(pos=='bottom')
		{
		    scrollY = $('body').height() - $(window).height();
		}
		$('html,body').animate({scrollTop: scrollY}, 800);
}
/* */

 
/*
$(function() {
		var userAgent = window.navigator.userAgent.toLowerCase();

		$.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
		$.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
		$.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);
});
*/