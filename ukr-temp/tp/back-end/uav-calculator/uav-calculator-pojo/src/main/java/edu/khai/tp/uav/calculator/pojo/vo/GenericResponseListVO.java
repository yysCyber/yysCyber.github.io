package edu.khai.tp.uav.calculator.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class GenericResponseListVO<T> {

    private Boolean state;

    private String code;

    private String description;

    private List<T> data;

}