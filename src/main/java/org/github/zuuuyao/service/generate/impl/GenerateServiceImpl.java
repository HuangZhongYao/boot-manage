package org.github.zuuuyao.service.generate.impl;

import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.entity.generate.TableEntity;
import org.github.zuuuyao.repository.GenerateRepository;
import org.github.zuuuyao.service.generate.IGenerateService;
import org.github.zuuuyao.service.generate.dto.TableVO;
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
        List<TableEntity> tables = generateRepository.getTables();
        return ModelMapperUtil.mapList(tables, TableVO.class);
    }
}
