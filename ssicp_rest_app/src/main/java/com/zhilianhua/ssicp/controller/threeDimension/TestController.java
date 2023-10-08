package com.zhilianhua.ssicp.controller.threeDimension;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zhilianhua.ssicp.utils.JsonUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import com.zhilianhua.ssicp.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/3d/test")
@RestController
@CrossOrigin
public class TestController {
    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("/position")
    public ResponseEntity position() {
        JSONObject position = JsonUtils.parse("position.json");
        webSocketServer.sendToAll(JSON.toJSONString(position));
        return ResponseEntityUtils.success();
    }

    @GetMapping("/alarm")
    public ResponseEntity alarm() {
        JSONObject position = JsonUtils.parse("alarm.json");
        webSocketServer.sendToAll(JSON.toJSONString(position));
        return ResponseEntityUtils.success();
    }

    @GetMapping("/inspection")
    public ResponseEntity inspection() {
        JSONObject position = JsonUtils.parse("inspection.json");
        webSocketServer.sendToAll(JSON.toJSONString(position));
        return ResponseEntityUtils.success();
    }

    @GetMapping("/simulation")
    public ResponseEntity simulation(@RequestParam("id") String id) {
        JSONObject position = JsonUtils.parse("simulation_request.json");
        webSocketServer.sendTo(id, JSON.toJSONString(position));
        return ResponseEntityUtils.success();
    }
}
