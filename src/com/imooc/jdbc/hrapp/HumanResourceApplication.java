package com.imooc.jdbc.hrapp;

import com.imooc.jdbc.hrapp.command.*;

import java.util.Scanner;

public class HumanResourceApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        System.out.println("2-办理员工入职");
        System.out.println("5-分页查询员工数据");
        System.out.print("请选择功能：");
        Scanner in = new Scanner(System.in);
        int cmd = in.nextInt();
        Command command = null;
        switch(cmd){
            case 1: //查询部门员工
                command = new PstmtQureyCommand();
                command.excute();
                break;
            case 2:
                command = new InsertCommand();
                command.excute();
                break;
            case 5:
                command = new PaginationCommand();
                command.excute();
                break;
        }
    }
}
