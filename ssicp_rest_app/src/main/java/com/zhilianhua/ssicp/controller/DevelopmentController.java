package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.remote.AiModelService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import com.zhilianhua.ssicp.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/develop")
public class DevelopmentController {
    @Autowired
    private AiModelService aiModelService;

    @Autowired
    private WebSocketServer webSocketServer;

    @PostMapping("/aiModel")
    public ResponseEntity<String> aiModel(@RequestBody String params) {
        return ResponseEntityUtils.success(aiModelService.call(params));
    }

    @GetMapping("/3d")
    public ResponseEntity listClient() {
        return ResponseEntityUtils.success(webSocketServer.getClientList());
    }

    @PostMapping("/3d")
    public ResponseEntity threeDimension(@RequestParam("clientId") String clientId, @RequestBody String body) {
        webSocketServer.sendTo(clientId, body);
        return ResponseEntityUtils.success();
    }
}
