<c:import url="inc/header.jsp" />
<div class="container">

    <h2 class="text-center">${lang["ERROR_404"]}</h2>
    <div class="text-center">
        ${lang["ERROR_404_MSG"]}<br>
        <a href="<c:url value="/"/>">${lang["HOME"]}</a>
    </div>
</div>
<c:import url="inc/footer.jsp" />
