/**
 * confirma~ção de exclusão de contato
 */

function confirmar(id) {
	let resposta = confirm("Confirma a exclusão do contato "+id+"?")
	if (resposta === true) {
		window.location.href = "delete?id=" + id

	}
}