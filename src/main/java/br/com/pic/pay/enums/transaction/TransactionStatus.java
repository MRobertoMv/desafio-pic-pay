package br.com.pic.pay.enums.transaction;

public enum TransactionStatus {

	INIT,
	PROCESSING,
	AUTHORIZED,
	DENIED,
	FINALIZE,
	ROLLBACK,
	PENDING_ROLLBACK,
	ERROR
}
