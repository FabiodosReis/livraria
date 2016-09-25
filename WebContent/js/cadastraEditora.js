(function($){
	
	var _editora = {};
	var _editoraCodigo;
	
	$(function(){
		
		enviar();
		get();
		
	});	
	
	var get = function(){
		
		//recupera os parametros da url
		var url = window.location.search;		
		
		_editoraCodigo = parseInt(url.substring(url.indexOf('=')+1,url.lenght));		
		
		if(_editoraCodigo){
			
			$.getJSON('getEditoraServlet',{
				
				codigo: _editoraCodigo
				
			}).done(function(response){
				
				renderFields(response);								
				
			}).fail(function(error){
				
				console.log(error.statusText);
				
			});		
			
		}		
		
	};	
	
	
	var cadastrar = function(editora){	
		
		_editoraCodigo > 0 ? editora.codigo = _editoraCodigo : editora.codigo = 0;		
			
		$.post('cadastrarEditoraServlet',{
				
				editora : JSON.stringify(editora)
			
				}).done(function(response){
					
					$.notify({
						icon: "glyphicon glyphicon-success-sign",
						message: "Editora salvo com sucesso"
						
					},
					{
						type: 'success'
						
					});			
					
					
					setTimeout(function(){
						
						window.location = "pesquisaEditora.html";
						
						
					},2000);
					
				}).fail(function(error){
					
					console.log(error.statustext);
					
				});		
	};
	
	
	var extractForm = function(){		
		
		_editora.nome = $('#input-nome').val();				
		_editora.cidade = $('#input-cidade').val();			
		
	}
	
	
	var renderFields = function(editora){
		
		$('#input-nome').val(editora.nome);		
		$('#input-cidade').val(editora.cidade);
						
		
	};
	
	var enviar = function(){
		
		$('form').validator().on('submit', function (e) {
			
			if(!e.isDefaultPrevented()) {
				e.preventDefault()				
		        extractForm();
				cadastrar(_editora);		        
		    } 
			  
		});		
		
	};	
	
	
})(jQuery);





	