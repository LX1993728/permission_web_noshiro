package liuxun.ssm.mapper;

import java.util.List;

import liuxun.ssm.po.ItemsCustom;
import liuxun.ssm.po.ItemsQueryVo;

/**
 * 商品自定义Mapper
 * @author liuxun
 *
 */

public interface ItemsMapperCustom {
  // 商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
