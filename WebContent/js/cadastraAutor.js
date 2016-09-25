(function($){
	
	var _autor = {};
	var _autorCodigo;
	
	$(function(){
		$('#input-data-nascimento').datepicker({
			format: 'dd/mm/yyyy',
			language: 'pt-BR',
			autoclose:true
		});			
		enviar();
		get();
		
	});	
	
	var get = function(){
		
		//recupera os parametros da url
		var url = window.location.search;		
		
		_autorCodigo = parseInt(url.substring(url.indexOf('=')+1,url.lenght));		
		
		if(_autorCodigo){
			
			$.getJSON('getAutorServlet',{
				
				codigo: _autorCodigo
				
			}).done(function(response){
				
				renderFields(response);								
				
			}).fail(function(error){
				
				console.log(error.statusText);
				
			});		
			
		}		
		
	};	
	
	
	var cadastrar = function(autor){	
		
		_autorCodigo > 0 ? _autor.codigo = _autorCodigo : _autorCodigo = 0;		
		
		$.post('cadastrarAutorServlet',{
				
				autor : JSON.stringify(autor)
			
				}).done(function(response){
					
					$.notify({
						icon: "glyphicon glyphicon-success-sign",
						message: "Autor salvo com sucesso"
						
					},
					{
						type: 'success'
						
					});			
					
					
					setTimeout(function(){
						
						window.location = "pesquisaAutor.html";
						
						
					},2000);
					
				}).fail(function(error){
					
					console.log(error.statustext);
					
				});		
	};
	
	
	var extractForm = function(){		
		
		_autor.nome = $('#input-nome').val();
		_autor.dataNascimento = dateToEnglish($('#input-data-nascimento').val());		
		_autor.email = $('#input-email').val();		
		
	}
	
	
	var renderFields = function(autor){
		
		$('#input-nome').val(autor.nome);
		$('#input-data-nascimento').val(autor.dataNascimento);
		$('#input-email').val(autor.email);
						
		
	};
	
	var enviar = function(){
		
		$('form').validator().on('submit', function (e) {
			
			if(!e.isDefaultPrevented()) {
				e.preventDefault()				
		        extractForm();
				cadastrar(_autor);		        
		    } 
			  
		});		
		
	};	
	
	
})(jQuery);





	