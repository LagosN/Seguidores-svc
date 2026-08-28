package cl.duoc.jv0101.caso12.seguidores.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import cl.duoc.jv0101.caso12.seguidores.model.Seguimiento;
import cl.duoc.jv0101.caso12.seguidores.repository.SeguimientoRepository;

@Service
public class SeguimientoService {

    private final SeguimientoRepository repository;

    public SeguimientoService(SeguimientoRepository repository) {
        this.repository = repository;
    }

    public List<Seguimiento> findAll() {
        return repository.findAll();
    }

    public Optional<Seguimiento> findById(Long id) {
        return repository.findById(id);
    }

    public Seguimiento create(Seguimiento recurso) {
        return repository.save(recurso);
    }

    public Optional<Seguimiento> update(Long id, Seguimiento datos) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(datos.getNombre());
            existente.setSeguidor(datos.getSeguidor());
            existente.setSeguido(datos.getSeguido());
            return repository.save(existente);
        });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(existente -> {
            repository.delete(existente);
            return true;
        }).orElse(false);
    }
}
