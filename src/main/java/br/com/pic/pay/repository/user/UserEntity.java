package br.com.pic.pay.repository.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.pic.pay.enums.user.UserType;
import br.com.pic.pay.repository.AbstractEntity;
import br.com.pic.pay.repository.portfolio.PortfolioEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity(name = "users")
@SequenceGenerator(name = "users_seq", sequenceName = "users_seq_id")
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends AbstractEntity {

	private static final long serialVersionUID = -3781050220193136481L;

	@Id
	@GeneratedValue(generator = "users_seq")
	@ToString.Exclude
	private Long id;

	@Column(name = "full_name", length = 255)
	private String fullName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "document", unique = true)
	@ToString.Exclude
	private String document;

	@ToString.Exclude
	private String pwd;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	private UserType type;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    @JsonIgnore
    private PortfolioEntity portfolio;
	
    @ToString.Include(name = "document")
    public String documentMask() {
        return document == null ? "" : document.substring(0,2).concat("*****").concat(document.substring(document.length()-2,document.length()));
    }
    
    @ToString.Include(name = "pwd")
    public String pwdHidden() {
        return pwd == null ? "" : "********";
    }

}
