package mx.edu.utez.almacenes.service;

import mx.edu.utez.almacenes.model.Almacen;
import mx.edu.utez.almacenes.repository.AlmacenRepository;
import mx.edu.utez.almacenes.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    public List<Almacen> findAll() {
        return almacenRepository.findAll();
    }

    public Optional<Almacen> findById(Long id) {
        return almacenRepository.findById(id);
    }

    @Autowired
    private AlmacenRepository AlmacenRepository;

    @Autowired
    private SedeRepository sedeRepository;

    public Almacen save(Almacen almacen) {
        almacen.setFechaRegistro(LocalDate.now());

        // Cargar la sede completa desde la BD si viene el ID
        if (almacen.getSede() != null && almacen.getSede().getId() != null) {
            almacen.setSede(sedeRepository.findById(almacen.getSede().getId()).orElse(null));
        }

        // Guardar para obtener el ID
        Almacen saved = almacenRepository.save(almacen);

        String sedeClave = (saved.getSede() != null && saved.getSede().getClave() != null)
                ? saved.getSede().getClave()
                : "SINSEDE";

        saved.setClave(sedeClave + "-A" + saved.getId());

        return almacenRepository.save(saved);
    }


    public Almacen update(Long id, Almacen updated) {
        if (almacenRepository.existsById(id)) {
            updated.setId(id);
            return almacenRepository.save(updated);
        }
        return null;
    }

    public void delete(Long id) {
        almacenRepository.deleteById(id);
    }

    public Almacen rentar(Long id) {
        Almacen almacen = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
        almacen.setEstado("RENTADO");
        return almacenRepository.save(almacen);
    }

    public Almacen vender(Long id) {
        Almacen almacen = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
        almacen.setEstado("VENDIDO");
        return almacenRepository.save(almacen);
    }
}
