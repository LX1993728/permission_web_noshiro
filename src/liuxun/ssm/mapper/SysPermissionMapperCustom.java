package liuxun.ssm.mapper;

import java.util.List;
import liuxun.ssm.po.SysPermission;
import liuxun.ssm.po.SysPermissionExample;
import org.apache.ibatis.annotations.Param;
/**
 * 权限mapper
 * @author liuxun
 *
 */
public interface SysPermissionMapperCustom {
    //根据用户id查询菜单
	public List<SysPermission> findMenuListByUserId(String userid) throws Exception;
	//根据用户id查询权限URL
	public List<SysPermission> findPermissionListByUserId(String userid) throws Exception;
}