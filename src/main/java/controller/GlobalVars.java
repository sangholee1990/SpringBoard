package controller;

//public class controller.GlobalVars implements CERS {
public class GlobalVars {

    public class Params {
        /**
         * 사이트 코드
         */
        public static final String SITE = "site";
        /**
         * 언어
         */
        public static final String LANG = "lang";
        /**
         * 뷰페이지 정보
         */
        public static final String VIEW = "view";

        /**
         * 메뉴 접근 키 정보
         */
        public static final String ROLE = "role";

        /**
         * 정적 페이지 키정보
         */
        public static final String PAGE = "page";

        /**
         * 페이징 현재 페이징 번호
         */
        public static final String PAGING_PAGE_INDEX = "pageIndex";
        /**
         * 한번에 보여줄 페이지 갯수
         */
        public static final String PAGING_PAGE_UNIT = "pageUnit";
        /**
         * 페이지 목록 카운트
         */
        public static final String PAGING_PAGE_SIZE = "pageSize";
        /**
         * 첫번째 인덱스 키정보
         */
        public static final String PAGING_FIRST_INDEX = "firstIndex";

        public static final String PAGING_USE_FLAG = "usePaging";
        /**
         * 등록자 키 상수
         */
//        public static final String USER_ID = CERS.USER_ID;
//        public static final String USER_NM = CERS.USER_NM;
//    	public static final String SES_USERID = CERS.SES_USERID;
//    	public static final String SES_REP_ORG_CD = CERS.SES_REP_ORG_CD;
//    	public static final String SES_REP_ORG_NM = CERS.SES_REP_ORG_NM;
//    	public static final String SES_POS_ORG_CD = CERS.SES_POS_ORG_CD;
//    	public static final String SES_POS_ORG_NM = CERS.SES_POS_ORG_NM;
//    	public static final String SES_CLS_CD = CERS.SES_CLS_CD;
//    	public static final String SES_CLS_NM = CERS.SES_CLS_NM;
//    	public static final String SES_RESPN_OFFI_CD = CERS.SES_RESPN_OFFI_CD;
//    	public static final String SES_RESPN_OFFI_NM = CERS.SES_RESPN_OFFI_NM;
//    	public static final String SES_PRNT_ORG_CD = CERS.SES_PRNT_ORG_CD;
//    	public static final String SES_PRNT_ORG_NM = CERS.SES_PRNT_ORG_NM;
//    	public static final String SES_ORG_SHIP_CD = CERS.SES_ORG_SHIP_CD;
//    	public static final String SES_ORG_SHIP_NM = CERS.SES_ORG_SHIP_NM;
//    	public static final String sesCageFrame = CERS.sesCageFrame;
//    	public static final String sesCageLevel = CERS.sesCageLevel;
//    	public static final String SES_ROLE = CERS.SES_ROLE;
//    	public static final String SES_AIR_ORG_CD = CERS.SES_AIR_ORG_CD; // 2014.09.04 항공( 1.2.3. ) 자재관리
//    	public static final String sesIsAdmin = CERS.sesIsAdmin;

        /**
         * 사용자 아이피
         */
        public static final String USER_IP = "USER_IP";
        /**
         * SSO 세션키
         */
        public static final String SESSION_KEY = "sessionKey";
        /**
         * 사용자 정보 객체
         */
        public static final String USER_DOMAIN = "USER_DOMAIN";

        public static final String USER_NAME = "USER_NAME";

        public static final String REG_USER_SEQ = "REG_USER_SEQ";

        public static final String REG_USER_ID = "REG_USER_ID";

        public static final String AUTHORITY = "AUTHORITY";
        /**
         * 수정자 키 상수
         */
        public static final String MDF_USER_SEQ = "MDF_USER_SEQ";
        public static final String MDF_USER_ID = "MDF_USER_ID";

        /**
         * 응답 요청 속성
         */
        public static final String REQUEST_ATTRIBUTE_RESPONSE_CONTENT_TYPE = "response_content_type";


    }

    /**
     * 결과에 관련된 공통키 정보
     *
     * @author mjhwang
     */
    public class Result {

        /**
         * 페이지 번호
         */
        public static final String PAGE = "page";

        /**
         * 페이지 크기
         */
        public static final String ROWS = "rows";

        /**
         * 데이터
         */
        public static final String DATA = "data";

        /**
         * 서비스 아이디
         */
        public static final String SERVICE_ID = "svcId";

        /**
         * 검색 카운트
         */
        public static final String COUNT = "count";

        /**
         * 전체 카운트
         */
        public static final String TOTAL = "total";

        /**
         * 처리결과 : 성공
         */
        public static final String SUCCESS = "success";
        /**
         * 처리결과: 실패
         */
        public static final String WARN = "warn";
        /**
         * 처리결과 : 에러
         */
        public static final String ERROR = "error";

        /**
         * 메시지
         */
        public static final String MESSAGES = "messages";
    }

    public class View {

        public static final String JSON = "jsonView";

        public static final String XML = "xmlView";

        public static final String DOWNLOAD = "downloadView";

        public static final String PDF = "pdfView";

        public static final String EXCEL = "excelView";

        public static final String JAVASCRIPT = "javascriptView";
    }

    /**
     * @author mjhwang
     */
    public class Key {
        public static final String UPLOAD_FILES_KEY = "uploadFileNames";
        public static final String DOWNLOAD_FILE_KEY = "downloadFileId";
    }

    /**
     * 사이트 관련 상수
     */
    public class Site {
        public static final String MANAGE = "manage";
        public static final String INTRANET = "intranet";
        public static final String HOMEPAGE = "homepage";
        public static final String INTRO = "intro";
        public static final String ENHOME = "enhome";
        public static final String DATASERVICE = "datasvc";
        public static final String DATASERVICE_LAB = "datasvclab";
    }

    public class ViewType {
        public static final String HTML = "html";
        public static final String JSON = "json";

    }

    public class Mail {
        /**
         * 메일 발신자
         */
        public static final String MAIL_FROM = "mail.from";

        /**
         * 메일 수신자
         */
        public static final String MAIL_TO = "mail.to";

        /**
         * 메일 제목
         */
        public static final String MAIL_SUBJECT = "mail.subject";

        /**
         * 메일 내용
         */
        public static final String MAIL_TEXT = "mail.text";

        /**
         * 메일 내용URL
         */
        public static final String MAIL_TEXT_URL = "mail.text.url";

        /**
         * 메일 첨부파일
         */
        public static final String MAIL_ATTACHMENTS_FILE = "mail.attachments.file";
    }

}


