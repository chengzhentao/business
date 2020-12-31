class Constant {

    //生成的文件夹名称
    static final String MOULD = "base";
    //表名。多了以逗号分割
    static final String TABLES = "user,user_role,role,menu,role_menu";
    //数据库地址
    static final String URL ="jdbc:mysql://localhost:3306/xbs?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&connectTimeout=5000&socketTimeout=5000&autoReconnect=true&maxReconnects=5&failOverReadOnly=false&zeroDateTimeBehavior=convertToNull";
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER_NAME = "root";
    static final String PASSWORD = "123456";

    //各种路径
    static final String PARENT = "com.xbs.business.dao";
    static final String PATH = "/templates/mapper.xml.vm";
    static final String MAPPER_PATH = "/dao/src/main/resources/mapper/";
    static final String OUT_PATH = "/dao/src/main/java";
}
