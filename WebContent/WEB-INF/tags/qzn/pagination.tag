<%@tag import="com.itextpdf.text.log.SysoCounter"%>
<%@attribute name="url" required="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>  
<%!
String changeUrl(String url, Integer pageIndex) {
	int index = url.indexOf('?');
	String head = url.substring(0, index + 1);
	String tail = url.substring(index + 1);
	tail = tail.replace('?', '&');
	String newPageUrl = head + tail;
	if (pageIndex == null) {
		return newPageUrl.replaceAll("pager\\.offset", "offset");
	} else {
		return head + "offset=" + ((pageIndex - 1) * 10);
	}
}
%>
<%
jp.iesolutions.core.daos.Pagination<?> pagination =
	(jp.iesolutions.core.daos.Pagination<?>)request.getAttribute("pagination");
int current_page_number = pagination.getCurrentPageNumber();
int last_page_number = pagination.getMaxPageNumber();
int page_size = pagination.getPageSize();
%>
<pg:pager items="${pagination.totalCount}" url="${url}"
	export="pageOffset,currentPageNumber=pageNumber" 
	maxPageItems="${pagination.pageSize}" maxIndexPages="10">
	<pg:index>
    <font face=Helvetica size=-1><spring:message code="labels.pager.result" />
<%
if(current_page_number != 1) {
	int prev_page_number_offset = (current_page_number-2)*page_size;
	if( prev_page_number_offset < 0 )
		prev_page_number_offset = 0;
%>
    <pg:first>&nbsp;<a href="<%=changeUrl(pageUrl, null)%>"><spring:message code="labels.pager.first" /></a></pg:first>
    <a href="${url}?offset=<%=prev_page_number_offset%>"><spring:message code="labels.pager.previous" /></a>
<%
}
%>
    <pg:pages><%
      if (pageNumber.intValue() < 10) { 
        %>&nbsp;<%
      }
	  pageNumber=pageNumber + ((current_page_number - 1)/10) * 10;
      //if (pageNumber == currentPageNumber) { 
      if (pageNumber == current_page_number) { 
        %><b><%=pageNumber%></b><%
      } else if (pageNumber <= last_page_number){ 
        %><a href="<%=changeUrl(pageUrl, pageNumber)%>"><%=pageNumber%></a><%
      }
    %>
    </pg:pages>
<%
if(current_page_number != last_page_number) {
	int next_page_number_offset = current_page_number*page_size;
%>
    <pg:next>&nbsp;<a href="${url}?offset=<%=next_page_number_offset%>"><spring:message code="labels.pager.next" /></a></pg:next>
    <pg:last>&nbsp;<a href="<%=changeUrl(pageUrl, null)%>"><spring:message code="labels.pager.last" /></a></pg:last>
<%
}
%>
    &nbsp;&nbsp;&nbsp;&nbsp;</font>
 	</pg:index>
</pg:pager>