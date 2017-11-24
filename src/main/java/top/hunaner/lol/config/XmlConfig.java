package top.hunaner.lol.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:applicationContext-place.xml","classpath:applicationContext-datasource.xml",
"classpath:applicationContext-mvc.xml"})
public class XmlConfig {

}
