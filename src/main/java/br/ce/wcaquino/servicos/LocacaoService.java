package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;


public class LocacaoService {
	/**
         * 
         * @param usuario
         * @param filme
         * @return 
         * @throws Exception - Lança um erro de exceção;
         */
	public Locacao alugarFilme(Usuario usuario, Filme filme) throws Exception {
                if(filme.getEstoque() == 0){
                    throw new Exception ("Filme sem estoque.");
                }
            
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());//Data Atual;
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1); //Set a data de retorno para o dia seguinte usando o método criado no pacote data utils;
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar mÃ©todo para salvar
		
		return locacao;
	}
}