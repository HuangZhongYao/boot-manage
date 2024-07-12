package org.github.zuuuyao.common.function;

/**
 * @Desc
 * @Time 2024-07-11 16:30
 * @Author HuangZhongYao
 */
@FunctionalInterface
public interface Function_1<T,U,E,R> {
    R apply(T t, U u,E e);
}
