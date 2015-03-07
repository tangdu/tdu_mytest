package net.code.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import net.code.bean.UpperFirstCharacter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 生成器父类
 * 
 * @author tangdu
 * 
 * @time 2013-4-21 上午9:15:34
 */
public abstract class MakeParent {
	private static final String BASEPATH = MakeParent.class.getClassLoader()
			.getResource("").getPath();

	protected void make(String ftl, Map<String, Object> data) {
		Configuration cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(new File(BASEPATH));
			cfg.setSharedVariable("upper", new UpperFirstCharacter());
			Template t = cfg.getTemplate(ftl);
			String className = data.get("className").toString();
			FileOutputStream fos = new FileOutputStream(new File(BASEPATH
					+ className.substring(0,1).toLowerCase()+className.substring(1) + ".js"));
			t.process(data, new OutputStreamWriter(fos, "utf-8")); //
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
