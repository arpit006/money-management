package com.management.money.framework.vo;

/**
 * @author <a href = "mailto: iarpitsrivastava06@gmail.com"> Arpit Srivastava</a>
 */
public interface BaseVo<V> {

    V withUuid(String uuid);

    String getUuid();
}
