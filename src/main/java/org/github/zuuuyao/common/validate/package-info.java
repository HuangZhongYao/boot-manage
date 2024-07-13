/**
 *
 * validation 扩展包,自带的验证不满足时进行扩展.
 * validation验证不通过时抛出 MethodArgumentNotValidException,再定义一个全局异常处理就可以了
 * Java 中的 validation 可以通过使用相关的注解和验证框架来实现。常见的验证注解如下：
 * @NotNull：用于确保注解的元素值不是null。任意类型都可使用。
 * @Null：表示注解的元素值必须是null。也适用于任意类型。
 * @AssertTrue：要求注解的元素值（布尔或Boolean类型）是true。
 * @AssertFalse：要求注解的元素值是false。
 * @Min(value=值)：适用于BigDecimal、BigInteger、byte、short、int、long等任何Number或CharSequence（存储的是数字）子类型。确保注解的元素值大于等于指定的value值。
 * @Max（value=值）：类似于@Min，但要求元素值小于等于指定的value值。
 * @DecimalMin(value=值)：与@Min要求相同，用于验证元素值大于等于指定的value值。
 * @DecimalMax(value=值)：与@Min要求一样，确保元素值小于等于指定的value值。
 * @Digits(integer=整数位数, fraction=小数位数)：用于验证元素值的整数位数和小数位数上限。适用于和@Min相同的类型。
 * @Size(min=下限, max=上限)：可用于字符串、Collection、Map、数组等。确保注解的元素值的大小在min和max（包含）指定区间之内，例如字符长度、集合大小等。
 * @Past：用于java.util.Date、java.util.Calendar以及 Joda Time 类库的日期类型，验证注解的元素值（日期类型）比当前时间早。
 * @Future：与@Past要求类似，验证注解的元素值（日期类型）比当前时间晚。
 * @NotBlank：应用于CharSequence子类型，要求注解的元素值不为空（不为null、去除首位空格后长度不为 0）。与@NotEmpty不同，@NotBlank只应用于字符串且在比较时会去除字符串的首位空格。
 * @Length(min=下限, max=上限)：适用于CharSequence子类型，验证注解的元素值长度在指定的min和max区间内。
 * @NotEmpty：可用于CharSequence子类型、Collection、Map、数组等，验证注解的元素值不为null且不为空（字符串长度不为 0、集合大小不为 0）。
 * @Range(min=最小值, max=最大值)：适用于BigDecimal、BigInteger、CharSequence、byte、short、int、long等原子类型和包装类型，验证注解的元素值在最小值和最大值之间。
 * @Email(regexp=正则表达式, flag=标志的模式)：用于CharSequence子类型（如String），验证注解的元素值是Email格式，也可以通过regexp和flag指定自定义的email格式。
 * @Pattern(regexp=正则表达式, flag=标志的模式)：适用于String及任何CharSequence的子类型，验证注解的元素值与指定的正则表达式匹配。
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 15:38
 */

package org.github.zuuuyao.common.validate;