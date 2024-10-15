package org.TaylorSz.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Venda {
    //Variaveis para a venda
    private int id;
    private List<String> produto; // IDs dos produtos
    private List<Integer> quantidade; // Quantidade de cada produto
    private BigDecimal valorTotal;
    private String formaPagamento;
    private LocalDate dataVenda;

    //Variaveis para estatisticas de vendas
    private int totalVendas;
    private BigDecimal lucro;

    //Construtor para a venda
    public Venda(int id, List<String> produto, List<Integer> quantidade, BigDecimal valorTotal, String formaPagamento, LocalDate dataVenda) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.dataVenda = dataVenda;
    }

    public Venda(List<String> produto, List<Integer> quantidade, BigDecimal valorTotal, String formaPagamento, LocalDate dataVenda) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.dataVenda = dataVenda;
    }

    //Construtor para estatisticas de vendas
    public Venda(int totalVendas) {
        this.totalVendas = totalVendas;
    }

    public Venda(BigDecimal lucro) {
        this.lucro = lucro;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getProduto() {
        return produto;
    }

    public void setProduto(List<String> produto) {
        this.produto = produto;
    }

    public List<Integer> getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(List<Integer> quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(int totalVendas) {
        this.totalVendas = totalVendas;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public void setLucro(BigDecimal lucro) {
        this.lucro = lucro;
    }
}