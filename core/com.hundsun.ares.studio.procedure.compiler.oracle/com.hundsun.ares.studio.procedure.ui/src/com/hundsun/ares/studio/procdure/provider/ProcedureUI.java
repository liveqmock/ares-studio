/**
 */
package com.hundsun.ares.studio.procdure.provider;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

import com.hundsun.ares.studio.biz.provider.BizUI;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.ui.ARESUI;
import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendPageInfo;
import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendPageManager;

/**
 * This is the central singleton for the Procedure edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class ProcedureUI extends EMFPlugin {
	public static final String PLUGIN_ID = "com.hundsun.ares.studio.procedure.ui"; //$NON-NLS-1$
	
	/**
	 * 是否自动将输入参数定义成变量
	 */
	public static final String PER_AUTO_DEFINE_PROC_INPARAM = "auto_define_in_params";
	
	/**
	 * 参数定义不带“%type”
	 */
	public static final String PER_NOT_DEFINE_CONNECT_TYPE = "not_define_connect_type";
	
	/**
	 * 是否需要返回出错信息
	 */
	public static final String PER_RETURN_ERROR_INFO = "return_error_info";
	
	/**
	 * 是否需要生成关联信息
	 */
	public static final String PER_GEN_RELATED_INFO = "gen_related_info";
	
	/**
	 * 是否生成前置代码
	 */
	public static final String PER_GEN_BEGIN_CODE = "gen_begin_code";
	
	/**
	 * 是否生成后置代码
	 */
	public static final String PER_GEN_END_CODE = "gen_end_code";
	
	/**
	 * 是否为证券二部扩展
	 * @return
	 */
	public static boolean isStock2Procedure() {
		for(ExtendPageInfo info:ExtendPageManager.getDefault().getPageInfo("com.hundsun.ares.studio.procedure.ui.editor.procedure")){
			if(StringUtils.equals(info.getPageId(), "com.hundsun.ares.studio.procedure.ui.extend.procedureextendpage")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ProcedureUI INSTANCE = new ProcedureUI();

	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Implementation plugin;

	/**
	 * Create the instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcedureUI() {
		super
		  (new ResourceLocator [] {
		     ARESUI.INSTANCE,
		     BizUI.INSTANCE,
		     MetadataUI.INSTANCE,
		   });
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static class Implementation extends EclipseUIPlugin {
		/**
		 * Creates an instance.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}
	}

}
