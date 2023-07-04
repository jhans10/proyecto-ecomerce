package aplication.tienda.producto.factory.service;

import aplication.tienda.producto.factory.repository.ProductoMovilRepository;
import application.entity.models.productos.ProductoMovil;
import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductoMovilServiceImpl extends ServiceCommonsImpl<ProductoMovil, ProductoMovilRepository> implements ProductoMovilService{
}
