package br.com.pic.pay.repository.transaction;

import br.com.pic.pay.enums.transaction.TransactionStatus;
import br.com.pic.pay.enums.transaction.TransactionType;
import br.com.pic.pay.repository.AbstractEntity;
import br.com.pic.pay.repository.portfolio.PortfolioEntity;
import br.com.pic.pay.repository.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity(name = "transaction")
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq_id")
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class TransactionEntity extends AbstractEntity {

	private static final long serialVersionUID = 8104629740132790884L;

	@GeneratedValue(generator = "transaction_seq")
	@Id
	@ToString.Exclude
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_portfolio", referencedColumnName = "id", insertable = false, updatable = false)
	private PortfolioEntity portfolio;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType type;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_status")
	private TransactionStatus status;

	@DecimalMin(value = "0.01")
	private Double valueTransaction;

	private UserEntity sender;

	private UserEntity receive;

	private Double initialBalance;

	private Double balance;

}
