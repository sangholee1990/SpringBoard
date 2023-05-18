<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" isThreadSafe="true" %>

<div class="col-md-3">
    <div class="card">
<%--        <div class="card-header">DTO 객체 전달</div>--%>
<%--        <ul class="list-group list-group-flush">--%>
<%--            <li class="list-group-item"><a href="/ajax/list.do">AJAX</a></li>--%>
<%--            <li class="list-group-item"><a href="/jstl/list.do">JSTL</a></li>--%>
<%--            <li class="list-group-item"><a href="/ajax/list.do">AJAX</a></li>--%>
<%--        </ul>--%>

        <div class="card-header">테스트</div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><a href="/json/service/DynamicBean/sayHello.do">json</a></li>
            <li class="list-group-item"><a href="/html/service/DynamicBean/sayHello.do">json + html</a></li>
            <li class="list-group-item"><a href=/html/static/home/home.do">html</a></li>
            <li class="list-group-item"><a href=/json/service/FileService/FileList.do">json 파일목록</a></li>

            <li class="list-group-item"><a href="/html/service/DynamicBean/sayHello3.do">json+html 에러</a></li>
            <li class="list-group-item"><a href="/json/service/DynamicBean/sayHello2.do">json 에러 </a></li>
        </ul>

        <div class="card-header">파일 객체</div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><a href="/html//static/file/fileUpload.do">업로드</a></li>
            <li class="list-group-item"><a href="/html//static/file/fileDown.do">다운로드</a></li>
        </ul>
    </div>
</div>