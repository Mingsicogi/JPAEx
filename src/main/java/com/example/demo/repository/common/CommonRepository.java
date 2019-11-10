package com.example.demo.repository.common;


import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * jpa common method define
 *
 * @param <T>
 * @param <ID>
 *
 * @author minssogi
 */
@NoRepositoryBean
public interface CommonRepository<T, ID extends Serializable> extends Repository<T, ID> {

    <E extends T> E save(@NonNull E entity);

    List<T> findAll();

    long count();

    @Nullable
    <E extends T> Optional<E> findById(ID id); // null 처리를 위해 Optional 객체로 감싸서 사용.
}
