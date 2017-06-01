package com.ynnjii.utils

import groovy.sql.Sql
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row
import org.springframework.context.support.ClassPathXmlApplicationContext
import spock.lang.Specification

/**
 * 项目名称：ynnjii-web
 * 类描述：
 * 创建人：yzh
 * 创建时间：2017/6/1
 * 备注：
 */
class ExcelTest extends Specification {
    def "Add"() {
        def context = new ClassPathXmlApplicationContext("spring.xml")
        List list = getData()
        saveData(list)

        expect:
        1 == 1

    }

    def getData() {
        def filePath = "F://file//车到山前花名册20170601.xls"
        List list = new ArrayList()

        File file = new File(filePath)

        FileInputStream is = new FileInputStream(file);

        HSSFWorkbook workbook = new HSSFWorkbook(is);
        workbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
        HSSFSheet sheet = workbook.getSheet("在职")
        int rows = sheet.physicalNumberOfRows;

        //忽略第一行,标题行
        (3..<rows).each{r->
            HSSFRow row = sheet.getRow(r);
            HSSFCell name = row.getCell(3);
            HSSFCell account = row.getCell(4);

            String[] strs = [name.stringCellValue ,account.stringCellValue]
            list.add(strs)
        }
        return list
    }

    def saveData(List list) {
        def url = 'jdbc:mysql://127.0.0.1:3306/cdsq_manage'
        def user = 'root'
        def password = '123456'
        def driver = 'com.mysql.jdbc.Driver'
        def sql = Sql.newInstance(url, user, password, driver)

        def hsql = 'insert into sys_user (login_name,password,name,create_by,update_by,salt) values (?,?,?,1,1,?)'

        for (Object obj : list) {
            String[] salt =EncodePwdUtils.getsaltAndPwd(obj[1],'123456');
            def params = [obj[1], salt[1],obj[0],salt[0]]
            def keys = sql.executeInsert hsql, params
        }

        sql.close()
    }
}
