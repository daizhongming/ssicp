package com.zhilianhua.ssicp.controller.threeDimension;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zhilianhua.ssicp.utils.JsonUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/3d")
@RestController
@CrossOrigin
public class ThreeDimensionController {
    @GetMapping("/base/colors")
    public ResponseEntity colors() {
        return ResponseEntityUtils.success(JsonUtils.parse("colors.json"));
    }

    @GetMapping("/base/systemParams")
    public ResponseEntity systemParams() {
        return ResponseEntityUtils.success(JsonUtils.parse("systemParams.json"));
    }


    @GetMapping("/base/objects")
    public ResponseEntity objects() {
        JSONArray objects = JsonUtils.parseArray("objects.json");
        objects.stream().map(o -> (JSONObject) o).forEach(jsonObject -> {
            if (StringUtils.equals(jsonObject.getString("type"), "instrument")) {
                // 仪表
                jsonObject.put("temperature", Math.random() * 100);
                jsonObject.put("flow", Math.random() * 100);
                jsonObject.put("pressure", Math.random() * 100);
            } else if (StringUtils.equals(jsonObject.getString("type"), "valve")) {
                // 阀门
                jsonObject.put("open", Math.random() > 0.5);
            } else if (StringUtils.equals(jsonObject.getString("type"), "trap")) {
                // 疏水阀
                jsonObject.put("leakLevel", (int) (Math.random() * 4));
            }
        });
        return ResponseEntityUtils.success(objects);
    }

    @Deprecated
    @GetMapping("/color/temperatureDrop")
    public ResponseEntity temperatureDrop() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("temperatureDrop.json"));
    }

    @Deprecated
    @GetMapping("/color/pressureDrop")
    public ResponseEntity pressureDrop() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("pressureDrop.json"));
    }

    @Deprecated
    @GetMapping("/color/flowVelocity")
    public ResponseEntity flowVelocity() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("flowVelocity.json"));
    }

    @Deprecated
    @GetMapping("/color/condensate")
    public ResponseEntity condensate() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("condensate.json"));
    }

    @Deprecated
    @GetMapping("/color/flowDirection")
    public ResponseEntity flowDirection() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("flowDirection.json"));
    }

    @GetMapping("/steam/production")
    public ResponseEntity production() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("production.json"));
    }

    @GetMapping("/steam/consumption")
    public ResponseEntity consumption() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("consumption.json"));
    }

    @GetMapping("/alarm/temperatureDropAlarms")
    public ResponseEntity temperatureDropAlarms() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("temperatureDropAlarms.json"));
    }

    @GetMapping("/alarm/temperatureAlarms")
    public ResponseEntity temperatureAlarms() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("temperatureAlarms.json"));
    }

    @GetMapping("/alarm/pressureDropAlarms")
    public ResponseEntity pressureDropAlarms() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("pressureDropAlarms.json"));
    }

    @GetMapping("/alarm/pressureAlarms")
    public ResponseEntity pressureAlarms() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("pressureAlarms.json"));
    }

    @GetMapping("/alarm/flowVelocityAlarms")
    public ResponseEntity flowVelocityAlarms() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("flowVelocityAlarms.json"));
    }

    @GetMapping("/alarm/flowAlarms")
    public ResponseEntity flowAlarms() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("flowAlarms.json"));
    }

    @GetMapping("/alarm/condensateAlarms")
    public ResponseEntity condensateAlarms() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("condensateAlarms.json"));
    }

    @GetMapping("/alarm/trapAlarms")
    public ResponseEntity trapAlarms() {
        return ResponseEntityUtils.success(JsonUtils.parseArray("trapAlarms.json"));
    }

    @GetMapping("/steam/analyse")
    public ResponseEntity analyse() {
        return ResponseEntityUtils.success(JsonUtils.parse("analyse.json"));
    }

    @GetMapping("/object/details")
    public ResponseEntity details(String id, String type, String page) {
        JSONObject jsonObject = JsonUtils.parse("details.json");
        String pageUrl = jsonObject.getString("pageUrl") + "/" + type + "?id=" + id + "&page=" + page + "&type=" + type;
        jsonObject.put("pageUrl", pageUrl);
        return ResponseEntityUtils.success(jsonObject);
    }
}
