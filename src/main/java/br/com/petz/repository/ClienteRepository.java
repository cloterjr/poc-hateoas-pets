package br.com.petz.repository;

import br.com.petz.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /*@Query(value = "SELECT c FROM Cliente c INNER JOIN FETCH c.pets p",
            countQuery = "SELECT COUNT(c.id) FROM Cliente c")*/
    Page<Cliente> findAll(Pageable pageable);

}
