package aplication.tienda.producto.factory.service;

import aplication.tienda.producto.factory.repository.ProductoElectronicoRepository;
import application.entity.models.productos.ProductoElectronico;
import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductoElectronicServiceImpl extends ServiceCommonsImpl<ProductoElectronico, ProductoElectronicoRepository>  implements ProductoElectronicoService {




}
