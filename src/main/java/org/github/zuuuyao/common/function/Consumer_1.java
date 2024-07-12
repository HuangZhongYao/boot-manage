package org.github.zuuuyao.common.function;

/**
 * @Desc
 * @Time 2024-07-11 16:30
 * @Author HuangZhongYao
 */
@FunctionalInterface
public interface Consumer_1<S, T,E> {
    void accept(S s, T t,E e);
}
