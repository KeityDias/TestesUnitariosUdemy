package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Calendar;
import java.util.List;

public class LocacaoService {

    /**
     *
     * @param usuario
     * @param filme
     * @return
     * @throws Exception - Lança um erro de exceção;
     */
    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {

        if (usuario == null) {
            throw new LocadoraException("Usuário vazio.");
        }

        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Filme vazio.");
        }

        for (Filme filme : filmes) {
            if (filme.getEstoque() == 0) {
                //  throw new Exception ("Filme sem estoque.");
                throw new FilmeSemEstoqueException("Filme sem estoque."); //Garante que a exceção seja lançada apenas por esse motivo;
            }
        }

        Locacao locacao = new Locacao();
        locacao.setFilmes(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());//Data Atual;
        Double valorTotal = 0d;
        for (int i = 1; i < filmes.size(); i++) { //Contador para saber qual é o f1, f2,f3..
            Filme filme = filmes.get(i);
            double valorFilme = filme.getPrecoLocacao();
            
            switch (i){
                case 3: valorFilme = valorFilme*0.75; break;
                case 4: valorFilme = valorFilme*0.50; break;
                case 5: valorFilme = valorFilme*0.255; break;
                case 6: valorFilme = 0d; break;
            }
            valorTotal += valorFilme;
        }
        locacao.setValor(valorTotal);
            
           /* if (i == 3) { //Se "i" estiver na 3 interação
                valorFilme = valorFilme*0.75;
            }
            if (i == 4) { //Se "i" estiver na 4 interação, sendo i=4;
                valorFilme = valorFilme*0.50;
            }
            if (i == 5) { //Se "i" estiver na 5 interação
                valorFilme = valorFilme*0.25;
            }
            if (i == 6) { //Se "i" estiver na 6 interação
                valorFilme = 0;
            }
            valorTotal += valorFilme;
        }
        locacao.setValor(valorTotal);
        */
        
        //***Entrega no dia seguinte***
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1); //Set a data de retorno para o dia seguinte usando o método criado no pacote data utils;
        if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
            dataEntrega = adicionarDias(dataEntrega,1);
        }
        locacao.setDataRetorno(dataEntrega);
        //Salvando a locacao...	
        return locacao;
    }
}

