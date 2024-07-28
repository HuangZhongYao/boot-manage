package org.github.zuuuyao.web;

import cn.dev33.satoken.annotation.SaIgnore;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.web.BaseController;
import org.github.zuuuyao.common.response.annotations.ApiIgnoreWrapper;
import org.github.zuuuyao.service.example.IExampleService;
import org.github.zuuuyao.service.example.dto.ExampleValidateInputDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;
/**
 * 演示controller,演示Validate注解使用。接口编写方式、mybatis-plus查询
 *
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 17:05
 */
@AllArgsConstructor // @AllArgsConstructor 加了全参构造函数注解可以省掉 @Autowired、@Resource来注入所需要的bean
@Tag(name = "演示使用", description = "演示使用")
@RestController
@RequestMapping("/test")
public class ExampleController extends BaseController {

    IExampleService exampleService;

    @SaIgnore// 添加此注解让 satoken 框架忽略认证，可以添加到类上
    @Operation(summary = "演示validate注解", description = "测试validate注解的使用")//接口描述
    @ApiOperationSupport(authors = {"zuuuYao", ""})//添加接口作者选择性添加
    @PostMapping(value = "/exampleValidate", produces = MediaType.APPLICATION_JSON_VALUE)
    public String exampleValidateAnnotations(
            @RequestBody @Validated ExampleValidateInputDTO inputDTO) {
        return "参数全部验证通过";
    }

    @SaIgnore// 添加此注解让 satoken 框架忽略认证，可以添加到类上
    @Operation(summary = "演示RequestParam注解参数验证", description = "测试validate注解的使用")//接口描述
    @ApiOperationSupport(authors = {"zuuuYao", ""})//添加接口作者选择性添加
    @PostMapping(value = "/exampleRequestParam", produces = MediaType.APPLICATION_JSON_VALUE)
    public String exampleRequestParam(@RequestParam(name = "name") String name, @RequestParam(name = "code") String code) {
        return "参数全部验证通过";
    }

    @SaIgnore// 添加此注解让 satoken 框架忽略认证，可以添加到类上
    @Operation(summary = "演示不包装返回值", description = "演示不对api返回值包装")//接口描述
    @ApiIgnoreWrapper // 方法添加该注解该接口就会忽略包装，如果添加到controller上整个controller都不进行包装
    @PostMapping("/exampleNotWrapper")
    public String exampleNotWrapper() {
        // 不通过自定义包装 可以使用 ApiResponse 类自己写传统方式不过完全没必要多此一举
        return "这是没有包装的返回值,就是一个字符串";
    }

    @SaIgnore
    @ApiIgnoreWrapper
    @GetMapping("/createMemoryDirectoryAndZip")
    public ResponseEntity<byte[]> createMemoryDirectoryAndZip() throws IOException {

        StringWriter writer = new StringWriter();

        writer.append("""
                    // 创建内存中的多层级目录
        File memoryRootFolder = new File("memoryRoot");
        File subFolder1 = new File(memoryRootFolder, "subFolder1");
        File subFolder2 = new File(subFolder1, "subFolder2");

        if (!memoryRootFolder.exists()) {
            memoryRootFolder.mkdirs();
            subFolder1.mkdirs();
            subFolder2.mkdirs();
        }

        // 在最内层文件夹中创建文件
        File fileInSubFolder2 = new File(subFolder2, "file.txt");
        try (FileOutputStream fos = new FileOutputStream(fileInSubFolder2)) {
            fos.write("This is the content of the file.".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将内存中的目录和文件打包为 ZIP
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
            """);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        //添加到zip
        zip.putNextEntry(new ZipEntry("src/java/main/a.java"));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();

        StringWriter writer1 = new StringWriter();
        zip.putNextEntry(new ZipEntry("src/java/main/b.java"));
        IOUtils.write(writer1.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer1);
        zip.closeEntry();

        StringWriter writer2 = new StringWriter();
        zip.putNextEntry(new ZipEntry("src/java/main/org/github/zuuyao/entity/b.java"));
        IOUtils.write(writer2.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer2);
        zip.closeEntry();


        IOUtils.closeQuietly(zip);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "memoryDirectory.zip");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    private void addDirectoryToZip(File directory, ZipOutputStream zos) throws IOException {
        File[] files = directory.listFiles();
        if (files!= null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    addDirectoryToZip(file, zos);
                } else {
                    try (InputStream is = new FileInputStream(file)) {
                        ZipEntry zipEntry = new ZipEntry(file.getAbsolutePath().replace(directory.getAbsolutePath() + File.separator, ""));
                        zos.putNextEntry(zipEntry);
                        IOUtils.copy(is, zos);
                        zos.closeEntry();
                    }
                }
            }
        }
    }
}
