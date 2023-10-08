package com.zhilianhua.ssicp.entity.aimodel.enums;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.writer.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

// 保温层材料
public enum STInsulParaEnum {
    微孔硅酸钙170号("微孔硅酸钙170号", 0.055, 0.00011, 70),
    微孔硅酸钙220号("微孔硅酸钙220号", 0.062, 0.00011, 70),
    微孔硅酸钙240号("微孔硅酸钙240号", 0.064, 0.00011, 70),
    岩棉制品("岩棉制品", 0.042, 0.00018, 70),
    轻质镁铝材料SML2("轻质镁铝材料SML2", 0.0534, 0.000114, 0),
    轻质镁铝材料SML3("轻质镁铝材料SML3", 0.0806, 0.000546, 0),
    中级纤维淀粉粘结制品("中级纤维淀粉粘结制品", 0.044, 0.0002, 0),
    中级纤维酚醛树脂制品("中级纤维酚醛树脂制品", 0.044, 0.0002, 0),
    玻璃棉沥青粘结制品("玻璃棉沥青粘结制品", 0.058, 0.00019, 0),
    无碱超细玻璃棉制品("无碱超细玻璃棉制品", 0.035, 0.0002, 0),
    超细棉无脂毡和缝合垫("超细棉无脂毡和缝合垫", 0.035, 0.0002, 0),
    超细棉树脂制品("超细棉树脂制品", 0.038, 0.0002, 0),
    硅酸铝纤维制品("硅酸铝纤维制品", 0.056, 0.0002, 70),
    沥青矿渣棉制品("沥青矿渣棉制品", 0.05, 0.0002, 0),
    酚醛矿渣棉制品("酚醛矿渣棉制品", 0.047, 0.00017, 0),
    聚苯乙烯泡沫塑料("聚苯乙烯泡沫塑料", 0.0349, 0.00014, 0),
    石棉绳("石棉绳", 0.0128, 0.00015, 0),
    泡沫玻璃("泡沫玻璃", 0.05, 0.00023, 0),
    珍珠岩("珍珠岩", 0.057, 0.00015, 0),
    硅酸铝("硅酸铝", 0.035, 0.000185, 0),
    超轻硅酸铝毡("超轻硅酸铝毡", 0.0254, 0.000203, 0),
    防水型矿物棉泡沫毡("防水型矿物棉泡沫毡", 0.041, 0, 0),
    温矿棉("温矿棉", 0.0406, 0.000142, 0),
    硅酸镁铝("硅酸镁铝", 0.041, 0.000112, 0),
    矿棉("矿棉", 0.043, 0.00011, 0),
    泡沫石棉("泡沫石棉", 0.046, 0.00014, 70),
    复合硅酸盐瓦壳("复合硅酸盐瓦壳", 0.065, 0.00017, 70),
    复合硅酸盐软管("复合硅酸盐软管", 0.045, 0.00017, 70),
    复合硅酸盐涂料("复合硅酸盐涂料", 0.06018, 0.00009, 0),
    硅酸铝镁纤维("硅酸铝镁纤维", 0.0384, 0.000139, 0),
    FBT稀土复合保温材料("FBT(稀土)复合保温材料", 0.0585, 0.00009, 0),
    离心玻璃棉("离心玻璃棉", 0.042, 0.00011, 0),
    半硬质复合硅酸盐型材("半硬质复合硅酸盐型材", 0.05023, 0.00014, 0),
    普通硅酸铝纤维毯("普通硅酸铝纤维毯", 0.03993, 0.00024, 0),
    高纯硅酸铝纤维毯("高纯硅酸铝纤维毯", 0.03399, 0.00024, 0),
    高纯硅酸铝纤维毯2("高纯硅酸铝纤维毯2", 0.03399, 0.00024, 0),
    ;
    private String name;
    private double a;
    private double b;
    private double c;

    STInsulParaEnum(String name, double a, double b, double c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    static Optional<STInsulParaEnum> findByABC(double a, double b, double c) {
        return Arrays.stream(STInsulParaEnum.values()).filter(stInsulParaEnum -> stInsulParaEnum.a == a && stInsulParaEnum.b == b && stInsulParaEnum.c == c).findFirst();
    }

    @Slf4j
    public static class STInsulParaEnumWriter implements ObjectWriter<STInsulParaEnum> {
        @Override
        public void write(JSONWriter jsonWriter, Object o, Object o1, Type type, long l) {
            if (Objects.nonNull(o)) {
                STInsulParaEnum stInsulParaEnum = (STInsulParaEnum) o;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("a", stInsulParaEnum.a);
                jsonObject.put("b", stInsulParaEnum.b);
                jsonObject.put("c", stInsulParaEnum.c);
                jsonWriter.write(jsonObject);
            }
            log.info("write");
        }

    }

    @Slf4j
    public static class STInsulParaEnumReader implements ObjectReader<STInsulParaEnum> {
        @Override
        public STInsulParaEnum readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
            Map<String, Object> stringObjectMap = jsonReader.readObject();
            double a = Double.parseDouble(stringObjectMap.getOrDefault("a", 0) + "");
            double b = Double.parseDouble(stringObjectMap.getOrDefault("b", 0) + "");
            double c = Double.parseDouble(stringObjectMap.getOrDefault("c", 0) + "");
            return findByABC(a, b, c).get();
        }
    }
}