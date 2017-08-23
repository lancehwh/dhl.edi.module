package dhl.edi.module.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import dhl.edi.module.exportdata.entity.CtcEcShuttleExportH;
import dhl.edi.module.exportdata.entity.CtcEcShuttleExportL;




public class HtmlBuilder {

	public String buildExportDataAlertHtml(CtcEcShuttleExportH exportHeader, String ieUserMail){
		
		String html = null;
		initVelocity();
		
		//å°‡å?¼æ?‡å?šçµ¦ templateä¸­ç?„è?Šæ•¸
		VelocityContext context = new VelocityContext();
		context.put("entryNo", exportHeader.getEntryNo());
		context.put("entryCate", exportHeader.getEntryCate());
		context.put("currency", exportHeader.getCurrency());
		context.put("fob", exportHeader.getFob());
		
		//?–¼mail address??–å?—user 
		String user[] = ieUserMail.split("@");
		context.put("ieUser", user[0]);
		context.put("dataList", exportHeader.getCtcEcShuttleExportLs());
		
		Template template = Velocity.getTemplate("dhl/edi/module/util/ErrorAlertHtml.vm", "UTF-8");
		StringWriter stringWriter = new StringWriter();
		template.merge(context, stringWriter);
		html = stringWriter.toString();
		
		if(stringWriter!=null)
			try {
				stringWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return html;
	}
	
	private void initVelocity(){
		
		//Singleton model
		//see http://velocity.apache.org/engine/releases/velocity-1.7/developer-guide.html#singleton
		Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		Velocity.init();
	}
}
