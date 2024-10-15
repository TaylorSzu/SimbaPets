package org.TaylorSz.controller;

import org.TaylorSz.model.Financeiro;

import java.util.List;
import java.util.Optional;

public interface IFinanceiroDAO {

    Financeiro save(Financeiro financeiro);

    Financeiro update(Financeiro financeiro);

    void delete(int id);

    List<Financeiro> findAll();

    Optional<Financeiro> findById(int id);

    List<Financeiro> contasPendentes(); //Metodo para listar as contas não pagas

    List<Financeiro> contasPagas(); //Metodo para listar as contas que estão pagas

    List<Financeiro> contasVencidas(); //Metodo para listar cotas vencidas
}
