package org.github.zuuuyao.service.dict.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.common.util.tree.ITreeNode;
import org.github.zuuuyao.common.util.tree.TreeUtil;
import org.github.zuuuyao.entity.dict.DictDataEntity;
import org.github.zuuuyao.entity.dict.DictTypeEntity;
import org.github.zuuuyao.repository.DictDataRepository;
import org.github.zuuuyao.repository.DictTypeRepository;
import org.github.zuuuyao.service.dict.IDictService;
import org.github.zuuuyao.service.dict.dto.inpnt.AddDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.AddDictTypeInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.EditDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.EditDictTypeInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.SetStateDictInputDTO;
import org.github.zuuuyao.service.dict.output.DictDataVO;
import org.github.zuuuyao.service.dict.output.DictTypeTreeVO;
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

    DictDataRepository dictDataRepository;
    DictTypeRepository dictTypeRepository;

    @Override
    public Boolean addDictData(AddDictInputDTO inputDTO) {
        DictDataEntity dictionaryDataEntity = ModelMapperUtil.map(inputDTO, DictDataEntity.class);

        // 检查字典数据是否重复
        checkDictDataExistence(dictionaryDataEntity);

        this.dictDataRepository.insert(dictionaryDataEntity);
        return true;
    }

    @Override
    public Boolean delDictData(BaseManyLongIdInputDTO inputDTO) {
        // 根据id删除字典
        this.dictDataRepository.deleteByIds(inputDTO.getIds());
        return true;
    }

    @Override
    public Boolean setStateDictData(SetStateDictInputDTO inputDTO) {

        // 更新的数据
        DictDataEntity dictionaryDataEntity = new DictDataEntity();
        dictionaryDataEntity.setId(inputDTO.getId());
        dictionaryDataEntity.setEnable(inputDTO.getState());

        // 执行更新
        this.dictDataRepository.updateById(dictionaryDataEntity);
        return true;
    }

    @Override
    public Boolean editDictData(EditDictInputDTO inputDTO) {

        // 更新的数据
        DictDataEntity updateEntity =
            ModelMapperUtil.map(inputDTO, DictDataEntity.class);

        // 检查字典数据是否重复
        checkDictDataExistence(updateEntity);

        // 执行更新
        this.dictDataRepository.updateById(updateEntity);

        return true;
    }

    @Override
    public List<DictDataVO> dictDataQueryList(Long dictTypeId) {
        return this.dictDataRepository.selectList(
            Wrappers.<DictDataEntity>lambdaQuery()
                .eq(DictDataEntity::getDictTypeId, dictTypeId)
                .orderByAsc(DictDataEntity::getSort), DictDataVO.class);
    }

    /**
     * 检查字典数据是否已存在
     *
     * @param dictData 字典数据
     */
    private void checkDictDataExistence(DictDataEntity dictData) {

        // 查询条件
        LambdaQueryWrapper<DictDataEntity> queryWrapper = Wrappers.<DictDataEntity>lambdaQuery()
            .eq(DictDataEntity::getDictTypeId, dictData.getDictTypeId());

        // 检查code是否重复
        queryWrapper.eq(DictDataEntity::getCode, dictData.getCode());
        if (this.dictDataRepository.selectCount(queryWrapper) > 0) {
            throw new UserFriendlyException("该字典编码已存在", 450);
        }

        // 检查名称是否重复
        queryWrapper.eq(DictDataEntity::getName, dictData.getName());
        if (this.dictDataRepository.selectCount(queryWrapper) > 0) {
            throw new UserFriendlyException("该字典名称已存在", 451);
        }
    }

    @Override
    public Boolean addDictType(AddDictTypeInputDTO inputDTO) {

        DictTypeEntity dictTypeEntity = ModelMapperUtil.map(inputDTO, DictTypeEntity.class);

        // 检查字典类型是否重复
        checkDictTypeExistence(dictTypeEntity);

        // 执行插入字典类型
        this.dictTypeRepository.insert(dictTypeEntity);
        return true;
    }

    @Override
    public Boolean delDictType(BaseManyLongIdInputDTO inputDTO) {
        // 根据id删除字典类型
        this.dictTypeRepository.deleteByIds(inputDTO.getIds());
        return true;
    }

    @Override
    public Boolean editDictType(EditDictTypeInputDTO inputDTO) {
        // 数据库中的数据
        DictTypeEntity dictTypeEntityDB = dictTypeRepository.selectById(inputDTO.getId());
        // 更新的数据
        DictTypeEntity updateEntity =
            ModelMapperUtil.map(inputDTO, DictTypeEntity.class);

        // 如果修改了类型名称
        if (!StrUtil.equals(dictTypeEntityDB.getName(), inputDTO.getName())) {
            // 检查字典类型是否重复
            this.checkDictTypeExistence(updateEntity);
        }

        // 执行更新
        this.dictTypeRepository.updateById(updateEntity);
        return true;
    }

    @Override
    public List<DictTypeTreeVO> dictTypeTree() {
        // 字典类型集合
        List<DictTypeTreeVO> dictTypeTreeVOS = this.dictTypeRepository.selectList(
            Wrappers.<DictTypeEntity>lambdaQuery()
                .orderByAsc(DictTypeEntity::getParentId, DictTypeEntity::getSort),
            DictTypeTreeVO.class);
        // 转换ITreeNode List
        List<ITreeNode<Long>> treeNodeList = new ArrayList<>(dictTypeTreeVOS.size());
        treeNodeList.addAll(dictTypeTreeVOS);
        // 转换树结构
        List<ITreeNode<Long>> tree = TreeUtil.listToTree(treeNodeList);
        // 转换DictTypeTreeVO List
        return ModelMapperUtil.mapList(tree, DictTypeTreeVO.class);
    }

    @Override
    public Boolean setStateDictType(SetStateDictInputDTO inputDTO) {
        // 更新的数据
        DictTypeEntity dictTypeEntity = new DictTypeEntity();
        dictTypeEntity.setId(inputDTO.getId());
        dictTypeEntity.setEnable(inputDTO.getState());

        // 执行更新
        this.dictTypeRepository.updateById(dictTypeEntity);
        return true;
    }

    /**
     * 检查字典类型是否已存在
     *
     * @param dictType 字典类型
     * @return true存在
     */
    private void checkDictTypeExistence(DictTypeEntity dictType) {
        // 查询条件
        LambdaQueryWrapper<DictTypeEntity> queryWrapper = Wrappers.<DictTypeEntity>lambdaQuery()
            .eq(dictType.getParentId() != null, DictTypeEntity::getParentId, dictType.getParentId())
            .isNull(dictType.getParentId() == null, DictTypeEntity::getParentId);

        // 检查名称是否重复
        queryWrapper.eq(DictTypeEntity::getName, dictType.getName());
        queryWrapper.eq(DictTypeEntity::getParentId, dictType.getParentId());
        if (this.dictTypeRepository.selectCount(queryWrapper) > 0) {
            throw new UserFriendlyException("该字典类型已存在", 450);
        }
    }
}
