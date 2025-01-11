package com.laapicallbat.lostarkapicallbatchservice.aa.interceptor;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Properties;

@Log4j2
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
//        @Signature(type = Executor.class,method = "update", args = {MappedStatement.class, Object.class}),
//        @Signature(type = Executor.class,method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class AddAuditingInterceptor implements Interceptor {


    @Value("${service}")
    private String serviceName;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
//        Object[] args = invocation.getArgs();
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//        MappedStatement mappedStatement = (MappedStatement) args[0];
//        Object parameter = args[1];
//        String sqlCommandType = mappedStatement.getSqlCommandType().name();
//        BoundSql sql = mappedStatement.getBoundSql(parameter);
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        BoundSql boundSql = statementHandler.getBoundSql();
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String statementId = mappedStatement.getId();
        SqlCommandType sqlCommandType =  mappedStatement.getSqlCommandType();

        log.debug("statementId {}",statementId);
        log.debug("service {}",serviceName);
        log.debug("boundSql.getSql {}",boundSql.getSql());
        log.debug("sqlCommandType {}",sqlCommandType);

        switch (sqlCommandType){
            case SELECT -> log.debug("SELECT");
            case INSERT -> {
                log.debug("INSERT");
                this.setAuditForInsert(statementHandler,statementId);
            }
            case UPDATE -> {
                log.debug("UPDATE");
                this.setAuditForUpdate(statementHandler,statementId);
            }
        }

        return invocation.proceed();
    }

    private void setAuditForInsert(StatementHandler statementHandler,String statementId) {
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        String modiSql = sql
                .replace(System.lineSeparator(),"")
                .replace(")value",", sys_creation_dttm, sys_update_dttm, sys_service_name, sys_func_name)value")
                .replace(");",",now(),now(),'"+serviceName+"','"+statementId+"');");
        log.debug("modisql {}",modiSql);
        setAuditField(boundSql,"sql",modiSql);
    }

    private void setAuditForUpdate(StatementHandler statementHandler,String statementId) {
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        String modiSql = sql.replace("SET" , "SET SYS_UPDATE_DTTM = NOW(), SYS_SERVICE_NAME = '" + serviceName + "'," +"SYS_FUNC_NAME = '"+statementId+"',");
        log.debug("modiSql {}", modiSql);
        setAuditField(boundSql,"sql",modiSql);
    }

    private void setAuditField(Object parameter, String fieldName, Object value){
        try {
            Field field = parameter.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(parameter, value);
        } catch (IllegalAccessException | NoSuchFieldException e){
        }

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

        Interceptor.super.setProperties(properties);
    }
}
