package servicio.despacho.service;

import application.entity.serviceCommons.ServiceCommons;
import servicio.despacho.entity.Despacho;

import java.util.List;

public interface DespachoService extends ServiceCommons<Despacho> {

    public void habilitar(Iterable<Long> id);

    public void desHabilitar(List<Long> id);


}
