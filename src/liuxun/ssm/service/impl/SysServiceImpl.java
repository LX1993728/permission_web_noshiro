package liuxun.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import liuxun.ssm.exception.CustomException;
import liuxun.ssm.mapper.SysPermissionMapperCustom;
import liuxun.ssm.mapper.SysUserMapper;
import liuxun.ssm.po.ActiveUser;
import liuxun.ssm.po.SysPermission;
import liuxun.ssm.po.SysUser;
import liuxun.ssm.po.SysUserExample;
import liuxun.ssm.service.SysService;
import liuxun.ssm.util.MD5;

public class SysServiceImpl implements SysService {
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysPermissionMapperCustom sysPermissionMapperCustom;

	public ActiveUser authenticat(String usercode, String password) throws Exception {

		/**
		 * 认证过程： 根据用户身份(账号)查询数据库，如果查询不到则用户不存在 
		 * 对输入的密码和数据库密码进行比对，如果一致则认证通过
		 */
		// 根据用户账号查询数据库
		SysUser sysUser = this.findSysUserByUserCode(usercode);

		if (sysUser == null) {
			// 抛出异常
			throw new CustomException("用户账号不存在");
		}

		// 数据库密码（MD5加密后的密码）
		String password_db = sysUser.getPassword();
        
		// 对输入的密码和数据库密码进行比对，如果一致，认证通过
		// 对页面输入的密码进行MD5加密
		String password_input_md5 = new MD5().getMD5ofStr(password);
		if (!password_db.equalsIgnoreCase(password_input_md5)) {
			//抛出异常
			throw new CustomException("用户名或密码错误");
		}
		//得到用户id
		String userid = sysUser.getId();
		//根据用户id查询菜单
		List<SysPermission> menus = this.findMenuListByUserId(userid);
		//根据用户id查询权限url
		List<SysPermission> permissions = this.findPermissionListByUserId(userid);
		
		//认证通过，返回用户身份信息
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(userid);
		activeUser.setUsercode(usercode);
		activeUser.setUsername(sysUser.getUsername());
		
        //放入权限范围的菜单和url
		activeUser.setMenus(menus);
		activeUser.setPermissions(permissions);
		
		return activeUser;
	}

	public SysUser findSysUserByUserCode(String userCode) throws Exception {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andUsercodeEqualTo(userCode);

		List<SysUser> list = sysUserMapper.selectByExample(sysUserExample);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	@Override
	public List<SysPermission> findMenuListByUserId(String userid) throws Exception {
		return sysPermissionMapperCustom.findMenuListByUserId(userid);
	}

	@Override
	public List<SysPermission> findPermissionListByUserId(String userid) throws Exception {
		return sysPermissionMapperCustom.findPermissionListByUserId(userid);
	}


}
