package liuxun.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import liuxun.ssm.exception.CustomException;
import liuxun.ssm.mapper.ItemsMapper;
import liuxun.ssm.mapper.ItemsMapperCustom;
import liuxun.ssm.po.Items;
import liuxun.ssm.po.ItemsCustom;
import liuxun.ssm.po.ItemsQueryVo;
import liuxun.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {

	// 注入mapper
	@Autowired
	private ItemsMapperCustom itemsMapperCustom;

	@Autowired
	private ItemsMapper itemsMapper;

	// 商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	public ItemsCustom findItemsById(int id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		//如果查询的商品信息为空，抛出系统自定义异常
		if (items == null) {
			throw new CustomException("修改商品信息不存在");
		}
		// 在这里随着需求的变量，需要查询商品的其他相关信息，返回到controller

		ItemsCustom itemsCustom = new ItemsCustom();
		// 将items的属性拷贝到itemsCustom
		BeanUtils.copyProperties(items, itemsCustom);

		return itemsCustom;
	}

	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		// 写业务代码
		
		// 对于关键业务数据的非空校验
		if (id == null) {
			// 抛出异常，提示调用接口的用户，id不能为空
			// ...
		}
		
		itemsMapper.updateByPrimaryKeySelective(itemsCustom);
	}

}
