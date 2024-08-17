package org.github.zuuuyao.service.dict.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.List;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.entity.dict.DictionaryDataEntity;
import org.github.zuuuyao.repository.DictionaryDataRepository;
import org.github.zuuuyao.service.dict.IDictService;
import org.github.zuuuyao.service.dict.dto.inpnt.AddDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.EditDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.SetStateDictInputDTO;
import org.github.zuuuyao.service.dict.output.DictDataVO;
import org.springframework.stereotype.Service;

/**
 * @Desc Created by IntelliJ IDEA.
 * @Author ZhongYao.Huang (https://github.com/HuangZhongYao)
 * @Copyright ZuuuuYao By Github
 * @Time 2024-08-18 4:07
 */
@Service
@AllArgsConstructor
public class DictServiceImpl implements IDictService {

    DictionaryDataRepository dictionaryDataRepository;

    @Override
    public Boolean addDict(AddDictInputDTO inputDTO) {
        DictionaryDataEntity dictionaryDataEntity =
            ModelMapperUtil.map(inputDTO, DictionaryDataEntity.class);
        this.dictionaryDataRepository.insert(dictionaryDataEntity);
        return true;
    }

    @Override
    public Boolean delDict(BaseManyLongIdInputDTO inputDTO) {
        // 根据id删除字典
        this.dictionaryDataRepository.deleteByIds(inputDTO.getIds());
        return true;
    }

    @Override
    public Boolean setStateDict(SetStateDictInputDTO inputDTO) {

        // 更新的数据
        DictionaryDataEntity dictionaryDataEntity = new DictionaryDataEntity();
        dictionaryDataEntity.setId(inputDTO.getId());
        dictionaryDataEntity.setEnable(inputDTO.getState());

        // 执行更新
        this.dictionaryDataRepository.updateById(dictionaryDataEntity);
        return true;
    }

    @Override
    public Boolean editDict(EditDictInputDTO inputDTO) {

        // 更新的数据
        DictionaryDataEntity updateEntity =
            ModelMapperUtil.map(inputDTO, DictionaryDataEntity.class);

        // 执行更新
        this.dictionaryDataRepository.updateById(updateEntity);

        return true;
    }

    @Override
    public List<DictDataVO> queryList(Long dictTypeId) {
        return this.dictionaryDataRepository.selectList(
            Wrappers.<DictionaryDataEntity>lambdaQuery()
                .eq(DictionaryDataEntity::getDictTypeId, dictTypeId)
                .orderByAsc(DictionaryDataEntity::getSort), DictDataVO.class);
    }
}
