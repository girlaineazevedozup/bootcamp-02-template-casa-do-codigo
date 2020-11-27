package com.zup.bootcamp.infrastructure;

import com.zup.bootcamp.model.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

    Cupom findByCodigo(String codigo);
}
