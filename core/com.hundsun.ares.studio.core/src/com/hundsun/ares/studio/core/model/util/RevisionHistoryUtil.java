package com.hundsun.ares.studio.core.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;

/**
 * 修订记录相关的功能工具类
 * @author sundl
 *
 */
public class RevisionHistoryUtil {

	private static final Logger logger = Logger.getLogger(RevisionHistory.class);
	
	/**
	 * 项目属性中的版本
	 * @param project
	 * @return
	 */
	public static String getProjectPropertyVersion(IARESProject project) {
		IARESProjectProperty property = null;
		try {
			property = project.getProjectProperty();
		} catch (ARESModelException e) {
			e.printStackTrace();
			logger.error("读取项目属性失败", e);
		}
		
		if (property != null)
			return property.getVersion();
		return null;
	}
	
	/**
	 * 获取模块和下面所有资源的修改记录列表, 排序按照
	 * @param module
	 * @return
	 */
	public static List<RevisionHistory> getHistories(IARESModule module) {
		List<RevisionHistory> result = new ArrayList<RevisionHistory>();
		IARESResource[] resources = module.getARESResources(true);
		for (IARESResource res : resources) {
			try {
				JRESResourceInfo info = res.getInfo(JRESResourceInfo.class);
				if (info == null) {
					ModuleProperty mp = res.getInfo(ModuleProperty.class);
					if (mp != null) {
						info = (JRESResourceInfo) mp.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
					}
				}
				if (info != null) {
					List<RevisionHistory> histories = info.getHistories();
					for (RevisionHistory his : histories) {
						if (!contains(result, his))
							result.add(his);
					}
				}
				
			} catch (ARESModelException e) {
			}
		}
		
		Collections.sort(result, new Comparator<RevisionHistory>() {
			@Override
			public int compare(RevisionHistory o1, RevisionHistory o2) {
				return RevisionHistoryUtil.compare(o1.getVersion(), o2.getVersion());
			}
		});
		
		Collections.reverse(result);
		
		return result;
	}
	
	private static boolean contains(List<RevisionHistory> histories, RevisionHistory his) {
		for (RevisionHistory history : histories) {
			if (EcoreUtil.equals(his, history)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取模块下最大版本号,如果最大版本号比基线版本小,则返回null
	 * 
	 * @param module
	 * @return
	 */
	public static RevisionHistory getMaxVersionHisInfo(IARESModule module ){
		if (module == null)
			return null;
		
		IARESResource[] resources = module.getARESResources(true);
		List<RevisionHistory> allRevList = new ArrayList<RevisionHistory>();
		
		for (IARESResource res : resources) {
			try {
				JRESResourceInfo info = res.getInfo(JRESResourceInfo.class);
				if (info == null) {
					if (res.getName().equals(IARESModule.MODULE_PROPERTY_FILE)) {
						ModuleProperty mp = res.getInfo(ModuleProperty.class);
						Object obj = mp.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
						if(obj instanceof JRESResourceInfo){
						info = (JRESResourceInfo)obj ;
						}
					}
				}
				
				// 遍历取最大版本
				if (info != null) {
					List<RevisionHistory> hisList = info.getHistories();
					allRevList.addAll(hisList);
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(module.getARESProject());
		if (StringUtils.isBlank(projectVersion)) {
			projectVersion = "1.0.0.1";
		}
		return getMaxVersionHisInfo(allRevList , projectVersion);
	}
	
	/**
	 * 获取模块下最大版本号,如果最大版本号比基线版本小,则返回null
	 * 
	 * @param module
	 * @return
	 */
	public static RevisionHistory getMaxVersionHisInfo(IARESModule module ,String resType){
		if (module == null)
			return null;
		
		IARESResource[] resources = module.getARESResources(true);
		List<RevisionHistory> allRevList = new ArrayList<RevisionHistory>();
		
		for (IARESResource res : resources) {
			if (StringUtils.equals(resType, res.getType())) {
				try {
					JRESResourceInfo info = res.getInfo(JRESResourceInfo.class);
					if (info == null) {
						if (res.getName().equals(IARESModule.MODULE_PROPERTY_FILE)) {
							ModuleProperty mp = res.getInfo(ModuleProperty.class);
							Object obj = mp.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
							if(obj instanceof JRESResourceInfo){
								info = (JRESResourceInfo)obj ;
							}
						}
					}
					
					// 遍历取最大版本
					if (info != null) {
						List<RevisionHistory> hisList = info.getHistories();
						allRevList.addAll(hisList);
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
		}
		String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(module.getARESProject());
		if (StringUtils.isBlank(projectVersion)) {
			projectVersion = "1.0.0.1";
		}
		return getMaxVersionHisInfo(allRevList , projectVersion);
	}
	
	/**
	 * 2014年4月9日16:49:19
	 * 
	 * 获取指定的模块根下面最大版本号，如果没有资源或者所有资源都没有历史记录，则返回null
	 * @param root
	 * @return
	 */
	public static String getMaxVersionByModuleRoot(IARESModuleRoot root) {
		if(root == null)
			return null;
		
		List<RevisionHistory> allRevList = new ArrayList<RevisionHistory>();
		try {
			IARESResource[] resources = root.getResources();
			for (IARESResource res : resources) {
				JRESResourceInfo info = res.getInfo(JRESResourceInfo.class);
				if (info == null) {
					if (res.getName().equals(IARESModule.MODULE_PROPERTY_FILE)) {
						ModuleProperty mp = res.getInfo(ModuleProperty.class);
						Object obj = mp.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
						if(obj instanceof JRESResourceInfo){
							info = (JRESResourceInfo) obj;
						}
					}
				} 
				
				// 遍历取最大版本
				if (info != null) {
					List<RevisionHistory> hisList = info.getHistories();
					allRevList.addAll(hisList);
				}
			}
			
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return getMaxVersion(allRevList);
	}
	
	/**
	 * 获取指定的模块下面最大版本号，如果没有资源或者所有资源都没有历史记录，则返回null
	 * @param module
	 * @return
	 */
	public static String getMaxVersion(IARESModule module) {
		if (module == null)
			return null;
		
		IARESResource[] resources = module.getARESResources(true);
		List<RevisionHistory> allRevList = new ArrayList<RevisionHistory>();
		
		for (IARESResource res : resources) {
			try {
				JRESResourceInfo info = res.getInfo(JRESResourceInfo.class);
				if (info == null) {
					if (res.getName().equals(IARESModule.MODULE_PROPERTY_FILE)) {
						ModuleProperty mp = res.getInfo(ModuleProperty.class);
						Object obj = mp.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
						if(obj instanceof JRESResourceInfo){
						info = (JRESResourceInfo) obj;
						}
					}
				} 
				
				// 遍历取最大版本
				if (info != null) {
					List<RevisionHistory> hisList = info.getHistories();
					allRevList.addAll(hisList);
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		
		return getMaxVersion(allRevList);
	}
	
	public static RevisionHistory getMaxVersionHisInfo(Collection<RevisionHistory> versionList){
		String max = null;
		RevisionHistory revRet = null;
		
		for (RevisionHistory rev : versionList) {
			try {
				if (max == null) {
					max = rev.getVersion();
					revRet = rev;
					continue;
				}
				
				if (compare(max, rev.getVersion()) < 0) {
					max = rev.getVersion();
					revRet = rev;
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return revRet;
	}
	
	public static RevisionHistory getMaxVersionHisInfo(Collection<RevisionHistory> versionList ,String proVer){
		String max = null;
		RevisionHistory revRet = null;
		
		for (RevisionHistory rev : versionList) {
			try {
				if (max == null) {
					max = rev.getVersion();
					revRet = rev;
					continue;
				}
				
				if (compare(max, rev.getVersion()) < 0) {
					max = rev.getVersion();
					revRet = rev;
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
		if (compare(max, proVer) < 0) {
			return null;
		}
		return revRet;
	}
	
	/**
	 * 找出列表中的版本的最大的那个，并将版本以字符串的形式返回.
	 * @param versionList
	 * @return
	 */
	public static String getMaxVersion(Collection<RevisionHistory> versionList) {
		String max = null;
		
		for (RevisionHistory rev : versionList) {
			try {
				if (max == null) {
					max = rev.getVersion();
					continue;
				}
				
				if (compare(max, rev.getVersion()) < 0) {
					max = rev.getVersion();
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
		
		return max;
	}
	
	/**
	 * 计算给定的集合中最大的版本
	 * @param versions
	 * @return
	 */
	public static String max(Collection<String> versions) {
		String max = null;
		
		for (String rev : versions) {
			if (StringUtils.isEmpty(rev))
				continue;
			
			try {
				if (max == null) {
					max = rev;
					continue;
				}
				
				if (compare(max, rev) < 0) {
					max = rev;
				}
			} catch (Exception e) {
			}
		}
		
		return max == null ? null : max.toString();
	}
	
	/**
	 * 比较两个字符串形式的版本，格式应该为类似<code> 1.0.0.1</code>
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static int compare(String v1, String v2) {
		if (StringUtils.isBlank(v1) && StringUtils.isBlank(v2)) {
			return 0;
		}
		if (StringUtils.isBlank(v1))
			return -1;
		if (StringUtils.isBlank(v2))
			return 1;
		
		// 2012-12-28 sundl 兼容带v和不带v的比较
		v1 = StringUtils.removeStartIgnoreCase(v1, "v");
		v2 = StringUtils.removeStartIgnoreCase(v2, "v");
		
		RevisionHistoryVersion version1 = new RevisionHistoryVersion(v1);
		RevisionHistoryVersion version2 = new RevisionHistoryVersion(v2);
		return version1.compareTo(version2);
	}
	
	/**
	 * 版本号++; 兼容带v和不带v的格式。
	 * 如果是空或者null，原样返回。
	 * @param version
	 * @return
	 */
	public static String increase(String version) {
		if (StringUtils.isEmpty(version))
			return version;
		
		if (StringUtils.startsWithIgnoreCase(version, "v")) {
			String versionStr = StringUtils.substring(version, 1);
			RevisionHistoryVersion rev = new RevisionHistoryVersion(versionStr);
			RevisionHistoryVersion newVersion = new RevisionHistoryVersion(rev.getMajor(), rev.getMinor(), rev.getMicro(), rev.getQualifier() + 1);
			return version.charAt(0) + newVersion.toString();
		} else {
			RevisionHistoryVersion rev = new RevisionHistoryVersion(version);
			RevisionHistoryVersion newVersion = new RevisionHistoryVersion(rev.getMajor(), rev.getMinor(), rev.getMicro(), rev.getQualifier() + 1);
			return newVersion.toString();
		}
		
	}
	
	public static String getLatestUpdateDate(Collection<RevisionHistory> history) {
		RevisionHistory latest = null;
		for (RevisionHistory his : history) {
			if (latest == null)
				latest = his;
			// 先比较版本号，版本号相同的再比较修改日期
			int compare = compare(latest.getVersion(), his.getVersion());
			if (compare < 0) {
				latest = his;
			} else if (compare == 0) {
				String date1 = latest.getModifiedDate();
				String date2 = his.getModifiedDate();
				if (date1 == null || date2 ==null) {
					continue;
				}
				if (date1.compareTo(date2) < 0) {
					latest = his;
				}
			} 
		}
		return latest == null ? StringUtils.EMPTY : latest.getModifiedDate();
	}
	
}
