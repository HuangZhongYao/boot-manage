package org.github.zuuuyao.service.generate.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.repository.GenerateRepository;
import org.github.zuuuyao.service.generate.IGenerateService;
import org.github.zuuuyao.service.generate.dto.input.CodeGenerateInputDTO;
import org.github.zuuuyao.service.generate.dto.output.ColumnVO;
import org.github.zuuuyao.service.generate.dto.output.TableVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 代码生成服务类
 *
 * @Desc
 * @Time 2024-07-29 10:01
 * @Author HuangZhongYao
 */
@Service
@AllArgsConstructor
public class GenerateServiceImpl implements IGenerateService {

    GenerateRepository generateRepository;

    @Override
    public List<TableVO> getTables() {
        return ModelMapperUtil.mapList(generateRepository.getTables(), TableVO.class);
    }

    @Override
    public List<ColumnVO> getTableColumns(String tableName) {
        return ModelMapperUtil.mapList(generateRepository.getTableColumns(tableName), ColumnVO.class);
    }

    @Override
    public void codeGeneration(CodeGenerateInputDTO inputDTO, HttpServletResponse response) {

    }
}
