package com.management.money.framework.convertor;

import com.management.money.framework.entity.BaseEntity;
import com.management.money.framework.vo.BaseVo;

/**
 * @author <a href = "mailto: iarpitsrivastava06@gmail.com"> Arpit Srivastava</a>
 */
public interface BaseConvertor<T extends BaseEntity, V extends BaseVo> {

    T convertVoToEntity(V v);

    V convertEntityToVo(T t);

    T updateEntityFromVo(T t, V v);

}
