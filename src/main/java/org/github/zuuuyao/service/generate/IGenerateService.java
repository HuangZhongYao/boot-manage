package org.github.zuuuyao.service.generate;

import jakarta.servlet.http.HttpServletResponse;
import org.github.zuuuyao.service.generate.dto.input.CodeGenerateInputDTO;
import org.github.zuuuyao.service.generate.dto.output.ColumnVO;
import org.github.zuuuyao.service.generate.dto.output.TableVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @Desc
 * @Time 2024-07-29 10:01
 * @Author HuangZhongYao
 */
public interface IGenerateService {
    List<TableVO> getTables();

    List<ColumnVO> getTableColumns(String tableName);

    ResponseEntity<byte[]> codeGeneration(CodeGenerateInputDTO inputDTO, HttpServletResponse response);
}
