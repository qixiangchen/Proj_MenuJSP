package com.gufang.oa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.cache.URLTemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerUtil {

	public static void generateHtml(ServletContext servletContext,
			String template,Map map)
	{
		try
		{
			Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
			conf.setEncoding(Locale.getDefault(), "UTF-8");
			WebappTemplateLoader wtl = new WebappTemplateLoader(servletContext, "/ftl");
			conf.setTemplateLoader(wtl);
			Template tmplt = conf.getTemplate(template);
			String path = servletContext.getRealPath("/html");
			String file = path+"/"+map.get("id")+".html";
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			tmplt.process(map, bw);

			fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
