package com.inxedu.os.edu.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

import com.inxedu.os.common.util.StringUtils;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseStudyHistoryDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
    private int userId;//用户ID
    private String email;
    private String mobile;
    private String userName;
    private String studentNumber;
    private String collegeName;
    private String majorName;
    private String gradeName;
    private String className;
    private int classId;
    private String sysName;
    private int courseId;//课程ID
    private int kpointId;//节点ID
    private String courseName;//课程名称
    private String kpointName;//节点名称
    private java.util.Date updateTime;//更新时间
    private String logo;	//图片
    private String teacherName;	//教师名称
    private int parentId;
    /**视频路径*/
    private String videoUrl;
    private String parentName;
    private String updateTimeFormat;//时间 格式化显示
    public void setUpdateTime(Date date){
        this.updateTime=date;
        this.updateTimeFormat= StringUtils.getModelDate(this.getUpdateTime());
    }
}
