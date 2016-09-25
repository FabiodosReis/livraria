(function($){
	
	var obj = {},
		autor = {},
		livroCodigo;
	
	$(function(){	
		
		init();		
		
	});	
	
	var init = function(){			
		getAutores();		
		enviar();
		
	};	
	
	var getAutores = function(){		
					
		$.getJSON('listAutoresServlet').done(function(response){			
			if(response){
				$(response).each(function(i,v){					
					$('#input-autor').append(
							"<option value='" + response[i].codigo + "'"  + ">" + response[i].nome + "</option>" 
					);					
				});
				
			}	
			
			get();
			
		}).fail(function(error){
			
			console.log(error.statusText);
		});	
				
	};	
	
	var get = function(){
		
		var url = window.location.search;		
		
		livroCodigo = parseInt(url.substring(url.indexOf('=')+1,url.lenght));		
		
		if(livroCodigo){
			
			$.getJSON('getLivroServlet',{
				
				codigo: livroCodigo
				
			}).done(function(response){
				
				renderFields(response);								
				
			}).fail(function(error){
				
				console.log(error.statusText);
				
			});		
			
		}		
		
	};	
	
	
	var cadastrar = function(livro){	
		
		livroCodigo > 0 ? livro.codigo = livroCodigo : livroCodigo = 0;		
		
		$.post('cadastrarLivroServlet',
				{
				
				livro : JSON.stringify(livro)
			
				}).done(function(response){
					
					$.notify({
						icon: "glyphicon glyphicon-success-sign",
						message: "Livro salvo com sucesso"
						
					},
					{
						type: 'success'
						
					});			
					
					
					setTimeout(function(){
						
						window.location = "pesquisaLivro.html";
						
						
					},2000);
					
				}).fail(function(error){
					
					console.log(error.statustext);
					
				});		
	};
	
	
	var renderFields = function(livro){
		
		$('#input-nome').val(livro.nome);
		$('#input-descricao').val(livro.descricao);
		$('#input-valor').val(livro.valor);
		$('#input-isbn').val(livro.isbn);		
		$('#input-autor option[value="' + livro.autor.codigo + '"]').attr({ selected : "selected" });				
		
	};
	
	
	var extractForm = function(){		
		
		obj.nome = $('#input-nome').val();
		obj.descricao = $('#input-descricao').val();
		obj.valor = parseInt($('#input-valor').val());
		obj.isbn = $('#input-isbn').val();		
		autor.codigo = parseInt($('#input-autor').val());		
		obj.autor = autor;
		
	}
	
	
	var enviar = function(){
		
		$('form').validator().on('submit', function (e) {
			
			if(!e.isDefaultPrevented()) {
				e.preventDefault()				
		        extractForm();
				cadastrar(obj);		        
		    } 
			  
		});		
		
	};
	
	
})(jQuery);