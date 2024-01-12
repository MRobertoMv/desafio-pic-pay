package br.com.pic.pay.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.pic.pay.enums.user.UserType;
import br.com.pic.pay.repository.portfolio.PortfolioEntity;
import br.com.pic.pay.repository.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractEntity implements Serializable{

    private static final long serialVersionUID = -3408232367947073453L;

	@CreationTimestamp
    @Column(updatable = false)
    protected LocalDateTime createdAt;

    @Column(insertable = false)
    protected LocalDateTime updatedAt;
    
	@Column(length = 50)
	protected String uuid;
	
    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    @PrePersist
    public void onPersist() {
    	uuid = UUID.randomUUID().toString();
    }
}
