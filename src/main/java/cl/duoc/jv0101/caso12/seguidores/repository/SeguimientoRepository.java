package cl.duoc.jv0101.caso12.seguidores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.jv0101.caso12.seguidores.model.Seguimiento;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> {
}
