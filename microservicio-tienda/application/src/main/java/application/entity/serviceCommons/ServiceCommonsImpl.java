package application.entity.serviceCommons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCommonsImpl <E, R extends PagingAndSortingRepository<E, Long>> implements ServiceCommons<E>{

    @Autowired
    protected  R repository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return  repository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Page<E> findAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
       repository.deleteById(id);
    }
}
