package mx.edu.utez.almacenes.service;

import mx.edu.utez.almacenes.model.Sede;
import mx.edu.utez.almacenes.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    public List<Sede> findAll() {
        return sedeRepository.findAll();
    }

    public Optional<Sede> findById(Long id) {
        return sedeRepository.findById(id);
    }

    public Sede save(Sede sede) {
        // Primero guardamos para obtener el ID
        Sede saved = sedeRepository.save(sede);

        // Luego generamos la clave con el ID real
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String aleatorio = String.format("%04d", (int)(Math.random() * 10000));
        saved.setClave("C" + saved.getId() + "-" + fecha + "-" + aleatorio);

        return sedeRepository.save(saved);
    }

    public Sede update(Long id, Sede updated) {
        if (sedeRepository.existsById(id)) {
            updated.setId(id);
            return sedeRepository.save(updated);
        }
        return null;
    }

    public void delete(Long id) {
        sedeRepository.deleteById(id);
    }
}

