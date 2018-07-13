package com.gufang.tld;

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
	public void process(final String templateName,Map map,Writer out)
	{
		try
		{
//			URL classpath = Thread.currentThread().getContextClassLoader().getResource("com/gufang/tld/"+templateName);
//	        String path = classpath.getPath();
//	        System.out.println(path);
//	        int pos = path.indexOf("classes/com/gufang/tld/FreeMarkerUtil.class");
//	        path = path.substring(0,pos);
//	        String templatePath = path + "template" + File.separator;
//	        Configuration configuration = new Configuration();  
//	        configuration.setDefaultEncoding("utf-8");  
//	        TemplateLoader templateLoader = new FileTemplateLoader(new File(templatePath));  
//	        configuration.setTemplateLoader(templateLoader);  
//	        Template template = configuration.getTemplate(templateName);
			
			
	        Configuration configuration = new Configuration();  
	        configuration.setDefaultEncoding("utf-8");  
	        TemplateLoader templateLoader = new URLTemplateLoader(){

				@Override
				protected URL getURL(String arg0) {
					URL url = Thread.currentThread().getContextClassLoader().getResource("com/gufang/tld/"+templateName);
					return url;
				}
	        };
	        configuration.setTemplateLoader(templateLoader);  
	        Template template = configuration.getTemplate(templateName);
        
            template.process(map, out);  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void generateHtml(ServletContext servletContext,
			String staticHtmlPath,String template,Map map)
	{
		try
		{
			//声明配置对象
			Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
			conf.setEncoding(Locale.getDefault(), "UTF-8");
			//声明模板加载器
			WebappTemplateLoader wtl = new WebappTemplateLoader(servletContext, "/ftl");
			//绑定到配置对象
			conf.setTemplateLoader(wtl);
			Template tmplt = conf.getTemplate(template);
			//根据模板与数据模型生成静态网页
			String path = servletContext.getRealPath("/"+staticHtmlPath);
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
