package com.rishi.service;

import com.rishi.modal.Order;
import com.rishi.modal.Seller;
import com.rishi.modal.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySellerId(Seller seller);
    List<Transaction> getAllTransactions();

}
