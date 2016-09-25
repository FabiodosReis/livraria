(function($){
	
	var _editora = {};
	
	$(function(){
		
		getAll();
		registInteraction();		
		
	});	
	
	var getAll = function(){	
		
		var tabela = $("table tbody");
		tabela.empty();
		
		$.getJSON('listEditorasServlet').done(function(response){			
			if(response){
				$(response).each(function(i,v){					
					tabela.append(
							"<tr>" +
								"<td>" + response[i].codigo + "</td>" +
								"<td>" + response[i].nome + "</td>" +
								"<td>" + response[i].cidade + "</td>" +								
								"<td>" + 
									"<a href='#'><span class='glyphicon glyphicon-pencil'></span></a>" +
									"<a href='#' class='link-excluir'><span class='glyphicon glyphicon-remove' data-toggle='modal' data-target='#modal-excluir'></span></a>" +							
								"</td>" +
							"</tr>"
					);					
				});
				
			}			
		
		}).fail(function(error){
			
			console.log(error.statusText);
		});	
				
	};	
	
	
	var remove_editora = function(id){
		
		$.post('removEditoraServlet',{codigo: id}).done(function(response){
			
			getAll();
			
			$.notify({
				icon: "glyphicon glyphicon-success-sign",
				message: "Editora Excluído com sucesso"
				
			},
			{
				type: 'success'
				
			});			

			
		}).fail(function(error){
			
			console.log(error.statusText)
			
		});		
		
	};	
	
	var registInteraction = function(){			
		
		$(document).on("click",".link-excluir",function(evt){
			
			if(!evt.preventDefault()){
				
				var codigo = $(this).parents('tr').find('td:eq(0)').text();
				var nome = $(this).parents('tr').find('td:eq(1)').text();
				
				_editora.codigo = codigo;				
				
				$("#nome-editora").text(nome);				
				
			}			
			
		});	// ao clicar no icoce de excluir recupera o id e nome da editora	
		
		$("#btn-excluir").on('click',function(){
			
			remove_editora(parseInt(_editora.codigo));	
						
			
		}); //exclui o livro ao clicar no botao de excluir no modal	
		
		
		$(document).on('click','.glyphicon-pencil',function(evt){
			
			evt.preventDefault();
			
			var codigo = $(this).parents('tr').find('td:eq(0)').text();
			
			window.location = "cadastraEditora.html?codigo=" + parseInt(codigo);					
			
		});
			
	};	// manda a editora selecionado par a página de edição	
	
	
})(jQuery);