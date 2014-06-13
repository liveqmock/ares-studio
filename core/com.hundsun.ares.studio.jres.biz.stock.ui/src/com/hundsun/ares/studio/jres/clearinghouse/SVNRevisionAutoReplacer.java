/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TriggerListener;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.model.chouse.HistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.IStock3Constant;

/**
 * 
 * 2012年3月26日16:25:46 gongyf 将svn提交版本的字段上升到jres.core层面
 * @author gongyf
 *
 */
public class SVNRevisionAutoReplacer extends TriggerListener {

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.TriggerListener#trigger(org.eclipse.emf.transaction.TransactionalEditingDomain, org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	protected Command trigger(TransactionalEditingDomain domain,
			Notification notification) {
		if (CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES.equals(notification.getFeature()) 
				&& notification.getNewValue() instanceof RevisionHistory 
				/*&& notification.getNotifier() instanceof TableResourceData*/) {
			// 增加了修订历史后，把svn关键字进行替换
			CompoundCommand cmd = new CompoundCommand();
			cmd.append(new ReplaceSVNKeyWordCommand(domain, (JRESResourceInfo) notification.getNotifier()));
			return cmd;
			
		}
		return null;
	}

	static class ReplaceSVNKeyWordCommand extends RecordingCommand {
		
		static Pattern[] REPLACE = {
			// 需要替换的列表，必须包含一个括号区，会被留下来的内容
			Pattern.compile("\\$LastChangedDate:\\s*([^\\$]*)\\$"),
			Pattern.compile("\\$Date:\\s*([^\\$]*)\\$"),
			
			Pattern.compile("\\$LastChangedBy:\\s*([^\\$]*)\\$"),
			Pattern.compile("\\$Author:\\s*([^\\$]*)\\$"),
			
			Pattern.compile("\\$LastChangedRevision:\\s*([^\\$]*)\\$"),
			Pattern.compile("\\$Revision:\\s*([^\\$]*)\\$"),
			Pattern.compile("\\$Rev:\\s*([^\\$]*)\\$"),
			
			Pattern.compile("\\$HeadURL:\\s*([^\\$]*)\\$"),
			Pattern.compile("\\$URL:\\s*([^\\$]*)\\$"),
			
			Pattern.compile("\\$Id:\\s*([^\\$]*)\\$"),
			Pattern.compile("\\$ID:\\s*([^\\$]*)\\$"),
		};
		private JRESResourceInfo data;
		
		public ReplaceSVNKeyWordCommand(TransactionalEditingDomain domain, JRESResourceInfo data) {
			super(domain);
			this.data = data;
		}
		
		@Override
		protected void doExecute() {
			// 替换掉svn关键字
			for (RevisionHistory rh : data.getHistories()) {
				HistoryProperty p = (HistoryProperty) rh.getData2().get(IStock3Constant.HISTORY_DATA2_KEY);
				if (p != null) {
					// 先收集修改再统一替换
					String internalVersion = StringUtils.defaultString(p.getInternalVersion());

					Document content = new Document(internalVersion) ;
					MultiTextEdit edit = new MultiTextEdit();
					
					for (Pattern pattern : REPLACE) {
						Matcher m = pattern.matcher(content.get());
						int start = 0;
						while (m.find(start)) {
							edit.addChild(new ReplaceEdit(m.start(), m.end() - m.start(), m.group(1)));
							
							start = m.end();
						}
					}
					
					try {
						edit.apply(content);
						
						p.setInternalVersion(content.get());
					} catch (Exception e) {
					}
				}
			}
		}
		
	}
}
