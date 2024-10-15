package org.TaylorSz.model;

import java.math.BigDecimal;

public class Produto {
    //Variaveis para produto
    private int id;
    private Long codigo;
    private String nome;
    private String marca;
    private String categoria;
    private String tipoPeso;
    private BigDecimal peso;
    private BigDecimal precoFornecimento;
    private BigDecimal precoRevenda;
    private int estoque;
    private boolean vendaGranel;
    private float porcentagem;
    private String descricao;

    //Variaveis para estatisticas de produtos
    private int produtoID;
    private Long produtoCodigo;
    private String produtoNome;
    private int totalVendido;
    private int estoqueProduto;
    private BigDecimal margemLucro;

    //Construtor para o produto;
    public Produto(int id, Long codigo, String nome, String marca, String categoria, String tipoPeso, BigDecimal peso, BigDecimal preçoFornecimento, BigDecimal precoRevenda, boolean vendaGranel, float porcentagem, String descricao){
        this.id=id;
        this.codigo=codigo;
        this.nome=nome;
        this.marca=marca;
        this.categoria=categoria;
        this.tipoPeso=tipoPeso;
        this.peso=peso;
        this.precoFornecimento =preçoFornecimento;
        this.precoRevenda=precoRevenda;
        this.vendaGranel=vendaGranel;
        this.porcentagem=porcentagem;
        this.descricao=descricao;
    }

    public Produto(Long codigo, String nome, String marca, String categoria, String tipoPeso, BigDecimal peso, BigDecimal precoFornecimento, BigDecimal precoRevenda, int estoque, boolean vendaGranel, float porcentagem, String descricao) {
        this.codigo=codigo;
        this.nome=nome;
        this.marca=marca;
        this.categoria=categoria;
        this.tipoPeso=tipoPeso;
        this.peso=peso;
        this.precoFornecimento=precoFornecimento;
        this.precoRevenda=precoRevenda;
        this.estoque=estoque;
        this.vendaGranel=vendaGranel;
        this.porcentagem=porcentagem;
        this.descricao=descricao;
    }

    public Produto(int id, Long codigo, String nome, String marca, String categoria, String tipoPeso, BigDecimal peso, BigDecimal precoFornecimento, BigDecimal precoRevenda, int estoque, boolean vendaGranel, float porcentagem, String descricao) {
        this.id=id;
        this.codigo=codigo;
        this.nome=nome;
        this.marca=marca;
        this.categoria=categoria;
        this.tipoPeso=tipoPeso;
        this.peso=peso;
        this.precoFornecimento=precoFornecimento;
        this.precoRevenda=precoRevenda;
        this.estoque=estoque;
        this.vendaGranel=vendaGranel;
        this.porcentagem=porcentagem;
        this.descricao=descricao;
    }

    public Produto(Long codigo, int estoque) {
        this.codigo = codigo;
        this.estoque = estoque;
    }

    //Construtores para estatisticas
    public Produto(int produtoID, Long produtoCodigo, String produtoNome, int totalVendido) {
        this.produtoID=produtoID;
        this.produtoCodigo=produtoCodigo;
        this.produtoNome=produtoNome;
        this.totalVendido=totalVendido;
    }

    public Produto(int produtoID, Long produtoCodigo, String produtoNome, int estoqueProduto, BigDecimal margemLuro) {
        this.produtoID=produtoID;
        this.produtoCodigo=produtoCodigo;
        this.produtoNome=produtoNome;
        this.estoqueProduto=estoqueProduto;
        this.margemLucro=margemLuro;
    }

    public Produto(int produtoId, Long produtoCodigo, String nomeProduto, BigDecimal margemLucro) {
        this.produtoID=produtoId;
        this.produtoCodigo=produtoCodigo;
        this.produtoNome=nomeProduto;
        this.margemLucro=margemLucro;
    }


    //Getters e Setters para produto
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipoPeso() {
        return tipoPeso;
    }

    public void setTipoPeso(String tipoPeso) {
        this.tipoPeso = tipoPeso;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getPrecoFornecimento() {
        return precoFornecimento;
    }

    public void setPrecoFornecimento(BigDecimal precoFornecimento) {
        this.precoFornecimento = precoFornecimento;
    }

    public BigDecimal getPrecoRevenda() {
        return precoRevenda;
    }

    public void setPrecoRevenda(BigDecimal precoRevenda) {
        this.precoRevenda = precoRevenda;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public boolean isVendaGranel() {
        return vendaGranel;
    }

    public void setVendaGranel(boolean vendaGranel) {
        this.vendaGranel = vendaGranel;
    }

    public float getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(float porcentagem) {
        this.porcentagem = porcentagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //Getters e Setters para estatisticas
    public int getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(int produtoID) {
        this.produtoID = produtoID;
    }

    public Long getProdutoCodigo() {
        return produtoCodigo;
    }

    public void setProdutoCodigo(Long produtoCodigo) {
        this.produtoCodigo = produtoCodigo;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public int getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(int totalVendido) {
        this.totalVendido = totalVendido;
    }

    public int getEstoqueProduto() {
        return estoqueProduto;
    }

    public void setEstoqueProduto(int estoqueProduto) {
        this.estoqueProduto = estoqueProduto;
    }

    public BigDecimal getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(BigDecimal margemLuro) {
        this.margemLucro = margemLuro;
    }
}
