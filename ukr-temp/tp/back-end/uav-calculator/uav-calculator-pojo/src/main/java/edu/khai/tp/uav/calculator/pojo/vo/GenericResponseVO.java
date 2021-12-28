package edu.khai.tp.uav.calculator.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class GenericResponseVO<T> {

    private Boolean state;

    private String code;

    private String description;

    private T data;

}