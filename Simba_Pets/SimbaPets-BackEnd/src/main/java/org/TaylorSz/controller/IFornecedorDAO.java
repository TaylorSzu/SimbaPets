package org.TaylorSz.controller;

import org.TaylorSz.model.Fornecedor;

import java.util.List;

public interface IFornecedorDAO {

    Fornecedor save(Fornecedor fornecedor);

    Fornecedor update(Fornecedor fornecedor);

    void delete(String nomeEmpresa);

    List<Fornecedor> findAll();

    List<Fornecedor> findByName(String name);
}
