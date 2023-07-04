package application.entity.serviceCommons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceCommons <E>{

    public Iterable<E> findAll();

    public Page<E> findAllPage(Pageable pageable);

    public Optional<E> findById(Long id);
    public E save(E entity);
    public void deleteById(Long id);
}
