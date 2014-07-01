function Jumping(){
					document.PageForm.submit();
					return;
				}
				function gotoPage(pagenum){
					document.PageForm.jumpPage.value = pagenum;
					document.PageForm.submit();
					return;
				}