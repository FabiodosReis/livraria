(function($){
	
	var _autor = {};
	
	$(function(){
		
		getAll();
		registInteraction();		
		
	});	
	
	var getAll = function(){	
		
		var tabela = $("table tbody");
		tabela.empty();
		
		$.getJSON('listAutoresServlet').done(function(response){			
			if(response){
				$(response).each(function(i,v){					
					tabela.append(
							"<tr>" +
								"<td>" + response[i].codigo + "</td>" +
								"<td>" + response[i].nome + "</td>" +
								"<td>" + response[i].dataNascimento + "</td>" +
								"<td>" + response[i].email + "</td>" +
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
	
	
	var remove_autor = function(id){
		
		$.post('removAutorServlet',{codigo: id}).done(function(response){
			
			getAll();
			
			$.notify({
				icon: "glyphicon glyphicon-success-sign",
				message: "Autor Excluído com sucesso"
				
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
				
				_autor.codigo = codigo;				
				
				$("#nome-autor").text(nome);				
				
			}			
			
		});	// ao clicar no icoce de excluir recupera o id e nome do livro	
		
		$("#btn-excluir").on('click',function(){
			
			remove_autor(parseInt(_autor.codigo));	
						
			
		}); //exclui o livro ao clicar no botao de excluir no modal	
		
		
		$(document).on('click','.glyphicon-pencil',function(evt){
			
			evt.preventDefault();
			
			var codigo = $(this).parents('tr').find('td:eq(0)').text();
			
			window.location = "cadastraAutor.html?codigo=" + parseInt(codigo);					
			
		});
			
	};	// manda o autor selecionado par a página de edição	
	
	
})(jQuery);