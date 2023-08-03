package aplication.tienda.producto.factory.service;

import aplication.tienda.producto.factory.repository.RepositoryCategoria;
import application.entity.models.EntityServiceProducto.Categoria;
import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoriaImpl extends ServiceCommonsImpl<Categoria, RepositoryCategoria> implements ServiceCategoria {


    @Override
    public List<Categoria> listarCategoria() {
       return (List<Categoria>) repository.findAll();
    }
}
