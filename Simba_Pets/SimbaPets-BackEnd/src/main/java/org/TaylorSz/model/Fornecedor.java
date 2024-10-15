package org.TaylorSz.model;

import java.util.List;

public class Fornecedor {
    private int id;
    private String nomeEmpresa;
    private String cnpj;
    private String rua;
    private String bairro;
    private int numero;
    private String cidade;
    private String estado;
    private String nomeVendedor;
    private String telefone;
    private String email;
    private List<String> produtosFornecidos;

    public Fornecedor(int id, String nomeEmpresa, String cnpj, String rua, String bairro, int numero, String cidade, String estado, String nomeVendedor, String telefone, String email, List<String> produtosFornecidos) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.nomeVendedor = nomeVendedor;
        this.telefone = telefone;
        this.email = email;
        this.produtosFornecidos = produtosFornecidos;
    }

    public Fornecedor(String nomeEmpresa, String cnpj, String rua, String bairro, int numero, String cidade, String estado, String nomeVendedor, String telefone, String email, List<String> produtosFornecidos) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.nomeVendedor = nomeVendedor;
        this.telefone = telefone;
        this.email = email;
        this.produtosFornecidos = produtosFornecidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getProdutosFornecidos() {
        return produtosFornecidos;
    }

    public void setProdutosFornecidos(List<String> produtosFornecidos) {
        this.produtosFornecidos = produtosFornecidos;
    }
}
