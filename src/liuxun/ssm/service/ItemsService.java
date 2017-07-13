package liuxun.ssm.service;

import java.util.List;

import liuxun.ssm.po.ItemsCustom;
import liuxun.ssm.po.ItemsQueryVo;

/**
 * 商品Service接口
 * 
 * @author liuxun
 *
 */
public interface ItemsService {
	// 商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

	// 根据商品id查询商品信息
	public ItemsCustom findItemsById(int id) throws Exception;
	
	// 更新商品信息
	/**
	 * 定义Service接口，遵循单一职责，将业务参数细化（不要使用包装类型，比如Map）
	 * @param id  修改商品的id
	 * @param itemsCustom  修改商品的信息
	 * @throws Exception
	 */
	public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;

}
