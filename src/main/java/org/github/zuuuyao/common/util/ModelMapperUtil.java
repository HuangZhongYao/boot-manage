package org.github.zuuuyao.common.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 对象映射转换工具类,转换逻辑属性名完全匹配进行转换
 *
 * @Description:
 * @Time: 2019-11-28 20:18
 * @Author: HuangZhangYao
 */
public final class ModelMapperUtil {

    public static final ModelMapper MODEL_MAPPER;

    static {

        MODEL_MAPPER = new ModelMapper();
        // 完全类型匹配
        //MODEL_MAPPER.getConfiguration().setFullTypeMatchingRequired(true);
        // 设置属性匹配规则,设置为最严格匹配,必须属性名相同
        MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * 禁止实列化该类
     * @throws InstantiationException 不能实列化
     */
    public ModelMapperUtil() throws InstantiationException {
        throw new InstantiationException("Tool Class Cannot Be Created Instantiation !");
    }

    /**
     * 将对象转换为指定class类型对象。
     * @param source 源对象
     * @param targetClass 转换目标类型
     * @return 转换后对象
     * @param <Target> 目标类型
     */
    public static <Target> Target map(Object source, Class<Target> targetClass) {
        return source == null ? null : MODEL_MAPPER.map(source, targetClass);
    }


    /**
     * 将源对象中的属性转换到目标对象中,源对象与目标对象属性名相同的则转换
     * @param source 源对象
     * @param target 目标对象
     */
    public static void map(Object source, Object target) {
        if (source == null || target == null) {
            return;
        } else {
            MODEL_MAPPER.map(source, target);
        }
    }

    /**
     * 将对象转换为指定class类型对象,并对转换后对象进行处理
     * @param source 源对象
     * @param targetClass 转换目标类型
     * @param consumer 对转换后对象进行消费处理
     * @return 转换后对象
     * @param <Target> 目标类型
     */
    public static <Target> Target map(Object source, Class<Target> targetClass, Consumer<Target> consumer) {
        Target target = source == null ? null : MODEL_MAPPER.map(source, targetClass);
        if (consumer != null) {
            consumer.accept(target);
        }
        return target;
    }

    /**
     * 将对象转换为指定class类型对象,并对转换后对象进行处理
     * @param source 源对象
     * @param targetClass 转换目标类型
     * @param consumer 对转换后对象进行消费处理
     * @return 转换后对象
     * @param <Source> 源对象类型
     * @param <Target> 目标类型
     */
    public static <Source, Target> Target map(Source source, Class<Target> targetClass, BiConsumer<Source, Target> consumer) {
        Target target = source == null ? null : MODEL_MAPPER.map(source, targetClass);
        if (consumer != null) {
            consumer.accept(source, target);
        }
        return target;
    }

    /**
     * 将集合中的元素抓换为指定类型
     * @param sourceList 源集合
     * @param targetClass 转换目标类型
     * @return 转换后的目标类型集合
     * @param <TSource> 源类型
     * @param <Target> 目标类型
     */
    public static <TSource, Target> List<Target> mapList(Collection<TSource> sourceList, Class<Target> targetClass) {

        if (sourceList == null) {
            return new ArrayList<Target>();
        }

        ArrayList<Target> targets = new ArrayList<>(sourceList.size());

        sourceList.forEach(p -> {
            Target item = map(p, targetClass);
            targets.add(item);
        });

        return targets;
    }

    /**
     * 将集合中的元素抓换为指定类型,并对转换后的元素处理
     * @param sourceList 源集合
     * @param targetClass 转换目标类型
     * @param consumer 对转换后元素处理
     * @return 转换后的集合
     * @param <TSource> 源类型
     * @param <Target> 目标类型
     */
    public static <TSource, Target> List<Target> mapList(Collection<TSource> sourceList, Class<Target> targetClass, Consumer<Target> consumer) {

        if (sourceList == null) {
            return new ArrayList<Target>();
        }

        ArrayList<Target> targets = new ArrayList<>(sourceList.size());

        sourceList.forEach(p -> {
            Target item = map(p, targetClass);

            if (consumer != null) {
                consumer.accept(item);
            }
            targets.add(item);
        });

        return targets;
    }

    /**
     * 将集合中的元素抓换为指定类型,并对转换后的元素处理
     * @param sourceList 源集合
     * @param targetClass 转换目标类型
     * @param consumer 对转换后元素处理
     * @return 转换后的集合
     * @param <TSource> 源类型
     * @param <Target> 目标类型
     */
    public static <TSource, Target> List<Target> mapList(Collection<TSource> sourceList, Class<Target> targetClass, BiConsumer<TSource, Target> consumer) {

        if (sourceList == null) {
            return new ArrayList<Target>();
        }

        ArrayList<Target> targets = new ArrayList<>(sourceList.size());

        sourceList.forEach(p -> {
            Target map = ModelMapperUtil.map(p, targetClass);
            if (consumer != null) {
                consumer.accept(p, map);
            }
            targets.add(map);
        });
        return targets;
    }

}
