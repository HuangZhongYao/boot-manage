package org.github.zuuuyao.service.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.common.validate.ValidateChinese;
import org.github.zuuuyao.common.validate.ValidateDate;
import org.github.zuuuyao.common.validate.ValidateEmail;
import org.github.zuuuyao.common.validate.ValidateIP;
import org.github.zuuuyao.common.validate.ValidateIdCard;
import org.github.zuuuyao.common.validate.ValidateMAC;
import org.github.zuuuyao.common.validate.ValidateNotChinese;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;
import org.github.zuuuyao.common.validate.ValidateNumber;
import org.github.zuuuyao.common.validate.ValidateNumberFloat;
import org.github.zuuuyao.common.validate.ValidateNumberInteger;
import org.github.zuuuyao.common.validate.ValidatePhone;
import org.github.zuuuyao.common.validate.ValidatePlateNum;
import org.github.zuuuyao.common.validate.ValidatePostalCode;
import org.github.zuuuyao.common.validate.ValidateQQ;
import org.github.zuuuyao.common.validate.ValidateTEL;
import org.github.zuuuyao.common.validate.ValidateURL;
import org.github.zuuuyao.common.validate.ValidateUnifiedSocialCreditIdentifier;
import org.github.zuuuyao.common.validate.ValidateWord;

/**
 * 演示Validate注解使用的DTO
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 16:08
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExampleValidateInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = -7539590436814191245L;

    /**
     * 昵称
     */
    @ValidateNotNullAndEmpty(message = "昵称不能为空")
    @Schema(description = "昵称")
    private String nickName;


    /**
     * 真实姓名
     */
    @ValidateChinese(message = "真实姓名只能输入中文")
    @Schema(description = "真实姓名")
    private String realName;

    /**
     * 账号
     */
    @ValidateNotChinese(message = "账号不能输入中文")
    @Schema(description = "账号")
    private String account;

    /**
     * 出身日期
     */
    @ValidateDate(message = "出身日期只能输入yyyy-MM-dd日期格式", format = "yyyy-MM-dd")
    @Schema(description = "出身日期")
    private String birthDate;

    /**
     * 邮箱
     */
    @ValidateEmail(message = "邮箱格式错误")
    @Schema(description = "邮箱")
    private String email;

    /**
     * 身份证号
     */
    @ValidateIdCard(message = "身份证号格式错误")
    @Schema(description = "身份证号")
    private String idCard;

    /**
     * host主机
     */
    @ValidateIP(message = "主机ip地址格式错误")
    @Schema(description = "host主机")
    private String host;

    /**
     * 主机mac地址
     */
    @ValidateMAC(message = "主机mac地址格式错误")
    @Schema(description = "主机mac地址")
    private String mac;

    /**
     * 价格
     */
    @ValidateNumber(message = "价格只能输入数字")
    @Schema(description = "价格")
    private String price;

    /**
     * 长度
     */
    @ValidateNumberFloat(message = "长度只能输入小数")
    @Schema(description = "长度,单位cm")
    private String length;

    /**
     * 年龄
     */
    @ValidateNumberInteger(message = "年龄只能输入整数")
    @Schema(description = "年龄")
    private String age;

    /**
     * 手机号
     */
    @ValidatePhone(message = "手机号码输入不正确")
    @Schema(description = "手机号")
    private String phone;

    /**
     * 车牌号
     */
    @ValidatePlateNum(message = "车牌号格式错误")
    @Schema(description = "车牌号")
    private String licensePlateNumber;

    /**
     * 收货地址邮政编码
     */
    @ValidatePostalCode(message = "收货地址邮政编码不正确")
    @Schema(description = "收货地址邮政编码")
    private String harvestAddressZipCode;

    /**
     * qq号码
     */
    @ValidateQQ(message = "qq号码不正确")
    @Schema(description = "qq号码")
    private String qq;

    /**
     * 公司电话
     */
    @ValidateTEL(message = "公司电话号码不正确")
    @Schema(description = "公司电话")
    private String tel;

    /**
     * 公司统一社会信用代码
     */
    @ValidateUnifiedSocialCreditIdentifier(message = "公司统一社会信用代码不正确")
    @Schema(description = "公司统一社会信用代码")
    private String unifiedSocialCreditIdentifier;

    /**
     * 官网地址
     */
    @ValidateURL(message = "官网地址不正确")
    @Schema(description = "官网地址")
    private String officialWebsite;

    /**
     * 英文名
     */
    @ValidateWord(message = "英文名只能输入字母")
    @Schema(description = "英文名")
    private String englishName;
}
