package com.management.money.framework.service;

import com.management.money.framework.vo.BaseVo;
import lombok.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author <a href = "mailto: iarpitsrivastava06@gmail.com"> Arpit Srivastava</a>
 */
public interface DataService<V extends BaseVo<V>> {

    @NonNull
    Flux<V> findAll();

    @NonNull
    Mono<V> find(String uuid);

    @NonNull
    Mono<V> create(V vo);

    @NonNull
    Mono<V> update(String uuid, V vo);

    @NonNull
    Mono<Boolean> delete(String uuid);
}
