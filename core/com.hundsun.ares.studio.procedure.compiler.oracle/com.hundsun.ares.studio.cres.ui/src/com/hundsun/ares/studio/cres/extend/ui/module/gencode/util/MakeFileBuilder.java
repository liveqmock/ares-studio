/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.cres.extend.ui.module.gencode.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.cres.extend.cresextend.GccDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.MvcDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.ProcDefine;

/**
 * MakeFile文件生成器
 * @author 
 * @version 1.0
 * @history
 */
public class MakeFileBuilder {
	
	private static final String HEADER_COMMENT =
			"# *********************************************************************\n"+
			"# Make文件注意：\n"+
			"# 必须定义以下环境变量：\n"+
			"#   windows: \n"+
			"#       ORACLE_HOME （例如：e:\\oracle\\ora10g）\n"+
			"#       VC_HOME （例如：c:\\program files\\devstudio\\vc\\include）\n"+
			"#   linux: \n"+
			"#       GCC_VER （例如：export GCC_VER = 3.2.3） 可以执行：g++ -v查找当前版本 }\n"+
			"# \n"+
			"# 编译实例：\n"+
			"#   windows: \n"+
			"#       执行版本:     nmake -fs_XXXXflow.mvc -D _DEBUG=1 ORA_VER=10  \n"+
			"#   linux: \n"+
			"#       执行版本:       make -fs_XXXXflow.gcc ORA_VER=10  \n"+
			"# \n"+
			"# *********************************************************************\n" ;

	private static final String BASE_DIR_DEFINE = 
			"#功能组件makefile\n" +
			"#目标: %s\n" +
			"FBASE_HOME = %s\n\n";
	
	private static final String EXT_DEFINE =
			"#obj扩展名定义$TO\n" +
			"TO=%s\n" +
			"#proc文件扩展名定义$FROM\n" +
			"FROM=pc\n\n";
	
	
	
	private static final String GCC_OBJS_DEFINE =
			"#要编译的组件名,构成组件的obj文件定义\n" +
			"FC1 = %s\n" +
			"FC2 = s_glbfunction_or\n" +
			"FC3 = libpublic\n" +
			"FC4 = s_publicfunc\n" +
			"FC5 = %s\n" + 
			"%s\n" +
			"OBJS1 =  $(FC1).$(TO) $(FC5).$(TO)\n\n";
	
	private static final String GCC_OBJS_DEFINE2 =
		"#要编译的组件名,构成组件的obj文件定义\n" +
		"FC1 = %s\n" +
		"FC2 = s_libpublic\n" +
		"FC3 = s_helper\n" +
		"FC4 = s_glbfunction_or\n" +
		"FC5 = %s\n" + 
		"%s\n" +
		"OBJS1 =  $(FC1).$(TO) $(FC5).$(TO)\n\n";
	
	private static final String MVC_OBJS_DEFINE =
			"#要编译的组件名,构成组件的obj文件定义\n" +
			"FC1 = %s\n" +
			"FC2 = s_glbfunction_or\n" +
			"FC3 = libpublic\n" +
			"FC4 = s_publicfunc\n" +
			"FC5 = %s\n" + 
			"%s\n" +
			"OBJS1 = $(FC1).$(TO) $(FC2).$(TO) $(FC3).$(TO) $(FC4).$(TO) $(FC5).$(TO) %s\n\n";
	
	/** 2.0 MVC 定义 */
	private static final String MVC_OBJS_DEFINE2 =
		"#要编译的组件名,构成组件的obj文件定义\n" +
		"FC1 = %s\n" +
		"FC2 = s_libpublic\n" +
		"FC3 = s_helper\n" +
		"FC4 = s_glbfunction_or\n" +
		"FC5 = %s\n" + 
		"%s\n" +
		"OBJS1 = $(FC1).$(TO) $(FC2).$(TO) $(FC3).$(TO) $(FC4).$(TO) $(FC5).$(TO) %s\n\n";
	
	private static final String GCC_OUTDIR_DEFINE =
			"ifneq (,$(findstring solaris,$(OSTYPE)))\n" +
			"OUTDIR = %s\n" +
			"else\n" +
			"OUTDIR = %s\n" +
			"endif\n\n";
	
	private static final String MVC_OUTDIR_DEFINE =
			"#======= 文件路径 =================\n" +
			"OUTDIR=%s\n";
	
	private static final String PROC_DEFINE =
			"#PROC预处理器\n" +
			"PROC = %s\n";
	
	private static final String CC_DEFINE = 
			"#CPP编译器\n" +
			"CC = %s\n\n";
		
	private static final String PROC_PARAMETER_DEFINE =
			"#预编译选项\n" +
			"CPP_PROFLAGS = %s\n\n";
	
//	private static final String INCLUDE_DEFINE = 
//			"INCDIR=%s\n\n";
	
	private static final String GCC_DEFS_DEFINE =
			"#(-Dxxx should be added to DEFS macro):\n" +
			"DEFS =%s\n\n";
	
	private static final String GCC_OPTION_DEFINE2 =
		"#option 编译，连接选项\n" +
		"CFLAGS = -c -fPIC $(DEFS)\n" +
		"LDFLAGS = -shared\n" +
		"STDLIBS = -lc -lnsl -ldl -lm -lstdc++ -lpthread\n" +
		"LIBPATH=$(OUTDIR)\n" +
		"DBLIB = -L$(ORACLE_HOME)/lib -lclntsh -L$(ORACLE_HOME)/lib  -lsql$(ORA_VER)\n" +
		"HSLIB = -L$(OUTDIR) -l$(FC4) -l$(FC2) -l$(FC3) %s\n\n";
	
	private static final String GCC_OPTION_DEFINE =
			"#option 编译，连接选项\n" +
			"CFLAGS = -c -fPIC $(DEFS)\n" +
			"LDFLAGS = -shared\n" +
			"STDLIBS = -lc -lnsl -ldl -lm -lstdc++ -lpthread\n" +
			"LIBPATH=$(OUTDIR)\n" +
			"DBLIB = -L$(ORACLE_HOME)/lib -lclntsh -L$(ORACLE_HOME)/lib  -lsql$(ORA_VER)\n" +
			"HSLIB = -L$(OUTDIR) -l$(FC4).$(ORA_VER) -l$(FC2).$(ORA_VER) -l$(FC3) %s\n\n";

	/** 参数1是链接库，参数2是导出函数，如/EXPORT:OnLoad */
	private static final String MVC_OPTION_DEFIEN =
		"#编译选项\n" +
		"CFLAGS = -c -nologo -W3 -MD -GX\n" +
		"#link库路径及库\n" +
		"LIBPATH=$(FBASE_HOME)/Lib/mvc\n" +
		"LIBS= /link %s\n" +
		"LDFLAGS = /LD\n" +
		"#动态库输出函数\n" +
		"EXP= %s \n" +///EXPORT:GetCompsInfo /EXPORT:OnLoad /EXPORT:OnUnload /EXPORT:OnStart /EXPORT:OnStop\n" +
		"DBLIB= $(ORACLE_HOME)\\precomp\\lib\\orasql$(ORA_VER).lib\n";

	//头文件路径
	private static final String GCC_INCLAALL_DEFINE_DESC = "#头文件路径\n";
	//2013年7月8日15:51:50 qinyuan 头文件路径支持项目属性配置
	private static final String GCC_INCLALL_DEFINE_DEFAULT = 
			"INCLALL=-I $(FBASE_HOME)  -I $(ORACLE_HOME)/precomp/public -I $(ORACLE_HOME)/oci/include\n\n";

	private static final String MVC_INCLALL_DEFINE =
			"#头文件路径\n" +
			"INCLALL = %s \n";
	
	private static final String GCC_DEBUG_DEFINE =
			"#debug版本\n" +
			"ifdef _DEBUG\n" +
			"DEFS = -D_DEBUG\n" +
			"CFLAGS = -c -g -fPIC $(DEFS)\n" +
			"endif\n";
	
	private static final String MVC_DEBUG_DEFINE =
		"#debug版本\n" +
		"!IFDEF _DEBUG\n" +
		"CFLAGS = -c -nologo -W3 -GX -MDd \n" +
		"!ENDIF\n";
	
	private static final String GCC_COMMAND =
			"all:$(FC1)\n" +
			"\n" +
			"$(FC1):$(OBJS1)\n" +
			"\t$(CC)  $(LDFLAGS) $(OBJS1) $(DBLIB) $(HSLIB) -o$(OUTDIR)/lib$(FC1).$(ORA_VER).so $(LIBS) $(EXP)\n" +
			"\n" +
			"$(FC1).$(TO):$(FC1).cpp\n" +
			"\t$(CC) $(CFLAGS) $(FC1).cpp $(INCLALL)\n" +
			"\n" +
			"$(FC2).$(TO):$(FC2).$(FROM)\n" +
			"\t$(PROC) $(CPP_PROFLAGS) iname=$(FC2).$(FROM) oname=$(FC2).cpp\n" +
			"\t$(CC) $(CFLAGS) $(INCLALL) $(FC2).cpp -o $(FC2).o\n" +
			"\n" +
			"$(FC3).$(TO):$(FC3).cpp\n" +
			"\t$(CC) $(CFLAGS) $(FC3).cpp $(INCLALL)\n" +
			"\n" +
			"$(FC5).$(TO):$(FC5).$(FROM)\n" +
			"\t$(PROC) $(CPP_PROFLAGS) iname=$(FC5).$(FROM) oname=$(FC5).cpp\n" +
			"\t$(CC) $(CFLAGS) $(INCLALL) $(FC5).cpp -o $(FC5).o\n" +
			"\n" +
			"\n" +
			"cleanobj:\n" +
			"\trm -fr *.o core *~ cxx_repository\n" +
			"clean: cleanobj\n" +
			"\trm -f $(OUTDIR)/$(ASPACK_DLL)\n" +
			"doxygen:\n" +
			"\tdoxygen doxygen.cfg\n" +
			"\t-del *.obj\n\n";
	
	private static final String AS_GCC_COMMAND =
		"all:$(FC1)\n" +
		"\n" +
		"$(FC1):$(OBJS1)\n" +
		"\t$(CC)  $(LDFLAGS) $(OBJS1) $(DBLIB) $(HSLIB) -o$(OUTDIR)/lib$(FC1).$(ORA_VER).so $(LIBS) $(EXP)\n" +
		"\n" +
		"$(FC1).$(TO):$(FC1).$(FROM)\n" +
		"\t$(PROC) $(CPP_PROFLAGS) iname=$(FC1).$(FROM) oname=$(FC1).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC1).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC2).$(TO):$(FC2).cpp)\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) $(FC2).cpp -o $(FC2).o\n" +
		"\n" +
		"$(FC3).$(TO):$(FC3).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC3).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC4).$(TO):$(FC4).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC4).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC5).$(TO):$(FC5).$(FROM)\n" +
		"\t$(PROC) $(CPP_PROFLAGS) iname=$(FC5).$(FROM) oname=$(FC5).cpp\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) $(FC5).cpp -o $(FC5).o\n" +
		"\n" +
		"\n" +
		"cleanobj:\n" +
		"\trm -fr *.o core *~ cxx_repository\n" +
		"clean: cleanobj\n" +
		"\trm -f $(OUTDIR)/$(ASPACK_DLL)\n" +
		"doxygen:\n" +
		"\tdoxygen doxygen.cfg\n" +
		"\t-del *.obj\n\n";
	
	private static final String LS_GCC_COMMAND =
		"all:$(FC1)\n" +
		"\n" +
		"$(FC1):$(OBJS1)\n" +
		"\t $(CC) $(LDFLAGS) $(OBJS1) $(HSLIB) -o$(OUTDIR)/lib$(FC1).$(ORA_VER).so $(LIBS) $(EXP)\n" +
		"\n" +
		"$(FC1).$(TO):$(FC1).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC1).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC2).$(TO):$(FC2).cpp\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) $(FC2).cpp -o $(FC2).o\n" +
		"\n" +
		"$(FC3).$(TO):$(FC3).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC3).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC5).$(TO):$(FC5).cpp\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) $(FC5).cpp -o $(FC5).o\n" +
		"\n" +
		"\n" +
		"cleanobj:\n" +
		"\trm -fr *.o core *~ cxx_repository\n" +
		"clean: cleanobj\n" +
		"\trm -f $(OUTDIR)/$(ASPACK_DLL)\n" +
		"doxygen:\n" +
		"\tdoxygen doxygen.cfg\n" +
		"\t-del *.obj\n\n";
	
	private static final String MVC_COMMAND = 
		"all:$(FC1)\n" +
		"\n" +
		"$(FC1):$(OBJS1)\n" +
		"\t$(CC)  $(LDFLAGS) $(INCLALL) $(OBJS1) -o$(OUTDIR)/$(FC1)$(ORA_VER) $(LIBS) $(DBLIB) $(EXP)\n" +
		"\n" +
		"$(FC1).$(TO):$(FC1).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC1).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC2).$(TO):$(FC2).$(FROM)\n" +
		"\t$(PROC) iname=$(FC2).$(FROM) oname=$(FC2).cpp $(CPP_PROFLAGS)\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC2).obj $(FC2).cpp\n" +
		"\n" +
		"$(FC3).$(TO):$(FC3).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC3).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC4).$(TO):$(FC4).$(FROM)\n" +
		"\t$(PROC) iname=$(FC4).$(FROM) oname=$(FC4).cpp $(CPP_PROFLAGS)\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC4).obj $(FC4).cpp\n" +
		"\n" +
		"$(FC5).$(TO):$(FC5).$(FROM)\n" +
		"\t$(PROC) iname=$(FC5).$(FROM) oname=$(FC5).cpp $(CPP_PROFLAGS)\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC5).obj $(FC5).cpp\n" +
		"%s\n" +
		"\n" +
		"clean:\n" +
		"\t-del *.obj\n" +
		"\t-del $(OUTDIR)\\$(FC1)\n";
	
	private static final String AS_MVC_COMMAND = 
		"all:$(FC1)\n" +
		"\n" +
		"$(FC1):$(OBJS1)\n" +
		"\t$(CC)  $(LDFLAGS) $(INCLALL) $(OBJS1) -o$(OUTDIR)/$(FC1)$(ORA_VER) $(LIBS) $(DBLIB) $(EXP)\n" +
		"\n" +
		"$(FC1).$(TO):$(FC1).$(FROM)\n" +
		"\t$(PROC) $(CPP_PROFLAGS) iname=$(FC1).$(FROM) oname=$(FC1).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC1).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC2).$(TO):$(FC2).cpp\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC2).obj $(FC2).cpp\n" +
		"\n" +
		"$(FC3).$(TO):$(FC3).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC3).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC4).$(TO):$(FC4).cpp\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC4).obj $(FC4).cpp\n" +
		"\n" +
		"$(FC5).$(TO):$(FC5).$(FROM)\n" +
		"\t$(PROC) iname=$(FC5).$(FROM) oname=$(FC5).cpp $(CPP_PROFLAGS)\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC5).obj $(FC5).cpp\n" +
		"%s\n" +
		"\n" +
		"clean:\n" +
		"\t-del *.obj\n" +
		"\t-del $(OUTDIR)\\$(FC1)\n";
	
	private static final String LS_MVC_COMMAND = 
		"all:$(FC1)\n" +
		"\n" +
		"$(FC1):$(OBJS1)\n" +
		"\t$(CC) $(LDFLAGS) $(INCLALL) $(OBJS1) -o$(OUTDIR)/$(FC1)$(ORA_VER) $(LIBS) $(EXP)\n" +
		"\n" +
		"$(FC1).$(TO):$(FC1).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC1).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC2).$(TO):$(FC2).cpp\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC2).obj $(FC2).cpp\n" +
		"\n" +
		"$(FC3).$(TO):$(FC3).cpp\n" +
		"\t$(CC) $(CFLAGS) $(FC3).cpp $(INCLALL)\n" +
		"\n" +
		"$(FC4).$(TO):$(FC4).cpp\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC4).obj $(FC4).cpp\n" +
		"\n" +
		"$(FC5).$(TO):$(FC5).cpp\n" +
		"\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC5).obj $(FC5).cpp\n" +
		"%s\n" +
		"\n" +
		"clean:\n" +
		"\t-del *.obj\n" +
		"\t-del $(OUTDIR)\\$(FC1)\n";
	
	/**
	 * 生成2.0业务层的gcc文件
	 * 
	 * @param project
	 * @param sb
	 * @param soName
	 * @param serviceName
	 * @param functionName
	 * @param depends
	 */
	static public void writeLSGccMakeFile(IARESProject project, StringBuffer sb, String soName, String serviceName, String functionName, String depends[] ) throws Exception{
		EList<GccDefine> gccDefine = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getGccDefine();
		Map<String, GccDefine> gccDefineMap = new HashMap<String, GccDefine>();
		for (GccDefine define : gccDefine) {
			gccDefineMap.put(define.getParameter(), define);
		}
		// 头注释
		sb.append(HEADER_COMMENT);
		
		// 目标名称，基本目录
		if(null != gccDefineMap.get("FBASE_HOME")){
			if(gccDefineMap.get("FBASE_HOME").isIsUsed()){
				sb.append(String.format(BASE_DIR_DEFINE, soName, gccDefineMap.get("FBASE_HOME").getValue() )) ;
			}
		}else {
			sb.append(String.format(BASE_DIR_DEFINE, soName, ".." )) ;
		}
		
		// 扩展名定义
		if(null != gccDefineMap.get("TO")){
			if(gccDefineMap.get("TO").isIsUsed()){
				sb.append(String.format(EXT_DEFINE, gccDefineMap.get("TO").getValue()));
			}
		}else {
			sb.append(String.format(EXT_DEFINE, "o"));
		}
		
		StringBuffer dependObjs = new StringBuffer();
		StringBuffer hslibex = new StringBuffer();
		StringBuffer inclall = new StringBuffer();
		//用户自定的FC
		int fcNum = 0;//用户自定的FC个数
		if(null != gccDefineMap.get("FC") && gccDefineMap.get("FC").isIsUsed()){
			String fc = gccDefineMap.get("FC").getValue();
			if(StringUtils.isNotBlank(fc)){
				if(StringUtils.contains(fc, ",")){
					String[] fcs = fc.split(",");
					for (int i = 0; i < fcs.length; i++) {
						if(StringUtils.isNotBlank(fcs[i])){
							dependObjs.append("FC");
							dependObjs.append(i+6);
							dependObjs.append(" = ");
							dependObjs.append(fcs[i]);//添加模块依赖时，这里不需要加func.h后缀，需要加s_ls_${module_name}flow即可
							dependObjs.append("\n");
							
							hslibex.append("-l$(FC");
							hslibex.append(i+6);
							hslibex.append(") ");
							
							fcNum++;
						}
					}
				}else {
					dependObjs.append("FC");
					dependObjs.append(6);
					dependObjs.append(" = ");
					dependObjs.append(fc);//添加模块依赖时，这里不需要加func.h后缀，需要加s_ls_${module_name}flow即可
					dependObjs.append("\n");
					
					hslibex.append("-l$(FC");
					hslibex.append(6);
					hslibex.append(") ");
					
					fcNum++;
				}
			}
		}
		//模块依赖
		for (int i = 0; i < depends.length; i++) {
			dependObjs.append("FC");
			dependObjs.append(i+6+fcNum);
			dependObjs.append(" = ");
			dependObjs.append(depends[i]);//添加模块依赖时，这里不需要加func.h后缀，需要加s_ls_${module_name}flow即可
			dependObjs.append("\n");
			
			hslibex.append("-l$(FC");
			hslibex.append(i+6+fcNum);
			hslibex.append(").$(ORA_VER) ");
		}
		
		// Objects
		sb.append(String.format(GCC_OBJS_DEFINE2,  serviceName, functionName, dependObjs));
		
		//outdir
		String outdir = "$(FBASE_HOME)/appcom";
		if(null != gccDefineMap.get("OUTDIR") && gccDefineMap.get("OUTDIR").isIsUsed()){
			outdir = gccDefineMap.get("OUTDIR").getValue();
		}
		sb.append(String.format(GCC_OUTDIR_DEFINE,outdir,outdir));
		
		//CC
		if(null != gccDefineMap.get("CC")){
			if(gccDefineMap.get("CC").isIsUsed()) {
				sb.append(String.format(CC_DEFINE, gccDefineMap.get("CC").getValue()));
			}
		}else{
			sb.append(String.format(CC_DEFINE, "g++"));
		}

		//DEFS
		if(null != gccDefineMap.get("DEFS")){
			if(gccDefineMap.get("DEFS").isIsUsed()) {
				sb.append(String.format(GCC_DEFS_DEFINE, gccDefineMap.get("DEFS").getValue()));
			}
		}else {
			sb.append(String.format(GCC_DEFS_DEFINE, "-D OS_UNIX"));
		}
		
		sb.append("\n");
		
		for (GccDefine define : gccDefine) {
			if(!define.isIsUsed()){
				continue;
			}
			
			if(StringUtils.equals(define.getParameter(), "LIBS")) {
				if(!define.getValue().trim().isEmpty()) {
					String libs[] = define.getValue().split(",");
					for (String lib : libs) {
						hslibex.append( " -l " );
						hslibex.append( lib );
						hslibex.append( " " );
					}
				}
			}
			if(StringUtils.equalsIgnoreCase(define.getParameter(), "CC_INCLUDE")){
				if(!define.getValue().trim().isEmpty()) {
					inclall.append("INCLALL = ");
					String libs[] = define.getValue().split(",");
					for (String lib : libs) {
						inclall.append( " -I " );
						inclall.append( lib );
						inclall.append( " " );
					}
					inclall.append("\n\n");
				}
			}
		}
		
		sb.append(String.format(GCC_OPTION_DEFINE2, hslibex));

		//头文件
		sb.append(GCC_INCLAALL_DEFINE_DESC);
		if(StringUtils.isEmpty(inclall.toString())){
			sb.append(GCC_INCLALL_DEFINE_DEFAULT);
		}else {
			sb.append(inclall);
		}
		
		sb.append(GCC_DEBUG_DEFINE);
		
		sb.append(LS_GCC_COMMAND);
	}
	
	/**
	 * 生成2.0的原子层gcc文件
	 * 
	 * @param project
	 * @param sb
	 * @param soName
	 * @param serviceName
	 * @param functionName
	 * @param depends
	 */
	static public void writeASGccMakeFile(IARESProject project, StringBuffer sb, String userid,String soName, String serviceName, String functionName, String depends[] ) throws Exception{
		EList<GccDefine> gccDefine = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getGccDefine();
		Map<String, GccDefine> gccDefineMap = new HashMap<String, GccDefine>();
		for (GccDefine define : gccDefine) {
			gccDefineMap.put(define.getParameter(), define);
		}
		// 头注释
		sb.append(HEADER_COMMENT);
		
		// 目标名称，基本目录
		if(null != gccDefineMap.get("FBASE_HOME")){
			if(gccDefineMap.get("FBASE_HOME").isIsUsed()) {
				sb.append(String.format(BASE_DIR_DEFINE, soName, gccDefineMap.get("FBASE_HOME").getValue())) ;
			}
		}else{
			sb.append(String.format(BASE_DIR_DEFINE, soName, ".." )) ;
		}
		
		// 扩展名定义
		if(null != gccDefineMap.get("TO")){
			if(gccDefineMap.get("TO").isIsUsed()){
				sb.append(String.format(EXT_DEFINE, gccDefineMap.get("TO").getValue()));
			}
		}else {
			sb.append(String.format(EXT_DEFINE, "o"));
		}
		
		StringBuffer dependObjs = new StringBuffer();
		StringBuffer hslibex = new StringBuffer();
		//用户自定的FC
		int fcNum = 0;//用户自定的FC个数
		if(null != gccDefineMap.get("FC") && gccDefineMap.get("FC").isIsUsed()){
			String fc = gccDefineMap.get("FC").getValue();
			if(StringUtils.isNotBlank(fc)){
				if(StringUtils.contains(fc, ",")){
					String[] fcs = fc.split(",");
					for (int i = 0; i < fcs.length; i++) {
						if(StringUtils.isNotBlank(fcs[i])){
							dependObjs.append("FC");
							dependObjs.append(i+6);
							dependObjs.append(" = ");
							dependObjs.append(fcs[i]);//添加模块依赖时，这里不需要加func.h后缀，需要加s_ls_${module_name}flow即可
							dependObjs.append("\n");
							
							hslibex.append("-l$(FC");
							hslibex.append(i+6);
							hslibex.append(") ");
							
							fcNum++;
						}
					}
				}else {
					dependObjs.append("FC");
					dependObjs.append(6);
					dependObjs.append(" = ");
					dependObjs.append(fc);//添加模块依赖时，这里不需要加func.h后缀，需要加s_ls_${module_name}flow即可
					dependObjs.append("\n");
					
					hslibex.append("-l$(FC");
					hslibex.append(6);
					hslibex.append(") ");
					
					fcNum++;
				}
			}
		}
		//模块依赖
		for (int i = 0; i < depends.length; i++) {
			dependObjs.append("FC");
			dependObjs.append(i+6+fcNum);
			dependObjs.append(" = ");
			dependObjs.append(depends[i]);
			dependObjs.append("\n");
			
			hslibex.append("-l$(FC");
			hslibex.append(i+6+fcNum);
			hslibex.append(").$(ORA_VER) ");
		}
		
		// Objects
		sb.append(String.format(GCC_OBJS_DEFINE2,  serviceName, functionName, dependObjs));
		
		//outdir
		String outdir = "$(FBASE_HOME)/appcom";
		if(null != gccDefineMap.get("OUTDIR")){
			if(gccDefineMap.get("OUTDIR").isIsUsed()){
				outdir = gccDefineMap.get("OUTDIR").getValue();
			}
		}
		sb.append(String.format(GCC_OUTDIR_DEFINE,outdir,outdir));
		
		//PROC
		if(null != gccDefineMap.get("PROC")){
			if(gccDefineMap.get("PROC").isIsUsed()) {
				sb.append(String.format(PROC_DEFINE, gccDefineMap.get("PROC").getValue()));
			}
		}else {
			sb.append(String.format(PROC_DEFINE, "$(ORACLE_HOME)/bin/proc"));
		}
		//CC
		if(null != gccDefineMap.get("CC")){
			if(gccDefineMap.get("CC").isIsUsed()) {
				sb.append(String.format(CC_DEFINE, gccDefineMap.get("CC").getValue()));
			}
		}else{
			sb.append(String.format(CC_DEFINE, "g++"));
		}
		
		EList<ProcDefine> procDefine = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getProcDefine();
		
		StringBuffer procSb = new StringBuffer();
//		// 2.0 下优先使用模块定义的
//		if (userid != null && !userid.isEmpty()) {
//			procSb.append("userid");
//			procSb.append( "=" );
//			procSb.append(userid);
//			procSb.append(" \\\n");
//		}
		
		boolean hasUseridProcDefine = false;//proc预处理是否有“userid”选项
		for (ProcDefine define : procDefine) {
			if(define.isIsUsed()) {
				if(StringUtils.equalsIgnoreCase(define.getParameter(), "userid")){
					hasUseridProcDefine = true;
				}
				procSb.append( define.getParameter() );
				procSb.append( "=" );
				procSb.append( define.getValue() );
				procSb.append(" \\\n");
			}
		}
		//O4优先使用proc预处理中的“userid”选项
		if(!hasUseridProcDefine){
			if (userid != null && !userid.isEmpty()) {
				procSb.append("userid");
				procSb.append( "=" );
				procSb.append(userid);
				procSb.append(" \\\n");
			}
		}
		
		boolean hasProcInclude = false;
		String gccDefineLIBS = "";//特殊处理
		StringBuffer inclall = new StringBuffer();
		for (GccDefine define : gccDefine) {
			if(!define.isIsUsed()){
				continue;
			}
			
			if(StringUtils.equals(define.getParameter(), "PROC_INCLUDE")) {
				hasProcInclude = true;
				StringBuffer procInclude = new StringBuffer();
				String strInclude[] = define.getValue().split(",");
				int j = 0;
				for (; j < strInclude.length - 1; j++) {
					procInclude.append("include=");
					procInclude.append(strInclude[j]);
					procInclude.append("\\\n");
				}
				
				procInclude.append("include=");
				procInclude.append(strInclude[j]);
				procInclude.append("\n");
				
				sb.append(String.format(PROC_PARAMETER_DEFINE, 
						procSb.toString() +
						procInclude.toString()
				));
			}
			if(StringUtils.equals(define.getParameter(), "LIBS")){
				gccDefineLIBS = define.getValue();
			}
			if(StringUtils.equalsIgnoreCase(define.getParameter(), "CC_INCLUDE")){
				if(!define.getValue().trim().isEmpty()) {
					inclall.append("INCLALL = ");
					String libs[] = define.getValue().split(",");
					for (String lib : libs) {
						inclall.append( " -I " );
						inclall.append( lib );
						inclall.append( " " );
					}
					inclall.append("\n\n");
				}
			}
		}
		if(!hasProcInclude) {
			sb.append(String.format(PROC_PARAMETER_DEFINE, 
					procSb.toString() +
					"include=$(ORACLE_HOME)/precomp/public  \\\n"+
					"include=$(ORACLE_HOME)/oci/include  \\\n"+
					"include=$(FBASE_HOME) \\\n"+
					"include=/usr/lib/gcc-lib/i386-redhat-linux/$(GCC_VER)/include\n"

			));
		}
		
//		sb.append(String.format(INCLUDE_DEFINE, "-I$(FBASE_HOME)"));
		
		//DEFS
		if(null != gccDefineMap.get("DEFS")){
			if(gccDefineMap.get("DEFS").isIsUsed()) {
				sb.append(String.format(GCC_DEFS_DEFINE, gccDefineMap.get("DEFS").getValue()));
			}
		}else {
			sb.append(String.format(GCC_DEFS_DEFINE, "-D OS_UNIX"));
		}
		
		sb.append("\n");
		
		if(gccDefineLIBS.trim().length() > 0) {
			String libs[] = gccDefineLIBS.split(",");
			for (String lib : libs) {
				hslibex.append( " -l " );
				hslibex.append( lib );
				hslibex.append( " " );
			}
		}
		
		sb.append(String.format(GCC_OPTION_DEFINE2, hslibex));

		//头文件
		sb.append(GCC_INCLAALL_DEFINE_DESC);
		if(StringUtils.isEmpty(inclall.toString())){
			sb.append(GCC_INCLALL_DEFINE_DEFAULT);
		}else {
			sb.append(inclall);
		}
		
		sb.append(GCC_DEBUG_DEFINE);
		
		sb.append(AS_GCC_COMMAND);
	}
	
	
	static public void writeASMvcMakeFile(IARESProject project, StringBuffer sb, String userid,String soName, String serviceName, String functionName, String depends[] ) throws Exception{
		// 头注释
		sb.append(HEADER_COMMENT);
		
		// 目标名称，基本目录
		Map<String, String> mvcConfigMap = new HashMap<String, String>();
		EList<MvcDefine> mvcDefine = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getMvcDefine();
		for (MvcDefine define : mvcDefine) {
			mvcConfigMap.put(define.getParameter(), define.getValue());
		}
		
		String base_dir = mvcConfigMap.get("FBASE_HOME");
		if (base_dir != null) {
			sb.append(String.format(BASE_DIR_DEFINE, soName, base_dir )) ;
		} else {
			sb.append(String.format(BASE_DIR_DEFINE, soName, "../.." )) ;
		}
		
		
		// 扩展名定义
		sb.append(String.format(EXT_DEFINE, "obj"));
		
		StringBuffer dependObjs = new StringBuffer();
		StringBuffer objslink = new StringBuffer();
		StringBuffer exCommand = new StringBuffer();
		for (int i = 0; i < depends.length; i++) {
			dependObjs.append("FC");
			dependObjs.append(i+6);
			dependObjs.append(" = ");
			dependObjs.append(depends[i]);
			dependObjs.append("\n");
			
			// $(FC1).$(TO)
			objslink.append("$(FC");
			objslink.append(i+6);
			objslink.append(").$(TO) ");
			
			/*
			$(FC14).$(TO):$(FC14).$(FROM)
				$(PROC) $(CPP_PROFLAGS) iname=$(FC14).$(FROM) oname=$(FC14).cpp
				$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC14).obj $(FC14).cpp
			*/
			exCommand.append("$(FC");
			exCommand.append(i+6);
			exCommand.append(").$(TO):$(FC");
			exCommand.append(i+6);
			exCommand.append(").$(FROM)\n");
			
			exCommand.append("\t$(PROC) $(CPP_PROFLAGS) iname=$(FC"+ (i+6) + ").$(FROM) oname=$(FC" + (i+6) + ").cpp\n");
			exCommand.append("\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC"+ (i+6) + ").obj $(FC"+ (i+6) + ").cpp\n");
		}
		
		sb.append(String.format(MVC_OBJS_DEFINE2, serviceName, functionName, dependObjs, objslink));
		
		String outdir = String.format(MVC_OUTDIR_DEFINE, "$(FBASE_HOME)\\Bin");
		// 设置的话，使用设置的值
		if (mvcConfigMap.containsKey("OUTDIR")) {
			outdir = String.format(MVC_OUTDIR_DEFINE,mvcConfigMap.get("OUTDIR"));
		}
		sb.append(outdir);
		
		sb.append(String.format(PROC_DEFINE, "$(ORACLE_HOME)\\bin\\proc"));
		sb.append(String.format(CC_DEFINE, "CL"));
		
//		sb.append(String.format(USERID_DEFINE, "hs_his/handsome@gfdb"));
		
		Map<String, String> procSetting = new HashMap<String, String>();
		EList<ProcDefine> procDefine = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getProcDefine();
		for (ProcDefine define : procDefine) {
			procSetting.put(define.getParameter(), define.getValue());
		}
		// 2.0 下优先使用模块定义的
		if (userid != null && !userid.isEmpty()) {
			procSetting.put("userid", userid);
		}
		StringBuffer procSb = new StringBuffer();
		for (String setting : procSetting.keySet()) {
			procSb.append( setting );
			procSb.append( "=" );
			procSb.append( procSetting.get(setting) );
			procSb.append(" \\\n");
		}
		

		sb.append("\n");
		if (mvcConfigMap.containsKey("PROC_INCLUDE")) {
			StringBuffer procInclude = new StringBuffer();
			String strInclude[] = mvcConfigMap.get("PROC_INCLUDE").split(",");
			int j = 0;
			for (; j < strInclude.length - 1; j++) {
				procInclude.append("include=");
				procInclude.append(strInclude[j]);
				procInclude.append("\\\n");
			}
			
			procInclude.append("include=");
			procInclude.append(strInclude[j]);
			procInclude.append("\n");
			
			sb.append(String.format(PROC_PARAMETER_DEFINE, 
					procSb.toString() +
					procInclude.toString()
			));
			
		} else {
			sb.append(String.format(PROC_PARAMETER_DEFINE, 
					procSb.toString() +
					"include=$(ORACLE_HOME)/precomp/public  \\\n"+
					"include=$(ORACLE_HOME)/oci/include  \\\n"+
					"include=$(FBASE_HOME) \\\n"+
					"include=\"$(VC_HOME)\""

			));
		}
		
		
//		sb.append(String.format(INCLUDE_DEFINE, "/I$(FBASE_HOME)"));
		StringBuffer sbExport = new StringBuffer();
		writeExportString(sbExport, new String[]{"GetBizFunctionsInfo","OnLoad","OnUnload","OnStart", "OnStop","GetLibVersion"});
		
		sb.append("\n");
		if (mvcConfigMap.containsKey("LIBS")) {
			sb.append(String.format(MVC_OPTION_DEFIEN, mvcConfigMap.get("LIBS"), sbExport.toString()));
		} else {
			sb.append(String.format(MVC_OPTION_DEFIEN, "", sbExport.toString()));
		}
		
		
		StringBuffer sbInclall = new StringBuffer("/I $(FBASE_HOME) /I $(ORACLE_HOME)/precomp/public /I $(ORACLE_HOME)/oci/include");
		if (mvcConfigMap.containsKey("CC_INCLUDE")) {
			sbInclall = new StringBuffer();
			writeIncludeAllString(sbInclall, mvcConfigMap.get("CC_INCLUDE").split(","));
		}
		
		sb.append("\n");
		sb.append(String.format(MVC_INCLALL_DEFINE, sbInclall.toString())  );
		
		sb.append("\n");
		sb.append(MVC_DEBUG_DEFINE);
		
		sb.append("\n");
		sb.append( String.format(AS_MVC_COMMAND, exCommand));
	}
	
	static public void writeLSMvcMakeFile(IARESProject project, StringBuffer sb, String soName, String serviceName, String functionName, String depends[] ) throws Exception{
		// 头注释
		sb.append(HEADER_COMMENT);
		
		// 目标名称，基本目录
		Map<String, String> mvcConfigMap = new HashMap<String, String>();
		EList<MvcDefine> mvcDefine = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getMvcDefine();
		for (MvcDefine define : mvcDefine) {
			mvcConfigMap.put(define.getParameter(), define.getValue());
		}
		
		String base_dir = mvcConfigMap.get("FBASE_HOME");
		if (base_dir != null) {
			sb.append(String.format(BASE_DIR_DEFINE, soName, base_dir )) ;
		} else {
			sb.append(String.format(BASE_DIR_DEFINE, soName, "../.." )) ;
		}
		
		
		// 扩展名定义
		sb.append(String.format(EXT_DEFINE, "obj"));
		
		StringBuffer dependObjs = new StringBuffer();
		StringBuffer objslink = new StringBuffer();
		StringBuffer exCommand = new StringBuffer();
		for (int i = 0; i < depends.length; i++) {
			dependObjs.append("FC");
			dependObjs.append(i+6);
			dependObjs.append(" = ");
			dependObjs.append(depends[i]);
			dependObjs.append("\n");
			
			// $(FC1).$(TO)
			objslink.append("$(FC");
			objslink.append(i+6);
			objslink.append(").$(TO) ");
			
			/*
			$(FC14).$(TO):$(FC14).$(FROM)
				$(PROC) $(CPP_PROFLAGS) iname=$(FC14).$(FROM) oname=$(FC14).cpp
				$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC14).obj $(FC14).cpp
			*/
			exCommand.append("$(FC");
			exCommand.append(i+6);
			exCommand.append(").$(TO):$(FC");
			exCommand.append(i+6);
			exCommand.append(").cpp\n");
			
			exCommand.append("\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC"+ (i+6) + ").obj $(FC"+ (i+6) + ").cpp\n");
		}
		
		sb.append(String.format(MVC_OBJS_DEFINE2, serviceName, functionName, dependObjs, objslink));
		
		String outdir = String.format(MVC_OUTDIR_DEFINE, "$(FBASE_HOME)\\Bin");
		// 设置的话，使用设置的值
		if (mvcConfigMap.containsKey("OUTDIR")) {
			outdir = String.format(MVC_OUTDIR_DEFINE,mvcConfigMap.get("OUTDIR"));
		}
		sb.append(outdir);
		
		sb.append(String.format(PROC_DEFINE, "$(ORACLE_HOME)\\bin\\proc"));
		sb.append(String.format(CC_DEFINE, "CL"));
		

		
//		sb.append(String.format(INCLUDE_DEFINE, "/I$(FBASE_HOME)"));
		StringBuffer sbExport = new StringBuffer();
		writeExportString(sbExport, new String[]{"GetBizFunctionsInfo","OnLoad","OnUnload","OnStart", "OnStop","GetLibVersion"});

		
		sb.append("\n");
		if (mvcConfigMap.containsKey("LIBS")) {
			sb.append(String.format(MVC_OPTION_DEFIEN, mvcConfigMap.get("LIBS"), sbExport.toString()));
		} else {
			sb.append(String.format(MVC_OPTION_DEFIEN, "", sbExport.toString()));
		}
		
		
		StringBuffer sbInclall = new StringBuffer("/I $(FBASE_HOME) /I $(ORACLE_HOME)/precomp/public /I $(ORACLE_HOME)/oci/include");
		if (mvcConfigMap.containsKey("CC_INCLUDE")) {
			sbInclall = new StringBuffer();
			writeIncludeAllString(sbInclall, mvcConfigMap.get("CC_INCLUDE").split(","));
		}
		
		sb.append("\n");
		sb.append(String.format(MVC_INCLALL_DEFINE, sbInclall.toString())  );
		
		sb.append("\n");
		sb.append(MVC_DEBUG_DEFINE);
		
		sb.append("\n");
		sb.append( String.format(LS_MVC_COMMAND, exCommand));
	}
	
	/**
	 * 
	 * @param project
	 * @param sb
	 * @param soName
	 * @param serviceName
	 * @param functionName
	 * @param depends 包含func字符的，也就是全名
	 */
	static public void writeMvcMakeFile(IARESProject project, StringBuffer sb, String soName, String serviceName, String functionName, String depends[] ) throws Exception{
		
		// 头注释
		sb.append(HEADER_COMMENT);
		
		// 目标名称，基本目录
		Map<String, String> mvcConfigMap = new HashMap<String, String>();
		EList<MvcDefine> mvcDefine = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getMvcDefine();
		for (MvcDefine define : mvcDefine) {
			mvcConfigMap.put(define.getParameter(), define.getValue());
		}
		
		String base_dir = mvcConfigMap.get("FBASE_HOME");
		if (base_dir != null) {
			sb.append(String.format(BASE_DIR_DEFINE, soName, base_dir )) ;
		} else {
			sb.append(String.format(BASE_DIR_DEFINE, soName, "../.." )) ;
		}
		
		
		// 扩展名定义
		sb.append(String.format(EXT_DEFINE, "obj"));
		
		StringBuffer dependObjs = new StringBuffer();
		StringBuffer objslink = new StringBuffer();
		StringBuffer exCommand = new StringBuffer();
		for (int i = 0; i < depends.length; i++) {
			dependObjs.append("FC");
			dependObjs.append(i+6);
			dependObjs.append(" = ");
			dependObjs.append(depends[i]);
			dependObjs.append("\n");
			
			// $(FC1).$(TO)
			objslink.append("$(FC");
			objslink.append(i+6);
			objslink.append(").$(TO) ");
			
			/*
			$(FC14).$(TO):$(FC14).$(FROM)
				$(PROC) $(CPP_PROFLAGS) iname=$(FC14).$(FROM) oname=$(FC14).cpp
				$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC14).obj $(FC14).cpp
			*/
			exCommand.append("$(FC");
			exCommand.append(i+6);
			exCommand.append(").$(TO):$(FC");
			exCommand.append(i+6);
			exCommand.append(").$(FROM)\n");
			
			exCommand.append("\t$(PROC) $(CPP_PROFLAGS) iname=$(FC"+ (i+6) + ").$(FROM) oname=$(FC" + (i+6) + ").cpp\n");
			exCommand.append("\t$(CC) $(CFLAGS) $(INCLALL) /Fo$(FC"+ (i+6) + ").obj $(FC"+ (i+6) + ").cpp\n");
		}
		
		sb.append(String.format(MVC_OBJS_DEFINE, serviceName, functionName, dependObjs, objslink));
		
		String outdir = String.format(MVC_OUTDIR_DEFINE, "$(FBASE_HOME)\\Bin");
		// 设置的话，使用设置的值
		if (mvcConfigMap.containsKey("OUTDIR")) {
			outdir = String.format(MVC_OUTDIR_DEFINE,mvcConfigMap.get("OUTDIR"));
		}
		sb.append(outdir);
		
		sb.append(String.format(PROC_DEFINE, "$(ORACLE_HOME)\\bin\\proc"));
		sb.append(String.format(CC_DEFINE, "CL"));
		
//		sb.append(String.format(USERID_DEFINE, "hs_his/handsome@gfdb"));
		
		Map<String, String> procSetting = new HashMap<String, String>();
		EList<ProcDefine> procDefine = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getProcDefine();
		for (ProcDefine define : procDefine) {
			mvcConfigMap.put(define.getParameter(), define.getValue());
		}
		
		StringBuffer procSb = new StringBuffer();
		for (String setting : procSetting.keySet()) {
			procSb.append( setting );
			procSb.append( "=" );
			procSb.append( procSetting.get(setting) );
			procSb.append(" \\\n");
		}
		

		sb.append("\n");
		if (mvcConfigMap.containsKey("PROC_INCLUDE")) {
			StringBuffer procInclude = new StringBuffer();
			String strInclude[] = mvcConfigMap.get("PROC_INCLUDE").split(",");
			int j = 0;
			for (; j < strInclude.length - 1; j++) {
				procInclude.append("include=");
				procInclude.append(strInclude[j]);
				procInclude.append("\\\n");
			}
			
			procInclude.append("include=");
			procInclude.append(strInclude[j]);
			procInclude.append("\n");
			
			sb.append(String.format(PROC_PARAMETER_DEFINE, 
					procSb.toString() +
					procInclude.toString()
			));
			
		} else {
			sb.append(String.format(PROC_PARAMETER_DEFINE, 
					procSb.toString() +
					"include=$(ORACLE_HOME)/precomp/public  \\\n"+
					"include=$(ORACLE_HOME)/oci/include  \\\n"+
					"include=$(FBASE_HOME) \\\n"+
					"include=\"$(VC_HOME)\""

			));
		}
		
		
//		sb.append(String.format(INCLUDE_DEFINE, "/I$(FBASE_HOME)"));
		StringBuffer sbExport = new StringBuffer();
//		if (project.getProperty().getDevelopVersion() == 0) {
//			// EXPORT:GetCompsInfo /EXPORT:OnLoad /EXPORT:OnUnload /EXPORT:OnStart /EXPORT:OnStop
//			writeExportString(sbExport, new String[]{"GetCompsInfo","OnLoad","OnUnload","OnStart", "OnStop"});
//		} else {
//			writeExportString(sbExport, new String[]{"GetBizFunctionsInfo","OnLoad","OnUnload","OnStart", "OnStop"});
//		}
		writeExportString(sbExport, new String[]{"GetBizFunctionsInfo","OnLoad","OnUnload","OnStart", "OnStop"});
		
		sb.append("\n");
		if (mvcConfigMap.containsKey("LIBS")) {
			sb.append(String.format(MVC_OPTION_DEFIEN, mvcConfigMap.get("LIBS"), sbExport.toString()));
		} else {
			sb.append(String.format(MVC_OPTION_DEFIEN, "", sbExport.toString()));
		}
		
		
		StringBuffer sbInclall = new StringBuffer("/I $(FBASE_HOME) /I $(ORACLE_HOME)/precomp/public /I $(ORACLE_HOME)/oci/include");
		if (mvcConfigMap.containsKey("CC_INCLUDE")) {
			sbInclall = new StringBuffer();
			writeIncludeAllString(sbInclall, mvcConfigMap.get("CC_INCLUDE").split(","));
		}
		
		sb.append("\n");
		sb.append(String.format(MVC_INCLALL_DEFINE, sbInclall.toString())  );
		
		sb.append("\n");
		sb.append(MVC_DEBUG_DEFINE);
		
		sb.append("\n");
		sb.append( String.format(MVC_COMMAND, exCommand));

		
	}
	
	private static void writeIncludeAllString(StringBuffer sb, String[] strPath) {
		for (String path : strPath) {
			sb.append(" /I ");
			sb.append(path);
		}
		sb.append(" ");
	}
	
	private static void writeExportString(StringBuffer sb, String[] strMethod) {
		for (String method : strMethod) {
			sb.append(" /EXPORT:");
			sb.append(method);
		}
		sb.append(" ");
	}
	
//	/**
//	 * 生成一个简单的makefile
//	 * 
//	 * @param moduleEnName 
//	 * @param oracleConnectionParameter "hs_his/handsome@gfdb"
//	 * @return
//	 */
//	public static String getGccContent(String moduleEnName, String oracleConnectionParameter) {
//		ClassLoader loader = MakeFileBuilder.class.getClassLoader();
//		
//		
//		InputStream in = loader.getResourceAsStream("com/hundsun/hdt/generate/makefile.gcc");
//		BufferedReader reader = new BufferedReader(
//				new InputStreamReader(in));
//		
//		StringBuffer buffer = new StringBuffer();
//		try {
//			String line = reader.readLine();
//			while (line != null) {
//				buffer.append(line + "\n");
//				line = reader.readLine();
//			}
//			reader.close();
//			in.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return MessageFormat.format(buffer.toString(), "s_" + moduleEnName + "flow", "s_" + moduleEnName + "func",oracleConnectionParameter);
//		
//	}
//	
//	public static String getMvcContent(String moduleEnName, String oracleConnectionParameter) {
//		ClassLoader loader = MakeFileBuilder.class.getClassLoader();
//		
//		
//		InputStream in = loader.getResourceAsStream("com/hundsun/hdt/generate/makefile.mvc");
//		BufferedReader reader = new BufferedReader(
//				new InputStreamReader(in));
//		
//		StringBuffer buffer = new StringBuffer();
//		try {
//			String line = reader.readLine();
//			while (line != null) {
//				buffer.append(line + "\n");
//				line = reader.readLine();
//			}
//			reader.close();
//			in.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return MessageFormat.format(buffer.toString(), "s_" + moduleEnName + "flow", "s_" + moduleEnName + "func",oracleConnectionParameter);
//		
//	}
}
