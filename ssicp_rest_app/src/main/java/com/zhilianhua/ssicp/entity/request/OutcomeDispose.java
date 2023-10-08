package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OutcomeDispose {
    private List<String> detailsId;
    private List<String> observesId;
    private List<String> imagesId;
    @NotEmpty(message = "处理前图片不能为空")
    private List<String> fileList;
    @NotEmpty(message = "处理后图片不能为空")
    private List<String> fileListBefore;
    private String deviceId;
    private String deviceName;
    private String remarks;
    @NotBlank(message = "原因不能为空")
    private String resolutionReason;
    @NotBlank(message = "简述不能为空")
    private String summary;
}
