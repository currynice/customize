package com.cxy.soft_design.principle.OCP.bad;

/**
 * Description: 通知的紧急程度，不同的紧急程度对应不同的发送渠道  </br>
 * Date: 2021/3/24 11:08
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public enum NotificationEmergencyLevel {
    SEVERE,//（严重）
    URGENCY,//（紧急）
    NORMAL,//（普通）
    TRIVIAL//（无关紧要）
}
