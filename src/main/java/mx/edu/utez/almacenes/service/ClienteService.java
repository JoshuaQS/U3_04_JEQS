package mx.edu.utez.almacenes.service;

import mx.edu.utez.almacenes.model.Cliente;
import mx.edu.utez.almacenes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente updated) {
        if (clienteRepository.existsById(id)) {
            updated.setId(id);
            return clienteRepository.save(updated);
        }
        return null;
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}

