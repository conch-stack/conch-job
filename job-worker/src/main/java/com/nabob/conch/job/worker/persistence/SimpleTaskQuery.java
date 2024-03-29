package com.nabob.conch.job.worker.persistence;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 简单查询直接类，只支持 select * from task_info where xxx = xxx and xxx = xxx 的查询
 *
 * @author Adam
 * @date 2021/2/18
 */
@Data
public class SimpleTaskQuery {

    private static final String PREFIX_SQL = "select * from task_info where ";
    private static final String LINK = " and ";

    private String taskId;
    private String jobId;
    private String instanceId;
    private String taskName;
    private String address;
    private Integer status;

    private Integer limit;

    public String getQuerySQL() {
        StringBuilder sb = new StringBuilder(PREFIX_SQL);
        if (!StringUtils.isEmpty(taskId)) {
            sb.append("task_id = '").append(taskId).append("'").append(LINK);
        }
        if (!StringUtils.isEmpty(jobId)) {
            sb.append("job_id = '").append(jobId).append("'").append(LINK);
        }
        if (!StringUtils.isEmpty(instanceId)) {
            sb.append("instance_id = '").append(instanceId).append("'").append(LINK);
        }
        if (!StringUtils.isEmpty(address)) {
            sb.append("address = '").append(address).append("'").append(LINK);
        }
        if (!StringUtils.isEmpty(taskName)) {
            sb.append("task_name = '").append(taskName).append("'").append(LINK);
        }
        if (status != null) {
            sb.append("status = ").append(status).append(LINK);
        }

        String substring = sb.substring(0, sb.length() - LINK.length());
        if (limit != null) {
            substring = substring + " limit " + limit;
        }
        return substring;
    }
}
