package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ManualInspectionOutcomeAdd {
    private String outcomeCode;
    private String planCode;
    private String planName;
    private String responsibleName;
    @Valid
    private List<ManualOutcomeDetailsAdd> manualOutcomeDetails;
    @Valid
    private List<ManualOutcomeImageAdd> manualOutcomeImages;
    @Valid
    private List<ManualOutcomeObserveAdd> manualOutcomeObserves;

    @Data
    static public class ManualOutcomeDetailsAdd {
        private String outcomeCode;
        private String id;
        private String index;
        private String deviceId;
        @NotNull(message = "请输入指标值")
        private Double indexValue;
        private Integer alarmLevel;

    }

    @Data
    static public
    class ManualOutcomeImageAdd {
        private String outcomeCode;
        private String id;
        @NotBlank(message = "请上传图片")
        private String fileList;
    }

    @Data
    static public
    class ManualOutcomeObserveAdd {
        private String outcomeCode;
        private String id;
        @NotNull(message = "请输入观察项")
        private Integer decide;
    }
}
