package servicio.despacho.service;

import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.stereotype.Service;
import servicio.despacho.entity.Rutas;
import servicio.despacho.repository.RutaRepository;

@Service
public class RutaServiceImpl  extends ServiceCommonsImpl<Rutas, RutaRepository> implements RutaService {
}
