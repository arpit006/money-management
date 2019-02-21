package com.management.money.framework.service;

import com.management.money.framework.convertor.BaseConvertor;
import com.management.money.framework.entity.BaseEntity;
import com.management.money.framework.exception.NotFoundException;
import com.management.money.framework.vo.BaseVo;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href = "mailto: iarpitsrivastava06@gmail.com"> Arpit Srivastava</a>
 */
public abstract class DataServiceImpl<T extends BaseEntity, V extends BaseVo<V>> implements DataService<V> {

    private final JpaRepository<T, String> repository;
    private final BaseConvertor<T, V> convertor;

    public DataServiceImpl(JpaRepository<T, String> jpaRepository, BaseConvertor<T, V> baseConvertor) {
        this.repository = jpaRepository;
        this.convertor = baseConvertor;
    }

    @NonNull
    @Override
    public Flux<V> findAll() {
        List<T> listOfEntity = repository.findAll();
        List<V> listOfVo = new ArrayList<>();
        for(T t: listOfEntity) {
            listOfVo.add(convertor.convertEntityToVo(t));
        }
        return Flux.fromIterable(listOfVo);
    }

    @NonNull
    @Override
    public Mono<V> find(String uuid) {
        if(repository.findById(uuid).isPresent()) {
            return Mono.just(repository.findById(uuid).get())
                    .flatMap(t -> Mono.just(convertor.convertEntityToVo(t)));
        }
        throw new NotFoundException("Record not found in database");
    }

    @NonNull
    @Override
    public Mono<V> create(V vo) {
        return Mono.just(convertor.convertVoToEntity(vo))
                .flatMap(t -> Mono.just(repository.save(t)))
                .flatMap(e -> Mono.just(convertor.convertEntityToVo(e)));
    }

    @NonNull
    @Override
    public Mono<V> update(String uuid, V vo) {
        return Mono.just(repository.findById(uuid).get())
                .flatMap(t -> Mono.just(convertor.updateEntityFromVo(t, vo)))
                .flatMap(e -> Mono.just(repository.save(e)))
                .flatMap(x -> Mono.just(convertor.convertEntityToVo(x)));
    }

    @NonNull
    @Override
    public Mono<Boolean> delete(String uuid) {
        return Mono.fromSupplier(() -> {
            repository.deleteById(uuid);
            return true;
        });
    }
}
