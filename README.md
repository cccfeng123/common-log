# common-log

@Log放到类上

如果要开始链路
yml配置:


trace:
  enable: true



Springboot 启动类增加注解@ComponentScans

@ComponentScans(value = {@ComponentScan("com.log.*"), @ComponentScan("com.example.demo.*")})

#@ComponentScan("com.log.*") 是common-log的包
#@ComponentScan("com.example.demo.*") 是自身项目的包
