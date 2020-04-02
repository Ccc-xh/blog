package com.wzh.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chenxh
 * @date 2020/3/3 19:27
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Data
public class MessageBoard {

    private  int id;

    private String time;

    private int fId;

    private String name;

    private String message;


}
