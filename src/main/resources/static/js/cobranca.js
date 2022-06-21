/*
    Usando Jquery para pegar o evento da exibição do modal
    exemplo pego na documentação do bootstrap:
    https://getbootstrap.com/docs/4.6/components/modal/#:~:text=%24(%27%23exampleModal,recipient)%0A%7D)
*/
$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
    // botão que acionou o modal
    var botao = $(event.relatedTarget)
    // Extraindo as informações dos atributos data-* (data-codigo)
    var codigoTitulo = botao.data('codigo')
    var descricaoTitulo = botao.data('descricao')

    // pegando meu modal
    var modal =$(this)
    // procurando um form dentro do modal e armazenando na variavel
    var form = modal.find('form')
    // pegando um atributo do form
    var action = form.data('url-base')

    // verificando se meu action NÃO termina com barra
    if(!action.endsWith('/')) {
        action += '/'
    }

    // adicionando ao atributo action o codigo
    form.attr('action', action + codigoTitulo)

    modal.find('.modal-body span').html('Tem certeza que deseja excluir o titulo <strong>' + descricaoTitulo + '</strong>?')

});

/*
    Inicializando via jquery o componente tooltip
*/
$(function () {

    /**
    * Inicialização do tooltip
    */
  $('[rel="tooltip"]').tooltip()

  /**
   * inicialização do maskmoney
   */
  $('.js-currency').maskMoney({decimal: ',', thousands: '.'})

  /**
   * Evendo de click para atualização de status
   */
  $('.js-atualizar-status').on('click', function (event) {
    event.preventDefault();

    var botao = $(event.currentTarget)
    var url = botao.attr('href')

    /**
     * REQUISIÇÃO COM AJAX
     * pegando uma requisição PUT com AJAX
     */
    var response = $.ajax({
        url: url,
        type: 'PUT'
    }).done(function (e) {
        var codigoTitulo = botao.data('codigo')
        $(`#${codigoTitulo}-badge`).removeClass('badge-danger').addClass('badge-success').text(`${e}`)
        botao.hide();
    }).fail(function (msg) {
        console.log(`Falha ${msg}`)
    })

 

    

  })
})