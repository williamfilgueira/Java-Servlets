/**
 * validação de formulario 

 */

function validar(){
	
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	

	if(nome === ""){
		alert("Entre com o nome")
		fmrContato.nome.focus()
		return false
	}else if(fone === ""){
		alert("Entre com o telefone")
		fmrContato.fone.focus()
		return false
	}else {
		document.forms["frmContato"].submit()
	}

}