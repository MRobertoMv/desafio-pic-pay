package br.com.pic.pay.repository.portfolio;

import java.util.List;

import br.com.pic.pay.repository.AbstractEntity;
import br.com.pic.pay.repository.transaction.TransactionEntity;
import br.com.pic.pay.repository.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity(name = "portfolio")
@SequenceGenerator(name = "portfolio_seq", sequenceName = "portfolio_seq_id")
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class PortfolioEntity extends AbstractEntity {

	private static final long serialVersionUID = -4243701422184841119L;

	@GeneratedValue(generator = "portfolio_seq")
	@Id
	@ToString.Exclude
	private Long id;

	@OneToOne(mappedBy = "portfolio")
	private UserEntity user;

	private Double initialBalance;

	private Double balance;

	@OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY)
	private List<TransactionEntity> transactions;

}
