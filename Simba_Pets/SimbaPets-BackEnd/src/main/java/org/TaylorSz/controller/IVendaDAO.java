package org.TaylorSz.controller;

import org.TaylorSz.model.Venda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IVendaDAO {

    Venda save(Venda venda);

    Venda update(Venda venda);

    void delete(int id);

    List<Venda> findAll();

    List<Venda> findByDate(LocalDate date);

    int countDayVenda();

    int countMonthVenda();

    int countYearVenda();

    BigDecimal lucroDayVenda();

    BigDecimal lucroWeakVenda();

    BigDecimal lucroMonthVenda();

    BigDecimal lucroYearVenda();
}
