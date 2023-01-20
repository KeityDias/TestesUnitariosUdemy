package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;


public class LocacaoService {
	/**
         * 
         * @param usuario
         * @param filme
         * @return 
         * @throws Exception - Lan�a um erro de exce��o;
         */
	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {
                
                if (usuario == null){
                 throw new LocadoraException("Usu�rio vazio.");
                }
                
                if (filme == null){
                throw new LocadoraException("Filme vazio.");
                }
                if(filme.getEstoque() == 0){
                  //  throw new Exception ("Filme sem estoque.");
                  throw new FilmeSemEstoqueException("Filme sem estoque."); //Garante que a exce��o seja lan�ada apenas por esse motivo;
                }
                
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());//Data Atual;
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1); //Set a data de retorno para o dia seguinte usando o m�todo criado no pacote data utils;
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
}