package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管件表
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
@Data
public class Fittings implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String pipelineId;

    private Integer parentFittingsTypes;

    private String fittingsTypes;

    private String fittingsModel;

    private String location;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createdById;

    private Long updatedById;

    private Integer deleteFlag;

    private String comments;


}




