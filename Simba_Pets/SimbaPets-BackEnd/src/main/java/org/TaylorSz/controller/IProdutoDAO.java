package org.TaylorSz.controller;

import org.TaylorSz.model.Produto;

import java.util.List;
import java.util.Optional;

public interface IProdutoDAO {

    Produto save(Produto produto);//Metodo para Adicionar um produto

    Produto update(Produto produto);//Metodo para editar ou atualizar um produto

    void delete(Long codigo);//Metodo para deletar o produto

    List<Produto> findAll();//Metodo para listar todos os produtos

    Produto findById(Long codigo);//Metodo para pesquisar um produto pelo id

    Produto updateEstque(Produto produto);

    List<Produto> top10Products(); //Metodo para listar os 10 produtos mais vendidos

    List<Produto> findLowStockProducts(); //Metodo para listar os produtos com baix estoque

    List<Produto> findHighMarginProducts(); //Metodo para listar os produtos mais lucrativos
}
