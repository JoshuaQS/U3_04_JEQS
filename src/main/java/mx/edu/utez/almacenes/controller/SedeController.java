package mx.edu.utez.almacenes.controller;

import mx.edu.utez.almacenes.model.Sede;
import mx.edu.utez.almacenes.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedes")
@CrossOrigin(origins = "*")
public class SedeController {

    @Autowired
    private SedeService sedeService;

    @GetMapping
    public List<Sede> getAll() {
        return sedeService.findAll();
    }

    @GetMapping("/{id}")
    public Sede getById(@PathVariable Long id) {
        return sedeService.findById(id).orElse(null);
    }

    @PostMapping
    public Sede create(@RequestBody Sede sede) {
        return sedeService.save(sede);
    }

    @PutMapping("/{id}")
    public Sede update(@PathVariable Long id, @RequestBody Sede sede) {
        return sedeService.update(id, sede);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sedeService.delete(id);
    }
}
