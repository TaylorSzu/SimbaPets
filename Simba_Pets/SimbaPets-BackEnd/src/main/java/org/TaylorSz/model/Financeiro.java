package org.TaylorSz.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Financeiro {
    private int id;
    private String beneficiario;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    private String status;

    public Financeiro(int id, String beneficiario, LocalDate dataVencimento, BigDecimal valor, String status) {
        this.id = id;
        this.beneficiario = beneficiario;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.status = status;
    }

    public Financeiro(String beneficiario, LocalDate dataVencimento, BigDecimal valor, String status) {
        this.beneficiario = beneficiario;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
