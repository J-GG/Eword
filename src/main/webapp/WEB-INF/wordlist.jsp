<c:import url="inc/header.jsp" />

<div class="container">

    <c:choose>
        <c:when test="${empty wordlist}">The list does not exist.</c:when>
        <c:otherwise>
            <div class="row">
                <h2 class="col-xs-12"><c:out value="${wordlist.title}" /></h2>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <table class="datatable table table-striped table-hover">
                        <thead>
                            <tr>
                                <th><c:out value="${wordlist.sourceLanguage}" /></th>
                                <th><c:out value="${wordlist.destinationLanguage}" /></th>
                                <th>Part of Speech</th>
                                <th>Examples</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${wordlist.words}" var="word">
                                <tr>
                                    <td><c:out value="${word.word}" /></td>
                                    <td><c:out value="${word.translation}" /></td>
                                    <td><c:out value="${word.partOfSpeech}" /></td>
                                    <td><c:out value="${word.examples}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<c:import url="inc/footer.jsp" />
