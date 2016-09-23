(function($){	
	
	
	var _livro = {};
	
	
	$(function(){
		
		getAll_livro();		
		registInteraction();		
		
	});
	
	
	var getAll_livro = function(){
		
		$.getJSON('ListLivrosServlet')
		.done(function(response){		
			
			var tabela = $("table tbody");
			tabela.empty();
			obj = response;
			
			if(obj.length){
				
				$(obj).each(function(i,value){
					
					tabela.append(
							"<tr>" +
								"<td>" + obj[i].codigo + "</td>" +	
								"<td>" + obj[i].nome + "</td>" +	
								"<td>" + obj[i].descricao + "</td>" +	
								"<td>" + obj[i].valor + "</td>" +	
								"<td>" + obj[i].isbn + "</td>" +	
								"<td>" + obj[i].autor.nome + "</td>" +	
								"<td>" + 
									"<a href='#'><span class='glyphicon glyphicon-pencil'></span></a>" +
									"<a href='#' class='link-excluir'><span class='glyphicon glyphicon-remove' data-toggle='modal' data-target='#modal-excluir'></span></a>" +							
								"</td>" +
							"</tr>"
					);
					
				});				
				
			}else{
				
				tabela.append(
						
						"<tr>" + "<td colspan='7'>Nenhuma informação encontrada</td>"+"</tr>"
				);			
				
			}			
			
		}		
		
		).fail(function(error){
			
			console.log("Erro:" + error.statusText);
			
		});
	};
	
	
	
	var remove_livro = function(id){
		
		$.post('RemovLivroServlet',{codigo: id}).done(function(response){
			
			getAll_livro();
			
			$.notify({
				icon: "glyphicon glyphicon-success-sign",
				message: "Livro Excluído com sucesso"
				
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
				
				_livro.codigo = codigo;				
				
				$("#nome-livro").text(nome);				
				
			}			
			
		});	// ao clicar no icoce de excluir recupera o id e nome do livro	
		
		$("#btn-excluir").on('click',function(){
			
			remove_livro(parseInt(_livro.codigo));	
						
			
		}); //exclui o livro ao clicar no botao de excluir no modal	
		
		
		$(document).on('click','.glyphicon-pencil',function(evt){
			
			evt.preventDefault();
			
			var codigo = $(this).parents('tr').find('td:eq(0)').text();
			
			window.location = "cadastraLivro.html?codigo=" + parseInt(codigo);					
			
		});
			
	};	// manda o livro selecionado par a página de edição
	
	
})(jQuery);