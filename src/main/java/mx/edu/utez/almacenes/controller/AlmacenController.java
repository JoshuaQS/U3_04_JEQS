package mx.edu.utez.almacenes.controller;

import mx.edu.utez.almacenes.service.AlmacenService;
import mx.edu.utez.almacenes.model.Almacen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@CrossOrigin(origins = "*")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    @GetMapping
    public List<Almacen> getAll() {
        return almacenService.findAll();
    }

    @GetMapping("/{id}")
    public Almacen getById(@PathVariable Long id) {
        return almacenService.findById(id).orElse(null);
    }

    @PostMapping
    public Almacen save(@RequestBody Almacen almacen) {
        return almacenService.save(almacen);
    }

    @PutMapping("/{id}")
    public Almacen update(@PathVariable Long id, @RequestBody Almacen almacen) {
        return almacenService.update(id, almacen);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        almacenService.delete(id);
    }

    @PutMapping("/{id}/rentar")
    public Almacen rentar(@PathVariable Long id) {
        return almacenService.rentar(id);
    }

    @PutMapping("/{id}/vender")
    public Almacen vender(@PathVariable Long id) {
        return almacenService.vender(id);
    }
}
