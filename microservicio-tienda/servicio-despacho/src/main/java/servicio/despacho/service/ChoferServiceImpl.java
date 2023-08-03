package servicio.despacho.service;

import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.stereotype.Service;
import servicio.despacho.entity.Chofer;
import servicio.despacho.repository.ChoferRepository;

@Service
public class ChoferServiceImpl extends ServiceCommonsImpl<Chofer, ChoferRepository> implements ChoferService {
}
