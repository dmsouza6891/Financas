package br.com.dmsouza.financas.service;

import br.com.dmsouza.financas.model.Competencia;
import br.com.dmsouza.financas.model.FonteDeRecurso;
import br.com.dmsouza.financas.model.Transacao;

public class TransacaoService {
	
	public void registrarTransacao(Competencia competencia, Transacao transacao) {

        // adiciona na competência
        competencia.getTransacoes().add(transacao);

        // atualiza saldo da fonte
        FonteDeRecurso fonte = transacao.getFonteDeRecurso();
        fonte.setSaldo(fonte.getSaldo().subtract(transacao.getValor()));


    }
}
