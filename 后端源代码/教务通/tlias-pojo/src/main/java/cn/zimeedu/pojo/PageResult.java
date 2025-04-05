package cn.zimeedu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装类  分页查询
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<E> {
    private Long total; //总记录数
    private List<E> rows; //当前页数据列表
}



//在 Java 中，泛型类型参数通常使用单个大写字母来表示，以下是一些常用的字符及其含义：
//T：代表 Type，表示类型，是最常用的泛型类型参数名称，通常用于表示一个通用的类型。
//E：代表 Element，通常用于集合类中，表示集合中的元素类型，例如 List<E>。
//K：代表 Key，通常用于表示键值对中的键的类型，例如 Map<K, V> 中的键。
//V：代表 Value，通常用于表示键值对中的值的类型，例如 Map<K, V> 中的值。
//N：代表 Number，通常用于表示数值类型，例如 List<N> 可以表示一个存储数值的列表。