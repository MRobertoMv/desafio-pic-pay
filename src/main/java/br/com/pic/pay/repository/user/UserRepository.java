package br.com.pic.pay.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByDocument(@Param("document") String document);

	Optional<UserEntity> findByEmail(@Param("email") String email);

}
