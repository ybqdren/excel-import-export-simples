package com.github.ybqdren;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * created by ybqdren
 */

@Getter
@Setter
@EqualsAndHashCode
public class Table {
    @ExcelProperty(value = "arg_1" , index = 0)
    private String arg1;
    @ExcelProperty(value = "arg_2" , index = 1)
    private String arg2;
    @ExcelProperty(value = "arg_3" , index = 2)
    private String arg3;
    @ExcelProperty(value = "arg_4" , index = 3)
    private String arg4;
}
