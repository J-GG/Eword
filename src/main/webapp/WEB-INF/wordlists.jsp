<c:import url="inc/header.jsp" />

<div class="container">
    <div class="row">
        <h2 class="col-xs-12">Here are all your lists : </h2>
    </div>

    <div class="panel">
        <div class="tabbable tabs-left clearfix">
            <ul id="tabs" class="nav nav-tabs">
                <c:forEach  items="${mapByLanguages}" var="wordlists" varStatus="status">
                    <li ${status.first ? 'class="active"' : ""}>
                        <a href="#tab<c:out value="${wordlists.key[0]}${wordlists.key[1]}" />" data-toggle="tab"><c:out value="${wordlists.key[0]} => ${wordlists.key[1]}" /></a>
                    </li>
                </c:forEach>
            </ul>
            <div id="tabsContent" class="tab-content">
                <c:forEach items="${mapByLanguages}" var="wordlists" varStatus="status">
                    <div class="tab-pane fade ${status.first ? "active in" : ""}" id="tab<c:out value="${wordlists.key[0]}${wordlists.key[1]}" />">
                        <ul>
                            <c:forEach items="${wordlists.value}" var="wordlist">
                                <li>
                                    <a href="<c:url value="/lists/${wordlist.id}" />">
                                        <c:out value="${wordlist.title}" />
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<c:import url="inc/footer.jsp" />
