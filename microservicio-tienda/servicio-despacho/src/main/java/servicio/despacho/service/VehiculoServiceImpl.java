package servicio.despacho.service;

import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import servicio.despacho.entity.Vehiculos;
import servicio.despacho.repository.VehiculoRepository;

@Service
public class VehiculoServiceImpl extends ServiceCommonsImpl<Vehiculos, VehiculoRepository> implements VehiculoService {
}
