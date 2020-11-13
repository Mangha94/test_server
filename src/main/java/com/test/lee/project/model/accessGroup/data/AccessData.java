package com.test.lee.project.model.accessGroup.data;

import lombok.Data;

@Data
public class AccessData {
    //접근권한
    /**
     * 전자우편
     */
    private boolean mail;

    /**
     * 문자
     */
    private boolean sms;

    /**
     * 쪽지
     */
    private boolean note;

    /**
     * 사내일정
     */
    private boolean businessSchedule;

    /**
     * 부서일정
     */
    private boolean departmentSchedule;

    /**
     * 개인일정
     */
    private boolean personalSchedule;

    /**
     * 주소록
     */
    private boolean address;

    /**
     * 회의실 예약
     */
    private boolean reserveMeetingRoom;

    /**
     * 방문자 예약
     */
    private boolean reserveGuest;

    /**
     * 공지사항
     */
    private boolean notice;

    /**
     * 근태관리
     */
    private boolean timeManage;

    /**
     * 사내정보
     */
    private boolean businessInfo;

    /**
     * 프로젝트
     */
    private boolean project;

    /**
     * 업무보고서
     */
    private boolean businessReport;

    /**
     * 업무지시서
     */
    private boolean businessOrder;

    /**
     * 일반결제
     */
    private boolean generalPayment;

    /**
     * 대외문 결제
     */
    private boolean externalPayment;

    /**
     * 지출결의서
     */
    private boolean expenditureResolution;

    /**
     * 공문
     */
    private boolean official;

    /**
     * 지식 관리
     */
    private boolean knowledgeManagement;

    /**
     * 커뮤니티
     */
    private boolean community;

    /**
     * 제안관리
     */
    private boolean proposalManagement;
}
