package org.github.zuuuyao.service.dict;

import java.util.List;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.AddDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.AddDictTypeInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.EditDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.EditDictTypeInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.SetStateDictInputDTO;
import org.github.zuuuyao.service.dict.output.DictDataVO;
import org.github.zuuuyao.service.dict.output.DictTypeTreeVO;

/**
 * @Desc Created by IntelliJ IDEA.
 * @Author ZhongYao.Huang (https://github.com/HuangZhongYao)
 * @Copyright ZuuuuYao By Github
 * @Time 2024-08-18 4:07
 */
public interface IDictService {
    Boolean addDict(AddDictInputDTO inputDTO);

    Boolean delDict(BaseManyLongIdInputDTO inputDTO);

    Boolean setStateDictData(SetStateDictInputDTO inputDTO);

    Boolean editDict(EditDictInputDTO inputDTO);

    List<DictDataVO> dictDataQueryList(Long dictTypeId);

    Boolean addDictType(AddDictTypeInputDTO inputDTO);

    Boolean delDictType(BaseManyLongIdInputDTO inputDTO);

    Boolean editDictType(EditDictTypeInputDTO inputDTO);

    List<DictTypeTreeVO> dictTypeTree();

    Boolean setStateDictType(SetStateDictInputDTO inputDTO);
}
