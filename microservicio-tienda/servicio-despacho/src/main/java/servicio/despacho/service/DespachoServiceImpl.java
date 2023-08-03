package servicio.despacho.service;

import application.entity.serviceCommons.ServiceCommonsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicio.despacho.ClientHttpOrden.OrdenFeignClient;
import servicio.despacho.entity.Despacho;
import servicio.despacho.repository.DespachoRepository;

import java.util.List;

@Service
public class DespachoServiceImpl extends ServiceCommonsImpl<Despacho, DespachoRepository> implements DespachoService {

    @Autowired
    OrdenFeignClient ordenFeignClient;



    @Override
    @Transactional
    public void habilitar(Iterable<Long> id) {
        ordenFeignClient.habilitar(id);
    }

    @Override
    public void desHabilitar(List<Long> id) {
        ordenFeignClient.desHabilitar(id);
    }


}
