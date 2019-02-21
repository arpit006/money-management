package com.management.money.framework.controller;

import com.management.money.framework.service.DataService;
import com.management.money.framework.vo.BaseVo;
import lombok.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author <a href = "mailto: iarpitsrivastava06@gmail.com"> Arpit Srivastava</a>
 */
public abstract class BaseController<V extends BaseVo<V>> {


    private final DataService dataService;

    public BaseController(DataService dataService) {
        this.dataService = dataService;
    }

    @NonNull
    @GetMapping("")
    public Flux<V> findAll() {
        return dataService.findAll();
    }

    @NonNull
    @GetMapping("{uuid}")
    public Mono<V> findByUuid(@PathVariable String uuid) {
        return dataService.find(uuid);
    }

    @NonNull
    @PostMapping("")
    public Mono<V> create(@RequestBody V vo) {
        return dataService.create(vo);
    }

    @NonNull
    @PutMapping("{uuid}")
    public Mono<V> update(@PathVariable String uuid, @RequestBody V vo) {
        return dataService.update(uuid, vo.withUuid(uuid));
    }

    @NonNull
    @DeleteMapping("{uuid}")
    public Mono<V> delete(@PathVariable String uuid) {
        return dataService.delete(uuid);
    }
}
