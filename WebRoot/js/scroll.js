 $(document).ready(function(){
			var offsetY = $(window).height() - $('#go_top_bottom').height() + $(document).scrollTop();
			var offsetX = $('.w960').width() + $('.w960').offset().left + $(document).scrollLeft();
			$('#go_top_bottom').css({'left':offsetX, 'top':offsetY}); 
			$(window).scroll(function(){scroll_position('go_top_bottom');});
			$(window).resize(function(){scroll_position('go_top_bottom');});
			$(window).load(function(){scroll_position('go_top_bottom');});
			$('#go_top_bottom #go_top').click(function(){
				scroll_to_pos('top');
				return false;
			});
			
			$('#go_top_bottom #go_bottom').click(function(){
				scroll_to_pos('bottom');
				return false;
			});
			
			if(!isScroll())
			{
				$('#go_top_bottom').hide();
			}
			else
			{
				 $('#go_top_bottom').show();
			}
			
	    });